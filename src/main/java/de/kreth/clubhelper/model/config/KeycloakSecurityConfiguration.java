package de.kreth.clubhelper.model.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@KeycloakConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KeycloakSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter
{
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth)
   {
      KeycloakAuthenticationProvider keyCloakAuthProvider = keycloakAuthenticationProvider();
      keyCloakAuthProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
      auth.authenticationProvider(keyCloakAuthProvider);
   }

   @Bean
   public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher()
   {
      return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
   }

   @Bean
   public KeycloakConfigResolver keyCloakConfigResolver()
   {
      return new KeycloakSpringBootConfigResolver();
   }

   @Override
   protected SessionAuthenticationStrategy sessionAuthenticationStrategy()
   {
      return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      super.configure(http);
      http.authorizeRequests();
   }
}
