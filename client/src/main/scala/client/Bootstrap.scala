package client

import DomUtils._

object Bootstrap {

  def main(args: Array[String]): Unit = {
    println("Bootstrap!")
    if (text("on_load") == "start") {
      ScalaJSMain.start()
    }
  }
}