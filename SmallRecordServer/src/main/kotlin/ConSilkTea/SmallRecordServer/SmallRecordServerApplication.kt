package ConSilkTea.SmallRecordServer

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.cio.websocket.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.ServerSocket
import java.time.Duration
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.Executors
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject


@SpringBootApplication
class SmallRecordServerApplication

fun main(args: Array<String>) {
    runApplication<SmallRecordServerApplication>(*args)
    val server = EchoServer(1234)
    server.start()
}

class EchoServer(private val port: Int) {
    private val executor = Executors.newFixedThreadPool(10)

    fun start() {
        val serverSocket = ServerSocket(port)
        println("Server listening on port $port")

        while (true) {
            val clientSocket = serverSocket.accept()
            println("Client connected: ${clientSocket.inetAddress.hostAddress}")

            executor.submit {
                handleClient(clientSocket)
            }
        }
    }

    private fun handleClient(clientSocket: Socket) {
        val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
        val writer = PrintWriter(clientSocket.getOutputStream(), true)

        while (true) {
            val message = reader.readLine() ?: break
            val messageJson = Json.decodeFromString<JsonObject>(message)
            val pureMessageJson = messageJson["messageType"].toString().replace("\"", "")
            if(pureMessageJson == "barcodeNum"){
                println("바코드 번호입니다.")
                println(messageJson)
                println(messageJson["messageType"].toString())
                println(messageJson["barcodeNum"].toString())
            }
            else if(pureMessageJson == "communityWrite"){
                println("게시글 생성입니다.")
                println(messageJson)
                println(messageJson["messageType"].toString())
                println(messageJson["title"].toString())
                println(messageJson["content"].toString())
            }







            // Echo the message back to the client
          //  writer.println("Server response: $message")
        }

        println("Client disconnected: ${clientSocket.inetAddress.hostAddress}")
        clientSocket.close()
    }
}

