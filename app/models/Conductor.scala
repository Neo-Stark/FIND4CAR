package models

case class Conductor(
                    nombre: String,
                    valoracion: Int,
                    foto: String,
                    coche: String, // TODO: Pensar en crear una clase coche
                    enlace_perfil: String, // Es un enlace genérico, la plataforma a la que apunte será indiferente y se definirá en la ejecución
                    ) {

}
