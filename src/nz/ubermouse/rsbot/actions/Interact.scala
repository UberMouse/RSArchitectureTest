package nz.ubermouse.rsbot.actions

import com.epicbot.api.input.Mouse
import com.epicbot.api.rs3.methods.Calculations
import com.epicbot.api.rs3.methods.node.Menu
import com.epicbot.api.shared.wrappers.{ViewportEntity, Locatable}
import nz.ubermouse.rsbot.helpers.Wait

class Interact(entity: ViewportEntity, action: String) extends Action {
  override def run: Boolean = {
    val centerPoint = entity.getCentralPoint
    if(!Calculations.isOnScreen(centerPoint) && !Calculations.isOnCanvas(centerPoint.x, centerPoint.y))
      return false

    val moveTo = entity.getCentralPoint
    Mouse.move(moveTo)
    println("Waiting for mouse to move to location: ", moveTo)
    if(!Wait.until(1000){Mouse.getLocation == moveTo || Menu.contains(action)}) {
      println(Mouse.getLocation == moveTo)
      println(Menu.contains(action))
      return false
    }

    if(!Menu.select(action)) {
      println("Menu selection failed, aborting")
      return false
    }

    true
  }
}
