package com.spring.security.test.demo.handler;

import com.alibaba.fastjson.JSON;
import com.spring.security.test.demo.bean.AjaxResponseBody;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("300");
        responseBody.setMsg("当前用户没有访问权限!");
        httpServletResponse.setContentType("application/json;charset=utf-8");

        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
