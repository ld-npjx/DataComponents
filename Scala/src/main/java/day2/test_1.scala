package day2

object test_1 {
  def main(args: Array[String]): Unit = {
    def addFunction(a:Int,b:Int):Int={
      a+b
    }
    //闭包
    def addByA(a:Int):Int=>Int=a+_

    def addByA1(a:Int):Int=>Int={
      def addByB(b:Int):Int={
        a+b
      }
      addByB
    }
    val intToInt = addByA(12)
    print(intToInt(20))
  }

}
