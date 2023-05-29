package day1

object test_4 {
  def main(args: Array[String]): Unit = {
    for(i<-1 to 20 if i<2){
        println(i)
    }

    //yield  便利的数据名需要和yield后面数据名是同一个
    val lists2=List(1,2,3,4,5,6,7,8,9)
    var record=for{list<-lists2 if list<8}yield list
    for (a<-record)
      println(a)

    val numList = List(1,2,3,4,5,6,7,8,9,10);

    // for 循环
    var retVal = for{ a <- numList
                      if a != 3; if a < 8
                      }yield a
    // 输出返回值
    for( a <- retVal){
      println( "Value of a: " + a );
    }
  }
}
