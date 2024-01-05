package hyun.portfolio9.filter;

import hyun.portfolio9.configures.auth.PrincipalDetails;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.repositories.UserRepository;
import hyun.portfolio9.service.JwtProviderService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtProviderService jwtProviderService;
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProviderService jwtProviderService, UserRepository userRepository) {
        super(authenticationManager);
        this.jwtProviderService = jwtProviderService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("인증 혹은 권한 필요한 주소 요청");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader : " + jwtHeader);

        // header 존재 여부 확인
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        // JWT 토큰으로 정상적인 사용자 확인
        String jwtToken = jwtHeader.replace("Bearer ", "");
        String username = jwtProviderService.validate(jwtToken);

        if (username != null) {
            User entity = userRepository.findByUserNickName(username);
            PrincipalDetails principalDetails = new PrincipalDetails(entity);
            System.out.println(principalDetails.getUsername() + "님의 저장된 유저 권한 : "+principalDetails.getAuthorities());

            Authentication authentication = new UsernamePasswordAuthenticationToken
                    (principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
        }
        chain.doFilter(request, response);
    }
}
