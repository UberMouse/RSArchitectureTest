package nz.ubermouse.rsbot.tasks

import nz.ubermouse.rsbot.actions.{ActionContainer, Action}
import nz.ubermouse.rsbot._

abstract class Task(val priority: Int) {
  def shouldExecute: Boolean
  def execute: ActionContainer
}
