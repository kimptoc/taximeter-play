package controllers

import javax.inject._

import models.TaxiFare
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc._

import scala.collection.mutable

import shared.SharedMessages

@Singleton
class HomeController @Inject()(cache: mutable.HashMap[String, TaxiFare] = new mutable.HashMap[String, TaxiFare](), cc: ControllerComponents) extends AbstractController(cc) {


  /**
   * Index is the main/single page of the app. Other routers are called via JavaScript
   *
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(SharedMessages.itWorks))
  }

  /**
    * Called to indicate the start of a new journey
    * @param latitude
    * @param longitude
    * @return
    */
  def startJourney(latitude:Double, longitude:Double) = Action {
    val journey = new TaxiFare(startLocation = new models.Location(latitude, longitude))
    val fare = journey.currentFare
    cache += ("journey" -> journey)
    Ok(Json.obj(
      "id" -> "id11!",
      "latitude" -> latitude,
      "longitude" -> longitude,
      "fare" -> fare.value, // TODO remove ".value" - how to make own objects work in JSON
      "timestamp" -> DateTime.now().toString(),
      "elapsed" -> journey.elapsed,
      "distance" -> journey.distance,
      "status" -> "ok"
    ))
  }

  /**
    * Called to give an update on a journey - could be same or new location, time is assumed to be now.
    * TODO allow for late arriving update, allow time to be passed.
    * @param latitude
    * @param longitude
    * @return
    */
  def locationUpdate(latitude:Double, longitude:Double) = Action {
    val journeyMaybe: Option[TaxiFare] = cache.get("journey")
    if (journeyMaybe.isEmpty) {
      Ok(Json.obj(
        "id" -> "id11!",
        "error" -> "journey not found",
        "status" -> "error"
      ))
    } else {
      val journey = journeyMaybe.get
      journey.journeyUpdate(new models.Location(latitude, longitude))
      val fare = journey.currentFare
      Ok(Json.obj(
        "id" -> "id11!",
        "latitude" -> latitude,
        "longitude" -> longitude,
        "fare" -> fare.value, // TODO remove ".value" - how to make own objects work in JSON
        "timestamp" -> DateTime.now().toString(),
        "elapsed" -> journey.elapsed,
        "distance" -> journey.distance,
        "status" -> "ok"
      ))
    }
  }

  def endJourney(latitude:Double, longitude:Double) = Action {
    val journeyMaybe: Option[TaxiFare] = cache.get("journey")
    if (journeyMaybe.isEmpty) {
      Ok(Json.obj(
        "id" -> "id11!",
        "error" -> "journey not found",
        "status" -> "error"
      ))
    } else {
      val journey = journeyMaybe.get
      val fare = journey.stop
      Ok(Json.obj(
        "id" -> "id11!",
        "fare" -> fare.value, // TODO remove ".value" - how to make own objects work in JSON
        "timestamp" -> DateTime.now().toString(),
        "elapsed" -> journey.elapsed,
        "distance" -> journey.distance,
        "status" -> "ok"
      ))
    }
  }

}
