package com.chupryna.spring.util;

import com.chupryna.spring.security.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        String redirectURL = request.getContextPath();
        if (userDetails.hasRole("ROLE_ADMIN")) {
            redirectURL = "/adminHome";
        } else if (userDetails.hasRole("ROLE_USER")) {
            redirectURL = "/userHome";
        }
        response.sendRedirect(redirectURL);
    }
}
