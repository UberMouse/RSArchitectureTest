package nz.ubermouse.rsbot.tasks

import com.epicbot.api.rs3.methods.tab.inventory.Inventory
import com.epicbot.api.rs3.wrappers.node.Item
import nz.ubermouse.rsbot.actions.{ActionContainer, Interact, Action}
import nz.ubermouse.rsbot._

class EmptyInventoryState(val inventoryItems: Array[Item])
class EmptyInventory extends Task(1) {
  def getStateFromWorld = new EmptyInventoryState(Inventory.getItems)

  override def execute: ActionContainer = {
    val state = getStateFromWorld
    state.inventoryItems.map(i => new Interact(i, "drop")).toList.toStrongContainer
  }

  override def shouldExecute: Boolean = Inventory.isFull
}
