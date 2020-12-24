package com.github.jingou;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author caedmon
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
@SpringBootApplication
public class JingouManagerApplication {

    private static final Logger logger = LoggerFactory.getLogger(JingouManagerApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JingouManagerApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:{}/{}\n\t"
                        + "Application name:\t{}\n\t" + "\n----------------------------------------------------------",
                env.getProperty("server.port"), "swagger-ui/index.html", env.getProperty("spring.application.name"));
    }

}
