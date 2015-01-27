package nz.ubermouse.rsbot.models

import com.epicbot.api.rs3.methods.Calculations
import com.epicbot.api.rs3.wrappers.node.SceneObject
import com.sun.javaws.exceptions.InvalidArgumentException
import nz.ubermouse.rsbot.ids.TreeIds
import nz.ubermouse.rsbot.actions.{RotateCamera, Interact}

class Tree(obj: SceneObject) extends TreeIds {
  if(!treeIds.contains(obj.getID))
    throw new InvalidArgumentException(Array("Passed GameObject is not a tree"))

  def chop = {
    val interact = new Interact(obj, "chop")

    if(!Calculations.isOnScreen(obj.getCentralPoint))
      List(new RotateCamera(obj), interact)
    else
      List(interact)
  }
}
