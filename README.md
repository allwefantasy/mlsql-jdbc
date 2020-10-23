# MLSQL JDBC 驱动

[MLSQL](https://www.mlsql.tech) 是一门优秀的面向大数据和AI的语言。 该项目使得其支持JDBC协议。该驱动会自动将jdbc请求转化为MLSQL 识别的的
Rest请求并返回符合JDBC格式的数据。

注意： 需要MLSQL 1.6.0-SNAPSHOT及以上版本支持

## 当前状态

正在积极开发，还未发布稳定版本

```xml
<dependency>
    <groupId>tech.mlsql.api.jdbc</groupId>
    <artifactId>mlsql-jdbc</artifactId>
    <version>0.1.0</version>
</<dependency>
```

## 标准Spark SQL

```scala
 Class.forName("tech.mlsql.api.jdbc.MLSQLDriver")
    val conn = DriverManager.getConnection("jdbc:mlsql://127.0.0.1:9003/test?user=william&password=xxx", new Properties())
    val stat = conn.prepareStatement(
      """
        |select 1 as a,TIMESTAMP("2017-07-23 00:00:00") as k, current_date() as v, "wow'" as jack
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
```
## MLSQL使用示例

```scala
package tech.mlsql.api.jdbc.test

import java.sql.DriverManager
import java.util.Properties

import org.scalatest.FunSuite


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
}

```

## 如何实现鉴权

MLSQL Engine本身不支持鉴权。不过用户有两种方式实现鉴权：

1. MLSQL JDBC访问HTTP代理服务而不是直接访问MLSQL Engine.代理服务会实现鉴权。
2. 开发基于MLSQL Engine的鉴权插件，未来官方也可能会直接提供。


