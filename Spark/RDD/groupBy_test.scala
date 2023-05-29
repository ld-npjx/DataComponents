package RDD

import org.apache.spark.{SparkConf, SparkContext}

object groupBy_test {
  def main(args: Array[String]): Unit = {
    var sparkConf=new SparkConf().setMaster("local[*]").setAppName("groupBy")
    var sc=new SparkContext(sparkConf)

    val RDD=sc.makeRDD(List("hello","Spark","Hive","Hadoop"))
    val groupRdd=RDD.groupBy(_.charAt(0))

    groupRdd.collect().foreach(println)
  }
}
