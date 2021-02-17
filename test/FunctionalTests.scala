import models.{Alerta, Trayecto, Viaje}
import play.api.libs.json.{JsResult, Json}
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import api.JsonReaders
import api.JsonReaders._

import java.time.LocalDateTime

class FunctionalTests extends PlaySpec with GuiceOneAppPerSuite {
  "FIND4CAR application" must {
    "responder a peticiones" in {
      val request = FakeRequest(GET, "/").withHeaders(HOST -> "localhost:9000")
      val response = route(app, request).get
      assert(contentAsString(response) == "FIND4CAR - API")
    }
    "responder un 404 cuando la ruta no existe" in {
      val request = FakeRequest(GET, "/noexiste").withHeaders(HOST -> "localhost:9000")
      val response = route(app, request).get
      status(response) must be(NOT_FOUND)
    }
    "devolver una lista de viajes en formato JSON" in {
      val request = FakeRequest(GET, "/viajes").withHeaders(HOST -> "localhost:9000")
      val response = route(app, request).get
      val body = contentAsJson(response)
      val viajes: Seq[Viaje] = Json.fromJson[Seq[Viaje]](body).get
      val viajeEjemplo = viajes.filter(_.id == 1).head
      // Lo hacemos así porque si no el tiempo no coincide
      viajeEjemplo mustBe Viaje(1, Trayecto("algeciras", "granada"), 20, viajeEjemplo.fecha, 1)
      status(response) mustBe OK
    }
    "buscar un viaje" in {
      val request = FakeRequest(GET, "/viajes/buscar").withHeaders(HOST -> "localhost:9000", CONTENT_TYPE -> JSON).
        withBody(
          """{
            |    "trayecto":{
            |        "origen":"Algeciras",
            |        "destino":"Granada"
            |    },
            |    "fecha":"2021/02/16 15:30",
            |    "precio":18
            |}""".stripMargin)
      val response = route(app, request).get
      val body = contentAsJson(response)
      val viajes: Seq[Viaje] = Json.fromJson[Seq[Viaje]](body).get
      val viajeEjemplo = viajes.filter(_.id == 2).head
      // Lo hacemos así porque si no el tiempo no coincide
      viajeEjemplo mustBe Viaje(2, Trayecto("algeciras", "granada"), 18, viajeEjemplo.fecha, 1)
      status(response) mustBe OK
    }
    "buscar un viaje - por fecha" in {
      val request = FakeRequest(GET, "/viajes/buscar/fecha").withHeaders(HOST -> "localhost:9000", CONTENT_TYPE -> JSON).
        withBody(
          """{
            |    "trayecto":{
            |        "origen":"Algeciras",
            |        "destino":"Granada"
            |    },
            |    "fecha":"2021/02/16 15:30"
            |}""".stripMargin)
      val response = route(app, request).get
      val body = contentAsJson(response)
      val viajes: Seq[Viaje] = Json.fromJson[Seq[Viaje]](body).get
      val viajeEjemplo = viajes.filter(_.id == 2).head
      // Lo hacemos así porque si no el tiempo no coincide
      viajeEjemplo mustBe Viaje(2, Trayecto("algeciras", "granada"), 18, viajeEjemplo.fecha, 1)
      status(response) mustBe OK
    }
    "buscar un viaje - por precio" in {
      val request = FakeRequest(GET, "/viajes/buscar/precio").withHeaders(HOST -> "localhost:9000", CONTENT_TYPE -> JSON).
        withBody(
          """{
            |    "trayecto":{
            |        "origen":"Algeciras",
            |        "destino":"Granada"
            |    },
            |    "fecha":"2021/02/16 15:30"
            |}""".stripMargin)
      val response = route(app, request).get
      val body = contentAsJson(response)
      val viajes: Seq[Viaje] = Json.fromJson[Seq[Viaje]](body).get
      val viajeEjemplo = viajes.filter(_.id == 2).head
      // Lo hacemos así porque si no el tiempo no coincide
      viajeEjemplo mustBe Viaje(2, Trayecto("algeciras", "granada"), 18, viajeEjemplo.fecha, 1)
      status(response) mustBe OK
    }
    "crear alerta" in {
      val request = FakeRequest(POST, "/alertas").withHeaders(HOST -> "localhost:9000", CONTENT_TYPE -> JSON).
        withBody(
          """{
            |    "trayecto":{
            |        "origen":"Estepona",
            |        "destino":"Granada"
            |    },
            |    "fecha":"2021/02/16 22:30",
            |    "precio":15
            |}""".stripMargin)
      val response = route(app, request).get
      val body = contentAsJson(response)
      (body \ "recurso").as[String] mustBe "alertas/2"
      status(response) mustBe CREATED
    }
  }
  "devolver una lista de alertas en formato JSON" in {
    val request = FakeRequest(GET, "/alertas").withHeaders(HOST -> "localhost:9000")
    val response = route(app, request).get
    val body = contentAsJson(response)
    val alertas: Seq[Alerta] = Json.fromJson[Seq[Alerta]](body).get
    val alertaEjemplo = alertas.filter(_.id.get == 1).head
    alertaEjemplo mustBe Alerta(1, Trayecto("Algeciras", "Granada"), 18, alertaEjemplo.fecha.get)
    status(response) mustBe OK
  }
  "modificar alerta" in {
    val request = FakeRequest(PUT, "/alertas/1").withHeaders(HOST -> "localhost:9000", CONTENT_TYPE -> JSON).
      withBody(
        """{
          |    "trayecto":{
          |        "origen":"Estepona",
          |        "destino":"Granada"
          |    },
          |    "fecha":"2021/02/16 22:30",
          |    "precio":15
          |}""".stripMargin)
    val response = route(app, request).get
    val body = contentAsJson(response)
    val alertaModificada = (body \ "recurso-modificado").validate[Alerta].get
    alertaModificada mustBe new Alerta(None, Trayecto("Estepona", "Granada"), Some(15), alertaModificada.fecha)
    status(response) mustBe OK
  }
  "borrar una alerta" in {
    val request = FakeRequest(DELETE, "/alertas/1").withHeaders(HOST -> "localhost:9000")
    val response = route(app, request).get
    val body = contentAsJson(response)
    (body \ "recurso-eliminado").as[Int] mustBe 1
    status(response) mustBe OK
  }
}
