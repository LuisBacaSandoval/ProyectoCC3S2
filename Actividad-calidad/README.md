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
