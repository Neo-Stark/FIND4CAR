import java.time.LocalDateTime

import controllers._
import models._
import org.scalatestplus.play._

class UnitSpec extends PlaySpec {

  val busqueda: ControladorBusqueda = ControladorBusqueda(
    Busqueda(Trayecto("Algeciras", "Granada"), fecha = LocalDateTime.now().plusHours(1), precio = 18)
  )
  val viajes: List[Viaje] = ViajeDao.listaViajes
  "ControladorBusqueda" must {
    "devolver una lista de viajes con fecha válida" in {
      busqueda.buscarFecha.map(viaje => assert(viaje.fecha.isAfter(LocalDateTime.now())))
    }
    "devolver una lista de viajes con fecha posterior a la dada" in {
      busqueda.buscarFecha.map(viaje => assert(viaje.fecha.isAfter(busqueda.busqueda.fecha.get)))
    }

    "devolver una lista de viajes con origen y destino correctos" in {
      busqueda.buscar.map(viaje => viaje.trayecto.origen must not be viaje.trayecto.destino)
    }

    "devolver viajes con un precio menor al dado" in {
      busqueda.buscarPrecio.map(viaje => assert(viaje.precio <= busqueda.busqueda.precio.get))
    }
    "devolver viajes con un precio correcto" in {
      viajes.map(_.precio must be > 0.0)
    }
  }
}
