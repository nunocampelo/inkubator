package pt.base.inkubator.prism.algorithm.standard

import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm

@Component
class StandardLinearAlgorithm : Algorithm<Long, Unit>() {

    override fun produceArgument(): Long = defaultLongArgumentProducer()
    
    override suspend fun exec(arg: Long) {
        for (i in 0..arg) {
        }
    }
}