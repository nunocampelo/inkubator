package pt.base.inkubator.kit.exec

import pt.base.inkubator.kit.io.stream.StreamType
import pt.base.inkubator.kit.io.stream.blockingReadStream
import pt.base.inkubator.kit.io.stream.readStream

suspend fun exec(command: String): Map<StreamType, String> {

    val process = Runtime.getRuntime().exec(command)
    val result = hashMapOf(StreamType.STD to readStream(process.inputStream), StreamType.ERR
            to readStream(process.errorStream))

    process.waitFor()
    process.destroy()

    return result
}

fun blockingExec(command: String): Map<StreamType, String> {

    val process = Runtime.getRuntime().exec(command)
    val result = hashMapOf(StreamType.STD to blockingReadStream(process.inputStream), StreamType.ERR
            to blockingReadStream(process.errorStream))

    process.waitFor()
    process.destroy()

    return result
}
