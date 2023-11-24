package ConSilkTea.SmallRecordServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SmallRecordServerApplication

fun main(args: Array<String>) {
    runApplication<SmallRecordServerApplication>(*args)
    println("Server Open Complete!")
}

