package com.knoldus

import akka.actor.Actor
import akka.event.slf4j.Logger
import akka.util.ByteString
import scodec.bits.BitVector

import scala.util.Try

/**
  * Created by shivansh on 14/7/16.
  */

class MQTTConnectionHandler extends Actor {
  val logger = Logger.root
  def receive = {
    case a: Any =>
      println(s"Hey BitVector here is :::::::${Try(a.asInstanceOf[ByteString]).toOption.fold(BitVector.empty)(x => BitVector(x))}")
  }
}
