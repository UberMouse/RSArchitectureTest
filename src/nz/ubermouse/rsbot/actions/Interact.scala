package nz.ubermouse.rsbot.actions

import com.epicbot.api.input.Mouse
import com.epicbot.api.rs3.methods.Calculations
import com.epicbot.api.rs3.methods.node.Menu
import com.epicbot.api.shared.wrappers.{ViewportEntity, Locatable}
import nz.ubermouse.rsbot.helpers.Wait

class Interact(entity: ViewportEntity, action: String) extends Action {
  override def run: Unit = {
    if(!Calculations.isOnScreen(entity.getCentralPoint))
      return

    val moveTo = entity.getCentralPoint
    Mouse.move(moveTo)
    Wait.until(){Mouse.getLocation == moveTo}

    Mouse.click(false)
    Wait.until(750){Menu.isOpen}

    Menu.select(action)
    Wait.until(500){Menu.isCollapsed}
  }
}
