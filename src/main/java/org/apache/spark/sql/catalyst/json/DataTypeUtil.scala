//package org.apache.spark.sql.catalyst.json
//
//import org.apache.spark.sql.execution.datasources.FailureSafeParser
//import org.apache.spark.sql.types.{DataType, StructType}
//import org.apache.spark.unsafe.types.UTF8String
//
///**
// * 12/6/2020 WilliamZhu(allwefantasy@gmail.com)
// */
//object DataTypeUtil {
//  def convertToResultSet(json: Seq[String], schemaStr: String) = {
//    val schema = DataType.fromJson(schemaStr).asInstanceOf[StructType]
//    val parsedOptions = new JSONOptions(
//      Map[String, String](),
//      "alias/harbin",
//      "_corrupt_record")
//
//    val createParser = CreateJacksonParser.string _
//    val rawParser = new JacksonParser(schema, parsedOptions)
//    val parser = new FailureSafeParser[String](
//      input => rawParser.parse(input, createParser, UTF8String.fromString),
//      parsedOptions.parseMode,
//      schema,
//      parsedOptions.columnNameOfCorruptRecord)
//    val parsed = json.map { row =>
//      parser.parse(row).next()
//    }
//    (parsed, schema)
//
//  }
//
//
//}
