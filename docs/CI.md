# Integración Continua

Se han agregado dos herramientas de integración continua al proyecto para ejecutar los tests
del servicio para diferentes versiones o con diferentes entornos para asegurar que en el despliegue
o al añadir cambios a la rama principal se han pasado todos los tests satisfactoriamente.
Las plataformas elegidas han sido [Travis](https://travis-ci.com/github/Neo-Stark/FIND4CAR)
y [Circle CI](https://app.circleci.com/pipelines/github/Neo-Stark/FIND4CAR).

## Travis

![Travis](img/Captura%20de%20pantalla%20de%202020-12-16%2000-07-35.png)

Travis se ha configurado para que pasen todos los tests _unitarios_ de la aplicación para diferentes versiones
del lenguaje (scala). Estas versiones no han sido elegidas al azar, por supuesto. En este caso, se han elegido dos
la versión 2.12 y la 2.13. La razón es porque la versión 2.12 es la primera compatible con el framework que estamos 
utilizando (Play) en su versión 2.8. Por otro lado, testeamos además en la 2.13 porque es la versión _latest_ del
lenguaje.

## Circle CI

![Circle CI](img/Captura%20de%20pantalla%20de%202020-12-16%2000-26-17.png)

Cambiando de plataforma a Circle CI los ficheros de configuración son un tanto diferentes a lo que
podemos ver en travis, aunque tampoco muy dificiles de entender. En este caso este es el fichero de
configuración para la aplicación:

```yaml
# Scala CircleCI 2.0 configuration file
version: 2
jobs:
  build:
    docker:
      - image: neostark/find4car
    working_directory: ~/repo

    steps:
      - checkout
      # Descargamos y cacheamos las dependencias
      - restore_cache:
          keys:
            - dependencies-{{ checksum "build.sbt" }}
            - dependencies-

      - run: sbt test:compile

      - save_cache:
          paths:
            - ~/.m2
            - ~/.sbt
          key: dependencies-{{ checksum "build.sbt" }}

      - run: sbt test
```

Si nos fijamos, realmente se parece a un action de GitHub con una serie de _jobs_ y pasos (_steps_). El motivo de 
ser de este sistema de integración continua es testear la imagen de docker que tenemos para ejecutar los tests, de
esta forma nos aseguramos de que la imagen sigue siendo válida siempre para correr todo el código y tests que vayamos agregando
al proyecto.

Hemos configurado además un sistema de caché para, valga la redundancia, cachear las dependencias del proyecto y que no
se tengan que descargar cada vez que se active el disparador de CircleCI, de esta manera aligeramos aún más la ejecución
de los tests.

Ese es otro punto importante, el tiempo. Usar el contenedor que preparamos para ejecutar los tests en CircleCI acelera el
proceso un 100%, es decir, se ejecuta en la mitad de tiempo que lo hace travis. 

- Tiempo CircleCI con docker: 50s
- Tiempo travis con una de las versiones del lenguaje y sbt: 1.39s

Por lo que podemos asegurar de que la imágen elaborada funciona y es eficiente para ejecutar los tests e integrarla
en un sistema de Integración Continua.