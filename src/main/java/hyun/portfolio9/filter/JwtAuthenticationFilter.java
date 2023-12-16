package hyun.portfolio9.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.portfolio9.configures.auth.JwtAuthenticationManager;
import hyun.portfolio9.configures.auth.PrincipalDetails;
import hyun.portfolio9.entities.User;
import hyun.portfolio9.service.JwtProviderService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final JwtProviderService jwtProviderService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("Jwt필터 실행했습니다.");

        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword());

            Authentication authentication = jwtAuthenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getAuthorities() + "의 권한을 가지신 " + principalDetails.getUsername() + "님 로그인 감사합니다. ");

            return authentication;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        System.out.println("successfulAuthentication 실행: 인증 완료");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = jwtProviderService.create(principalDetails.getUsername());
        System.out.println("Jwt 발행번호 : " + jwtToken);

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
