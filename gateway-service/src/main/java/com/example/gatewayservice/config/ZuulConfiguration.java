package com.example.gatewayservice.config;

import com.example.gatewayservice.filters.errFilters.ErrorFilter;
import com.example.gatewayservice.filters.postFilteres.PostFilter;
import com.example.gatewayservice.filters.preFilters.PreFilter;
import com.example.gatewayservice.filters.routeFilters.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
}
