package nz.ubermouse.rsbot.actions

import com.epicbot.api.rs3.methods.widget.Camera
import com.epicbot.api.rs3.wrappers.Locatable

class RotateCamera(location: Locatable) extends Action {
  override def run: Boolean = {
    Camera.turnTo(location, 15)
    true
  }
}
