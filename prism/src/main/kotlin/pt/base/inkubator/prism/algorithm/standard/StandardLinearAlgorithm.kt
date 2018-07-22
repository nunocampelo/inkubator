package pt.base.inkubator.prism.algorithm.standard

import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm

@Component
class StandardLinearAlgorithm : Algorithm<Long, Unit>(1000L, 10000000000L) {

    override fun produceArgument(): Long = defaultLongArgumentProducer()

    override  fun exec(arg: Long) {
        for (i in 0..arg) {
        }
    }
}