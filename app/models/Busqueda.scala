package models

import java.time._

case class Localizacion(lat: Double, long: Double)

case class Busqueda(origen: Localizacion, destino: Localizacion, precio: Double, fecha: LocalDateTime)

object Busqueda {
  def apply(origen: Localizacion, destino: Localizacion, precio: Double, fecha: LocalDateTime): Busqueda = {
    require(fecha.isAfter(LocalDateTime.now()), "Fecha y hora no válidas. Deben ser posteriores a la fecha actual")
    require(origen != destino, "Origen y destino no pueden ser el mismo")
    require(precio > 0, "El precio no puede ser negativo")
    new Busqueda(origen, destino, precio, fecha)
  }
  def apply(origen: Localizacion, destino: Localizacion, precio: Double): Busqueda =
    apply(origen, destino, precio, LocalDateTime.now().plusMinutes(10))
  def apply(origen: Localizacion, destino: Localizacion, fecha:LocalDateTime): Busqueda =
    apply(origen, destino, Double.MaxValue, fecha)
  def apply(origen: Localizacion, destino: Localizacion): Busqueda = apply(origen, destino, Double.MaxValue)
}
