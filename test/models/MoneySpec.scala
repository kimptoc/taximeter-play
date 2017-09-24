package models

import java.time.LocalDateTime

import models.util.Money
import org.scalatest._

import scala.collection.mutable.ListBuffer

class MoneySpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  it should "allow creation of money" in {
    val change:Money = new Money()
    change shouldEqual(new Money(0.0))
  }

  it should "allow maths with money" in {
    var balance: Money = new Money()
    balance shouldEqual(new Money(0.0))
    balance += new Money(0.1)
    balance shouldEqual(new Money(0.1))
    balance = balance * 2
    balance shouldEqual(new Money(0.2))
    balance = balance + new Money(1.1) + (new Money(4.0)) * 3
    balance shouldEqual(new Money(13.3))
  }

}
