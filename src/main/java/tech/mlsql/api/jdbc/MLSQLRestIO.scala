package tech.mlsql.api.jdbc

import net.sf.json.JSONObject
import org.apache.http.client.fluent.{Form, Request}

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
object MLSQLRestIO {
  def internalExecuteQuery(sql: String, user: String, properties: Map[String, String]) = {

    val respJson = Request.Post(s"http://${properties(MLSQLConst.PROP_HOST)}:${properties(MLSQLConst.PROP_PORT)}/run/script").
      connectTimeout(1000 * 60 * 60).
      socketTimeout(1000 * 60 * 60 * 60).
      bodyForm(
        Form.form().
          add("owner", user).
          add("sql", sql).
          add("sessionPerUser", "true").
          add("includeSchema", "true").
          build()
      ).execute().returnContent().asString()

    //{ "schema":{"type":"struct","fields":[{"name":"a","type":"integer","nullable":false,"metadata":{}}]},"data": [{"a":1}]}
    val resp = JSONObject.fromObject(respJson)
    //DataTypeUtil.convertToResultSet(resp.getJSONArray("data").asScala.map(_.toString), resp.getJSONObject("schema").toString)

    //    new MLSQLResultSet(resp)
    respJson
  }


}
