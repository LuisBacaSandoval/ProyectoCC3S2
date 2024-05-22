### Sitio Web Seleccionado:
**Portal Académico UNI (https://www.academico.uni.edu.pe)**

### Atributos de Calidad y Escenarios


1. **Rendimiento**
   - **Escenario**: Se venció el plazo de entrega de notas del examen parcial y muchos profesores quieren subir las notas.
     - **Origen**: Un profesor desde un navegador web estándar.
     - **Estímulo**: El profesor intenta subir las notas del examen parcial de su curso al portal.
     - **Entorno**: El sistema está operando bajo una carga saturada con por lo menos 1000 usuarios concurrentes, entre alumnos y profesores.
     - **Artefacto**: Servidor web del Portal Académico UNI.
     - **Respuesta**: Tiempo desde que el profesor carga la nota hasta que se confirma la subida exitosa.
     - **Medida de respuesta**:
       - 95% del tiempo en menos de 9.5 segundos.
       - 99.9% del tiempo en menos de 10 segundos.

   - **Importancia**: El rendimiento es fundamental para el Portal Académico UNI porque el sitio debe manejar múltiples solicitudes simultáneas, especialmente durante periodos de matrícula o publicación de notas. Un buen rendimiento asegura tiempos de carga rápidos y evita la sobrecarga del sistema.

2. **Usabilidad**

   - **Escenario:** Un estudiante accede al portal académico desde un dispositivo móvil para consultar sus notas del examen parcial.

     - **Origen:** Un estudiante desde un dispositivo móvil (smartphone o tablet).

     - **Estímulo:** El estudiante intenta acceder a sus notas del examen parcial en el portal académico.

     - **Entorno:** El sistema está operando bajo una carga saturada con por lo menos 1000 usuarios concurrentes, entre alumnos y profesores.

     - **Artefacto:** Interfaz web responsiva del Portal Académico UNI.

     - **Respuesta:** Tiempo desde que el estudiante accede a la página hasta que las notas se despliegan correctamente en la pantalla de su dispositivo móvil.

     - **Medida de respuesta:**
       - El sitio debe ajustar correctamente su diseño y contenido al tamaño de la pantalla del dispositivo móvil en menos de 3 segundos.
       - 95% del tiempo en menos de 3 segundos.
       - 99.9% del tiempo en menos de 5 segundos.

   - **Importancia:** La usabilidad es fundamental para el Portal Académico UNI porque los estudiantes y profesores utilizan diversos dispositivos para acceder al portal. Un sitio responsivo asegura que la experiencia del usuario sea óptima independientemente del dispositivo utilizado, facilitando el acceso a la información y reduciendo la frustración por problemas de visualización o navegación.


3. **Fiabilidad**
   - **Escenario**: Personal de estadistica se encarga de hacer un reporte de matriculados en el curso CC3S2.
       - **Origen**: Personal administrativo accediendo al portal.
       - **Estímulo**: El personal intenta generar un reporte de matrícula.
       - **Entorno**: El sistema está operando normalmente, sin eventos de mantenimiento programados.
       - **Artefacto**: Servidores del Portal Académico UNI.
       - **Respuesta**: Disponibilidad del sistema y tiempo de respuesta para generar el reporte.
       - **Medida de respuesta**:
         - 99.99% del tiempo el sitio está disponible.
         - Tiempo de respuesta para generar el reporte en menos de 5 segundos el 99.9% del tiempo.
   - **Importancia**: La fiabilidad es vital para el Portal Académico UNI porque los usuarios confían en que el sistema estará disponible y funcionando correctamente, especialmente durante periodos críticos como la inscripción de cursos y la publicación de resultados. Una alta fiabilidad garantiza acceso continuo y sin interrupciones.
