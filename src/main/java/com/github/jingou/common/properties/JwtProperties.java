package com.github.jingou.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 *
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
@Data
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "authorization";

    private String secret = "defaultSecret";

    private Long expiration = 1800L;

    private String authPath = "login";

    private String md5Key = "randomKey";
}
