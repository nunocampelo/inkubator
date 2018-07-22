package pt.base.inkubator.prism.algorithm.standard

import org.springframework.stereotype.Component
import pt.base.inkubator.prism.algorithm.Algorithm

@Component
class StandardSixDegreeAlgorithm : Algorithm<Long, Unit>(12L, 45L) {

    override fun produceArgument(): Long = defaultLongArgumentProducer()

    override fun exec(arg: Long) {
        for (i in 0..arg) {
            for (j in 0..arg) {
                for (k in 0..arg) {
                    for (l in 0..arg) {
                        for (m in 0..arg) {
                            for (n in 0..arg) {
                            }
                        }
                    }
                }
            }
        }
    }
}