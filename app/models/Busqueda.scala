package models

import java.time._

case class Busqueda(trayecto: Trayecto, precio: Double, fecha: LocalDateTime)

object Busqueda {
  def apply(trayecto: Trayecto, precio: Double, fecha: LocalDateTime): Busqueda = {
    require(fecha.isAfter(LocalDateTime.now()), "Fecha y hora no válidas. Deben ser posteriores a la fecha actual")
    require(precio > 0, "El precio no puede ser negativo")
    new Busqueda(trayecto, precio, fecha)
  }
  def apply(trayecto: Trayecto, precio: Double): Busqueda =
    apply(trayecto, precio, LocalDateTime.now().plusMinutes(10))
  def apply(trayecto: Trayecto, fecha:LocalDateTime): Busqueda =
    apply(trayecto, Double.MaxValue, fecha)
  def apply(trayecto: Trayecto): Busqueda = apply(trayecto, Double.MaxValue)
}
