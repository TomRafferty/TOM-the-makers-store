import helpers.getContinentFromLocation.getContinentFromLocation
import main.db.DbAdapter
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class getContinentFromLocationTest extends AnyWordSpec with Matchers {
  val db = DbAdapter
  "getContinentFromLocation" should {
    "Be a String type" in {
      val subject = getContinentFromLocation("London")
      assert(subject.isInstanceOf[String])
    }
    "return the correct continent for Manchester" in {
      val subject = getContinentFromLocation("Manchester")
      assert(subject == "EU")
    }
    "return the correct continent for Toronto" in {
      val subject = getContinentFromLocation("Toronto")
      assert(subject == "NA")
    }
    "return the correct continent for Ney York" in {
      val subject = getContinentFromLocation("New York")
      assert(subject == "NA")
    }
  }
}
