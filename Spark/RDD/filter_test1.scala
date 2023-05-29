package RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object filter_test1 {
  def main(args: Array[String]): Unit = {
    val operator: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val context: SparkContext = new SparkContext(operator)

    val rdd=context.makeRDD(List(1,2,4,5,23,12))

    val value: RDD[Int] = rdd.filter(num => num % 2 == 0) //只留下偶数
    value.collect().foreach(println)

  }
}
