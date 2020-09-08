package com.home.app.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.home.app.service.endpoint.TopicRestService;

@Component
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(TopicRestService.class);
	}

}
