package controllers

import java.time.LocalDateTime
import models.{Busqueda, Trayecto, Viaje, ViajeDao}

case class ControladorBusqueda private(busqueda: Busqueda) {
  var listaViajes: List[Viaje] = ViajeDao.listaViajes

  def buscarTrayecto:List[Viaje] = listaViajes
    .filter(_.trayecto.origen.toLowerCase == busqueda.trayecto.origen.toLowerCase)
    .filter(_.trayecto.destino.toLowerCase == busqueda.trayecto.destino.toLowerCase)

  def buscarFecha:List[Viaje] = buscarTrayecto.filter(v => v.fecha
    .isAfter(busqueda.fecha.getOrElse(LocalDateTime.now().plusMinutes(10))))

  def buscarPrecio:List[Viaje] = buscarTrayecto.filter(v => v.precio <= busqueda.precio.getOrElse(1000.0))

  def buscar : List[Viaje] = {
    val busquedaFecha = buscarFecha
    val busquedaPrecio = buscarPrecio
    busquedaFecha.intersect(busquedaPrecio)
  }
}
