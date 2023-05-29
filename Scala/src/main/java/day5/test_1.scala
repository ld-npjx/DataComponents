package day5

object test_1 {
  def main(args: Array[String]): Unit = {
    val student:Student=new Student()
    student.row()
  }
}

class Person5{
  var name:String=_
  var age:Int=_
  def row(): Unit ={
    print("person")
  }
}
trait Young{
//  def row(): Unit ={
//    print("hello")
//  }
  def hello():Int;
}
//继承的父类和接口中不能有相同的方法
class Student extends Person5 with Young {
  override def hello(): Int = 10;
}