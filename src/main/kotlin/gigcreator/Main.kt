package gigcreator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration::class])
open class Main {

}

fun main(args: Array<out String>) {
    val context = SpringApplication.run(arrayOf(Main::class.java), args)
    var line = readln()
    while (line != "sus") line = readln()
    context.close()
}