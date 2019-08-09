import java.io.File
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SaveMode, SparkSession}

object MainApp {

    def main(args: Array[String]): Unit = {

        println("Spark job started ......")
        val dir = new File("spark-warehouse").getAbsolutePath()
        val conf = new SparkConf()
        conf.setMaster("local")
        conf.set("spark.sql.warehouse.dir", dir)

        val sc = new SparkSession.Builder().appName("sparkHive").config(conf).enableHiveSupport().getOrCreate()
        val crimesDF = sc.read.option("inferSchema", "true").option("header", "true").csv("hdfs:///user/spark/crime.csv")
        println("*************************************************************************************************************")
        crimesDF.printSchema()

        println("*************************************************************************************************************")
        val offenseDF = sc.read.option("inferSchema", "true").option("header", "true").csv("hdfs:///user/spark/offense_codes.csv")
        offenseDF.printSchema()

        println("*************************************************************************************************************")
        val records = crimesDF.join(offenseDF, crimesDF("OFFENSE_CODE") === offenseDF("CODE"))
        records.printSchema()
        records.createOrReplaceTempView("records")

        val recordsDF = sc.sql("SELECT NAME, STREET, COUNT(*) as number FROM records  group by NAME, STREET")

        sc.sql("CREATE TABLE IF NOT EXISTS hive_records(NAME string, STREET string,number int) USING hive")
        recordsDF.write.mode(SaveMode.Overwrite).saveAsTable("default.hive_records")

        val jsonDS  = sc.sql("SELECT * FROM hive_records").toJSON
        jsonDS.write.mode(SaveMode.Overwrite).json("hdfs:///user/spark/export-hive-records.json")
        println("Spark job done.")

    }
}

