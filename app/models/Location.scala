package models

/**
  * The location for when a journey update is given
  * Distance travelled can then be derived from distance between locations
  * Assumed that this is high accuracy - which may not be true #TODO ASSUMPTIONS
  * Also no handling of height - might be relevant too.
  */

object Location {

  /**
    * Algorithm is from https://davidkeen.com/blog/2013/10/calculating-distance-with-scalas-foldleft/
    * @param pointA
    * @param pointB
    * @return
    */
  def haversineDistance(pointA: (Double, Double), pointB: (Double, Double)): Double = {
    val deltaLat = math.toRadians(pointB._1 - pointA._1)
    val deltaLong = math.toRadians(pointB._2 - pointA._2)
    val a = math.pow(math.sin(deltaLat / 2), 2) + math.cos(math.toRadians(pointA._1)) * math.cos(math.toRadians(pointB._1)) * math.pow(math.sin(deltaLong / 2), 2)
    val greatCircleDistance = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
    6371e3 * greatCircleDistance
  }
  def haversineDistance(pointA: Location, pointB: Location): Double = {
    haversineDistance((pointA.latitude, pointA.longitude), (pointB.latitude, pointB.longitude))
  }
}

class Location (val latitude:Double, val longitude: Double) {
  override def toString = s"lat:$latitude, long:$longitude"
}
