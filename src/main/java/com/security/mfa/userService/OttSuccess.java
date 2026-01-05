package com.security.mfa.userService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
@Component
public class OttSuccess implements OneTimeTokenGenerationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(OttSuccess.class);

    private final OneTimeTokenGenerationSuccessHandler redirect=
            new RedirectOneTimeTokenGenerationSuccessHandler("ott/sent");
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken)
            throws IOException, ServletException {
        log.info("OttSuccess          : inside the Handler method");
        String tokenLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/login/ott")
                .queryParam("token",oneTimeToken.getTokenValue()).toUriString();
        System.out.println("magicLick  : " + tokenLink);
        redirect.handle(request,response,oneTimeToken);
    }
}
