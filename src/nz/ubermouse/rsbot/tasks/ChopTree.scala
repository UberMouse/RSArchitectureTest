package nz.ubermouse.rsbot.tasks

import com.epicbot.api.rs3.methods.interactive.Players
import com.epicbot.api.rs3.wrappers.interactive.Player
import nz.ubermouse.rsbot.models.Tree
import nz.ubermouse.rsbot.runescape.Trees
import nz.ubermouse.rsbot._

class ChopTreeState(val nearestTree: Option[Tree], val player: Player)

class ChopTree extends Task(2) {
  def getStateFromWorld = new ChopTreeState(Trees.nearest, Players.getLocal)

  override def execute = {
    val state = getStateFromWorld
    state.nearestTree.get.chop.toWeakContainer
  }
  override def shouldExecute = {
    val state = getStateFromWorld
    val p = Players.getLocal
    state.nearestTree.isDefined && (if(p.isMoving) true else p.isIdle)
  }
}
