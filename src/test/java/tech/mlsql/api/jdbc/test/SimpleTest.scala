package tech.mlsql.api.jdbc.test

import java.sql.DriverManager
import java.util.Properties

import org.scalatest.FunSuite

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class SimpleTest extends FunSuite {
  test("simple") {
    Class.forName("tech.mlsql.api.jdbc.MLSQLDriver")
    val conn = DriverManager.getConnection("jdbc:mlsql://127.0.0.1:9003/test?user=william&password=xxx", new Properties())
    val stat = conn.prepareStatement(
      """
        |select 1 as a,TIMESTAMP("2017-07-23 00:00:00") as k, current_date() as v as b;
        |""".stripMargin)
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
}
