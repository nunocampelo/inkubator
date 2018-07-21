package pt.base.inkubator.prism.algorithm.standard

import kotlinx.coroutines.experimental.NonCancellable
import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm

@Component
class StandardSixDegreeAlgorithm : Algorithm<Long, Unit>(12L, 45L) {

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
                for (k in 0..arg) {
                    if (!NonCancellable.isActive) {
                        StandardLinearAlgorithm.logger.debug("Algorithm {} execution canceled", this)
                        return
                    }
                    for (l in 0..arg) {
                        if (!NonCancellable.isActive) {
                            StandardLinearAlgorithm.logger.debug("Algorithm {} execution canceled", this)
                            return
                        }
                        for (m in 0..arg) {
                            if (!NonCancellable.isActive) {
                                StandardLinearAlgorithm.logger.debug("Algorithm {} execution canceled", this)
                                return
                            }
                            for (n in 0..arg) {
                                if (!NonCancellable.isActive) {
                                    StandardLinearAlgorithm.logger.debug("Algorithm {} execution canceled", this)
                                    return
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}