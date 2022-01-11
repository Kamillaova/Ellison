package dev.fstudio.mcworldstats.util

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.required

object CommandArguments {
    private val parser = ArgParser("mcworldstats")

    val host by parser.option(
        ArgType.String,
        "ip",
        "i",
        "Host IP"
    ).required()

    val port by parser.option(
        ArgType.Int,
        "port",
        "p",
        "Host port"
    ).default(8080)

    val ssl by parser.option(
        ArgType.Boolean,
        "ssl",
        "s",
        "SSL connection"
    ).default(false)

    val path by parser.option(
        ArgType.String,
        "path",
        "f",
        "Full path to minecraft world stats"
    ).required()

    fun parseArgs(args: Array<String>) {
        parser.parse(args)
    }
}