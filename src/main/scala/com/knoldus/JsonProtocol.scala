package com.knoldus

import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object JsonProtocol extends DefaultJsonProtocol {

  implicit object JsonFormat extends RootJsonFormat[Website] {
    def write(obj: Website): JsValue = JsObject(
      "ID" -> JsNumber(obj.id),
      "name" -> JsString(obj.name),
      "URL" -> JsString(obj.url),
      "description" -> JsString(obj.description),
      "subscribers_count" -> JsNumber(obj.subscribersCount)
    )

    def read(json: JsValue): Website = {
      val fields = json.asJsObject.fields
      Website(
        fields("ID").convertTo[Int],
        fields("name").convertTo[String],
        fields("URL").convertTo[String],
        fields("description").convertTo[String],
        fields("subscribers_count").convertTo[Int]
      )
    }
  }

  def marshalling: JsValue = {
    Website(24082518,"particuliersimmobilier","https://particuliersimmobilier.wordpress.com",
      "Just another WordPress.com site",0).toJson
  }

  def unmarshalling: Future[Website] = {
    ExtractJsonUsingAkkaHttp.extractJson.map(extractedJson => extractedJson.convertTo[Website])
  }

}
