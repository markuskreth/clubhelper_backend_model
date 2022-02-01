package de.kreth.clubhelper.model.config;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

@Configuration
public class SwaggerSpringfoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.tags(new Tag("Person", "Endpoints for Person Operations"), new Tag("Contact", "Endpoints for Contact Operations"))
				.apiInfo(new ApiInfoBuilder()
						.title("Clubhelper Rest Service")
						.contact(new Contact("Markus Kreth", null, "markus.kreth@web.de"))
						.description("Rest Backend for Clubhelper Applications, authenticated by Keykloak.")
						.build())
				.select()
				.apis(apis())
				.paths(paths())
				.build();
	}

	private Predicate<String> paths() {
		return PathSelectors.ant("/person/**")
				.or(PathSelectors.ant("/contact/**"));
	}
	
	private Predicate<RequestHandler> apis() {
		return new Predicate<RequestHandler>() {

			@Override
			public boolean test(RequestHandler t) {
				Class<?> declaringClass = t.declaringClass();
				
				return true;
			}
		};
	}
	
	@Bean
	public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
		
		return new BeanPostProcessor() {

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
					customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
				}
				return bean;
			}

			private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(
					List<T> mappings) {
				List<T> copy = mappings.stream().filter(mapping -> mapping.getPatternParser() == null)
						.collect(Collectors.toList());
				mappings.clear();
				mappings.addAll(copy);
			}

			@SuppressWarnings("unchecked")
			private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
				try {
					Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
					field.setAccessible(true);
					return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}
		};
		
	}
}
