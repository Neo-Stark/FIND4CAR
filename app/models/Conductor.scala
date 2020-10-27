package models

import java.net.URL

case class Conductor(
                    nombre: String,
                    valoracion: Int,
                    foto: URL,
                    coche: String, // TODO: Pensar en crear una clase coche
                    enlace_perfil: URL,
                    ) {

}
