package com.github.jingou.common.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author caedmon
 */
@Configuration
public class FlywayConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DataSource dataSource;

    @Value(value = "${spring.flyway.baseline-version}")
    private String baselineVersion;

    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .placeholderReplacement(false)
                .baselineVersion(baselineVersion)
                .baselineOnMigrate(true)
                .dataSource(dataSource)
                .load();
    }
}
