package pt.base.inkubator.tripping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
class TrippingApplication

fun main(args: Array<String>) {
    runApplication<TrippingApplication>(*args)
}
