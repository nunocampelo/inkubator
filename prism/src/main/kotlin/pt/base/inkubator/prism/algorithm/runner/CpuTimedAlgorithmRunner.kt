package pt.base.inkubator.prism.algorithm.runner

import pt.base.inkubator.prism.algorithm.Algorithm
import pt.base.inkubator.prism.logger

abstract class CpuTimedAlgorithmRunner<A, R>(private val algorithm: Algorithm<A, R>, private val argument: A) : AlgorithmRunner<A, R, Long>(
    algorithm,
    argument
) {
    companion object {
        val logger by logger()
    }

    override suspend fun run(): Long {

        val initialTime = getCurrentCpuTime()
        logger.debug("Running {} on {} stating at {}", algorithm, argument, initialTime)

        algorithm.exec(argument)

        val finalTime = getCurrentCpuTime()
        val execTime = finalTime - initialTime

        logger.debug(
            "Executed {} on {} in {} ended at {}", algorithm, argument, execTime, finalTime
        )

        if (execTime <= 0L) {
            logger.warn("Illegal execution time found {} with arg {}", execTime, argument)
        }

        return execTime
    }

    protected abstract fun getCurrentCpuTime(): Long
}
