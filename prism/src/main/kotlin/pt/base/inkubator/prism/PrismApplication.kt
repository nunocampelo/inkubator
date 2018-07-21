package pt.base.inkubator.prism

import kotlinx.coroutines.experimental.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import pt.base.inkubator.prism.algorithm.AlgorithmAnalyser
import pt.base.inkubator.prism.algorithm.standard.StandardLinearAlgorithm
import pt.base.inkubator.prism.algorithm.standard.StandardQuadraticAlgorithm
import pt.base.inkubator.prism.algorithm.standard.StandardSixDegreeAlgorithm

@SpringBootApplication
class
PrismApplication(
    private val applicationContext: ConfigurableApplicationContext, private val analyser: AlgorithmAnalyser,
    private val linearAlgorithm: StandardLinearAlgorithm, private val quadraticAlgorithm: StandardQuadraticAlgorithm,
    private val sixDegreeAlgorithm: StandardSixDegreeAlgorithm
) : ApplicationRunner {
    companion object {
        val logger by logger()
    }

    override fun run(args: ApplicationArguments?) {

        logger.info("Prism application started, buckle up for some cool computing...")

        runBlocking {
            //            analyser.analyseInSequence(linearAlgorithm)
//            analyser.analyseInParallel(linearAlgorithm)
            analyser.analyse(linearAlgorithm, quadraticAlgorithm, sixDegreeAlgorithm)
        }

        close()
    }

    fun close() {
        logger.info("Shutting Prism down, see you next time")
        applicationContext.close()
    }

}

fun main(args: Array<String>) {
    runApplication<PrismApplication>(*args)
}




