package tech.mlsql.api.jdbc.util

import java.net.URI
import java.util.Properties

import tech.mlsql.api.jdbc.MLSQLConst

import scala.collection.JavaConverters._

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
object MLSQLDriverUtil {

  def parseMergeProperties(url: String, prop: Properties = new Properties()) = {
    val uri = new URI(url.substring("jdbc:".length))
    val uriPath = if(uri.getPath==null) "" else uri.getPath
    var maps = Map(
      MLSQLConst.PROP_HOST -> uri.getHost,
      MLSQLConst.PROP_PORT -> (if (uri.getPort == -1) MLSQLConst.DEFAULT_PORT else uri.getPort).toString,
      MLSQLConst.PROP_PATH -> uriPath.replaceFirst("/", "")
    )

    if (uri.getQuery != null) {
      maps ++= uri.getQuery.split("&").map { p => p.split("=") }.map(p => (p(0), p(1))).toMap
    }

    if (prop != null) {
      maps ++= prop.entrySet().asScala.map { item =>
        (item.getKey.toString, item.getValue.toString)
      }.toMap
    }
    maps

  }


}
