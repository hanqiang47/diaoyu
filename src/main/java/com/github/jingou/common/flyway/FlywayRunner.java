package com.github.jingou.common.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author caedmon
 */
//@Component
public class FlywayRunner implements ApplicationRunner {
    @Autowired
    private Flyway flyway;

    @Value(value = "${spring.flyway.baseline-on-migrate}")
    private Boolean baselineOnMigrate;

    @Override
    public void run(ApplicationArguments args) {
        if (baselineOnMigrate) {
            flyway.migrate();
        }
    }
}
