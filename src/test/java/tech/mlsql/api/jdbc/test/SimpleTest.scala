package tech.mlsql.api.jdbc.test

import java.sql.DriverManager
import java.util.Properties

import org.scalatest.FunSuite
import tech.mlsql.api.jdbc.Utils

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class SimpleTest extends FunSuite {
  test("simple") {
    Class.forName("tech.mlsql.api.jdbc.MLSQLDriver")
    val properties = new Properties()
    properties.put("sqlType", "mlsql")
    val conn = DriverManager.getConnection("jdbc:mlsql://127.0.0.1:9003/test?user=william&password=xxx", properties)
    val stat = conn.prepareStatement(
      """
        |select 1 as a,TIMESTAMP("2017-07-23 00:00:00") as k, current_date() as v, "wow'" as jack as b;
        |select * from b where a=? and jack=? as output;
        |""".stripMargin)
    stat.setInt(1, 1)
    stat.setString(2, "wow'")
    val rs = stat.executeQuery()
    while (rs.next()) {
      println(rs.getString("a"))
      println(rs.getTimestamp("k"))
      println(rs.getDate("v"))
    }
    rs.close()
    stat.close()
    conn.close()
  }
  test("simple2") {
    Class.forName("tech.mlsql.api.jdbc.MLSQLDriver")
    val conn = DriverManager.getConnection("jdbc:mlsql://127.0.0.1:9003/test?user=william&password=xxx&access_token=mlsql", new Properties())
    val stat = conn.prepareStatement(
      """
        |select 1 as a,TIMESTAMP("2017-07-23 00:00:00") as k, current_date() as v, "wow'" as jack
        |""".stripMargin)
    val rs = conn.getMetaData.getTables("default","","",Array())
    //    val rs = stat.executeQuery()
    while (rs.next()) {
//      println(rs.getString(1))
      println(rs.getString(7))
      //      println(rs.getDate("v"))
    }
    rs.close()
    stat.close()
    conn.close()
  }

  test("simple3") {
    Class.forName("tech.mlsql.api.jdbc.MLSQLDriver")
    val conn = DriverManager.getConnection("jdbc:mlsql://127.0.0.1:9003/test?user=william&password=xxx&access_token=mlsql", new Properties())
    val stat = conn.prepareStatement(
      """
        |show databases
        |""".stripMargin)
    val rs = stat.executeQuery()
    while (rs.next()) {
      println(rs.getString(1))
    }
    rs.close()
    stat.close()
    conn.close()
  }
  test("simple4") {
    Class.forName("com.mysql.jdbc.Driver")
    val prop = new Properties()
    prop.put("user","root")
    prop.put("password","mlsql")
    val conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/wow?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false", prop)
    val rs = conn.getMetaData.getCatalogs
//    println(Utils.rsToMaps(rs))
    rs.next()
    println(rs.getString(1))
    rs.close()
    conn.close()
  }
}
