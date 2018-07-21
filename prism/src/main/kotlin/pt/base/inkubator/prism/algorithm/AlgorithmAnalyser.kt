package pt.base.inkubator.prism.algorithm

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import org.springframework.stereotype.Service
import pt.base.inkubator.prism.algorithm.executer.CpuTimedAlgorithmExecuter
import pt.base.inkubator.prism.logger

@Service
class AlgorithmAnalyser(private val executer: CpuTimedAlgorithmExecuter) {
    companion object {
        val logger by logger()
    }

    private val numberAlgorithmExecutions = 10

    suspend fun <A, AR, AT : Algorithm<A, AR>> analyse(vararg algorithms: AT) {

        logger.info("Analysing {} in a channel", algorithms.contentToString())

        algorithms.map {

            val channel: Channel<Long> = Channel()
            val results = mutableListOf<Long>()

            launch {
                executer.execute(channel, it, numberAlgorithmExecutions)
            }
            for (result in channel) {
                logger.info("Received result {}", result)
                results.add(result)
            }

            logger.info("Analysis finished with results {}", results)
        }
    }

    suspend fun <A, AR, AT : Algorithm<A, AR>> analyseInSequence(vararg algorithms: AT) {

        logger.info("Analysing {} in sequence", algorithms.contentToString())

        algorithms.map {
            logger.info("Analysis finished with results {}", executer.executeInSequence(it, numberAlgorithmExecutions))
        }

    }

    suspend fun <A, AR, AT : Algorithm<A, AR>> analyseInParallel(vararg algorithms: AT) {

        logger.info("Analysing {} in parallel", algorithms.contentToString())

        algorithms.map {
            logger.info("Analysis finished with results {}", executer.executeParallel(it, numberAlgorithmExecutions))
        }
    }
}
