package nz.ubermouse.rsbot.tasks

import com.epicbot.api.rs3.methods.tab.inventory.Inventory
import com.epicbot.api.rs3.wrappers.node.Item
import nz.ubermouse.rsbot.actions.{Interact, Action}

class EmptyInventoryState(val inventoryItems: Array[Item])
class EmptyInventory extends Task[EmptyInventoryState](1) {
  override def getStateFromWorld = new EmptyInventoryState(Inventory.getItems)

  override def execute(state: EmptyInventoryState): List[Action] = {
    state.inventoryItems.map(i => new Interact(i, "drop")).toList
  }

  override def shouldExecute(state: EmptyInventoryState): Boolean = Inventory.isFull
}
