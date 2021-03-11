package tech.mlsql.api.jdbc

import java.nio.charset.Charset

import org.apache.http.client.fluent.{Form, Request}

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
object MLSQLRestIO {
  def internalExecuteQuery(sql: String, user: String, properties: Map[String, String]) = {

    val form = Form.form()
    properties.foreach { case (k, v) => form.add(k, v) }

    val respJson = Request.Post(s"http://${properties(MLSQLConst.PROP_HOST)}:${properties(MLSQLConst.PROP_PORT)}/run/script").
      connectTimeout(properties.getOrElse(MLSQLConst.PROP_CONNECT_TIMEOUT, 1000 * 60 * 60).toString.toInt).
      socketTimeout(properties.getOrElse(MLSQLConst.PROP_SOCKET_TIMEOUT, 1000 * 60 * 60 * 60).toString.toInt).
      bodyForm(
        form.
          add("owner", user).
          add("sql", sql).
          add("sessionPerUser", "true").
          add("includeSchema", "true").
          build(),Charset.forName(properties.getOrElse("characterEncoding","ISO-8859-1"))
      ).execute().returnContent().asString()
    respJson
  }


}
