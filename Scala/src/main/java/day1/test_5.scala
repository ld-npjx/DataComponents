package day1

object test_5 {
  def main(args: Array[String]): Unit = {
    //二元运算函数
    def dualFunctionOneAndTwo(f:(Int,Int)=>Int):Int={
      f(1,2)
    }
    def dualFunction(f:(Int,Int)=>Int,a:Int,b:Int):Int={
      f(a,b)
    }
    val add=(a:Int,b:Int)=>a+b
    val minus=(a:Int,b:Int)=>a-b

    println(dualFunctionOneAndTwo(add))
    println(dualFunctionOneAndTwo(minus))

    //简化
    println(dualFunctionOneAndTwo((a:Int,b:Int)=>a+b))
    println(dualFunctionOneAndTwo((a:Int,b:Int)=>a-b))

    //继续简化
    println(dualFunctionOneAndTwo((a,b)=>a+b))
    println(dualFunctionOneAndTwo((a,b)=>a-b))

    //通配符
    println(dualFunctionOneAndTwo(_+_))
    println(dualFunctionOneAndTwo(_-_))



    def addFunction(a:Int,b:Int):Int={
      a+b;
    }
    println(dualFunction(addFunction,12,22))
    println(dualFunction(_+_,12,22))



    def function0(): (Int,String)=>Unit={
      def function1(age:Int,name:String):Unit={
        println(name+':'+age)
      }
      function1//将函数直接返回
    }

    println(function0())

     val f0=function0()
    println(f0(23,"Alice"))

  }
}
