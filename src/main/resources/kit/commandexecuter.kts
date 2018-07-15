import kotlin.system.exitProcess

if (args.isEmpty()) {
    println("Usage: commandexecuter command")
    exitProcess(1)
}

val command = args[0]
print("Executing command: '$command', please wait...")

val process = Runtime.getRuntime().exec(command)

process.printStdOut()
process.printErrOut()

process.waitFor()
process.destroy()

println("Closing commandexecuter, see you next time.")

fun Process.printStdOut() {
    val outputReader = this.inputStream.bufferedReader();
    outputReader.lines().forEach {
        println(it)
    }
}

fun Process.printErrOut() {
    val outputReader = this.errorStream.bufferedReader();
    outputReader.lines().forEach {
        println(it)
    }
}