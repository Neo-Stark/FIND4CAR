package controllers

import models.{Alerta, AlertaDao}

import scala.collection.mutable.ArrayBuffer

case class ControladorAlertas() {

  val listaAlertas: ArrayBuffer[Alerta] = AlertaDao.listaAlertas

  def crearAlerta(alerta: Alerta):Any = {
    alerta.id match {
      case Some(_) => listaAlertas += alerta
      case None => listaAlertas += Alerta(alerta, listaAlertas.lastOption.
        getOrElse(Alerta(0, alerta.trayecto)).id.get + 1)
    }
  }

  def modificarAlerta(id: Long, nuevaAlerta: Alerta): Boolean = {
    val busqueda = listaAlertas.find(_.id.get == id)
    busqueda match {
      case Some(alerta) =>
        listaAlertas -= alerta
        crearAlerta(nuevaAlerta)
        true
      case None => false
    }
  }

  def eliminarAlerta(id: Long): Boolean = {
    val busqueda = listaAlertas.find(_.id.get == id)
    busqueda match {
      case Some(alerta) =>
        listaAlertas -= alerta
        true
      case None => false
    }
  }
}
