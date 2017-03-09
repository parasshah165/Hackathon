package com.ge.tcs.cityOfTomorrow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "com.ge.tcs.cityOfTomorrow.*" })
public class CityOfTomorrowApplication {

	private static final Log logger = LogFactory.getLog(CityOfTomorrowApplication.class);
	@Value("${demo.assetservice.zoneId}")
	String zoneId;
	
	@Value("${demo.assetservice.timeSeriesZoneId}")
	String timeSeriesZoneId;

	
	
	@Bean
	@ConfigurationProperties("security.oauth2.client")
	public ClientCredentialsResourceDetails details() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public OAuth2RestTemplate restTemplate(OAuth2ClientContext context, ClientCredentialsResourceDetails details) {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details, context);
		restTemplate.getInterceptors().add(headersAddingInterceptor());
		return restTemplate;
	}

	
	@Bean
	public OAuth2RestTemplate restTemplateTimeSeries(OAuth2ClientContext context, ClientCredentialsResourceDetails details) {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details, context);
		restTemplate.getInterceptors().add(headersAddingInterceptorTimeSeries());
		return restTemplate;
	}
	
	public ClientHttpRequestInterceptor headersAddingInterceptorTimeSeries() {
		return (request, body, execution) -> {
			request.getHeaders().set("Predix-Zone-Id", timeSeriesZoneId);
			request.getHeaders().set("Content-Type","application/json;charSet=utf-8");
			return execution.execute(request, body);
		};
	}

	
	public ClientHttpRequestInterceptor headersAddingInterceptor() {
		return (request, body, execution) -> {
			request.getHeaders().set("Predix-Zone-Id", zoneId);
			request.getHeaders().set("Content-Type","application/json;charSet=utf-8");
			return execution.execute(request, body);
		};
	}

	public static void main(String[] args) {
		logger.info("Application Started");
		SpringApplication.run(CityOfTomorrowApplication.class, args);
	}
}
