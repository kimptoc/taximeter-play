package models

import java.time.LocalDateTime

import models.util.Money

import scala.collection.mutable.ListBuffer

/**
  * Assumed that fare gets told when it moves.
  * Alternatively it could track time itself, eg 5 mins after creation, it would assume fare for a 5 min journey. Not doing that for now...
  * @param startTime
  * @param startLocation
  */
class TaxiFare(startTime: LocalDateTime, startLocation: Location) {

  val locationUpdates = new ListBuffer[LocationUpdate]
  locationUpdates += new LocationUpdate(startLocation, startTime)

  // TODO - probably need to break down the updates into the min chargeable time slots and build the overall fare from that.
  // if needed could be cached, so only need to work on latest updates.
  def currentFare = {
    var fare : Money = new Money(2.60)
    // TODO select tariff - which can change during journey
    val tariff = new Tariff1()
    // get total distance and calc fares based on that
    val distanceTravelled = LocationUpdate.totalDistanceTravelled(locationUpdates)
    fare = tariff.calculateCharge(distanceTravelled)
    // TODO get total time, and calc fares based on that
    // TODO use higher total...
    fare
  }

  def journeyUpdate(time: LocalDateTime, location: Location) = locationUpdates += new LocationUpdate(location, time)
}
