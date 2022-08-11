package helpers
import main.db.DbAdapter
import scala.annotation.tailrec

object getContinentFromLocation {
  val db = DbAdapter
  @tailrec
  def getContinentFromLocation(location:String, iteration: Int = 0, continentNum: Int = 0): String = {
    val allLocations = db.getLocations()
    val thisContinent = allLocations.toList(continentNum)
    val locationsInContinent = thisContinent._2.values.toList.flatten
    locationsInContinent(iteration) match {
      case thisLocation if(thisLocation.name == location) => thisContinent._1
      case _ if(iteration == locationsInContinent.length-1) => getContinentFromLocation(location, iteration = 0, continentNum+1)
      case _ => getContinentFromLocation(location, iteration + 1, continentNum)
    }
  }
}
