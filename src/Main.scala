import com.epicbot.api.{ActiveLoopScript, GameType, Manifest}
import nz.ubermouse.rsbot.actions.{ActionContainerCreator, Action}
import nz.ubermouse.rsbot.tasks.{ChopTree, EmptyInventory, Task}

@Manifest(game = GameType.RS3, author = "UberMouse", name = "Test")
class Main extends ActiveLoopScript {
  val tasks = List[Task](new ChopTree,
                         new EmptyInventory).sortWith{case(l, r) => l.priority < r.priority}
  var actionsToProcess = ActionContainerCreator.empty
  var lastProcessedTask: Task = null

  override def loop(): Int = {
    println("======== Start ==========")
    var possibleActions = ActionContainerCreator.empty
    var currentTask: Task = null
    for(task <- tasks) {
      println("Should execute: ", task, task.shouldExecute)
      if(possibleActions.isEmpty && actionsToProcess.isEmpty && task.shouldExecute) {
        possibleActions = task.execute
        currentTask = task
      }
    }

    if(!possibleActions.isEmpty &&
      (actionsToProcess.isEmpty || possibleActions.dropWhile(a => !a.equals(actionsToProcess.head)).isEmpty)) {
      actionsToProcess = possibleActions
      lastProcessedTask = currentTask
      println("Updated actionsToProcess to", actionsToProcess)
    }

    if(!actionsToProcess.isEmpty) {
      val actionToRun = actionsToProcess.head
      actionsToProcess = actionsToProcess.drop(1)
      println("Running action", actionToRun)
      println("Actions in queue", actionsToProcess)
      
      if(!actionToRun.run && actionsToProcess.abortOnFailure)
        actionsToProcess = ActionContainerCreator.empty
      println("======= End ========")
      return 100
    }

    println("======= End ========")
    100
  }

  override def onStart(): Boolean = {
    true
  }
}
