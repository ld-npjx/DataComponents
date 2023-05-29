package RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object combineByKey_test {
  def main(args: Array[String]): Unit = {
    var sparkConf=new SparkConf().setMaster("local[*]").setAppName("groupBy")
    var sc=new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(("a",22),("a",231),("c",22)))
    val newRdd:RDD[(String,(Int,Int))]=rdd.combineByKey(
      v=>(v,1),
      (t:(Int,Int),v)=>{
        (t._1+v,t._2+1)
      },
      (t1:(Int,Int),t2:(Int,Int))=>(t1._1+t2._1,t1._2+t2._2)
    )
    newRdd.collect().foreach(println)
  }
}
