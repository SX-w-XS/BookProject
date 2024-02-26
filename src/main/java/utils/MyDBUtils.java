package utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;

/**
 * MySQL工具类：
 * 用于连接MySQL
 */
public class MyDBUtils {
    private static MysqlDataSource dataSource;

   static {
        // 初始化数据源
        dataSource = new MysqlDataSource();
        // 设置用户名
        dataSource.setUser("root");
        // 设置密码
        dataSource.setPassword("root");
        // 设置数据库连接地址
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_book?serverTimezone=UTC");
    }

    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }
}
