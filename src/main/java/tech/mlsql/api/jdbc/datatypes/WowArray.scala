package tech.mlsql.api.jdbc.datatypes

import java.sql.ResultSet
import java.util

/**
 * 12/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class WowArray extends java.sql.Array {
  override def getBaseTypeName: String = ???

  override def getBaseType: Int = ???

  override def getArray: AnyRef = ???

  override def getArray(map: (util.Map[String, Class[_]])): AnyRef = ???

  override def getArray(index: Long, count: Int): AnyRef = ???

  override def getArray(index: Long, count: Int, map: (util.Map[String, Class[_]])): AnyRef = ???

  override def getResultSet: ResultSet = ???

  override def getResultSet(map: (util.Map[String, Class[_]])): ResultSet = ???

  override def getResultSet(index: Long, count: Int): ResultSet = ???

  override def getResultSet(index: Long, count: Int, map: (util.Map[String, Class[_]])): ResultSet = ???

  override def free(): Unit = ???
}
