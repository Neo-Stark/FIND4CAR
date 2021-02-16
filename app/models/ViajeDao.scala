package models

import java.time.LocalDateTime

object ViajeDao {
  val listaViajes: List[Viaje] = List(
    Viaje(1, Trayecto("algeciras", "granada"), 20, LocalDateTime.now().plusHours(12), 1),
    Viaje(2, Trayecto("algeciras", "granada"), 18, LocalDateTime.now().plusDays(1), 1),
    Viaje(3, Trayecto("estepona", "granada"), 18, LocalDateTime.now().plusDays(1).plusHours(2), 3),
    Viaje(4, Trayecto("estepona", "granada"), 16, LocalDateTime.now().plusDays(1).plusHours(2), 3),
    Viaje(5, Trayecto("estepona", "guadix"), 18, LocalDateTime.now().plusDays(1).plusHours(3), 2),
  )
}
