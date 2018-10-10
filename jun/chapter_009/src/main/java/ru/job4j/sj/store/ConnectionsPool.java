package ru.job4j.sj.store;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class represent connections pool.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 25.03.2018.
 */
public class ConnectionsPool {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionsPool.class);

    private static final ComboPooledDataSource POOL;

    private ConnectionsPool() { }

    static {
        POOL = new ComboPooledDataSource();
        Properties prs = new Properties();

        try {
            prs.load(new FileInputStream(ConnectionsPool.class.getClassLoader().getResource("db.properties").getPath()));
            POOL.setDriverClass(prs.getProperty("jdbc.driver"));
        } catch (IOException | PropertyVetoException e) {
            LOG.error(e.getMessage(), e);
        }
        POOL.setJdbcUrl(prs.getProperty("jdbc.url"));
        POOL.setUser(prs.getProperty("jdbc.username"));
        POOL.setPassword(prs.getProperty("jdbc.password"));
    }

    public static Connection getConnection() throws SQLException {
        return POOL.getConnection();
    }
}
