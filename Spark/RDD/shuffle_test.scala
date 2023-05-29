package RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object shuffle_test {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf
    val context: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = context.makeRDD(List(1, 2, 3, 4), 4)
    //1 2 3 4分别在四个分区里面

    //1 2 分区合并  3 4分区合并
//    val newRdd: RDD[Int] = rdd.coalesce(2)
val newRdd: RDD[Int] = rdd.repartition(3)
    newRdd.saveAsTextFile("outPut")
  }
}
