package RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object sample_test {
  def main(args: Array[String]): Unit = {
    val testConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(testConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 3, 2, 13, 12, 31, 313, 14, 14))

    //第一个参数false不放回抽取  true是放回抽取，可能重复抽取
    //第二个参数在0-1之间，0表示全不取，1表示全抽取 
    println(rdd.sample(false,0.4).collect().mkString(","))
    //sample第三个参数是随机数种子，不传的话就是默认时间
  }
}
