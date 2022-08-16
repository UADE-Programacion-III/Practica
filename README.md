# Programación III - Práctica
Este repositorio corresponde a ejercicios y ejemplos prácticos de la materia Programación III. El mismo contiene paquetes correspondientes a cada técnica estudiada en la materia. Los tests de cada ejercicio se encuentran en la carpeta `src/test`.

## Importar y ejecutar proyecto
En caso de no tener Maven instalado (verificar ejecutando `mvn -v` desde la consola), descargarlo desde https://maven.apache.org/download.cgi

### Eclipse
1. `File` -> `Import` -> `Maven` -> `Existing Maven Projects`.
2. Buscar y seleccionar la carpeta donde se clonó el repositorio en `Root Directory`.
3. Seleccionar en `Projects` el archivo `pom.xml ar.edu.uade.p3:practica:1.0-SNAPSHOT:jar` y luego `Finish`.

Una vez importado el proyecto (esperar unos minutos a que se descarguen las dependencias), para ejecutar los tests individuales hacer click derecho sobre el test en cuestión y seleccionar `Run as` -> `JUnit Test`.

### IntelliJ
1. `File` -> `Open` y seleccionar la carpeta donde se clonó el repositorio.

Una vez importado el proyecto (esperar unos minutos a que se descarguen las dependencias), para ejecutar los tests individuales hacer click derecho sobre el triángulo verde al lado de cada test.

### Visual Studio Code  
1. `File` -> `Open Folder` y seleccionar la carpeta donde se clonó el repositorio.
2. En caso de no tenerlo instalado, descargar `Extension Pack for Java` desde `Extensions`.

Una vez importado el proyecto, para ejecutar los tests individuales hacer click derecho sobre el triángulo verde al lado de cada test. 