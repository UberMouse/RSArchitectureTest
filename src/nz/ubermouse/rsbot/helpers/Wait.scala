package nz.ubermouse.rsbot.helpers

import com.epicbot.api.util.Time

object Wait {
  val SLEEP_INCREMENT = 100

  def until(timeout: Int = 1500)(predicate: => Boolean): Boolean = {
    var waited = 0
    while(!predicate && waited < timeout) {
      Time.sleep(SLEEP_INCREMENT)
      waited += SLEEP_INCREMENT
    }

    println("Waited timeout", waited, timeout)
    waited != timeout
  }

}
