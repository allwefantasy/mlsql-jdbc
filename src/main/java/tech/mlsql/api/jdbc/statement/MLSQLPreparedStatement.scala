package tech.mlsql.api.jdbc.statement

import java.io.{InputStream, Reader}
import java.net.URL
import java.sql
import java.sql.{Blob, Clob, Connection, Date, NClob, ParameterMetaData, PreparedStatement, Ref, ResultSet, ResultSetMetaData, RowId, SQLWarning, SQLXML, Time, Timestamp}
import java.util.Calendar

import net.sf.json.JSONObject
import tech.mlsql.api.jdbc.metadata.MLSQLResultSetMetaData
import tech.mlsql.api.jdbc.resultset.MLSQLResultSet
import tech.mlsql.api.jdbc.{MLSQLConnection, MLSQLConst, MLSQLRestIO}

import scala.collection.mutable

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class MLSQLPreparedStatement(_sql: String, _conn: MLSQLConnection) extends MLSQLAbsStatement(_sql, _conn) with PreparedStatement {
  private val parameters = mutable.HashMap()

  override def execute(): Boolean = ???

  override def executeQuery(): ResultSet = {
    val respJsonStr = MLSQLRestIO.internalExecuteQuery(_sql, _conn.props(MLSQLConst.PROP_USER), _conn.props)
    val dataWithSchema = JSONObject.fromObject(respJsonStr)
    val schema = dataWithSchema.getJSONObject("schema")
    val meta = new MLSQLResultSetMetaData(schema.getJSONArray("fields"))
    val rs = new MLSQLResultSet(dataWithSchema, meta,_conn)
    rs
  }

  override def executeUpdate(): Int = ???

  override def setNull(parameterIndex: Int, sqlType: Int): Unit = ???

  override def setBoolean(parameterIndex: Int, x: Boolean): Unit = ???

  override def setByte(parameterIndex: Int, x: Byte): Unit = ???

  override def setShort(parameterIndex: Int, x: Short): Unit = ???

  override def setInt(parameterIndex: Int, x: Int): Unit = ???

  override def setLong(parameterIndex: Int, x: Long): Unit = ???

  override def setFloat(parameterIndex: Int, x: Float): Unit = ???

  override def setDouble(parameterIndex: Int, x: Double): Unit = ???

  override def setBigDecimal(parameterIndex: Int, x: java.math.BigDecimal): Unit = ???

  override def setString(parameterIndex: Int, x: String): Unit = ???

  override def setBytes(parameterIndex: Int, x: Array[Byte]): Unit = ???

  override def setDate(parameterIndex: Int, x: Date): Unit = ???

  override def setTime(parameterIndex: Int, x: Time): Unit = ???

  override def setTimestamp(parameterIndex: Int, x: Timestamp): Unit = ???

  override def setAsciiStream(parameterIndex: Int, x: InputStream, length: Int): Unit = ???

  override def setUnicodeStream(parameterIndex: Int, x: InputStream, length: Int): Unit = ???

  override def setBinaryStream(parameterIndex: Int, x: InputStream, length: Int): Unit = ???

  override def clearParameters(): Unit = ???

  override def setObject(parameterIndex: Int, x: Any, targetSqlType: Int): Unit = ???

  override def setObject(parameterIndex: Int, x: Any): Unit = ???


  override def addBatch(): Unit = ???

  override def setCharacterStream(parameterIndex: Int, reader: Reader, length: Int): Unit = ???

  override def setRef(parameterIndex: Int, x: Ref): Unit = ???

  override def setBlob(parameterIndex: Int, x: Blob): Unit = ???

  override def setClob(parameterIndex: Int, x: Clob): Unit = ???

  override def setArray(parameterIndex: Int, x: sql.Array): Unit = ???

  override def getMetaData: ResultSetMetaData = ???

  override def setDate(parameterIndex: Int, x: Date, cal: Calendar): Unit = ???

  override def setTime(parameterIndex: Int, x: Time, cal: Calendar): Unit = ???

  override def setTimestamp(parameterIndex: Int, x: Timestamp, cal: Calendar): Unit = ???

  override def setNull(parameterIndex: Int, sqlType: Int, typeName: String): Unit = ???

  override def setURL(parameterIndex: Int, x: URL): Unit = ???

  override def getParameterMetaData: ParameterMetaData = ???

  override def setRowId(parameterIndex: Int, x: RowId): Unit = ???

  override def setNString(parameterIndex: Int, value: String): Unit = ???

  override def setNCharacterStream(parameterIndex: Int, value: Reader, length: Long): Unit = ???

  override def setNClob(parameterIndex: Int, value: NClob): Unit = ???

  override def setClob(parameterIndex: Int, reader: Reader, length: Long): Unit = ???

  override def setBlob(parameterIndex: Int, inputStream: InputStream, length: Long): Unit = ???

  override def setNClob(parameterIndex: Int, reader: Reader, length: Long): Unit = ???

  override def setSQLXML(parameterIndex: Int, xmlObject: SQLXML): Unit = ???

  override def setObject(parameterIndex: Int, x: Any, targetSqlType: Int, scaleOrLength: Int): Unit = ???

  override def setAsciiStream(parameterIndex: Int, x: InputStream, length: Long): Unit = ???

  override def setBinaryStream(parameterIndex: Int, x: InputStream, length: Long): Unit = ???

  override def setCharacterStream(parameterIndex: Int, reader: Reader, length: Long): Unit = ???

  override def setAsciiStream(parameterIndex: Int, x: InputStream): Unit = ???

  override def setBinaryStream(parameterIndex: Int, x: InputStream): Unit = ???

  override def setCharacterStream(parameterIndex: Int, reader: Reader): Unit = ???

  override def setNCharacterStream(parameterIndex: Int, value: Reader): Unit = ???

  override def setClob(parameterIndex: Int, reader: Reader): Unit = ???

  override def setBlob(parameterIndex: Int, inputStream: InputStream): Unit = ???

  override def setNClob(parameterIndex: Int, reader: Reader): Unit = ???

  override def executeQuery(sql: String): ResultSet = ???

  override def executeUpdate(sql: String): Int = ???

  override def close(): Unit = {
     _isClosed = true
  }

  override def getMaxFieldSize: Int = ???

  override def setMaxFieldSize(max: Int): Unit = ???

  override def getMaxRows: Int = ???

  override def setMaxRows(max: Int): Unit = ???

  override def setEscapeProcessing(enable: Boolean): Unit = ???

  override def getQueryTimeout: Int = ???

  override def setQueryTimeout(seconds: Int): Unit = ???

  override def cancel(): Unit = ???

  override def getWarnings: SQLWarning = ???

  override def clearWarnings(): Unit = ???

  override def setCursorName(name: String): Unit = ???

  override def execute(sql: String): Boolean = ???

  override def getResultSet: ResultSet = ???

  override def getUpdateCount: Int = ???

  override def getMoreResults: Boolean = ???

  override def setFetchDirection(direction: Int): Unit = ???

  override def getFetchDirection: Int = ???

  override def setFetchSize(rows: Int): Unit = ???

  override def getFetchSize: Int = ???

  override def getResultSetConcurrency: Int = ???

  override def getResultSetType: Int = ???

  override def addBatch(sql: String): Unit = ???

  override def clearBatch(): Unit = ???

  override def executeBatch(): Array[Int] = ???

  override def getConnection: Connection = ???

  override def getMoreResults(current: Int): Boolean = ???

  override def getGeneratedKeys: ResultSet = ???

  override def executeUpdate(sql: String, autoGeneratedKeys: Int): Int = ???

  override def executeUpdate(sql: String, columnIndexes: Array[Int]): Int = ???

  override def executeUpdate(sql: String, columnNames: Array[String]): Int = ???

  override def execute(sql: String, autoGeneratedKeys: Int): Boolean = ???

  override def execute(sql: String, columnIndexes: Array[Int]): Boolean = ???

  override def execute(sql: String, columnNames: Array[String]): Boolean = ???

  override def getResultSetHoldability: Int = ???

  override def isClosed: Boolean = _isClosed

  override def setPoolable(poolable: Boolean): Unit = ???

  override def isPoolable: Boolean = ???

  override def closeOnCompletion(): Unit = ???

  override def isCloseOnCompletion: Boolean = ???

  override def unwrap[T](iface: Class[T]): T = ???

  override def isWrapperFor(iface: Class[_]): Boolean = ???
}
