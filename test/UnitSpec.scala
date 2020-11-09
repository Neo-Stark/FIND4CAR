import java.time.LocalDateTime

import controllers._
import models._
import org.scalatestplus.play._

class UnitSpec extends PlaySpec {

  val busqueda: ControladorBusqueda = ControladorBusqueda(
    Busqueda(Localizacion(23.25, 12.5), Localizacion(38.12, 121.16), fecha = LocalDateTime.now().plusSeconds(1))
  )
  val viajes: List[Viaje] = busqueda.realizarBusqueda
  "ControladorBusqueda" must {
    "devolver una lista de viajes con fecha válida" in {
      viajes.map(viaje => assert(viaje.fecha.isAfter(LocalDateTime.now())))
    }
    "devolver una lista de viajes con fecha posterior a la dada" in {
      viajes.map(viaje => assert(viaje.fecha.isAfter(busqueda.busqueda.fecha)))
    }

    "devolver una lista de viajes con origen y destino correctos" in {
      viajes.map(viaje => viaje.trayecto.inicio must not be viaje.trayecto.fin)
    }

    "devolver viajes con un precio menor al dado" in {
      val b2: ControladorBusqueda = ControladorBusqueda(
        Busqueda(Localizacion(23.25, 12.5), Localizacion(38.12, 121.16), fecha = LocalDateTime.now().plusDays(1), precio = 20))
      b2.realizarBusqueda.map(viaje => assert(viaje.precio <= b2.busqueda.precio))
    }
    "devolver viajes con un precio correcto" in {
      viajes.map(_.precio must be > 0.0)
    }
  }
}
