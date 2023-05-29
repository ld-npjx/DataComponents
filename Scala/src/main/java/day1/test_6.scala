package day1

object test_6 {
  def main(args: Array[String]): Unit = {

    //对数组进行处理，将操作抽象出来，处理完毕之后的结果返回一个新的数组
    def arrayFunction(array: Array[Int],op:Int=>Int):Array[Int]={
      for(num<-array)yield op(num)
    }
    val arr:Array[Int]=Array(12,22,33,44)

    def opFunction(num:Int):Int={
      num+1
    }
    val newArr:Array[Int]=arrayFunction(arr,opFunction)
    //使用匿名函数
    val newArr1:Array[Int]=arrayFunction(arr,_+1)

    println(newArr.mkString(","))
    println(newArr1.mkString(","))
    for(num<-newArr){
      println(num)
    }
  }
}
