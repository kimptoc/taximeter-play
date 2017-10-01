package models

import java.math

import models.util.{Log, Money}
import uk.co.epsilontechnologies.taximeter.{Clock, Odometer, TflTaxiMeter2017}

import scala.collection.mutable.ListBuffer

/**
  * Fare is calculated using an updated version of this library - https://github.com/shaneagibson/taxi-meter
  * Forked here - https://github.com/kimptoc/taxi-meter
  * @param startLocation
  */
class TaxiFare(startLocation: Location = new Location(0,0), clock : Clock = new Clock) extends Odometer{

  val locationUpdates = new ListBuffer[LocationUpdate]
  val tflTaxiMeter = new TflTaxiMeter2017(this, clock)
  tflTaxiMeter.startJourney()
  journeyUpdate(startLocation)
  Log.info(s"New journey started. Locations:${locationUpdates.length}")

  def currentFare : Money = {
    val fare = tflTaxiMeter.getFare
    Log.info(s"currentFare called:$fare")
    new Money(fare)
  }

  def journeyUpdate(location: Location): Unit = {
    Log.info(s"Got a location update:$location")
    locationUpdates += new LocationUpdate(location)
  }

  override def getDistance : math.BigDecimal = {
    val distance = LocationUpdate.totalDistanceTravelled(locationUpdates)
    Log.info(s"Current distance:$distance")
    math.BigDecimal.valueOf(distance)
  }

  override def reset() : Unit = {
    Log.info("Reset called!")
    locationUpdates.clear()
  }
}
