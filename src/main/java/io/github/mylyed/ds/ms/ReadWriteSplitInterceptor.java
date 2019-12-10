package io.github.mylyed.ds.ms;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


/**
 * 切换数据源拦截器
 */
@Aspect
@Component
@Slf4j
public class ReadWriteSplitInterceptor implements Ordered {

    @Before("execution(* io.github.mylyed.ds.dao..*.find*(..)) ")
    public void setWriteDataSourceType() {
        log.debug("切换到从库");
        DataSourceHolder.cleanDbType();
        DataSourceHolder.setDbType(DataSourceHolder.DbType.SLAVE);
    }

    @Before("execution(* io.github.mylyed.ds.dao..*.master*(..)) ")
    public void setReadDataSourceType() {
        log.debug("切换到主库");
        DataSourceHolder.cleanDbType();
        DataSourceHolder.setDbType(DataSourceHolder.DbType.MASTER);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
