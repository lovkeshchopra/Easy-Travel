package com.easytravel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//@EnableWebMvc
@EnableAsync
public class BeanConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {

		String activeProfile = System.getenv("environment");
		String propertiesFilename = "application-" + activeProfile + ".properties";
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource(propertiesFilename));
		return configurer;
	}

	@Bean(name ="taskExecutor")
	public TaskExecutor threadPoolTaskExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(15);
		executor.setMaxPoolSize(50);
		executor.setThreadNamePrefix("threadPoolExecutor-");
		executor.initialize();
		return executor;
	}

//	  @Bean
//	    public ViewResolver getViewResolver() {
//	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	        resolver.setPrefix("/WEB-INF/");
//	        resolver.setSuffix(".jsp");
//	        return resolver;
//	    }
}
