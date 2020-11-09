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

  }
}
