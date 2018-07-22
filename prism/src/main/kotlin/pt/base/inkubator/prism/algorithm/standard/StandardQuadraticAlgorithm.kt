package pt.base.inkubator.prism.algorithm.standard

import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm

@Component
class StandardQuadraticAlgorithm : Algorithm<Long, Unit>(300L, 50000L) {

    override fun produceArgument(): Long = defaultLongArgumentProducer()

    override fun exec(arg: Long) {
        for (i in 0..arg) {
            for (j in 0..arg) {
            }
        }
    }
}