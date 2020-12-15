# FIND4CAR

[![Build Status](https://travis-ci.com/Neo-Stark/FIND4CAR.svg?branch=main)](https://travis-ci.com/Neo-Stark/FIND4CAR)


## Introducción. Motivación y problema a resolver

Este proyecto se trata de una idea personal que me surge constantemente cuando viajo. Yo suelo viajar a menudo usando coches compartidos (también llamado _carpooling_) y siempre que busco un viaje pierdo mucho tiempo buscando en las diferentes plataformas que existen actualmente, ya que tengo que realizar exactamente la misma búsqueda en los diferentes sitios.

La solución que yo propongo es crear un servicio que proporcione información sobre viajes compartidos ofertados en las diferentes plataformas (Blablacar, Amovens, etc) en un mismo lugar. Pudiendo comparar precios, opiniones de los conductores, horarios... Además de poder crear alertas para que cuando se publique un nuevo viaje (sea la plataforma que sea) el usuario pueda ser notificado. Las funcionalidades completas se irán detallando próximamente.

# Instalación y arranque

Para ejecutar el servicio debemos tener instalado un jdk>8 y alguna versión de sbt. Clonamos el respositorio y arrancamos 
el servicio con `sbt start`

## TEST

Para lanzar los tests de la aplicación clonamos el repositorio y ejecutamos la siguiente orden:

```bash
docker run -v "$(pwd)"/test:/app/test -v "$(pwd)"/app:/app/app neostark/find4car
```

Se nos descargará el contenedor _neostark/find4car_ desde DockerHub con todo lo necesario para ejecutar los tests. 
## [Documentación](http://neostark.wtf/FIND4CAR "Enlace a documentación adicional")

- [Planificación - Arquitectura y herramientas - Roadmap](/docs/planificacion.md)
- [Tests y taskmanager](/docs/tests-sbt.md)
- [Contenedores](/docs/contenedores.md)
- [Integración Continua](/docs/CI.md)

> Proyecto de la asignatura Cloud Computing I, curso 2020-21, Master Ingeniería Informática, ETSIIT UGR
