package shared

object SharedMessages {
    var count:Int = 0
  def itWorks = {
      count += 1
      s"It works! ($count)"
  }
}
