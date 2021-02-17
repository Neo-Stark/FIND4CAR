package api

import models.{Alerta, AlertaDao, Busqueda, ViajeDao}
import play.api.Logger
import play.api.libs.json._
import play.api.mvc._
import JsonReaders._
import controllers._

import javax.inject.{Inject, Singleton}

@Singleton
class Controller @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  private val logger = Logger(getClass)
  private val listaViajes = ViajeDao.listaViajes
  private val controladorAlertas = ControladorAlertas()
  private val busqueda = ControladorBusqueda

  def index: Action[AnyContent] = Action {
    logger.info("acceso raíz")
    Ok("FIND4CAR - API")
  }

  def viajes: Action[AnyContent] = Action {
    logger.info("obtener todos los viajes")
    if (listaViajes.isEmpty) NoContent
    else Ok(Json.toJson(listaViajes))
  }

  def viajes(id: Long): Action[AnyContent] = Action {
    logger.info("busqueda por id: " + id + "[ID]")
    listaViajes.find(_.id == id) match {
      case Some(viaje) => Ok(Json.toJson(viaje))
      case None => NotFound
    }
  }

  def buscar: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Busqueda]
      .fold(
        errores => {
          logger.error("buscar: " + errores)
          BadRequest(Json.obj("error" -> JsError.toJson(errores)))
        },
        peticionOk => {
          logger.info("buscar: " + peticionOk)
          Ok(Json.toJson(busqueda(peticionOk).buscar))
        }
      )
  }

  def buscarFecha: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Busqueda]
      .fold(
        errores => {
          logger.error("buscarFecha: " + errores)
          BadRequest(Json.obj("error" -> JsError.toJson(errores)))
        },
        peticionOk => {
          logger.info("buscarFecha: " + peticionOk)
          Ok(Json.toJson(busqueda(peticionOk).buscarFecha))
        }
      )
  }

  def buscarPrecio: Action[JsValue] = Action(parse.json) { request =>
    request.body.validate[Busqueda]
      .fold(
        errores => {
          logger.error("buscarPrecio: " + errores)
          BadRequest(Json.obj("error" -> JsError.toJson(errores)))
        },
        peticionOk => {
          logger.info("buscarPrecio: " + peticionOk)
          Ok(Json.toJson(busqueda(peticionOk).buscarPrecio))
        }
      )
  }

  def alertas: Action[AnyContent] = Action {
    logger.info("obtener todas las alertas")
    if (controladorAlertas.listaAlertas.isEmpty) NoContent
    else Ok(Json.toJson(controladorAlertas.listaAlertas))
  }

  def alertas(id: Long): Action[AnyContent] = Action {
    logger.info("busqueda de alerta por id: " + id + "[ID]")
    controladorAlertas.listaAlertas.find(_.id.get == id) match {
      case Some(alerta) => Ok(Json.toJson(alerta))
      case None => NotFound
    }
  }

  def crearAlerta: Action[JsValue] = Action(parse.json) { request =>
    val peticion = request.body.validate[Alerta]
    peticion.fold(
      errores => {
        logger.error("crearAlerta: " + errores)
        BadRequest(Json.obj("errores" -> JsError.toJson(errores)))
      },
      peticionOk => {
        logger.info("crearAlerta: " + peticionOk)
        controladorAlertas.crearAlerta(peticionOk)
        Created(Json.obj("recurso" -> ("alertas/" + controladorAlertas.listaAlertas.
          last.id.get.toString)))
      }
    )
  }

  def modificarAlerta(id: Long): Action[JsValue] = Action(parse.json) { request =>
    val peticion = request.body.validate[Alerta]
    peticion.fold(
      errores => {
        logger.error("modificarAlerta: " + errores)
        BadRequest(Json.obj("errores" -> JsError.toJson(errores)))
      },
      peticionOk => {
        logger.info("modificarAlerta: " + peticionOk)
        if (controladorAlertas.modificarAlerta(id, peticionOk))
          Ok(Json.obj("recurso-modificado" -> peticionOk,
            "id" -> id))
        else NotFound
      }
    )
  }

  def eliminarAlerta(id: Long): Action[AnyContent] = Action {
    logger.info("eliminarAlerta: " + id)
    if (controladorAlertas.eliminarAlerta(id))
      Ok(Json.obj("recurso-eliminado" -> id))
    else NotFound
  }

}
