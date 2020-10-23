package tech.mlsql.api.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 23/10/2020 WilliamZhu(allwefantasy@gmail.com)
 */
public class Utils {

    public static Map rsToMap(ResultSet rs, String[] keys) {
        Map temp = new HashMap();
        for (int i = 0; i < keys.length; i++) {

            try {
                temp.put(keys[i], rs.getObject(keys[i]));
            } catch (SQLException e) {
                continue;
            }
        }
        return temp;
    }
    public static List<Map> rsToMaps(ResultSet rs) {
        List result = new ArrayList();
        try {
            while (rs.next()) {
                result.add(rsToMap(rs, getRsCloumns(rs)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String[] getRsCloumns(ResultSet rs) throws SQLException {
        ResultSetMetaData rsm = rs.getMetaData();
        String[] columns = new String[rsm.getColumnCount()];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = rsm.getColumnLabel(i + 1);
        }
        return columns;
    }
}
