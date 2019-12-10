package io.github.mylyed.ds.ms;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 *
 * @author lilei
 * created at 2019/12/10
 */
@Configuration
@MapperScan("io.github.mylyed.ds.dao")
@Slf4j
public class DataSourceConfig {
    /**
     * 主库
     *
     * @return
     */
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        log.debug("初始化主库数据源");
        return DataSourceBuilder.create().build();
    }

    /**
     * 从库
     *
     * @return
     */
    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        log.debug("初始化从库数据源");
        return DataSourceBuilder.create().build();
    }

    /**
     * 双数据源
     *
     * @return
     */
    @Bean("readWriteSplitRoutingDataSource")
    @Primary
    @DependsOn({"masterDataSource", "slaveDataSource"})
    public ReadWriteSplitRoutingDataSource dynamicDataSource() {
        log.debug("初始化主从库数据源");
        ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<Object, Object>();
        targetDataResources.put(DataSourceHolder.DbType.MASTER, masterDataSource());
        targetDataResources.put(DataSourceHolder.DbType.SLAVE, slaveDataSource());
        proxy.setDefaultTargetDataSource(masterDataSource());

        proxy.setTargetDataSources(targetDataResources);
        return proxy;
    }
}
