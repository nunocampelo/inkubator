package pt.base.inkubator.kit.io.commandline

suspend fun readWords(prompt: String, delimiter: String = " "): List<String> {
    print(prompt)
    return readLine()!!.split(delimiter)
}

fun blockingReadWords(prompt: String, delimiter: String = " "): List<String> {
    print(prompt)
    return readLine()!!.split(delimiter)
}
