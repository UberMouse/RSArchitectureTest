package nz.ubermouse.rsbot.actions

class ActionContainer protected[actions](actions: List[Action], val abortOnFailure: Boolean = true) {
  def isEmpty = actions.isEmpty
  def head = actions.head
  def drop(count: Int) = new ActionContainer(actions.drop(count), abortOnFailure)
  def dropWhile(predicate: Action => Boolean) = new ActionContainer(actions.dropWhile(predicate), abortOnFailure)

  override def toString = (actions, abortOnFailure).toString
}

class ActionContainerCreator(actions: List[Action]) {
  def toWeakContainer = new ActionContainer(actions, true)
  def toStrongContainer = new ActionContainer(actions, false)
}

object ActionContainerCreator {
  def empty = new ActionContainerCreator(List[Action]()).toWeakContainer
}


