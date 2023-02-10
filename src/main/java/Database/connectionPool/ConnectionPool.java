package Database.connectionPool;

import GlobalVar.Lock;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接池对象，单例
 */
public class ConnectionPool {
    private static volatile ConnectionPool dbConnection;
    private ComboPooledDataSource cpds1;
    private ComboPooledDataSource cpds2;

    /**
     * 在构造函数初始化的时候获取数据库连接
     */
    private ConnectionPool() {
        try {
            /*通过属性文件获取数据库连接的参数值**/
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\jdbc-sqlite.properties");
            properties.load(fileInputStream);
            /*获取属性文件中的值**/
            String driverClassName = properties.getProperty("jdbc.driverClassName");
            String url = properties.getProperty("jdbc.url1");
            int initconnect = Integer.parseInt(properties.getProperty("initconnect1"));
            int maxconnect = Integer.parseInt(properties.getProperty("maxconnect1"));
            int createconnectnum = Integer.parseInt(properties.getProperty("createconnectnum1"));
            int maxwaittime = Integer.parseInt(properties.getProperty("maxwaittime1"));
            int trytime = Integer.parseInt(properties.getProperty("trytime1"));
            int waittime = Integer.parseInt(properties.getProperty("waittime1"));
            cpds1 = new ComboPooledDataSource();
            cpds1.setDriverClass(driverClassName);
            cpds1.setJdbcUrl(url);
            cpds1.setInitialPoolSize(initconnect);
            cpds1.setMaxPoolSize(maxconnect);
            cpds1.setAcquireIncrement(createconnectnum);
            cpds1.setIdleConnectionTestPeriod(60);
            cpds1.setMaxIdleTime(maxwaittime);
            cpds1.setTestConnectionOnCheckout(true);
            cpds1.setTestConnectionOnCheckin(true);
            cpds1.setAcquireRetryAttempts(trytime);
            cpds1.setAcquireRetryDelay(waittime);
            cpds1.setBreakAfterAcquireFailure(true);

            /*获取属性文件中的值**/
            url = properties.getProperty("jdbc.url2");
            initconnect = Integer.parseInt(properties.getProperty("initconnect2"));
            maxconnect = Integer.parseInt(properties.getProperty("maxconnect2"));
            createconnectnum = Integer.parseInt(properties.getProperty("createconnectnum2"));
            maxwaittime = Integer.parseInt(properties.getProperty("maxwaittime2"));
            trytime = Integer.parseInt(properties.getProperty("trytime2"));
            waittime = Integer.parseInt(properties.getProperty("waittime2"));
            cpds2 = new ComboPooledDataSource();
            cpds2.setDriverClass(driverClassName);
            cpds2.setJdbcUrl(url);
            cpds2.setInitialPoolSize(initconnect);
            cpds2.setMaxPoolSize(maxconnect);
            cpds2.setAcquireIncrement(createconnectnum);
            cpds2.setIdleConnectionTestPeriod(60);
            cpds2.setMaxIdleTime(maxwaittime);
            cpds2.setTestConnectionOnCheckout(true);
            cpds2.setTestConnectionOnCheckin(true);
            cpds2.setAcquireRetryAttempts(trytime);
            cpds2.setAcquireRetryDelay(waittime);
            cpds2.setBreakAfterAcquireFailure(true);
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接对象，单例
     */
    public static ConnectionPool getInstance() {
        if (dbConnection == null) {
            synchronized (ConnectionPool.class) {
                if (dbConnection == null) {
                    dbConnection = new ConnectionPool();
                }
            }
        }
        return dbConnection;
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public final synchronized Connection getConnection() throws SQLException {
        // 当plane1在更新的时候返回plane2的连接
        if (Lock.lock)
            return cpds2.getConnection();
        else
            return cpds1.getConnection();
    }
}
