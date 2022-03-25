package com.franciumsources.Reactive.filters;

import com.franciumsources.Reactive.utils.AppConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
/**
 * A WebFilter will intercept the HTTP request to WebFlux,
 * read the X-TenantID and put it in the ContextView so anyone participating
 * in the request has access to the Tenant identifier
* */
@Component
public class TenantIdWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        var headerValues = serverWebExchange.getRequest().getHeaders().get(AppConstants.TenantId);
        if (headerValues == null || headerValues.size() == 0) {
            return webFilterChain.filter(serverWebExchange);
        }
        String tenantKey = headerValues.get(0);
        return webFilterChain
                .filter(serverWebExchange)
                .contextWrite(ctx -> ctx.put(AppConstants.TenantKey, tenantKey));
    }
}
