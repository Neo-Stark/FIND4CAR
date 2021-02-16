package models

import java.time.LocalDateTime

object ViajeDao {
  val listaViajes: List[Viaje] = List(
      Viaje(1, Trayecto(Option("algeciras"), Option("granada")), 20, LocalDateTime.now().plusHours(12), 1),
      Viaje(2, Trayecto(Option("algeciras"), Option("granada")), 18, LocalDateTime.now().plusDays(1), 1),
      Viaje(3, Trayecto("estepona", "granada"), 18, LocalDateTime.now().plusDays(1).plusHours(2), 3),
      Viaje(4, Trayecto("estepona", "guadix"), 18, LocalDateTime.now().plusDays(1).plusHours(3), 2),
    )
}
