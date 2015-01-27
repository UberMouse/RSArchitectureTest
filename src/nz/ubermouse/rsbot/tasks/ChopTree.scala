package nz.ubermouse.rsbot.tasks

import com.epicbot.api.rs3.methods.interactive.Players
import com.epicbot.api.rs3.wrappers.interactive.Player
import nz.ubermouse.rsbot.models.Tree
import nz.ubermouse.rsbot.runescape.Trees

class ChopTreeState(val nearestTree: Option[Tree], val player: Player)

class ChopTree extends Task[ChopTreeState](2) {
  override def getStateFromWorld = new ChopTreeState(Trees.nearest, Players.getLocal)

  def execute(state: ChopTreeState) = {
    state.nearestTree.get.chop
  }
  override def shouldExecute(state: ChopTreeState) = state.nearestTree.isDefined && state.player.isIdle
}
