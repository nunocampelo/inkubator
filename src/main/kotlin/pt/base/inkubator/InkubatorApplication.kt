package pt.base.inkubator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InkubatorApplication

fun main(args: Array<String>) {
    runApplication<InkubatorApplication>(*args)
}
