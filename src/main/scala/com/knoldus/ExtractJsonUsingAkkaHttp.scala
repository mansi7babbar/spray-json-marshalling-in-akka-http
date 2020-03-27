package com.knoldus

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import spray.json._
import scala.concurrent.{ExecutionContextExecutor, Future}

object ExtractJsonUsingAkkaHttp {
  def extractJson: Future[JsValue] = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    val response: Future[HttpResponse] = Http().singleRequest(
      HttpRequest(uri = "https://public-api.wordpress.com/rest/v1.1/sites/24082518"))
    response.flatMap(res => Unmarshal(res.entity).to[String].map(jsonData => jsonData.parseJson))
  }
}
