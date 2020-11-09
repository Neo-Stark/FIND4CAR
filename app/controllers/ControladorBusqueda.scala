package controllers

import java.time.LocalDateTime

import models.{Busqueda, Trayecto, Viaje}

case class ControladorBusqueda private(busqueda: Busqueda) {
  var listaViajes: List[Viaje] = {
    List(
      Viaje(Trayecto("algeciras", "granada"), 20, LocalDateTime.now().plusHours(12), 1),
      Viaje(Trayecto("algeciras", "granada"), 18, LocalDateTime.now().plusDays(1), 1),
      Viaje(Trayecto("estepona", "granada"), 18, LocalDateTime.now().plusDays(1).plusHours(2), 3),
      Viaje(Trayecto("estepona", "guadix"), 18, LocalDateTime.now().plusDays(1).plusHours(3), 2),
    )
  }
  def realizarBusqueda:List[Viaje] = listaViajes
}

object ControladorBusqueda {
  def apply(busqueda: Busqueda): ControladorBusqueda = new ControladorBusqueda(busqueda)
}
