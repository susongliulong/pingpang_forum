package com.sun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 实现类型转换： MultipartFile==>CommonsMultipartFile
 */
@Configuration
public class MultipartResolverConfig {

	@Bean(name="multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setResolveLazily(true);
		resolver.setMaxInMemorySize(40960);
		resolver.setMaxUploadSize(1024*1024*1024);
		return resolver;
	}
}
