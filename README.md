# FIND4CAR

## Introducción. Motivación y problema a resolver

Este proyecto se trata de una idea personal que me surge constantemente cuando viajo. Yo suelo viajar a menudo usando coches compartidos (también llamado _carpooling_) y siempre que busco un viaje pierdo mucho tiempo buscando en las diferentes plataformas que existen actualmente, ya que tengo que realizar exactamente la misma búsqueda en los diferentes sitios.

La solución que yo propongo es crear un servicio que proporcione información sobre viajes compartidos ofertados en las diferentes plataformas (Blablacar, Amovens, etc) en un mismo lugar. Pudiendo comparar precios, opiniones de los conductores, horarios... Además de poder crear alertas para que cuando se publique un nuevo viaje (sea la plataforma que sea) el usuario pueda ser notificado. Las funcionalidades completas se irán detallando próximamente.

## Arquitectura de la aplicación

La arquitectura software que se ha estudiado y resulta más viable para esta aplicación y, por tanto, la que elegimos
es la _arquitectura dirigida por eventos_. El principal motivo por el que se ha elegido esta arquitectura y no otra es
que nuestro servicio debe conectarse y obtener información de diferentes sitios webs para funcionar. Por tanto, no resulta
viable una arquitectura secuencial que deje al servidor esperando una respuesta de otro servicio mientras tenemos otras
muchas tareas que no dependen de ella y que se podrían ejecutar en paralelo. Sin entrar en detalles de implementación,
en las Arquitecturas dirigidas por eventos tenemos diferentes protocolos para montar nuestro backend, en este caso vamos
a usar _WebSockets_ principalmente porque es una solución que no genera apenas overhead y se integra estupendamente bien
con el framework que hemos elegido para el desarrollo.

## Herramientas a utilizar

El lenguaje de programación que se ha elegido es _Scala_ por su potencia, versatilidad y su creciente popularidad que,
pese a no ser un experto, me atrae a aprenderlo y a ponerlo en práctica con este proyecto. Además, como marco de trabajo
(framework) se utilizará _Play Framework_, un conocido framework utilizado por grandes páginas webs como Linkedin que 
destaca por tener una alta velocidad, estar basado casi en su totalidad en conceptos de programación funcional y ofrece 
una programación reactiva que es esencial para la arquitectura dirigida por eventos que estamos utlizando y que permite
realizar tareas en paralelo. Además, ofrece herramientas adicionales para manejar BBDD, servicios para logs... y es de 
código abierto. Como se ha dicho, se utilizará la API de loggin que incorpora el propio framework y que internamente 
utiliza [Logback](http://logback.qos.ch/) como núcleo. En principio no será necesario el uso de BBDD, en el caso de que 
en futuras HU (Historias de Usuario) se contemple la necesidad de usarlas se estudiará que herramienta utilizar.

## [Roadmap](docs/roadmap.md)

## Clases definidas hasta ahora

- [Conductor.scala](app/models/Conductor.scala)
- [Viaje.scala](app/models/Viaje.scala)

## [Documentación adicional](http://neostark.wtf/FIND4CAR/"Enlace a documentación adicional")

- [Correcta configuración de git y GitHub](/docs/configuracion-git-github.md)

> Proyecto de la asignatura Cloud Computing I, curso 2020-21, Master Ingeniería Informática, ETSIIT UGR
