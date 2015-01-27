package nz.ubermouse.rsbot.runescape

import com.epicbot.api.rs3.methods.node.SceneEntities
import nz.ubermouse.rsbot.ids.TreeIds
import nz.ubermouse.rsbot.models.Tree

object Trees extends TreeIds {

  def nearest: Option[Tree] = {
    val nearest = SceneEntities.getNearest(treeIds:_*)

    if(nearest == null) None
    else Option(new Tree(nearest))
  }
}
