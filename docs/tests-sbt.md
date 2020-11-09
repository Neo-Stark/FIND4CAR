# Tests y Task manager

## Elección del task manager

En el caso de Scala, la elección del gestor de tareas es sota, caballo y rey, es decir, **sbt**. Pese a que sus siglas corresponden a 
_scala build tool_ es mucho más que eso ya que más allá de ser solo una simple herramienta de construcción podemos hacer
infinidad de tareas si lo conocemos bien a fondo. Para nuestras necesidades, administrar dependencias y definir tareas 
funciona increiblemente bien. Definir tareas es tan sencillo como declarar una variable de tipo _taskKey[Unit]_ y asignarle
la tarea que queremos que haga, ya sea con en propio lenguaje de scala o una orden externa con el modificador **!**, un 
ejemplo de tarea en sbt puede ser el siguiente:

```scala
lazy val hello = taskKey[Unit]("An example task")

hello := {println("Hello from root settings");
    "echo #prueba de sbt task" #> file("prueba.md") !;
    "cat prueba.md" !;
    }
```

Como vemos, permite redirecciones a ficheros o crear pipelines usando **#|**, hay muchas más cosas que se pueden hacer,
pero para las tareas que se van a definir aquí es suficiente con esto. También hay que añadir que tiene muchas tareas ya
definidas por defecto, como test, compile, run, etc. 

En cuanto a tests, es bastante potente y flexible, tiene la mencionada tarea test que ejecuta todos los tests disponibles
bajo el directorio _/test/_, luego tenemos **testOnly** que ejecuta solamente el test que le pasemos como argumento, o 
**quick-test** que ejecuta solo los tests que hayan fallado. También tiene la opción de añadir el prefijo **~** a estas 
tareas que hará que se ejecuten en background, una opción iteresante si estamos depurando y queremos pasar los tests 
constantemente.

## Biblioteca de aserciones y marco de pruebas

En este caso tampoco hay muchas alternativas más allá de lo evidente, aunque si que hay alguna que otra opción más. Como
biblioteca de aserciones se ha usado  **scalatest**, en concreto las aserciones básicas **assert** que vienen por defecto 
en cualquier trait del que derivemos y **matchers** incluidos en el paquete **matchers.[should|must]._** el verbo a elegir
va a gusto del usuario y no cambia la funcionalidad (está más relacionado con el propio significado que tiene en inglés),
está en cada uno elegir el que le resulte más adecuado, en mi caso he elegido must. La elección de esta biblioteca de aserciones
así como el marco de pruebas van de la mano, en primer lugar porque proporciona un lenguaje muy expresivo y da lugar a escribir 
tests muy ricos en significado, la segunda es que el framework que estoy usando implementa un marco de pruebas y una batería 
de aserciones adicionales llamada **scalatestplus** o también llamado **ScalaTest + Play**. Se integran las bibliotecas 
de scalatest para que sea mucho más sencillo testear aplicaciones de Play. (En los primeros paso, donde solo hemos escrito
tests unitarios sobre nuestras clases no se ve el potencial de esta integración, pero en avances y tests posteriores si).

Un ejemplo de salida de este marco de pruebas, es el siguiente: 

![salida scalatest](img/Captura%20de%20pantalla%20de%202020-11-09%2001-40-47.png)

Aquí vemos como la salida que produce genera un arbol con todos los tests que pasamos y el tiempo que consume en total y en cada uno de ellos.

En caso de fallo nos muestra lo siguiete:

![fallo test](img/Captura%20de%20pantalla%20de%202020-11-09%2001-04-39.png)

Nos dice que tests ha fallado y por qué: ![motivo fallo](img/Captura%20de%20pantalla%20de%202020-11-09%2001-04-56.png)

Como vemos, la elección de estas herramientas están justificadas y en mi opinión son las mejores actualmente en scala.