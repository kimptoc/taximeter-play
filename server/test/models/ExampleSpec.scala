package models

import org.scalatest._

class ExampleSpec extends FlatSpec with Matchers {

  "A test" should "generally pass" in {
    1 should be (1) // fails!
    assert(1 === 1)
  }
}
