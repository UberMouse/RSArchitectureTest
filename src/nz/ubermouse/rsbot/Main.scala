package nz.ubermouse.rsbot

import com.epicbot.api.{GameType, ActiveLoopScript, Manifest}
import nz.ubermouse.rsbot.actions.Action
import nz.ubermouse.rsbot.tasks.{Task, EmptyInventory, ChopTree}

@Manifest(game = GameType.RS3, author = "UberMouse", name = "Test")
class Main extends ActiveLoopScript {
  val tasks = List[Task[Object]](new ChopTree,
                                 new EmptyInventory).sortWith{case(l, r) => l.priority < r.priority}
  var actionsToProcess = List[Action]()

  override def loop(): Int = {
    var possibleActions: List[Action] = null
    for(task <- tasks) {
      val state = task.getStateFromWorld
      if(task.shouldExecute(state))
        possibleActions = task.execute(state)
    }

    if(actionsToProcess.isEmpty || possibleActions.dropWhile(a => !a.equals(actionsToProcess.head)).isEmpty)
      actionsToProcess = possibleActions

    if(!actionsToProcess.isEmpty) {
      val actionToRun = actionsToProcess.head
      actionsToProcess = actionsToProcess.drop(1)
      
      actionToRun.run
      return 100
    }
    
    100
  }

  override def onStart(): Boolean = true
}
