package CountWord
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object CountWord_2 {
    def main(args: Array[String]): Unit = {
      val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
      val sc=new SparkContext(sparkConf)

      val lines: RDD[String] = sc.textFile("C:\\JavaCode\\Spark\\src\\main\\resources\\1.txt")
      val words: RDD[String] = lines.flatMap(_.split(" "))
      val wordToOne=words.map(
        word=>(word,1)
      )

      val wordGroup: RDD[(String,Iterable[(String,Int)])] = wordToOne.groupBy(word => word._1)
      //通过第一个参数进行分区

      val wordToCount = wordGroup.map {
        case (word, list) => {
          list.reduce((t1,t2)=>(t1._1,t1._2+t2._2))
        }
      }
      val array:Array[(String,Int)] = wordToCount.collect()

      array.foreach(println)
      sc.stop();
    }

}
