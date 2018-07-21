package pt.base.inkubator.prism.algorithm.executer

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.Channel
import org.springframework.beans.factory.BeanFactory
import org.springframework.stereotype.Service
import pt.base.inkubator.prism.algorithm.Algorithm
import pt.base.inkubator.prism.algorithm.runner.CpuTimedAlgorithmRunner
import pt.base.inkubator.prism.logger

@Service
class CpuTimedAlgorithmExecuter(private val beanFactory: BeanFactory) {
    companion object {
        val logger by logger()
    }

    suspend fun <A, R> execute(channel: Channel<Long>, algorithm: Algorithm<A, R>, numberResults: Int) {

        var currentNumberResults: Int = 0

        while (currentNumberResults < numberResults) {

            val jobExecution = async {

                val result = doExecuteAlgorithm(algorithm)

                if (result > 0L) {
                    logger.info("sending $result")
                    channel.send(result)
                    currentNumberResults++
                }
            }

            delay(1500L)
            jobExecution.cancel()
//            val result = doExecuteAlgorithm(algorithm)

        }
        channel.close()
    }

    suspend fun <A, R> executeInSequence(algorithm: Algorithm<A, R>, arguments: List<A>): List<Long> {
        return arguments.map { arg ->
            doExecuteAlgorithm(algorithm, arg)
        }
    }

    suspend fun <A, R> executeInSequence(algorithm: Algorithm<A, R>, minNumberResults: Int): List<Long> {

        val results = mutableListOf<Long>()

        while (results.size < minNumberResults) {
            repeat(minNumberResults) { it ->
                val result = doExecuteAlgorithm(algorithm)
                if (result > 0L) {
                    results.add(result)
                }
            }
        }

        return results
    }

    suspend fun <A, R> executeParallel(algorithm: Algorithm<A, R>, minNumberResults: Int): List<Long> {

        val job = Job()
        val results = mutableListOf<Long>()

        while (results.size < minNumberResults) {
            repeat(minNumberResults) { it ->
                launch(job) {
                    val result = doExecuteAlgorithm(algorithm)
                    if (result > 0L) {
                        results.add(result)
                    }
                }
            }
        }

        job.cancelAndJoin()
        return results
    }

    private suspend fun <A, R> doExecuteAlgorithm(algorithm: Algorithm<A, R>, argument: A = algorithm.produceArgument()): Long {
        return beanFactory.getBean(CpuTimedAlgorithmRunner::class.java, algorithm, argument).run()
//        val algorithmRunner = beanFactory.getBean(CpuTimedAlgorithmRunner::class.java, algorithm, argument)
//
//        val job = async {
//            algorithmRunner.run()
//        }
//
//        delay(500L)
//        job.cancelAndJoin()
//
//        return job.getCompleted()
    }
}