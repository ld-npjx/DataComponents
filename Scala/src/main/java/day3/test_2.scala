package day3

object test_2 {
  def main(args: Array[String]): Unit = {
    def sum(a:Int,b:Int):Int={println("调用sum")
      a+b}

    lazy val result:Int=sum(12,3)
    println("抽象1")
    println(result)
  }
}
