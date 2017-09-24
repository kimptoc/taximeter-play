package models

import java.time.LocalDateTime

import models.util.Money
import org.scalatest._

import scala.collection.mutable.ListBuffer

class DistanceSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  // check distances here http://www.movable-type.co.uk/scripts/latlong.html
  it should "calculate distance between 0,0 and .002,.002" in {
    val distance = Location.haversineDistance((0.0,0.0), (0.002,0.002))
    distance shouldBe > (300.0)
    distance shouldBe < (320.0)
  }

  it should "calculate distance between 0,0 and .002,.002 and back" in {
    val locations = new ListBuffer[LocationUpdate]
    locations += new LocationUpdate(new Location(0.0, 0.0), LocalDateTime.now())
    locations += new LocationUpdate(new Location(0.002, 0.002), LocalDateTime.now())
    locations += new LocationUpdate(new Location(0.0, 0.0), LocalDateTime.now())
    val distance = LocationUpdate.totalDistanceTravelled(locations)
    distance shouldBe > (600.0)
    distance shouldBe < (640.0)
  }

}
