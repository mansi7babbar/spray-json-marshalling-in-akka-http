package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global

object Driver extends App {
  println("Marshalling of given Scala case class object to the respective Spray JSON AST :")
  println(JsonProtocol.marshalling)
  println()
  println("Unmarshalling of given Spray JSON AST to the respective Scala case class object :")
  JsonProtocol.unmarshalling.map(caseClassObject => println(caseClassObject))
}
