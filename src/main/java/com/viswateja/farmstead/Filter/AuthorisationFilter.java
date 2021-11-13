package com.viswateja.farmstead.Filter;

import com.viswateja.farmstead.helper.JWTHelper;
import com.viswateja.farmstead.helper.SpringUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorisationFilter implements Filter {

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String authorizationHeader = req.getHeader("Authorization");
        if(authorizationHeader==null) {
            throw new Exception("Authorization Token not found");
        }
        String token = authorizationHeader.substring(7);
        JWTHelper jwtHelper = SpringUtils.getBean(JWTHelper.class);
        if(jwtHelper.isTokenValid(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new Exception("Invalid Token");
        }
    }
}
