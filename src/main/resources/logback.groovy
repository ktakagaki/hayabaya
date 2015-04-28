import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.status.OnConsoleStatusListener

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

//statusListener(OnConsoleStatusListener)
//scan("30 seconds") // Automatically reloading configuration file upon modification, scan every 30 seconds
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} Groovy - %msg%n"
    }
}

logger("com.lordofthejars.foo", INFO)
root(DEBUG, ["STDOUT"])