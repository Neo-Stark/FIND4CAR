package api

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

import javax.inject.Inject

class Router @Inject()(controller: Controller) extends SimpleRouter{
  override def routes: Routes = {
    case GET(p"/") => controller.index
    case GET(p"/viajes") => controller.getAll
    case GET(p"/viajes/buscar") => controller.buscar
    case GET(p"/viajes/buscar/fecha") => controller.buscarFecha
    case GET(p"/viajes/buscar/precio") => controller.buscarPrecio
    case GET(p"/viajes/$id") => controller.get(id.toLong)
  }
}
