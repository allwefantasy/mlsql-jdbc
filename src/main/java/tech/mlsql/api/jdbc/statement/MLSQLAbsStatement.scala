package tech.mlsql.api.jdbc.statement

import java.sql.{ResultSet, SQLException}

import tech.mlsql.api.jdbc.MLSQLConnection

/**
 * 11/6/2020 WilliamZhu(allwefantasy@gmail.com)
 */
class MLSQLAbsStatement(val _sql: String, _conn: MLSQLConnection) {

  protected var _isClosed = false
  protected var _resultSet: ResultSet = _

  def executeForResultSet(sql: String) = {
    if (_isClosed) throw new SQLException("This statement is closed.")
    try {
      true
    }
    catch {
      case e: Throwable => throw new SQLException(e)
    }
  }
}
