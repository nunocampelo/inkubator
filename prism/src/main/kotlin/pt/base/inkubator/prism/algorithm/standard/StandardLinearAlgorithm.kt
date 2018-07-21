package pt.base.inkubator.prism.algorithm.standard

import kotlinx.coroutines.experimental.isActive
import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm
import pt.base.inkubator.prism.logger
import kotlin.coroutines.experimental.coroutineContext

@Component
class StandardLinearAlgorithm : Algorithm<Long, Unit>(10000L, 1000000000L) {
    companion object {
        val logger by logger()
    }

    override fun produceArgument(): Long = defaultLongArgumentProducer()

    override suspend fun exec(arg: Long) {

        for (i in 0..arg) {

            if (!coroutineContext.isActive) {
                logger.debug("Algorithm {} execution canceled", this)
                return
            }
        }
    }
}