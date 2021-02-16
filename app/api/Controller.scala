package api

import models.{Busqueda, Conductor, Trayecto, Viaje, ViajeDao}
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import JsonReaders._
import controllers.ControladorBusqueda

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}

@Singleton
class Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  private val logger = Logger(getClass)
  private val listaViajes = ViajeDao.listaViajes
  private val busqueda = ControladorBusqueda

  def index: Action[AnyContent] = Action {
    logger.info("acceso raíz")
    Ok("FIND4CAR - API")
  }

  def getAll: Action[AnyContent] = Action {
    logger.info("obtener todos los viajes")
    if (listaViajes.isEmpty) NoContent
    else Ok(Json.toJson(listaViajes))
  }

  def get(id: Long): Action[AnyContent] = Action {
    logger.info("busqueda por id: " + id + "[ID]")
    listaViajes.find(_.id == id) match {
      case Some(viaje) => Ok(Json.toJson(viaje))
      case None => NoContent
    }
  }

  def buscar: Action[JsValue] = Action(parse.json) { request =>
    val peticion = request.body.validate[Busqueda]
    logger.info("buscar: " + peticion.getOrElse("Petición no válida"))
    peticion.fold(
      errores => BadRequest(Json.obj("error" -> JsError.toJson(errores))),
      peticionOk => {
        val viajes = busqueda(peticionOk).buscar
        Ok(Json.toJson(viajes))
      }
    )
  }
  def buscarFecha: Action[JsValue] = Action(parse.json) { request =>
    val peticion = request.body.validate[Busqueda]
    logger.info("buscarFecha: " + peticion.getOrElse("Petición no válida"))
    peticion.fold(
      errores => BadRequest(Json.obj("error" -> JsError.toJson(errores))),
      peticionOk => {
        val viajes = busqueda(peticionOk).buscarFecha
        Ok(Json.toJson(viajes))
      }
    )
  }
  def buscarPrecio: Action[JsValue] = Action(parse.json) { request =>
    val peticion = request.body.validate[Busqueda]
    logger.info("buscarPrecio: " + peticion.getOrElse("Petición no válida"))
    peticion.fold(
      errores => BadRequest(Json.obj("error" -> JsError.toJson(errores))),
      peticionOk => {
        val viajes = busqueda(peticionOk).buscarPrecio
        Ok(Json.toJson(viajes))
      }
    )
  }
}
