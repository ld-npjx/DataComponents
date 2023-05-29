package day1

object test_7 {
  def main(args: Array[String]): Unit = {
    val fun=(i:Int,s:String,c:Char)=>{
      if(i==0&&s==""&&c=='0')
        false
      else true
    }
    println(fun(2,"hello",'f'))
    println(fun(0,"",'0'))
  }
}
