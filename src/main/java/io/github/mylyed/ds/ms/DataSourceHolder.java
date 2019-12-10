package io.github.mylyed.ds.ms;

/**
 * 数据源切换
 *
 * @author lilei
 * created at 2019/12/10
 */
public class DataSourceHolder {


    public enum DbType {
        /**
         * 主库
         */
        MASTER,
        /**
         * 从库
         */
        SLAVE
    }


    private static final ThreadLocal<DbType> threadLocal = new ThreadLocal<>();

    public static void setDbType(DbType dbType) {
        threadLocal.set(dbType);
    }

    public static DbType getDbType() {
        return threadLocal.get();
    }

    public static void cleanDbType() {
        threadLocal.remove();
    }

}
