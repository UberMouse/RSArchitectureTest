package nz.ubermouse

import nz.ubermouse.rsbot.actions.{ActionContainerCreator, Action}

package object rsbot {
  implicit def actionListToContainerCreator(actions: List[Action]) = new ActionContainerCreator(actions)
}
