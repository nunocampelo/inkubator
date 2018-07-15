package pt.base.inkubator.kit.io.stream

import java.io.BufferedReader
import java.io.InputStream

suspend fun readStream(stream: InputStream): String {

    val reader: BufferedReader = stream.bufferedReader()
    return reader.lines().reduce { result, line ->
        println(line)
        result + line
    }.toString()
}

fun blockingReadStream(stream: InputStream): String {

    val reader: BufferedReader = stream.bufferedReader()
    return reader.lines().reduce { result, line -> println(line); result + line }.toString()
}
