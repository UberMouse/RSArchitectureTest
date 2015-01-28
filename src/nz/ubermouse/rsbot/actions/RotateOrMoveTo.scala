package nz.ubermouse.rsbot.actions

import com.epicbot.api.rs3.methods.Calculations
import com.epicbot.api.rs3.wrappers.Locatable

class RotateOrMoveTo(location: Locatable) extends Action {
  override def run: Boolean = {
    new RotateCamera(location).run
    if(!Calculations.isOnScreen(location.getLocation.getCentralPoint))
      return new MoveTo(location).run

    true
  }
}
