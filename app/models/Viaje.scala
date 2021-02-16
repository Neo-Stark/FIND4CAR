package models

import java.time.LocalDateTime

case class Trayecto(origen: Option[String], destino: Option[String])

object Trayecto {
  def apply(origen:String, destino:String): Trayecto = {
    require(origen.toLowerCase != destino.toLowerCase, "Origen y destino no pueden ser el mismo")
    Trayecto(Option(origen), Option(destino))
  }
}

case class Viaje(
                  id: Long,
                  trayecto: Trayecto,
                  precio: Double,
                  fecha: LocalDateTime,
                  plazas: Int,
                  conductor: Option[Conductor] = None,
                  informacion: Option[String] = None
                ) {
}

object Viaje {
  def apply(id: Long, trayecto: Trayecto, precio: Double, fecha: LocalDateTime, plazas: Int, conductor: Option[Conductor]): Viaje
  = Viaje(id, trayecto, precio, fecha, plazas, conductor, None)

  def apply(id: Long, trayecto: Trayecto, precio: Double, fecha: LocalDateTime, plazas: Int): Viaje =
    Viaje(id, trayecto, precio, fecha, plazas, None, None)
}
