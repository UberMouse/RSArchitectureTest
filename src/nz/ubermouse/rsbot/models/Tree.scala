package nz.ubermouse.rsbot.models

import com.epicbot.api.rs3.methods.{Walking, Calculations}
import com.epicbot.api.rs3.methods.interactive.Players
import com.epicbot.api.rs3.wrappers.node.SceneObject
import nz.ubermouse.rsbot.ids.TreeIds
import nz.ubermouse.rsbot.actions._

class Tree(obj: SceneObject) extends TreeIds {
  if(!treeIds.contains(obj.getID))
    throw new IllegalArgumentException("obj is not a tree")

  def chop: List[Action] = {
    if(Walking.getDestination.equals(obj.getLocation))
      return List[Action]()

    val predicate = Players.getLocal.isMoving || !Players.getLocal.isIdle
    var actions = List(new Interact(obj, "chop down"), new WaitUntil(predicate))

    if(!Calculations.isOnScreen(obj.getCentralPoint))
      actions = new RotateOrMoveTo(obj) :: actions

    actions
  }
}
