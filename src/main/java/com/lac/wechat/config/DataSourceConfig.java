package com.lac.wechat.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * ClassName: DataSourceConfig <br/>
 *  数据源
 * @author lac
 * @version 1.0
 * @date 2019/8/27 0027 - 16:44
 */
@Configuration
public class DataSourceConfig {


    /*
    Environment类是SpringBoot代表了环境上下文，
    包含了application.properties 配置属性,JVM系统属性和操作系统环境变量的类
     */
    @Bean(name = "dataSource")
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        ds.setMaximumPoolSize(10);
        return ds;
    }

}
