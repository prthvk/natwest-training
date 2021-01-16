package com.stackroute.jwtdemo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

     HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authheader = httpServletRequest.getHeader("Authorization");
        if(authheader == null || !authheader.startsWith("Bearer")){
            System.out.println("In filter");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //logger.log("Missing ivalid token");
        }
        else
        {
            String jwtToken = authheader.substring(7);
           Claims claims =  Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody();
            String username =  Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody().getSubject();
            httpServletRequest.setAttribute("username",username);
           filterChain.doFilter(servletRequest,servletResponse); //some more filters , controller
        }

    }
}
