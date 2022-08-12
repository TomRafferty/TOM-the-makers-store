package models

import java.util.UUID
//UUID generated on creation | item name and quantity desired  |  cost of all items in the cart
class Cart(val uuid: UUID, var items: Map[String, Int], var totalCost: Double)

