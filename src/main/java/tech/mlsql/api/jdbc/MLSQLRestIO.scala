package tech.mlsql.api.jdbc

import org.apache.http.client.fluent.{Form, Request}

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
object MLSQLRestIO {
  def internalExecuteQuery(sql: String, user: String, properties: Map[String, String]) = {

    val form = Form.form()
    properties.foreach { case (k, v) => form.add(k, v) }

    val respJson = Request.Post(s"http://${properties(MLSQLConst.PROP_HOST)}:${properties(MLSQLConst.PROP_PORT)}/run/script").
      connectTimeout(properties.getOrElse("connectTimeout", 1000 * 60 * 60).toString.toInt).
      socketTimeout(properties.getOrElse("socketTimeout", 1000 * 60 * 60 * 60).toString.toInt).
      bodyForm(
        form.
          add("owner", user).
          add("sql", sql).
          add("sessionPerUser", "true").
          add("includeSchema", "true").
          build()
      ).execute().returnContent().asString()
    respJson
  }


}
