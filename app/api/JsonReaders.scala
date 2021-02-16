package api

import models.{Busqueda, Conductor, Trayecto, Viaje}

import java.time.LocalDateTime
import play.api.libs.json._

import java.time.format.DateTimeFormatter
import play.api.libs.json.Reads.localDateTimeReads

object JsonReaders {
  val dateTimeFormat = "yyyy/MM/dd HH:mm"

  val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat)
  implicit val localDateTimeReader: Reads[LocalDateTime] = Reads[LocalDateTime](js =>
      js.validate[String].map[LocalDateTime](dtString => LocalDateTime.parse(dtString, formatter)))

  val localDateTimeWriter: Writes[LocalDateTime] = (d: LocalDateTime) => JsString(d.format(formatter))

  implicit val localDateTimeFormat: Format[LocalDateTime] = Format(localDateTimeReader, localDateTimeWriter)

  implicit val conductorFormat: Format[Conductor] = Json.format[Conductor]
  implicit val trayectoFormat: Format[Trayecto] = Json.format[Trayecto]
  implicit val viajeFormat: Format[Viaje] = Json.format[Viaje]
  implicit  val busquedaFormat: Format[Busqueda] = Json.format[Busqueda]
}
