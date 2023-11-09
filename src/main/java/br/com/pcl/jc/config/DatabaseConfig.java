package br.com.pcl.jc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "app.database")
public class DatabaseConfig {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private String poolName;
    private Integer maxPoolSize;
    private Integer minPoolSize;
    private Integer maxLifetime;
    private Integer validationTimeout;

    @Bean
    public DataSource getDataSource() throws IOException {
        log.info("Connecting to database: {}", url);
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setPoolName(poolName);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMinimumIdle(minPoolSize);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setValidationTimeout(validationTimeout);
        return new HikariDataSource(hikariConfig);
    }


}
