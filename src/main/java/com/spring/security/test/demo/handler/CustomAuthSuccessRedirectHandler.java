package com.spring.security.test.demo.handler;

import com.alibaba.fastjson.JSON;
import com.spring.security.test.demo.bean.AjaxResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("mAuthSuccessHandler")
public class CustomAuthSuccessRedirectHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

            logger.info("登录成功");
//            SavedRequest savedRequest = requestCache.getRequest(request,response);
//            String targetUrl = savedRequest.getRedirectUrl();
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("200");
        responseBody.setMsg("Login Success!");

        response.getWriter().write(JSON.toJSONString(responseBody));
    }

}
