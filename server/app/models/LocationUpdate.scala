package models

import java.time.LocalDateTime

import models.util.Log

import scala.collection.mutable.ListBuffer

object LocationUpdate {

  def totalDistanceTravelled(locationUpdates:ListBuffer[LocationUpdate]):Double = {
//    Log.info(s"Calculating distance for ${locationUpdates.length} locations")
    if (locationUpdates.length < 2)
      0.0
    else
      locationUpdates.tail.foldLeft(locationUpdates.head, 0.0)((accum, elem) => (elem, accum._2 + Location.haversineDistance(accum._1.location, elem.location)))._2
  }

}

/**
  * Location/time of an update
  * @param location
  */
class LocationUpdate(val location: Location) {

}
