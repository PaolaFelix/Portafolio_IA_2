// Databricks notebook source
// MAGIC %fs ls /databricks-datasets/structured-streaming/events/

// COMMAND ----------

// MAGIC %fs mkdir FileStore/tables/test/

// COMMAND ----------

dbutils.fs.cp("/databricks-datasets/structured-streaming/events/", "/FileStore/tables/streaming2/", recurse=true )

// COMMAND ----------

// MAGIC %fs ls /FileStore/tables/streaming2/

// COMMAND ----------

// MAGIC %fs rm /FileStore/tables/streaming/file_new

// COMMAND ----------

// MAGIC %fs head /FileStore/tables/streaming2/file-0.json

// COMMAND ----------

val list1: List[String] = "clase" :: "de" :: "Streaming" :: Nil
val list2: List[String] = List("clase", "de", "Streaming")
list1 == list2

// COMMAND ----------

val list1: List[String] = "clase" :: "de" :: "Streaming"

// COMMAND ----------

//StructType
//import org.apache.spark.sql.types._ // _ == *
import org.apache.spark.sql.types.TimestampType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField

val jsonSchema0 = StructType(
StructField("time", TimestampType) ::
StructField("action", StringType) ::
Nil
)

val jsonSchema = new StructType()
        .add("time", TimestampType)
        .add("action", StringType)

val jsonSchema1 = new StructType()
                                  .add("time", TimestampType)
                                  .add("action", StringType)

// COMMAND ----------

val inputPath = "/FileStore/tables/streaming2/"

// COMMAND ----------

spark

// COMMAND ----------

val staticInputDF = (
  spark
    .read
    .schema(jsonSchema)
    .json(inputPath)
)

// COMMAND ----------

display(staticInputDF)

// COMMAND ----------

staticInputDF.printSchema

// COMMAND ----------

staticInputDF.count

// COMMAND ----------

staticInputDF.columns

// COMMAND ----------

import org.apache.spark.sql.functions.window
import org.apache.spark.sql.functions.col

val staticCountDF =
  staticInputDF
      .groupBy(col("action"), window(col("time"), "1 hour"))
      .count()

// COMMAND ----------

staticCountDF.createOrReplaceTempView("static_counts")

// COMMAND ----------

// MAGIC %sql select count(1) from static_counts

// COMMAND ----------

// MAGIC %sql select * from static_counts order by count desc

// COMMAND ----------

// MAGIC %sql select action, date_format(window.end, "MMM-dd HH:mm") as time, 
// MAGIC count from static_counts order by time, action

// COMMAND ----------

// MAGIC %md
// MAGIC streaming

// COMMAND ----------

val streamingInputDF = (
  spark 
    .readStream
    .schema(jsonSchema)
    .option("maxFilesPerTrigger", 1)
    .json(inputPath)
)

// COMMAND ----------

val streamingCountDF =
  streamingInputDF
    .groupBy(col("action"), window(col("time"), "1 hour"))
    .count()

// COMMAND ----------

streamingCountDF.isStreaming

// COMMAND ----------

spark.conf.set("spark.sql.suffle.partitions", "1")

val query =
streamingCountDF
  .writeStream
  .format("memory")
  .queryName("counts_streaming")
  .outputMode("complete")
  

// COMMAND ----------

query.show(10,true)

// COMMAND ----------

spark.

// COMMAND ----------

val.queryHandler = query.start
