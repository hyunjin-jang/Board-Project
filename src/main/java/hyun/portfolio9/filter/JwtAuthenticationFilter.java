package hyun.portfolio9.filter;

import com.google.gson.Gson;
import hyun.portfolio9.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("Jwt로그인 시도");

        try {
            BufferedReader br = request.getReader();
            String input = null;
            while ((input = br.readLine()) != null) {
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===============================");

        return super.attemptAuthentication(request, response);
//        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated("testName1", "testPassword1");
//        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
