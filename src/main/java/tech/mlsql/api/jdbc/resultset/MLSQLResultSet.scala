package tech.mlsql.api.jdbc.resultset

import java.io.{InputStream, Reader}
import java.net.URL
import java.sql.{Blob, Clob, Date, NClob, Ref, ResultSet, ResultSetMetaData, RowId, SQLException, SQLWarning, SQLXML, Statement, Time, Timestamp}
import java.util.Calendar
import java.{sql, util}

import net.sf.json.JSONObject

import scala.collection.JavaConverters._

/**
 * 12/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class MLSQLResultSet(dataWithSchema: JSONObject, metaData: ResultSetMetaData) extends ResultSet {

  private val schema = dataWithSchema.getJSONObject("schema").getJSONArray("fields")
  private val fields = schema.asScala.map(_.asInstanceOf[JSONObject].getString("name")).toList
  private val data = dataWithSchema.getJSONArray("data")
  private var pos = -1
  private var _isClosed = false

  override def next(): Boolean = {
    pos += 1
    data.size() > pos
  }

  override def close(): Unit = {
    _isClosed = true
  }

  override def wasNull(): Boolean = {
    getRowObj == null
  }

  private def getRowObj = {
    data.getJSONObject(pos)
  }

  override def getString(columnIndex: Int): String = getRowObj.getString(fields(columnIndex))

  override def getBoolean(columnIndex: Int): Boolean = getRowObj.getBoolean(fields(columnIndex))

  override def getByte(columnIndex: Int): Byte = ???

  override def getShort(columnIndex: Int): Short = getRowObj.getInt(fields(columnIndex)).toShort

  override def getInt(columnIndex: Int): Int = getRowObj.getInt(fields(columnIndex))

  override def getLong(columnIndex: Int): Long = getRowObj.getLong(fields(columnIndex))

  override def getFloat(columnIndex: Int): Float = getRowObj.getDouble(fields(columnIndex)).toFloat

  override def getDouble(columnIndex: Int): Double = getRowObj.getDouble(fields(columnIndex))

  override def getBigDecimal(columnIndex: Int, scale: Int): java.math.BigDecimal = new java.math.BigDecimal(getRowObj.getDouble(fields(columnIndex))).setScale(scale)

  override def getBytes(columnIndex: Int): Array[Byte] = getString(columnIndex).getBytes

  override def getDate(columnIndex: Int): Date = Date.valueOf(getString(columnIndex))

  override def getTime(columnIndex: Int): Time = Time.valueOf(getString(columnIndex))

  override def getTimestamp(columnIndex: Int): Timestamp = Timestamp.valueOf(getString(columnIndex))

  override def getAsciiStream(columnIndex: Int): InputStream = ???

  override def getUnicodeStream(columnIndex: Int): InputStream = ???

  override def getBinaryStream(columnIndex: Int): InputStream = ???

  override def getString(columnLabel: String): String = getString(fields.indexOf(columnLabel))

  override def getBoolean(columnLabel: String): Boolean = getBoolean(fields.indexOf(columnLabel))

  override def getByte(columnLabel: String): Byte = getByte(fields.indexOf(columnLabel))

  override def getShort(columnLabel: String): Short = getShort(fields.indexOf(columnLabel))

  override def getInt(columnLabel: String): Int = getInt(fields.indexOf(columnLabel))

  override def getLong(columnLabel: String): Long = getLong(fields.indexOf(columnLabel))

  override def getFloat(columnLabel: String): Float = getFloat(fields.indexOf(columnLabel))

  override def getDouble(columnLabel: String): Double = getDouble(fields.indexOf(columnLabel))

  override def getBigDecimal(columnLabel: String, scale: Int): java.math.BigDecimal = getBigDecimal(fields.indexOf(columnLabel))

  override def getBytes(columnLabel: String): Array[Byte] = getBytes(fields.indexOf(columnLabel))

  override def getDate(columnLabel: String): Date = getDate(fields.indexOf(columnLabel))

  override def getTime(columnLabel: String): Time = getTime(fields.indexOf(columnLabel))

  override def getTimestamp(columnLabel: String): Timestamp = getTimestamp(fields.indexOf(columnLabel))

  override def getAsciiStream(columnLabel: String): InputStream = getAsciiStream(fields.indexOf(columnLabel))

  override def getUnicodeStream(columnLabel: String): InputStream = getUnicodeStream(fields.indexOf(columnLabel))

  override def getBinaryStream(columnLabel: String): InputStream = getBinaryStream(fields.indexOf(columnLabel))

  override def getWarnings: SQLWarning = null

  override def clearWarnings(): Unit = {}

  override def getCursorName: String = ???

  override def getMetaData: ResultSetMetaData = {
    metaData
  }

  override def getObject(columnIndex: Int): AnyRef = {
    getRowObj.get(columnIndex)
  }

  override def getObject(columnLabel: String): AnyRef = getObject(fields.indexOf(columnLabel))

  override def findColumn(columnLabel: String): Int = {
    fields.indexOf(columnLabel)
  }

  override def getCharacterStream(columnIndex: Int): Reader = ???

  override def getCharacterStream(columnLabel: String): Reader = ???

  override def getBigDecimal(columnIndex: Int): java.math.BigDecimal = getBigDecimal(columnIndex, 0)

  override def getBigDecimal(columnLabel: String): java.math.BigDecimal = getBigDecimal(fields.indexOf(columnLabel))

  override def isBeforeFirst: Boolean = {
    checkIfClosed()
    pos < 0
  }

  private def checkIfClosed() {
    if (isClosed()) throw new SQLException("ResultSet is already closed")
  }

  override def isAfterLast: Boolean = {
    checkIfClosed()
    pos >= data.size()
  }

  override def isFirst: Boolean = {
    checkIfClosed()
    return pos == 0
  }

  override def isLast: Boolean = {
    checkIfClosed()
    return pos == data.size() - 1
  }

  override def beforeFirst(): Unit = {
    checkIfClosed()
    pos = -1
  }

  override def afterLast(): Unit = {
    checkIfClosed()
    pos = data.size
  }

  override def first(): Boolean = {
    checkIfClosed()
    pos = 0
    !data.isEmpty
  }

  override def last(): Boolean = {
    checkIfClosed()
    pos = data.size() - 1
    !data.isEmpty
  }

  override def getRow: Int = {
    pos + 1
  }

  override def absolute(row: Int): Boolean = {
    checkIfClosed()
    if (row >= 1 && row < data.size()) {
      pos = row
      true
    } else false
  }

  override def relative(rows: Int): Boolean = {
    checkIfClosed
    val temp = rows + pos
    if (temp >= 1 && temp < data.size()) {
      pos += rows
      true
    } else false
  }

  override def previous(): Boolean = {
    checkIfClosed()
    if (pos > 1) {
      pos -= 1
      true
    } else false

  }

  override def setFetchDirection(direction: Int): Unit = ???

  override def getFetchDirection: Int = ???

  override def setFetchSize(rows: Int): Unit = ???

  override def getFetchSize: Int = data.size()

  override def getType: Int = ResultSet.TYPE_SCROLL_INSENSITIVE

  override def getConcurrency: Int = ResultSet.CONCUR_READ_ONLY

  override def rowUpdated(): Boolean = false

  override def rowInserted(): Boolean = false

  override def rowDeleted(): Boolean = false

  override def updateNull(columnIndex: Int): Unit = ???

  override def updateBoolean(columnIndex: Int, x: Boolean): Unit = ???

  override def updateByte(columnIndex: Int, x: Byte): Unit = ???

  override def updateShort(columnIndex: Int, x: Short): Unit = ???

  override def updateInt(columnIndex: Int, x: Int): Unit = ???

  override def updateLong(columnIndex: Int, x: Long): Unit = ???

  override def updateFloat(columnIndex: Int, x: Float): Unit = ???

  override def updateDouble(columnIndex: Int, x: Double): Unit = ???

  override def updateBigDecimal(columnIndex: Int, x: java.math.BigDecimal): Unit = ???

  override def updateString(columnIndex: Int, x: String): Unit = ???

  override def updateBytes(columnIndex: Int, x: Array[Byte]): Unit = ???

  override def updateDate(columnIndex: Int, x: Date): Unit = ???

  override def updateTime(columnIndex: Int, x: Time): Unit = ???

  override def updateTimestamp(columnIndex: Int, x: Timestamp): Unit = ???

  override def updateAsciiStream(columnIndex: Int, x: InputStream, length: Int): Unit = ???

  override def updateBinaryStream(columnIndex: Int, x: InputStream, length: Int): Unit = ???

  override def updateCharacterStream(columnIndex: Int, x: Reader, length: Int): Unit = ???

  override def updateObject(columnIndex: Int, x: Any, scaleOrLength: Int): Unit = ???

  override def updateObject(columnIndex: Int, x: Any): Unit = ???

  override def updateNull(columnLabel: String): Unit = ???

  override def updateBoolean(columnLabel: String, x: Boolean): Unit = ???

  override def updateByte(columnLabel: String, x: Byte): Unit = ???

  override def updateShort(columnLabel: String, x: Short): Unit = ???

  override def updateInt(columnLabel: String, x: Int): Unit = ???

  override def updateLong(columnLabel: String, x: Long): Unit = ???

  override def updateFloat(columnLabel: String, x: Float): Unit = ???

  override def updateDouble(columnLabel: String, x: Double): Unit = ???

  override def updateBigDecimal(columnLabel: String, x: java.math.BigDecimal): Unit = ???

  override def updateString(columnLabel: String, x: String): Unit = ???

  override def updateBytes(columnLabel: String, x: Array[Byte]): Unit = ???

  override def updateDate(columnLabel: String, x: Date): Unit = ???

  override def updateTime(columnLabel: String, x: Time): Unit = ???

  override def updateTimestamp(columnLabel: String, x: Timestamp): Unit = ???

  override def updateAsciiStream(columnLabel: String, x: InputStream, length: Int): Unit = ???

  override def updateBinaryStream(columnLabel: String, x: InputStream, length: Int): Unit = ???

  override def updateCharacterStream(columnLabel: String, reader: Reader, length: Int): Unit = ???

  override def updateObject(columnLabel: String, x: Any, scaleOrLength: Int): Unit = ???

  override def updateObject(columnLabel: String, x: Any): Unit = ???

  override def insertRow(): Unit = ???

  override def updateRow(): Unit = ???

  override def deleteRow(): Unit = ???

  override def refreshRow(): Unit = ???

  override def cancelRowUpdates(): Unit = ???

  override def moveToInsertRow(): Unit = ???

  override def moveToCurrentRow(): Unit = ???

  override def getStatement: Statement = ???

  override def getObject(columnIndex: Int, map: (util.Map[String, Class[_]])): AnyRef = ???

  override def getRef(columnIndex: Int): Ref = ???

  override def getBlob(columnIndex: Int): Blob = ???

  override def getClob(columnIndex: Int): Clob = ???

  override def getArray(columnIndex: Int): sql.Array = {
    //    val items = getRowObj.getJSONArray(fields(columnIndex))
    //    val objs = new Array[Object](items.size())
    //    items.asScala.zipWithIndex.foreach { case (item, index) =>
    //      objs(index) = item.asInstanceOf[Object]
    //    }
    //    new SerialArray(objs)
    ???
  }

  override def getObject(columnLabel: String, map: (util.Map[String, Class[_]])): AnyRef = ???

  override def getRef(columnLabel: String): Ref = ???

  override def getBlob(columnLabel: String): Blob = ???

  override def getClob(columnLabel: String): Clob = ???

  override def getArray(columnLabel: String): sql.Array = getArray(fields.indexOf(columnLabel))

  override def getDate(columnIndex: Int, cal: Calendar): Date = getDate(columnIndex)

  override def getDate(columnLabel: String, cal: Calendar): Date = getDate(fields.indexOf(columnLabel))

  override def getTime(columnIndex: Int, cal: Calendar): Time = getTime(columnIndex)

  override def getTime(columnLabel: String, cal: Calendar): Time = getTime(columnLabel)

  override def getTimestamp(columnIndex: Int, cal: Calendar): Timestamp = getTimestamp(columnIndex)

  override def getTimestamp(columnLabel: String, cal: Calendar): Timestamp = getTimestamp(columnLabel)

  override def getURL(columnIndex: Int): URL = ???

  override def getURL(columnLabel: String): URL = ???

  override def updateRef(columnIndex: Int, x: Ref): Unit = ???

  override def updateRef(columnLabel: String, x: Ref): Unit = ???

  override def updateBlob(columnIndex: Int, x: Blob): Unit = ???

  override def updateBlob(columnLabel: String, x: Blob): Unit = ???

  override def updateClob(columnIndex: Int, x: Clob): Unit = ???

  override def updateClob(columnLabel: String, x: Clob): Unit = ???

  override def updateArray(columnIndex: Int, x: sql.Array): Unit = ???

  override def updateArray(columnLabel: String, x: sql.Array): Unit = ???

  override def getRowId(columnIndex: Int): RowId = ???

  override def getRowId(columnLabel: String): RowId = ???

  override def updateRowId(columnIndex: Int, x: RowId): Unit = ???

  override def updateRowId(columnLabel: String, x: RowId): Unit = ???

  override def getHoldability: Int = ???

  override def isClosed: Boolean = _isClosed

  override def updateNString(columnIndex: Int, nString: String): Unit = ???

  override def updateNString(columnLabel: String, nString: String): Unit = ???

  override def updateNClob(columnIndex: Int, nClob: NClob): Unit = ???

  override def updateNClob(columnLabel: String, nClob: NClob): Unit = ???

  override def getNClob(columnIndex: Int): NClob = ???

  override def getNClob(columnLabel: String): NClob = ???

  override def getSQLXML(columnIndex: Int): SQLXML = ???

  override def getSQLXML(columnLabel: String): SQLXML = ???

  override def updateSQLXML(columnIndex: Int, xmlObject: SQLXML): Unit = ???

  override def updateSQLXML(columnLabel: String, xmlObject: SQLXML): Unit = ???

  override def getNString(columnIndex: Int): String = ???

  override def getNString(columnLabel: String): String = ???

  override def getNCharacterStream(columnIndex: Int): Reader = ???

  override def getNCharacterStream(columnLabel: String): Reader = ???

  override def updateNCharacterStream(columnIndex: Int, x: Reader, length: Long): Unit = ???

  override def updateNCharacterStream(columnLabel: String, reader: Reader, length: Long): Unit = ???

  override def updateAsciiStream(columnIndex: Int, x: InputStream, length: Long): Unit = ???

  override def updateBinaryStream(columnIndex: Int, x: InputStream, length: Long): Unit = ???

  override def updateCharacterStream(columnIndex: Int, x: Reader, length: Long): Unit = ???

  override def updateAsciiStream(columnLabel: String, x: InputStream, length: Long): Unit = ???

  override def updateBinaryStream(columnLabel: String, x: InputStream, length: Long): Unit = ???

  override def updateCharacterStream(columnLabel: String, reader: Reader, length: Long): Unit = ???

  override def updateBlob(columnIndex: Int, inputStream: InputStream, length: Long): Unit = ???

  override def updateBlob(columnLabel: String, inputStream: InputStream, length: Long): Unit = ???

  override def updateClob(columnIndex: Int, reader: Reader, length: Long): Unit = ???

  override def updateClob(columnLabel: String, reader: Reader, length: Long): Unit = ???

  override def updateNClob(columnIndex: Int, reader: Reader, length: Long): Unit = ???

  override def updateNClob(columnLabel: String, reader: Reader, length: Long): Unit = ???

  override def updateNCharacterStream(columnIndex: Int, x: Reader): Unit = ???

  override def updateNCharacterStream(columnLabel: String, reader: Reader): Unit = ???

  override def updateAsciiStream(columnIndex: Int, x: InputStream): Unit = ???

  override def updateBinaryStream(columnIndex: Int, x: InputStream): Unit = ???

  override def updateCharacterStream(columnIndex: Int, x: Reader): Unit = ???

  override def updateAsciiStream(columnLabel: String, x: InputStream): Unit = ???

  override def updateBinaryStream(columnLabel: String, x: InputStream): Unit = ???

  override def updateCharacterStream(columnLabel: String, reader: Reader): Unit = ???

  override def updateBlob(columnIndex: Int, inputStream: InputStream): Unit = ???

  override def updateBlob(columnLabel: String, inputStream: InputStream): Unit = ???

  override def updateClob(columnIndex: Int, reader: Reader): Unit = ???

  override def updateClob(columnLabel: String, reader: Reader): Unit = ???

  override def updateNClob(columnIndex: Int, reader: Reader): Unit = ???

  override def updateNClob(columnLabel: String, reader: Reader): Unit = ???

  override def getObject[T](columnIndex: Int, `type`: Class[T]): T = ???

  override def getObject[T](columnLabel: String, `type`: Class[T]): T = ???

  override def unwrap[T](iface: Class[T]): T = ???

  override def isWrapperFor(iface: Class[_]): Boolean = ???
}
