package nz.ubermouse.rsbot.actions

import com.epicbot.api.rs3.methods.interactive.Players
import com.epicbot.api.rs3.methods.{Walking, Calculations}
import com.epicbot.api.rs3.wrappers.Locatable
import nz.ubermouse.rsbot.helpers.Wait

class MoveTo(location: Locatable) extends Action {
  override def run: Boolean = {
    if(Walking.getDestination.equals(location.getLocation))
      return false

    if(Calculations.isOnScreen(location.getLocation.getCentralPoint))
      Walking.walk(location.getLocation.randomize(5, 5))
    else
      location.getLocation.clickOnMap()

    Wait.until()(Players.getLocal.isMoving)
  }
}
