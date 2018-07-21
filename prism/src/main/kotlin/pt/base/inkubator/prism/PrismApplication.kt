package pt.base.inkubator.prism

import kotlinx.coroutines.experimental.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import pt.base.inkubator.prism.algorithm.AlgorithmAnalyser
import pt.base.inkubator.prism.algorithm.standard.StandardLinearAlgorithm

@SpringBootApplication
class
PrismApplication(
    private val applicationContext: ConfigurableApplicationContext, private val analyser: AlgorithmAnalyser,
    private val algorithm: StandardLinearAlgorithm
) : ApplicationRunner {
    companion object {
        val logger by logger()
    }

    override fun run(args: ApplicationArguments?) {

        logger.info("Prism application started, buckle up for some cool computing...")

        runBlocking {
            //             analyser.analyseInSequence(algorithm)
            //            analyser.analyseInParallel(algorithm)
            analyser.analyse(algorithm)
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




