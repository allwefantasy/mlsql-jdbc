package tech.mlsql.api.jdbc;

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
public class MLSQLConst {
    public static String JDBC_URL = "jdbc:mlsql:";
    public static int MAJOR_VERSION = 1;
    public static int MINOR_VERSION = 0;
    public static int DEFAULT_PORT = 9003;
    public static String DRIVER_NAME = "MLSQL";
    public static String PRODUCT_NAME = "MLSQL";
    public static String PRODUCT_VERSION = "0.1";


    public static int MAX_SIZE = 1024;

    // 访问服务器的参数
    public static String PROP_SCHEMA = "schema";
    public static String PROP_HOST = "host";
    public static String PROP_PORT = "port";
    public static String PROP_PATH = "path";
    public static String PROP_USER = "user";
    public static String PROP_PASSWORD = "password";

    public static String PROP_CONNECT_TIMEOUT = "connectTimeout";
    public static String PROP_SOCKET_TIMEOUT = "socketTimeout";
}
