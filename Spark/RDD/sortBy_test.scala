package RDD

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object sortBy_test {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val context: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = context.makeRDD(List(1, 2, 3, 4,22,11,32,16,4234), 2)
    val newRdd: RDD[Int] = rdd.sortBy(num => num)

    newRdd.collect().foreach(println)
  }
}
