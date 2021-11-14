package com.viswateja.farmstead;

import com.viswateja.farmstead.Filter.AuthorisationFilter;
import com.viswateja.farmstead.helper.OrderShippingJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FarmsteadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmsteadApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthorisationFilter> loggingFilter(){
		FilterRegistrationBean<AuthorisationFilter> registrationBean
				= new FilterRegistrationBean<>();

		registrationBean.setFilter(new AuthorisationFilter());
		registrationBean.addUrlPatterns("/api/v1/*");

		return registrationBean;
	}

}
