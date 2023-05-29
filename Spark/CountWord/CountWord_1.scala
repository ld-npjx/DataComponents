package CountWord
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object CountWord_1 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc=new SparkContext(sparkConf)

    val lines: RDD[String] = sc.textFile("C:\\JavaCode\\Spark\\src\\main\\resources\\1.txt")
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordGroup: RDD[(String,Iterable[String])] = words.groupBy(word => word)

    val wordToCount = wordGroup.map {
      case (word, list) => {
        (word, list.size)
      }
    }
    val array:Array[(String,Int)] = wordToCount.collect()

    array.foreach(println)
    sc.stop();
  }
}
