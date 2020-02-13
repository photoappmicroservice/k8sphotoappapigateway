package com.appsdeveloperblog.photoapp.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.appsdeveloperblog.photoapp.api.gateway.config.AppAlbumConfig;
import com.appsdeveloperblog.photoapp.api.gateway.config.AppGatewayConfig;
import com.appsdeveloperblog.photoapp.api.gateway.config.AppUserConfig;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final AppGatewayConfig appGatewayConfig;
    private final AppUserConfig appUserConfig;
    private final AppAlbumConfig appAlbumConfig;

    @Autowired
    public WebSecurity(Environment environment, 
    		AppGatewayConfig appGatewayConfig,
    		AppUserConfig appUserConfig,
    		AppAlbumConfig appAlbumConfig) {
        this.environment = environment;
        this.appAlbumConfig = appAlbumConfig;
        this.appUserConfig = appUserConfig;
        this.appGatewayConfig = appGatewayConfig;
        
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.csrf().disable(); 
    	http.headers().frameOptions().disable();
    	http.authorizeRequests()
    	.antMatchers(this.appGatewayConfig.getActuatorurlpath()).permitAll()
    	.antMatchers(this.appUserConfig.getActuatorurlpath()).permitAll()
    	.antMatchers(this.appUserConfig.getH2consoleurlpath()).permitAll()
    	.antMatchers(HttpMethod.POST, this.appUserConfig.getRegistrationurlpath()).permitAll()
    	.antMatchers(HttpMethod.POST, this.appUserConfig.getLoginurlpath()).permitAll()
    	.antMatchers(this.appAlbumConfig.getActuatorurlpath()).permitAll()
    	.antMatchers(this.appAlbumConfig.getH2consoleurlpath()).permitAll().
    	anyRequest().authenticated()
    	.and()
    	.addFilter(new AuthorizationFilter(authenticationManager(), environment, this.appGatewayConfig));
    	
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    }	
	
}
