package day1

object test_3 {
  def main(args: Array[String]): Unit = {
    val factor=2

    val muter = (i: Int) => i * 12
    val muter2=(i:Int)=>i*factor
    println(muter(12))
    println(muter(22))
    println(muter2(2))
  }
}
