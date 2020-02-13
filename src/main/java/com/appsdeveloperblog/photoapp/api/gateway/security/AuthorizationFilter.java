package com.appsdeveloperblog.photoapp.api.gateway.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.appsdeveloperblog.photoapp.api.gateway.config.AppGatewayConfig;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    
	Environment environment;
    AppGatewayConfig appGatewayConfig;
	
    public AuthorizationFilter(AuthenticationManager authManager,
    		Environment environment, AppGatewayConfig appGatewayConfig) {
        super(authManager);
        this.environment = environment;
        this.appGatewayConfig = appGatewayConfig;
    }
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        String authorizationHeader = req.getHeader(this.appGatewayConfig.getAuthorizationtokenheadername());

        if (authorizationHeader == null || !authorizationHeader.
        		startsWith(this.appGatewayConfig.getAuthorizationtokenheaderprefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
       
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }  
    
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.
        		getHeader(this.appGatewayConfig.getAuthorizationtokenheadername());
   
         if (authorizationHeader == null) {
             return null;
         }

         String token = authorizationHeader.replace(
        		 this.appGatewayConfig.getAuthorizationtokenheaderprefix(), "");

         String userId = Jwts.parser()
                 .setSigningKey(this.appGatewayConfig.getTokensecret())
                 .parseClaimsJws(token)
                 .getBody()
                 .getSubject();

         if (userId == null) {
             return null;
         }
   
         return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());

     }
}
