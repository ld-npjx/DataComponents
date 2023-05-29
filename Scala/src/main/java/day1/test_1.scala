package day1

object test_1 {
  def main(array: Array[String]):Unit={
    //对象函数
    def sayHi(name:String):Unit={
      print("hi"+name);
    }
    //优先调用函数，其次调用方法
    sayHi("luci");

    //调用方法
    test_1.sayHi("luci")
  }

  //对象的方法
  def sayHi(name:String):Unit={
    print("Hi"+name)
  }
}
