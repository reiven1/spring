package com.bs.spring.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:myresources.properties")
public class PropertyData {
	@Value("${app.name}")
	private String appName;
	@Value("${db.url}")
	private String dbUrl;
	@Value("${db.user}")
	private String dbUserName;
	@Value("${db.pass}")
	private String dbPassword;
}
