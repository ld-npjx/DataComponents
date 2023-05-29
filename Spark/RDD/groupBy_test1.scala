package RDD

import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat
import java.util.Date

object groupBy_test1 {
  def main(args: Array[String]): Unit = {
    val testConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(testConf)

    val rdd=sc.textFile("C:\\JavaCode\\Spark\\src\\main\\resources\\time.txt")

    val value = rdd.map(
      line => {
        val datas = line.split(" ")
        val time = datas(0)

        val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
        val data: Date = sdf.parse(time)
        val sdf1 = new SimpleDateFormat("HH")
        val hour = sdf1.format(data)
        (hour, 1)
      }
    ).groupBy(_._1)
    value.map{
      case(hour,iter)=>
        (hour,iter.size)
    }
  }.collect().foreach(println)
}
