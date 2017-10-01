package models

import java.time.LocalDateTime

import models.util.Money
import org.joda.time.DateTime
import org.scalatest._
import uk.co.epsilontechnologies.taximeter.Clock

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
  *
  * TODO turn the test cases into a table/spreadsheet - would be easier to manage
  */
class FareSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  // this delay is to wait for the background poller thread to do fare recalc (every 100ms)
  // TODO probably best to expose mechanism to force fare recalc to make tests quicker
  var meterTickDelayMs: Int = 300 // TODO wouldn't expect to need to wait this long, but we do - bug?


  "When journeys starts, fare " should "be minimum for tariff" in {
    val clockSaturdayMorning : Clock = new Clock(){
      override def getNow = new DateTime(2017,9,23,9,0,0) // Saturday morning
    }
    val journey = new TaxiFare(new Location(0,0), clockSaturdayMorning)
    Thread.sleep(meterTickDelayMs) // wait for meter to tick over
    val taxiFare = journey.currentFare
    taxiFare should be (new Money(2.60)) // min fare
  }

  it should "have zero arg constructor" in {
    val journey = new TaxiFare()
  }

  it should "with tariff 1, fare change after 234.8 metres" in {
    val clockThursdayMorning : Clock = new Clock(){
      override def getNow = new DateTime(2017,9,21,9,0,0) // Thursday morning
    }
    val journey = new TaxiFare(new Location(0,0), clockThursdayMorning)
    Thread.sleep(meterTickDelayMs) // wait for meter to tick over
    var taxiFare = journey.currentFare
    taxiFare should be (new Money(2.60)) // min fare

    journey.journeyUpdate(new Location(0.002,0.002))
    Thread.sleep(meterTickDelayMs) // wait for meter to tick over
    taxiFare = journey.currentFare
    taxiFare should be (new Money(2.80)) // min fare plus one additional distance part

    journey.journeyUpdate(new Location(0,0)) // back to origin
    Thread.sleep(meterTickDelayMs) // wait for meter to tick over
    taxiFare = journey.currentFare
    taxiFare should be (new Money(3.4)) // min fare plus 4 additional distance parts

  }

  ignore should "with tariff 1, fare change after 50.4 seconds" in {
    // TODO rejig calc to use a min time charge - this would fix this test
    val clockThursdayMorning : Clock = new Clock(){
      override def getNow = new DateTime(2017,9,21,9,0,0) // Thursday morning
    }
    val journey = new TaxiFare(new Location(0,0), clockThursdayMorning) // Thursday morning
    var taxiFare = journey.currentFare
    taxiFare should be (new Money(2.60)) // min fare

    journey.journeyUpdate(new Location(0,0)) // dont move, just time
    taxiFare = journey.currentFare
    taxiFare should be (new Money(2.80)) // min fare plus one additional time part

    journey.journeyUpdate(new Location(0,0)) // another 1 min 10 secs, 3 time slots
    taxiFare = journey.currentFare
    taxiFare should be (new Money(3.2)) // min fare plus 3 time charges


  }

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
