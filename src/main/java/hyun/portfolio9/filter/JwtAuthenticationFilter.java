package hyun.portfolio9.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hyun.portfolio9.configures.JwtAuthenticationManager;
import hyun.portfolio9.configures.auth.PrincipalDetails;
import hyun.portfolio9.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    public JwtAuthenticationFilter(JwtAuthenticationManager jwtAuthenticationManager) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("Jwt로그인 시도");

        try {
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword());

            Authentication authentication = jwtAuthenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료: " + principalDetails.getUsername());

            return authentication;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===============================");

        return null;
//        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated("testName1", "testPassword1");
//        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
