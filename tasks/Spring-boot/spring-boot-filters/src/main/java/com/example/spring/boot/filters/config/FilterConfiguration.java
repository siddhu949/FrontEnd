package com.example.spring.boot.filters.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring.boot.filters.filter.MessageFilter;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<MessageFilter> registrationBean() {
        FilterRegistrationBean<MessageFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MessageFilter());
        registrationBean.addUrlPatterns("/customer/*");  // ✅ fixed typo
        registrationBean.setOrder(1); // optional - order if multiple filters

        return registrationBean;
    }
}
