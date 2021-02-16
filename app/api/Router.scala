package api

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

import javax.inject.Inject

class Router @Inject()(controller: Controller) extends SimpleRouter{
  override def routes: Routes = {
    case GET(p"/") => controller.index
    case GET(p"/viajes") => controller.viajes
    case GET(p"/viajes/buscar") => controller.buscar
    case GET(p"/viajes/buscar/fecha") => controller.buscarFecha
    case GET(p"/viajes/buscar/precio") => controller.buscarPrecio
    case GET(p"/viajes/$id") => controller.viajes(id.toLong)
    case GET(p"/alertas") => controller.alertas
    case GET(p"/alertas/$id") => controller.alertas(id.toLong)
    case POST(p"/alertas") => controller.crearAlerta
    case PUT(p"/alertas/$id") => controller.modificarAlerta(id.toLong)
    case DELETE(p"/alertas/$id") => controller.eliminarAlerta(id.toLong)

  }
}
