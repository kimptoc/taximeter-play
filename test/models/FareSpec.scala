package models

import java.time.LocalDateTime

import models.util.Money
import org.scalatest._

import scala.collection.mutable.ListBuffer

/**
  * Assume that we will get multiple time/location updates, usually one per second (tariffs are down to tenth of a second)
  * If only one update - assume journey is one second at most - #TODO REVIEW ASSUMPTIONS
  * Assume that we will be told of journey start/end
  * Charges are for distances moved and time taken
  *
  * Options for tracking journeys
  * - updates every second
  * - updates less frequent, then guess-timate the difference, ie assumed they moved steadily during that time period.
  *
  * Tests take the form of
  * - start time/location
  * - 0 or more location updates (time & location)
  * - end time /location
  * - expected fare for that journey
  */
class FareSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  "When journeys starts, fare " should "be minimum for tariff" in {
    val journey = new TaxiFare(LocalDateTime.parse("2017-09-23T09:00:00"), new Location(0,0)) // Saturday morning
    val taxiFare = journey.currentFare
    taxiFare should be (new Money(2.60)) // min fare
  }

  it should "with tariff 1, fare change after 234.8 metres" in {
    val journey = new TaxiFare(LocalDateTime.parse("2017-09-21T09:00:00"), new Location(0,0)) // Saturday morning
    var taxiFare = journey.currentFare
    taxiFare should be (new Money(2.60)) // min fare
    journey.journeyUpdate(LocalDateTime.parse("2017-09-21T09:00:00"), new Location(0.002,0.002))
    taxiFare = journey.currentFare
    taxiFare should be (new Money(2.80)) // min fare plus one additional distance part

  }

  ignore should "with tariff 1, fare change after 50.4 seconds" in {}
  ignore should "with tariff 1, fare rate change after 9656.1 metres when going fast" in {}
  ignore should "with tariff 1, fare rate change after 9656.1 metres when going slow" in {}
  ignore should "with tariff 1, fare for longer journey over 6 miles" in {}

  ignore should "with tariff 2, fare change after 95.5 metres" in {}
  ignore should "with tariff 2, fare change after 41 seconds" in {}
  ignore should "with tariff 2, fare rate change after 9656.1 metres when going fast" in {}
  ignore should "with tariff 2, fare rate change after 9656.1 metres when going slow" in {}
  ignore should "with tariff 2, fare for longer journey over 6 miles" in {}

  ignore should "with tariff 3, fare change after 162.4 metres" in {}
  ignore should "with tariff 3, fare change after 35 seconds" in {}
  ignore should "with tariff 3, fare rate change after 9656.1 metres when going fast" in {}
  ignore should "with tariff 3, fare rate change after 9656.1 metres when going slow" in {}
  ignore should "with tariff 3, fare for longer journey over 6 miles" in {}

  ignore should "fare that crosses tariff 1 and 2" in {}
  ignore should "fare that crosses tariff 2 and 3" in {}
  ignore should "fare that crosses tariff 1, 2 and 3" in {}
  ignore should "fare that crosses tariff 1, 2 and 3 and journey over 6 miles" in {}
}
