package models

import java.time.LocalDateTime

case class Trayecto(inicio: String, fin: String)

case class Viaje(
                  trayecto: Trayecto,
                  precio: Double,
                  fecha: LocalDateTime,
                  plazas: Int,
                  conductor: Option[Conductor],
                  informacion: Option[String]
                ) {
}

object Viaje {
  def apply(trayecto: Trayecto, precio: Double, fecha: LocalDateTime, plazas: Int, conductor: Option[Conductor]): Viaje
  = Viaje(trayecto, precio, fecha, plazas, conductor, None)

  def apply(trayecto: Trayecto, precio: Double, fecha: LocalDateTime, plazas: Int): Viaje =
    Viaje(trayecto, precio, fecha, plazas, None, None)
}