package com.yiran.config;

import com.alibaba.druid.pool.DruidDataSource;

import com.yiran.EvaluationApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Yiran on 17-1-16.
 * //
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = EvaluationApplication.class)
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.name}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DruidDataSource configureDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.addConnectionProperty("useUnicode", "true");
        ds.addConnectionProperty("characterEncoding", "utf8");
        ds.addConnectionProperty("cachePrepStmts", "true");
        ds.addConnectionProperty("prepStmtCacheSize", "250");
        ds.addConnectionProperty("prepStmtCacheSqlLimit", "2048");
        ds.addConnectionProperty("useServerPrepStmts", "true");

        return ds;
    }
}
