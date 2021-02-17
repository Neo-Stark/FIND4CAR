import controllers._
import models._
import org.scalatestplus.play._

import java.time.LocalDateTime

class controladorAlertas_unitTests extends PlaySpec {

  AlertaDao.listaAlertas.clear()
  AlertaDao.listaAlertas += Alerta(1, Trayecto("Algeciras", "Granada"), 18)
  val controladorAlertas: ControladorAlertas = ControladorAlertas()
  "ControladorAlertas" must {
    "Obtiene una alerta - id correcto" in {
        assert(controladorAlertas.getAlerta(1).orNull.id.get == 1)
    }
    "Obtiene una alerta - id incorrecto" in {
        assert(controladorAlertas.getAlerta(99).isEmpty)
    }
    "Crea una alerta" in {
      val listaAlertas = controladorAlertas.listaAlertas
      val sizeListaAntes = listaAlertas.size
      val nuevaAlerta = Alerta(2, trayecto=Trayecto("Sevilla", "Madrid"))
      controladorAlertas.crearAlerta(nuevaAlerta)
      assert(listaAlertas.size > sizeListaAntes)
    }
    "Modifica una alerta" in {
      val id = controladorAlertas.listaAlertas.last.id.get
      val nuevaAlerta = Alerta(id, Trayecto("Sevilla", "Madrid"), precio = 18)
      controladorAlertas.modificarAlerta(id, nuevaAlerta)
      val alertaModificada = controladorAlertas.getAlerta(id).orNull
      assert(alertaModificada.precio.getOrElse(0) == 18)
    }
    "Elimina una alerta" in {
      val id = controladorAlertas.listaAlertas.last.id.get
      assert(controladorAlertas.eliminarAlerta(id))
    }

  }
}
