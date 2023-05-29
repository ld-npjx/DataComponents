package day2

import scala.annotation.tailrec

object test_2 {
  def main(args: Array[String]): Unit = {
    println(fact(5))

    println(tailFact(5))
  }
  def fact(n:Int):Int={
    if(n==0) return 1
    return fact(n-1)*n
  }

  //尾递归
  def tailFact(n:Int):Int={
    @tailrec //判断尾递归是否正确
    def loop(n:Int,currRes:Int):Int={
      if(n==0) return  currRes
      loop(n-1,currRes*n)
    }
    loop(n,currRes = 1)
  }
}
