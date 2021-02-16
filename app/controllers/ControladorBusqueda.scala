package controllers

import java.time.LocalDateTime
import models.{Busqueda, Trayecto, Viaje, ViajeDao}

case class ControladorBusqueda private(busqueda: Busqueda) {
  var listaViajes: List[Viaje] = ViajeDao.listaViajes

  def buscarFecha:List[Viaje] = listaViajes.filter(v => v.fecha.isAfter(busqueda.fecha))

  def buscarPrecio:List[Viaje] = listaViajes.filter(v => v.precio <= busqueda.precio)
}
