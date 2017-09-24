package models.util

/**
  * To avoid issues with stray decimal figures, we will abstract money into this class.
  * Options include
  * - store as integer/pennies figure
  * - use BigDecimal
  * - third party lib, eg https://alvinalexander.com/scala/scala-money-currency-bigdecimal-class-libraries
  *
  * Will use BigDecimal for now
  */
class Money(var value:BigDecimal) {

  /**
    * Other constructor
    * @param amountAsDouble
    * @return
    */
  def this(amountAsDouble:Double = 0.0) = {
    this (BigDecimal(amountAsDouble))
  }

  def *(other:Money):Money = {
    this.value = this.value * other.value
    this
  }

  def *(other:Double):Money = {
    this.value = this.value * BigDecimal(other)
    this
  }

  def +(other:Money):Money = {
    this.value = this.value + other.value
    this
  }

  override def equals(that: scala.Any): Boolean = {
    that match {
      case that: Money => that.isInstanceOf[Money] && this.value == that.value
      case _ => false
    }
  }

  override def hashCode(): Int = value.hashCode()
}
