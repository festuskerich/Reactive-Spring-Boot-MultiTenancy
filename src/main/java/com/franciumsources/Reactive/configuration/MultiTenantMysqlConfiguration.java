package com.franciumsources.Reactive.configuration;

import com.franciumsources.Reactive.routing.MysqlTenantConnectionFactory;
import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.lookup.AbstractRoutingConnectionFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

import static java.util.Map.entry;
/**
 * MysqlTenantConnectionFactory configured by extending the AbstractR2dbcConfiguration.
 * This is where we are adding the connections for each tenant.
 * NOTE::All this can also be externalized to a configuration of course.
 * */
@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories
public class MultiTenantMysqlConfiguration extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        var connectionFactory = mysqlConnectionFactory();
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    private AbstractRoutingConnectionFactory mysqlConnectionFactory() {
        var routingConnectionFactory = new MysqlTenantConnectionFactory();
        routingConnectionFactory.setLenientFallback(false);
        routingConnectionFactory.setTargetConnectionFactories(tenants());
        return routingConnectionFactory;
    }

    private Map<String, ConnectionFactory> tenants() {
        return Map.ofEntries(
                entry("TenantOne", tenantOne()),
                entry("TenantTwo", tenantTwo())
        );
    }

    private ConnectionFactory tenantOne() {
        MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .user("root")
                .port(3306)
                .password("Coder_1234")
                .database("nigeria")
                .build();
        return MySqlConnectionFactory.from(configuration);
    }
    private ConnectionFactory tenantTwo() {
        MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .user("root")
                .port(3306)
                .password("Coder_1234")
                .database("kenya")
                .build();
        return MySqlConnectionFactory.from(configuration);
    }
}
