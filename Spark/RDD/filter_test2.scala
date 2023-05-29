package RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object filter_test2 {
  def main(args: Array[String]): Unit = {
    val filter: SparkConf = new SparkConf().setMaster("local[*]").setAppName("filter")
    val context: SparkContext = new SparkContext(filter)

    val value: RDD[String] = context.textFile("C:\\JavaCode\\Spark\\src\\main\\resources\\(sample)sam_tianchi_2014001_rec_tmall_product.csv")

    val value1: RDD[String] = value.filter(
      line => {
        val strings: Array[String] = line.split(",")
        strings(2).length > 80
      }
    )

    println(value1.collect().length)
  }
}
