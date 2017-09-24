package models

import models.util.Money

class Tariff1(val firstDistance:Double = 234.8,
              val firstSeconds:Double = 50.4,
              val minCharge:Double = 2.60,
              val additionalDistance:Double = 117.4,
              val additionalSeconds:Double = 25.2,
              val additionalCharge:Double = 0.20,
              val longDistance:Double = 9656.1,
              val farDistance:Double = 86.9,
              val farSeconds:Double = 18.7,
              val farCharge:Double = 0.20) {
  val moneyMinCharge:Money = new Money(minCharge)
  val moneyAdditionalCharge = new Money(additionalCharge)
  val moneyFarCharge = new Money(farCharge)

  def calculateCharge(distance:Double): Money = {
    distance match {
      case x:Double if x <= firstDistance => moneyMinCharge
      case x:Double if x < 9656.1 => {
        val distanceAtAdditionalRate = math.ceil((x - firstDistance) / additionalDistance)
        moneyMinCharge + moneyAdditionalCharge * distanceAtAdditionalRate
      }
    }
  }

}
