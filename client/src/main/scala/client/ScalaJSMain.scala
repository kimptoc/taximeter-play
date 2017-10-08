package client

import org.scalajs.dom
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.Element
import org.scalajs.dom.{Event, Position, PositionError}

import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExportTopLevel
//import shared.SharedMessages

import org.scalajs.dom.ext.Ajax
import scala.scalajs.js.timers.setInterval

object ScalaJSMain {

  var journeyUnderway:Boolean = false

  @JSExportTopLevel("client.ScalaJSMain.start") // can be used in JS as client.ScalaJSMain.start()
  def start(): Unit = {

    val main = dom.document.getElementById("main-app")

    addButton(main, "Start Journey (auto via GPS)", Some("start_journey") ,enableGPS = true) { journeyUnderway = true}
    main.appendChild(dom.document.createElement("br"))
    addButton(main, "Start Journey (manual)", Some("start_journey")) { journeyUnderway = true}
    main.appendChild(dom.document.createElement("p"))
//    addButton(main, "Update Journey", Some("location_update")) {}  // ideally lambda should be option - possible in Scala?
//    main.appendChild(dom.document.createElement("br"))
    addButton(main, "Go to Oxford Circus", action = None) { setInputValue("latitude", 51.515419); setInputValue("longitude",-0.141099	)}  // ideally lambda should be option - possible in Scala?
    addButton(main, "Go to Greenwich Park", action = None) { setInputValue("latitude", 51.47669); setInputValue("longitude",0.00013	)}  // ideally lambda should be option - possible in Scala?
    main.appendChild(dom.document.createElement("p"))
    addButton(main, "End Journey", Some("end_journey")) { journeyUnderway = false}
    main.appendChild(dom.document.createElement("br"))


    setInterval(100) {
      println(s"backgrounder, journey underway? $journeyUnderway")
      if (journeyUnderway) {
        sendUpdate("location_update")
      }
    }

  }

  private def addButton(main: Element, title: String, action: Option[String], enableGPS : Boolean = false)(body: => Unit): Any = {
    val startButton = dom.document.createElement("button")
    startButton.textContent = title
    startButton.addEventListener("click", { (e0: Event) =>
      body
      if (enableGPS) {
        // TODO how to detect if geolocation is available?
        var geo = dom.document.defaultView.navigator.geolocation
        def onSuccess(p:Position) = {
//          println( s"/latitude=${p.coords.latitude}")                // Latitude
//          println( s"/longitude=${p.coords.longitude}")              // Longitude
          setInputValue("latitude", p.coords.latitude)
          setInputValue("longitude", p.coords.longitude)

        }
        def onError(p:PositionError) = println("Error")
        geo.getCurrentPosition(onSuccess _)
        geo.watchPosition(onSuccess _, onError _)
      }
      if (action.nonEmpty) {
        sendUpdate(action.get)
      }
    }, false)

    main.appendChild(startButton)

  }

  private def sendUpdate(action:String) = {
    import scala.concurrent.ExecutionContext.Implicits.global

    val latitude = inputValue("latitude")
    val longitude = inputValue("longitude")
    val backend_url = dom.document.getElementById("backend_url").textContent
    println(s"backend_url:$backend_url")
    val url = s"$backend_url/$action/$latitude/$longitude"
    Ajax.post(url).onComplete { case xhr =>
      println("ajax call complete")
//      println(xhr.get.responseText)
      val r = JSON.parse(xhr.get.responseText)
      setText("fare", r.fare)
      setText("elapsed",r.elapsed)
      setText("distance",r.distance)
      setText("timestamp",r.timestamp)
    }
  }

  private def setText(id: String, value: Any) = {
    dom.document.getElementById(id).textContent = value.toString()
  }

  private def inputValue(id: String) = {
    dom.document.getElementById(id).asInstanceOf[Input].value
  }

  private def setInputValue(id: String, value:Double) = {
    dom.document.getElementById(id).asInstanceOf[Input].value = value.toString
  }
}
