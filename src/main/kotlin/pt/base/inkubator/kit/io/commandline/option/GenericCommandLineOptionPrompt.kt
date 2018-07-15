package pt.base.inkubator.kit.io.commandline.option

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import com.github.ajalt.clikt.parameters.arguments.argument

class CommandLineOption : CliktCommand() {
    val promptText by argument()

    override fun run() {
        TermUi.echo(TermUi.prompt(promptText))
    }
}

fun main(args: Array<String>) = CommandLineOption().main(args)


