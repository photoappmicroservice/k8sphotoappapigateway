package com.appsdeveloperblog.photoapp.api.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.albums")
public class AppAlbumConfig {

	private String actuatorurlpath;
	private String h2consoleurlpath;
	
	public String getActuatorurlpath() {
		return actuatorurlpath;
	}
	public void setActuatorurlpath(String actuatorurlpath) {
		this.actuatorurlpath = actuatorurlpath;
	}
	public String getH2consoleurlpath() {
		return h2consoleurlpath;
	}
	public void setH2consoleurlpath(String h2consoleurlpath) {
		this.h2consoleurlpath = h2consoleurlpath;
	}

	
}
