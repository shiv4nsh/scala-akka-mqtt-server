package com.knoldus

import akka.actor.{Props, ActorSystem}
import akka.event.slf4j.Logger
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl._
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by shivansh on 14/7/16.
  */

class MQTTServer(implicit val system: ActorSystem,
                 implicit val materializer: ActorMaterializer) {
  val config = ConfigFactory.load()
  val port = config.getInt("akka.mqtt.port")
  val url = config.getString("akka.mqtt.url")
  val logger = Logger.root
  def start() = {
    val bindedConnection = Tcp(system).bind(interface = url, port = port)
      .to(Sink.foreach { connection =>
        println("New client connection is accepted from " + connection.remoteAddress)
        val mqttConnectionHandler = system.actorOf(Props(new MQTTConnectionHandler()),
          connection.remoteAddress.getHostString + ":" + connection.remoteAddress.getPort)
        val publisher = ActorPublisher[ByteString](mqttConnectionHandler)
        Source(publisher).via(connection.flow).to(Sink.foreach(mqttConnectionHandler ! _)).run()
      })
      .run()
    bindedConnection.onComplete {
      case Success(b) => println("akka-mqtt server is started at: " + b.localAddress)
      case Failure(e) => println(s"Could not start the server $url:$port: ${e.getMessage}")
        system.terminate()
    }
  }
}

object Boot extends App {

  implicit val system = ActorSystem("akka-mqtt-server")
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val server = new MQTTServer()
  server.start
}
