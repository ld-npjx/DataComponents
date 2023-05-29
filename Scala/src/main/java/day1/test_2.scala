package day1

object test_2 {
  def main(args: Array[String]):Unit={
    def test1(str: String*):Unit={//可变参数
      println(str);
    }
    //可变参数得放在最后一个
    def test2(str1:String,str2:String*):Unit={
      println(str1+str2)
    }
    //默认参数
    def test3(str:String="2342"):Unit= {
      println(str)
    }
    //输入函数的参数变量名，可以在使用函数时调用参数位置
    def test4(name:String,age:Int): Unit ={
      println(name+age);
    }

    test1("12313","123411");
    test2("342342","24324q","4354rsd")
    test3();
    test4(age=12,name="Teddy")
  }

}
