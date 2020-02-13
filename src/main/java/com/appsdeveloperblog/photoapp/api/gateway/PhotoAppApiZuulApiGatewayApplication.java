package com.appsdeveloperblog.photoapp.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableZuulProxy
@Profile(value = {"kubernetes","local"})
public class PhotoAppApiZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiZuulApiGatewayApplication.class, args);
	}

}
