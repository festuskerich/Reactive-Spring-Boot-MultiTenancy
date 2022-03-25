package com.franciumsources.Reactive.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Start by enabling WebFlux using the @EnableWebFlux annotation and configure the framework
 * The idea is to pass the Tenant identifier to the Web service by using a HTTP header named X-TenantID
* */
@Configuration
@EnableWebFlux
class WebFluxConfiguration implements WebFluxConfigurer {
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().enableLoggingRequestDetails(true);
    }
}
