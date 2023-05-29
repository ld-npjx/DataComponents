package day4

object test_6 {
  def main(args: Array[String]): Unit = {
    val t: test6 = test6.getTest1
    println(t.toString)
  }
}
class test6 private (name:String,age:Int){
}
object test6{
  private val test:test6=new test6("test",12)
  def getTest1():test6=test
}
