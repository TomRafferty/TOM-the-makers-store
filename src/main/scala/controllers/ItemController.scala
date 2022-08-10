package controllers

import helpers.getContinentFromLocation.getContinentFromLocation
import main.db.DbAdapter
import main.model.{Item, Location}

import scala.collection.mutable.ArrayBuffer

object ItemController {
  val db: DbAdapter.type = DbAdapter
  //create new item
  def createItem(newItem: Item) = {
    db.createItem(newItem)
  }
  def updateItem(itemId: Int, newItem: Item) = {
    db.updateItem(itemId, newItem)
  }
  //fetch all items
  def fetchAllItems: ArrayBuffer[Item] = db.getItems()
  //fetch a specific item
  def fetchItem(id: Int): Item = {
    fetchAllItems.filter(item => item.id == id).head
  }
  //fetch all locations given a specific continent
  def fetchLocationsFromContinent(continent: String): Seq[Location] = {
    //locations.json looks like this:
    /*
    * continent
    * |  country
    * |  |  city <- what is meant by location (has an id and name property)
    * |  |  |  id
    * |  |  |  name
    * |  |
    * |  country
    * |  | etc
    */
    val filteredContinent = db.getLocations().filter(x => x._1 == continent).head._2
    val locations = filteredContinent.values.toSeq.flatten
    locations
  }

  //fetch all items from specific continent given a location inside sed continent
  def fetchItemsFromLocation(location: String): ArrayBuffer[Item] = {
    val parentContinent = getContinentFromLocation(location)
    val items = fetchAllItems
    items.filter(item => item.availableLocales match {
      case x if(x.contains(parentContinent)) => true
      case _ => false
    })
  }
}
