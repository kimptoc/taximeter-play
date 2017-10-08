package client

import org.scalajs.dom
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.Element

object DomUtils {

  def text(id: String) = {
    dom.document.getElementById(id).textContent
  }

  def setText(id: String, value: Any) = {
    dom.document.getElementById(id).textContent = value.toString()
  }

  def inputValue(id: String) = {
    dom.document.getElementById(id).asInstanceOf[Input].value
  }

  def setInputValue(id: String, value:Double) = {
    dom.document.getElementById(id).asInstanceOf[Input].value = value.toString
  }

  def element(tag: String): Element = {
    dom.document.createElement(tag)
  }

}
