package tech.mlsql.api.jdbc.metadata

import java.sql.{ResultSetMetaData, Types}

import net.sf.json.JSONArray


/**
 * 12/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class MLSQLResultSetMetaData(schema: JSONArray) extends ResultSetMetaData {
  override def getColumnCount: Int = {
    schema.size()
  }

  override def isAutoIncrement(column: Int): Boolean = false

  override def isCaseSensitive(column: Int): Boolean = true

  override def isSearchable(column: Int): Boolean = false

  override def isCurrency(column: Int): Boolean = false

  override def isNullable(column: Int): Int = {
    if (schema.getJSONObject(column).getBoolean("nullable")) ResultSetMetaData.columnNullable
    else ResultSetMetaData.columnNoNulls
  }

  override def isSigned(column: Int): Boolean = {
    false
  }

  override def getColumnDisplaySize(column: Int): Int = ???

  override def getColumnLabel(column: Int): String = {
    schema.getJSONObject(column).getString("name")
  }

  override def getColumnName(column: Int): String = {
    schema.getJSONObject(column).getString("name")
  }

  override def getSchemaName(column: Int): String = "default"

  override def getPrecision(column: Int): Int = 0

  override def getScale(column: Int): Int = 0

  override def getTableName(column: Int): String = "default"

  override def getCatalogName(column: Int): String = "default"

  override def getColumnType(column: Int): Int = {
    schema.getJSONObject(column).getString("type") match {
      case "integer" => Types.INTEGER
      case "double" => Types.DOUBLE
      case "long" => Types.BIGINT
      case "float" => Types.FLOAT
      case "short" => Types.SMALLINT
      case "byte" => Types.TINYINT
      case "boolean" => Types.BIT
      case "string" => Types.CLOB
      case "binary" => Types.BLOB
      case "timestamp" => Types.TIMESTAMP
      case "date" => Types.DATE
      case "decimal" => Types.DECIMAL
    }
  }

  override def getColumnTypeName(column: Int): String = schema.getJSONObject(column).getString("type")

  override def isReadOnly(column: Int): Boolean = true

  override def isWritable(column: Int): Boolean = false

  override def isDefinitelyWritable(column: Int): Boolean = false

  override def getColumnClassName(column: Int): String = {
    schema.getJSONObject(column).getString("type") match {
      case "integer" => "java.lang.Integer"
      case "double" => "java.lang.Double"
      case "long" => "java.lang.Long"
      case "float" => "java.lang.Float"
      case "short" => "java.lang.Short"
      case "byte" => "java.lang.Byte"
      case "boolean" => "java.lang.Boolean"
      case "string" => "java.lang.String"
      case "binary" => "java.lang.Array[java.lang.Byte]"
      case "timestamp" => "java.sql.Timestamp"
      case "date" => "java.util.Date"
      case "decimal" => "java.math.BigDecimal"
    }
  }

  override def unwrap[T](iface: Class[T]): T = ???

  override def isWrapperFor(iface: Class[_]): Boolean = ???
}
