import kotlinx.coroutines.experimental.launch
import kotlin.system.exitProcess

if (args.isEmpty()) {
    println("Usage: xcommandexecuter command")
    exitProcess(1)
}

val command = args[0]
println("Executing command: '$command', please wait...")

val process = Runtime.getRuntime().exec(command)

launch {
    process.printStdOut()
}

launch {
    process.printErrOut()
}

process.waitFor()
process.destroy()

println("Closing xcommandexecuter, see you next time.")

suspend fun Process.printStdOut() {
    val outputReader = this.inputStream.bufferedReader();
    outputReader.lines().forEach {
        println(it)
    }
}

suspend fun Process.printErrOut() {
    val outputReader = this.errorStream.bufferedReader();
    outputReader.lines().forEach {
        println(it)
    }
}