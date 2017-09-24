package controllers

import java.time.LocalDateTime
import javax.inject._

import models.TaxiFare
import play.api.libs.json.Json
import play.api.mvc._

import scala.collection.mutable

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cache: mutable.HashMap[String, TaxiFare] = new mutable.HashMap[String, TaxiFare](), cc: ControllerComponents) extends AbstractController(cc) {


  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def startJourney(latitude:Double, longitude:Double) = Action {
    val journey = new TaxiFare(LocalDateTime.now(), new models.Location(latitude, longitude))
    val fare = journey.currentFare
    cache += ("journey" -> journey)
    Ok(Json.obj(
      "id" -> "id11!",
      "latitude" -> latitude,
      "longitude" -> longitude,
      "fare" -> fare.value, // TODO remove ".value" - how to make own objects work in JSON
      "status" -> "ok"
    ))
  }

  def locationUpdate(latitude:Double, longitude:Double) = Action {
    val journey: Option[TaxiFare] = cache.get("journey")
    if (journey.isEmpty) {
      Ok(Json.obj(
        "id" -> "id11!",
        "error" -> "journey not found",
        "status" -> "error"
      ))
    } else {
      journey.get.journeyUpdate(LocalDateTime.now(), new models.Location(latitude, longitude))
      val fare = journey.get.currentFare
      Ok(Json.obj(
        "id" -> "id11!",
        "latitude" -> latitude,
        "longitude" -> longitude,
        "fare" -> fare.value, // TODO remove ".value" - how to make own objects work in JSON
        "status" -> "ok"
      ))
    }
  }

}
