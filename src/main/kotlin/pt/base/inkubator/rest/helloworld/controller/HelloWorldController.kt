package pt.base.inkubator.rest.helloworld.controller

import org.hibernate.internal.util.collections.ArrayHelper
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.base.inkubator.rest.helloworld.data.Greeting
import java.util.concurrent.atomic.AtomicLong

@RestController
class HelloWorldController{

    val counter = AtomicLong()

    @RequestMapping("/greet.txt")
    fun greet() = "Hello, World!"

    @RequestMapping("/greet.json")
    fun greetJson() = Greeting(counter.incrementAndGet(), "Hello, World!")

}