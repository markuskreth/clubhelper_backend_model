package de.kreth.clubhelper.model.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
	
	private KeycloakConfigResolver resolver;
	
	@Bean
	public KeycloakConfigResolver keycloakConfigResolver() {
		if (resolver == null) {
			resolver = new KeycloakSpringBootConfigResolver();
		}
		return resolver;
	}

}
