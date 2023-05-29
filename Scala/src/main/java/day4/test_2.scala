package day4

object test_2 {
  def main(args: Array[String]): Unit = {
    val test:test3=new test3(22)
  }
}

class test3 {
  var name:Int=_
  def this(name:Int){
    this()
    this.name=name
    println("name:"+name)
  }

}