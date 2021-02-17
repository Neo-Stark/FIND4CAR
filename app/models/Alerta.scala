package models

import java.time.LocalDateTime

case class Alerta private(id: Option[Long], trayecto: Trayecto, precio: Option[Double], fecha: Option[LocalDateTime])

object Alerta {
  def apply(id: Long, trayecto: Trayecto, precio: Double, fecha: LocalDateTime): Alerta = {
    require(fecha.isAfter(LocalDateTime.now()), "Fecha y hora no válidas. Deben ser posteriores a la fecha actual")
    require(precio > 0, "El precio no puede ser negativo")
    new Alerta(Option(id), trayecto, Option(precio), Option(fecha))
  }

  def apply(id: Long, trayecto: Trayecto, precio: Double): Alerta =
    apply(id, trayecto, precio, LocalDateTime.now().plusMinutes(10))

  def apply(id: Long, trayecto: Trayecto, fecha: LocalDateTime): Alerta =
    apply(id, trayecto, Double.MaxValue, fecha)

  def apply(id: Long, trayecto: Trayecto): Alerta =
    apply(id, trayecto, Double.MaxValue)

  def apply(alerta: Alerta, id: Long): Alerta =
    new Alerta(Option(id), alerta.trayecto, alerta.precio, alerta.fecha)
}


