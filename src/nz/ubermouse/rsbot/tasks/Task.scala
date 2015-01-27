package nz.ubermouse.rsbot.tasks

import nz.ubermouse.rsbot.actions.Action

abstract class Task[S](val priority: Int) {
  def getStateFromWorld: S
  def shouldExecute(state: S): Boolean
  def execute(state: S): List[Action]
}
