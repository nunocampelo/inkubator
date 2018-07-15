import pt.base.inkubator.kit.exec.blockingExec
import pt.base.inkubator.kit.io.commandline.option.main
import kotlin.system.exitProcess

if (args.isEmpty()) {
    println("Usage: commandexecuter command")
    exitProcess(1)
}

val command = args[0]
print("Executing command: '$command', please wait...")

main(arrayOf<String>("Pick your poison"))
blockingExec(command)

println("Closing commandexecuter, see you next time.")

