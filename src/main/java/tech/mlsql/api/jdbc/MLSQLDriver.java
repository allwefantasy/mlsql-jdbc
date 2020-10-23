package tech.mlsql.api.jdbc;

import tech.mlsql.api.jdbc.util.MLSQLDriverUtil;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
public class MLSQLDriver implements Driver {
    static {
        try {
            DriverManager.registerDriver(new MLSQLDriver());
        } catch (Exception e) {
            throw new RuntimeException("Can't register $DRIVER_NAME", e);
        }
    }

    public Connection connect(String url, Properties info) throws SQLException {
        if (!acceptsURL(url)) throw new SQLException("Invalid URL: " + url);
        scala.collection.immutable.Map<String, String> prop = MLSQLDriverUtil.parseMergeProperties(url, info);
        return new MLSQLConnection(prop);
    }

    public boolean acceptsURL(String url) throws SQLException {
        return url.toLowerCase().startsWith(MLSQLConst.JDBC_URL);
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return MLSQLConst.MAJOR_VERSION;
    }

    public int getMinorVersion() {
        return MLSQLConst.MINOR_VERSION;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException("getParentLogger");
    }
}
