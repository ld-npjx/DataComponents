package Practise

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test_1 {
  def main(args: Array[String]): Unit = {
    //top10 热门商品id
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("TOP")
    val context: SparkContext = new SparkContext(sparkConf)
    val Rdd: RDD[String] = context.textFile("C:\\JavaCode\\Spark\\src\\main\\resources\\user_action.csv")

    val RddMap: RDD[(String, Int)] = Rdd.map(s => {
      var datas = s.split(",")
      (datas(1), 1)
    })
    val reduceRdd: RDD[(String, Int)] = RddMap.reduceByKey(_ + _)
    //降序top10
    val tuples: Array[(String, Int)] = reduceRdd.sortBy(_._2,false).take(10)
    tuples.foreach(println)

  }
}
