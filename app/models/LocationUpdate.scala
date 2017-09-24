package models

import java.time.LocalDateTime

import scala.collection.mutable.ListBuffer

object LocationUpdate {

  def totalDistanceTravelled(locationUpdates:ListBuffer[LocationUpdate]):Double = {
    locationUpdates.tail.foldLeft(locationUpdates.head, 0.0)((accum, elem) => (elem, accum._2 + Location.haversineDistance(accum._1.location, elem.location)))._2
  }

  def totalTimeSpent(locationUpdates:ListBuffer[LocationUpdate]):Double = {
    0.0
  }
}

/**
  * Location/time of an update
  * @param location
  * @param time
  */
class LocationUpdate(val location: Location, val time:LocalDateTime) {

}
