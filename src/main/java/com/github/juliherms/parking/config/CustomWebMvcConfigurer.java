package com.github.juliherms.parking.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class responsible to configurate default values from pageable
 * @author jlv
 *
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * Method responsible to set default page and total elements show in the list
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
		PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
		pageHandler.setFallbackPageable(PageRequest.of(0,5));
		resolvers.add(pageHandler);
	}
}
