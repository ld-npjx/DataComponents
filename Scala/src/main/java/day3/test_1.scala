package day3

import java.util.concurrent.locks.Condition

object test_1 {
  def main(args: Array[String]): Unit = {
    def f0(a: Int): Int = {
      println("可执行参数被调用" + a)
      a
    }
      //传名参数  传入一段代码块，并且返回值为Int
      def f1(a: =>Int)={
        //被传入的参数a被调用几次，传入的代码块就会被使用几次
        println("a:"+a)
        println("a:"+a)
      }
      f1(f0(2))
      f1(22)
      def mywhile(condition: =>Boolean):(=>Unit)=>Unit ={
        def loopFun(op: => Unit): Unit = {
          if (condition) {
            op
          mywhile(condition)(op)
          }
        }
        loopFun
    }


    //lambda
    def myWhile0(condition: =>Boolean):(=>Unit)=>Unit={
      op=>{
        if(condition){
          op
          myWhile0(condition)(op)
        }
      }
    }


    //柯里化
    def myWhile1(condition: =>Boolean)(op: =>Unit): Unit={
      if(condition){
        op
        mywhile(condition)(op)
      }
    }


    var i = 10
    mywhile(i>1){
      println(i)
      i=i-1
    }
  }
}
