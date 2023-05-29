package RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object top3_test {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val context: SparkContext = new SparkContext(conf)
    val dataRDD: RDD[String] = context.textFile("/")
    //数据格式: XXXX,省份,City,广告名,XXXXX
    //统计各个省份广告TOP3
    var mapRDD=dataRDD.map(
      line=>{
        var datas=line.split(" ")
        ((datas(1),datas(2)),1)  //一定要将map数据设置为(key,value)  ((),())
      }
    )
    val reduceRDD:RDD[((String,String),Int)]=mapRDD.reduceByKey(_+_)
    val newMapRDD: RDD[(String, (String, Int))] = reduceRDD.map({
      case ((prv, ad), sum) => {
        (prv, (ad, sum))
      }
    })

    val groupRDD: RDD[(String, Iterable[(String, Int)])] = newMapRDD.groupByKey()
    val resultRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(
      iter => {
        //排序前三
        iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)
      }
    )
  }
}
