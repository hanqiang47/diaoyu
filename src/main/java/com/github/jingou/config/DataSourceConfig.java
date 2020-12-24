package com.github.jingou.config;

import cn.hutool.core.lang.Snowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author caedmon
 */
//@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        checkSchema();
        return DataSourceBuilder.create().build();
    }

    private void checkSchema() {
        try {
            Class.forName(driverClassName);
            String url01 = datasourceUrl.substring(0, datasourceUrl.indexOf("?"));
            String url02 = url01.substring(0, url01.lastIndexOf("/"));
            String dbName = url01.substring(url01.lastIndexOf("/") + 1);
            Connection connection = DriverManager.getConnection(url02, username, password);
            Statement statement = connection.createStatement();
            String sql = String.format("CREATE DATABASE IF NOT EXISTS `%s` DEFAULT CHARACTER SET utf8", dbName);
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e) {
            log.error("检查数据库时发生错误", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(0, 0);
    }
}
