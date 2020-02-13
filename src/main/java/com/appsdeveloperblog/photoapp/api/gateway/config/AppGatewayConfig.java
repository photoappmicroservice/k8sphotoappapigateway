package com.appsdeveloperblog.photoapp.api.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.gateway")
public class AppGatewayConfig {

	private String authorizationtokenheadername;
	private String actuatorurlpath;
	private String tokensecret;
	private String authorizationtokenheaderprefix;
	
	public String getAuthorizationtokenheadername() {
		return authorizationtokenheadername;
	}
	public void setAuthorizationtokenheadername(String authorizationtokenheadername) {
		this.authorizationtokenheadername = authorizationtokenheadername;
	}
	public String getActuatorurlpath() {
		return actuatorurlpath;
	}
	public void setActuatorurlpath(String actuatorurlpath) {
		this.actuatorurlpath = actuatorurlpath;
	}
	public String getTokensecret() {
		return tokensecret;
	}
	public void setTokensecret(String tokensecret) {
		this.tokensecret = tokensecret;
	}
	public String getAuthorizationtokenheaderprefix() {
		return authorizationtokenheaderprefix;
	}
	public void setAuthorizationtokenheaderprefix(String authorizationtokenheaderprefix) {
		this.authorizationtokenheaderprefix = authorizationtokenheaderprefix;
	}

}
