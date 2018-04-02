package com.chinapex.nexus.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by pengmingguo on 4/2/18
 */
@Configuration
public class DataSourceConfig {

  @Value("${spring.datasource.url}")
  private String jdbcurl;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Bean
  public DataSource dataSource() {
    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl(jdbcurl);
    ds.setUsername(username);
    ds.setPassword(password);
    ds.setMaximumPoolSize(5);
    ds.setMinimumIdle(2);
    return ds;
  }

}
