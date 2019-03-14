package com.louis.platform.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration
public class ActivitiConfig {
    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public SpringProcessEngineConfiguration getProcessEngineConfiguration() {
        DataSource dataSource = (DataSource)applicationContext.getBean("druidDataSource");
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        config.setDataSource(dataSource);
        //config.setDbHistoryUsed(false);
        config.setDbIdentityUsed(false);
        config.setTransactionManager(transactionManager);
        //添加字体
        config.setActivityFontName("宋体");
        config.setLabelFontName("宋体");
        config.setAnnotationFontName("宋体");
        config.setDatabaseType("mysql");
        // config.getJobExecutor();
        //第一生成表要启动下面配置，以后重新生成create改为drop-create
        //如果有表不存在就更新添加
          config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //设置Schema为ACT，他会字段添加表
         config.setDatabaseSchema("ACT");
        return config;
    }
}
