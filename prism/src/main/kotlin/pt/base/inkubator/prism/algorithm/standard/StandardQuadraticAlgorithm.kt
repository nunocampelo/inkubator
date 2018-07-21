package pt.base.inkubator.prism.algorithm.standard

import kotlinx.coroutines.experimental.NonCancellable
import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm

@Component
class StandardQuadraticAlgorithm : Algorithm<Long, Unit>(300L, 50000L) {

    override fun produceArgument(): Long = defaultLongArgumentProducer()

    override suspend fun exec(arg: Long) {
        for (i in 0..arg) {
            if (!NonCancellable.isActive) {
                StandardLinearAlgorithm.logger.debug("Algorithm {} execution canceled", this)
                return
            }
            for (j in 0..arg) {
                if (!NonCancellable.isActive) {
                    StandardLinearAlgorithm.logger.debug("Algorithm {} execution canceled", this)
                    return
                }
            }
        }
    }
}