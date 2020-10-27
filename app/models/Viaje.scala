package models

case class Trayecto(inicio: String, fin: String)

case class Viaje(
                  trayecto: Trayecto,
                  precio: Double,
                  fecha: Fecha,
                  plazas: Int,
                  conductor: Conductor,
                  informacion: String,
                )

case class Fecha(dia: Int, hora: Int)

// Aplicación de prueba
object main extends App {
  val data: List[Viaje] = List(Viaje(Trayecto("algeciras", "granada"), 20, Fecha(1, 23), 1, null, null))
  data foreach (d => if (d.precio < 25) println("es barato"))

}
