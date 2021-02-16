package models

object AlertaDao {
  import scala.collection.mutable.ArrayBuffer
  var listaAlertas : ArrayBuffer[Alerta] = ArrayBuffer()
  listaAlertas += Alerta(1, Trayecto("Algeciras", "Granada"), 18)
}
