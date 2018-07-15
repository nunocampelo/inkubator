import kotlinx.coroutines.experimental.runBlocking
import pt.base.inkubator.kit.exec.exec
import kotlin.system.exitProcess

if (args.isEmpty()) {
    println("Usage: xcommandexecuter command")
    exitProcess(1)
}

val command = args[0]
println("Executing command: '$command', please wait...")

runBlocking {
    exec(command)
}

println("Closing xcommandexecuter, see you next time.")
