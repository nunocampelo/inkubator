package pt.base.inkubator.prism.algorithm.executer

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch
import org.springframework.beans.factory.BeanFactory
import org.springframework.stereotype.Service
import pt.base.inkubator.prism.algorithm.Algorithm
import pt.base.inkubator.prism.algorithm.runner.CpuTimedAlgorithmRunner

@Service
class CpuTimedAlgorithmExecuter(private val beanFactory: BeanFactory) {

    suspend fun <A, R> execute(channel: Channel<Long>, algorithm: Algorithm<A, R>, numberResults: Int) {

        var currentNumberResults: Int = 0

        while (currentNumberResults < numberResults) {
            val result = doExecuteAlgorithm(algorithm)
            if (result > 0L) {
                channel.send(result)
                currentNumberResults++
            }
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
    }
}