package nz.ubermouse.rsbot.actions

import nz.ubermouse.rsbot.helpers.Wait

class WaitUntil(predicate: => Boolean, timeout: Int = 1500) extends Action {
  override def run: Boolean = {
    Wait.until(timeout)(predicate)
  }
}
