# FIND4CAR

## Introducción. Motivación y problema a resolver

Este proyecto se trata de una idea personal que me surge constantemente cuando viajo. Yo suelo viajar a menudo usando coches compartidos (también llamado _carpooling_) y siempre que busco un viaje pierdo mucho tiempo buscando en las diferentes plataformas que existen actualmente, ya que tengo que realizar exactamente la misma búsqueda en los diferentes sitios.

La solución que yo propongo es crear un servicio que proporcione información sobre viajes compartidos ofertados en las diferentes plataformas (Blablacar, Amovens, etc) en un mismo lugar. Pudiendo comparar precios, opiniones de los conductores, horarios... Además de poder crear alertas para que cuando se publique un nuevo viaje (sea la plataforma que sea) el usuario pueda ser notificado. Las funcionalidades completas se irán detallando próximamente.

## H2 - R4 Y R5. Avances en el código y Relación con HUs

Los ficheros en los que se han hecho avance hasta este punto del proyecto son:

- Tests:
  - test/UnitSpec.scala
- Clases:
  - /app/models/Busqueda.scala
  - /app/models/Viaje.scala
  - /app/controllers/ControladorBusqueda.scala
  
Estas clases están relacionadas con las HUs:

- https://github.com/Neo-Stark/FIND4CAR/issues/18
- https://github.com/Neo-Stark/FIND4CAR/issues/17
- https://github.com/Neo-Stark/FIND4CAR/issues/16
- https://github.com/Neo-Stark/FIND4CAR/issues/7

## [Documentación](http://neostark.wtf/FIND4CAR "Enlace a documentación adicional")

- [Correcta configuración de git y GitHub](/docs/configuracion-git-github.md)
- [Planificación - Arquitectura y herramientas - Roadmap](/docs/planificacion.md)
- [Tests y taskmanager](/docs/tests-sbt.md)

> Proyecto de la asignatura Cloud Computing I, curso 2020-21, Master Ingeniería Informática, ETSIIT UGR
