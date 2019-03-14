package com.louis.platform.config;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Arrays;

/**
 * Mybatis配置
 * @author Louis
 * @date Oct 29, 2018
 */
@Configuration
@MapperScan({"com.louis.platform.*.dao","com.louis.pp.app.*.dao"})	// 扫描DAO
public class MybatisConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    //sessionFactory.setTypeAliasesPackage("com.louis.platform.*.model");	// 扫描Model
    
	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    Resource[] pf=resolver.getResources("classpath:**/sqlmapper/*.xml");
    Resource[] other=resolver.getResources("classpath:**/mapper/*.xml");
    Resource[] allMappers= ArrayUtils.addAll(pf,other);

	sessionFactory.setMapperLocations(allMappers);	// 扫描映射文件
	
    return sessionFactory.getObject();
  }
}