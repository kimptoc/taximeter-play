package example

import org.scalajs.dom
import org.scalajs.dom.{Event, html}
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.Element

import scala.scalajs.js.JSON
//import shared.SharedMessages

import scala.scalajs.js
import dom.ext.Ajax
import org.w3c.dom.html.HTMLButtonElement


object ScalaJSExample {
  def main(args: Array[String]): Unit = {
//    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
//    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
//    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
//    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks

    val main = dom.document.getElementById("main-app")

    addButton(main, "Start Journey", "start_journey")
    addButton(main, "End Journey", "end_journey")
    addButton(main, "Update Journey", "location_update")

  }

  private def addButton(main: Element, title: String, action: String): Any = {
    val startButton = dom.document.createElement("button")
    startButton.textContent = title
    startButton.addEventListener("click", { (e0: Event) =>
      sendUpdate(action)
    }, false)

    main.appendChild(startButton)
  }

  private def sendUpdate(action:String) = {
    import scala.concurrent
    .ExecutionContext
    .Implicits
    .global

    val latitude = inputValue("latitude")
    val longitude = inputValue("longitude")
    val url = s"$action/$latitude/$longitude"
    Ajax.post(url).onComplete { case xhr =>
      println("ajax call complete")
      println(xhr.get.responseText)
      val r = JSON.parse(xhr.get.responseText)
      dom.document.getElementById("fare").textContent = r.fare.toString()
      dom.document.getElementById("elapsed").textContent = r.elapsed.toString()
      dom.document.getElementById("distance").textContent = r.distance.toString()
      dom.document.getElementById("timestamp").textContent = r.timestamp.toString()
    }
  }

  private def inputValue(id: String) = {
    dom.document.getElementById(id).asInstanceOf[Input].value
  }
}
