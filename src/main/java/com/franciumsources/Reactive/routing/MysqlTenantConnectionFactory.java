package com.franciumsources.Reactive.routing;

import com.franciumsources.Reactive.utils.AppConstants;
import io.r2dbc.spi.ConnectionFactoryMetadata;
import org.springframework.r2dbc.connection.lookup.AbstractRoutingConnectionFactory;
import reactor.core.publisher.Mono;

import static com.franciumsources.Reactive.utils.ReactorUtils.errorIfEmpty;

/**
 * The R2DBC API defines an AbstractRoutingConnectionFactory to resolve a ConnectionFactory based on a routing key
 * determined by a call to the abstract method AbstractRoutingConnectionFactory#determineCurrentLookupKey.
 * We also need to override AbstractRoutingConnectionFactory#getMetadata, so no default data source needs to be specified
 * */
public class MysqlTenantConnectionFactory extends AbstractRoutingConnectionFactory {
    static final class MysqlConnectionFactoryMetadata implements ConnectionFactoryMetadata {
        static final MysqlConnectionFactoryMetadata INSTANCE = new MysqlConnectionFactoryMetadata();
        public static final String NAME = "MYSQL";

        private MysqlConnectionFactoryMetadata() {
        }

        @Override
        public String getName() {
            return NAME;
        }
    }

    @Override
    protected Mono<Object> determineCurrentLookupKey() {
        return Mono
                .deferContextual(Mono::just)
                .filter(it -> it.hasKey(AppConstants.TenantKey))
                .map(it -> it.get(AppConstants.TenantKey))
                .transform(m -> errorIfEmpty(m, () -> new RuntimeException(String.format("ContextView does not contain the Lookup Key '%s'", AppConstants.TenantKey))));
    }

    @Override
    public ConnectionFactoryMetadata getMetadata() {
        return MysqlConnectionFactoryMetadata.INSTANCE;
    }
}
