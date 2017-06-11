package com.books.management.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.books.management",sqlSessionFactoryRef="sqlSessionFactory")
@EnableConfigurationProperties({DbProperties.class})
public class DbConfig {
	@Autowired
	private DbProperties properties;
	

    @Bean(name = "sqlDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        System.out.println("数据库连接成功！");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("sqlDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(plugins());
        return sessionFactory.getObject();
    }

    public Interceptor[] plugins() {
        OffsetLimitInterceptor plugin = new OffsetLimitInterceptor();
        plugin.setDialectClass("com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
        return new Interceptor[] { plugin };
    }
}
