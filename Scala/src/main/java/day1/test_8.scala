package day1

object test_8 {
  def main(args: Array[String]): Unit = {

    def func(a:Int): String=>(Char=>Boolean) ={
      def f1(s:String): Char=>Boolean ={
        def f2(c:Char):Boolean={
          if(a==0&&s==""&&c=='0')
          false
          else true
        }
        f2
      }
      f1
    }

    println(func(1)("")('0'))


    //匿名函数简化 闭包
    def func1(a:Int): String=>(Char=>Boolean) ={
        s=>c=>{if(a==0&&s==""&&c=='0') false else true
          }
        }
    println(func1(1)("")('0'))
  }

  //柯里化
  def fun2(a:Int)(s:String)(c:Char):Boolean=if(a==0&&s==""&&c=='0') false else true
}
