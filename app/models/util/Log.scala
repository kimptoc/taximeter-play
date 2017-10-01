package models.util

import org.joda.time.DateTime

object Log {

  def info(message:String) = {
    println(s"${new DateTime()}:T${Thread.currentThread().getId}:INFO:$message")
  }
}
