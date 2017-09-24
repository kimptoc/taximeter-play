package models

import java.time.LocalDateTime

import models.util.Money

import scala.collection.mutable.ListBuffer

class TaxiFare(startTime: LocalDateTime, startLocation: Location) {

  val locationUpdates = new ListBuffer[LocationUpdate]
  locationUpdates += new LocationUpdate(startLocation, startTime)

  def currentFare = {
    var fare : Money = new Money(2.60)
    // TODO select tariff - can change during journey
    // TODO get total distance and calc fares based on that
    val distanceTravelled = LocationUpdate.totalDistanceTravelled(locationUpdates)
    val tariff = new Tariff1()
    fare = tariff.calculateCharge(distanceTravelled)
    // TODO get total time, and calc fares based on that
    // TODO use higher total...
    fare
  }

  def journeyUpdate(time: LocalDateTime, location: Location) = locationUpdates += new LocationUpdate(location, time)
}
