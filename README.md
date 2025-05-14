# DAM1_GRUPO4_2025

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Participantes](#participantes)
3. [Diagrama Entidad-Relación (E/R)](#diagrama-entidad-relación-er)
4. [Diagrama Relacional](#diagrama-relacional)
5. [Script](#script-bd)
6. [Diagrama de Clases](#diagrama-de-clases)
7. [Diagrama de Casos de Uso](#diagrama-de-casos-de-uso)
8. [XSLT](#xslt)
9. [Infografía Seguridad Social](#infografía-seguridad-social)
10. [Subsidios Seguridad Social](#subsidios-de-la-seguridad-social)
11. [Peligros y riesgos de actividades](#peligros-y-riesgos)
12. [Instalación](#instalación)
13. [Uso](#uso)

## Descripción del Proyecto
Resumen del objetivo del proyecto, su funcionalidad principal y tecnologías utilizadas.

## Participantes
- Rebeca Cabo Cianca  (@Rebeca467)
- Oriol Fernández Saiz  (@MaxwellRoyers)
- Fabián Saiz Landeras  (@Napster002)
- Ciro  Galán Vertiz  (@CiroGalanVertiz)
- Ana María Rodríguez Méndez  (@anarodriguezm)

## Diagrama Entidad-Relación (E/R)
[Ver el Diagrama E/R](./diagrama_e-r.pdf)

> El diagrama muestra las entidades principales, sus relaciones y atributos clave en el sistema.

## Diagrama Relacional
[Ver el Diagrama Relacional](./diagramaRelacional-definitivo.mwb)

> El diagrama muestra las entidades principales, sus relaciones y atributos clave en el sistema.

## Script BD
[Ver el Script](./bd-script-grupo4-definitivo.sql)

> Aqui se puede ver el script de la base de datos.

## Diagrama de Clases
![Ver Diagrama de Clases](./diagrama_clases-def.png)

> El diagrama de clases ilustra la estructura del sistema orientado a objetos, incluyendo clases, métodos y relaciones de herencia o asociación.


## Diagrama de Casos de Uso
![Ver Diagrama de Casos de Uso](./casos_uso_def.png)

#### 1. Crear Ruta (Alumno/Diseñador)
**Actor:** Alumno / Diseñador  
**Resumen:** Permite introducir una nueva ruta de manera manual o cargando un archivo CSV.  
**Flujo principal:**
1. El actor accede a la aplicación y selecciona "Crear Ruta".
2. Introduce la información de la ruta o selecciona un archivo CSV.
3. El sistema calcula automáticamente el nivel de esfuerzo y riesgo.
4. Se guarda la ruta con estado "Pendiente de validación".

**Decisiones destacadas:**  
Se priorizó la carga desde archivo CSV para reducir errores de entrada manual y mejorar la eficiencia.


#### 2. Validar Ruta (Administrador)
**Actor:** Administrador  
**Resumen:** Aprueba o rechaza rutas pendientes para ser incluidas en el catálogo público.  
**Flujo principal:**
1. Accede a "Rutas Pendientes".
2. Revisa los datos de cada ruta.
3. Decide si validar o no.
4. El sistema cambia el estado a "Validada" o muestra mensaje de rechazo.

**Decisiones destacadas:**  
Solo los administradores pueden validar para asegurar calidad y veracidad.


#### 3. Valorar Ruta (Alumno/Profesor/Invitado)
**Actor:** Todos los perfiles (excepto no registrados)  
**Resumen:** Permite valorar la dificultad, belleza e interés cultural de una ruta.  
**Flujo principal:**
1. Seleccionar una ruta validada.
2. Introducir valores del 1 al 5 por cada criterio.
3. Guardar valoración.

**Decisiones destacadas:**  
Las valoraciones se almacenan por usuario y se calcula media para mostrar en la interfaz.


#### 4. Descargar Fichas Informativas (Diseñador/Profesor/Administrador)
**Actor:** Diseñador, Profesor, Administrador  
**Resumen:** Genera informes en formato texto (ficha de seguridad, usuario y organización).  
**Flujo principal:**
1. Selecciona una ruta validada.
2. Elige tipo de ficha.
3. Se genera documento con QR y datos clave.

**Decisiones destacadas:**  
Se usa una plantilla XSLT para personalizar la salida y facilitar impresión o distribución.


#### 5. Eliminar Reseñas (Administrador)
**Actor:** Administrador  
**Resumen:** Permite eliminar reseñas inapropiadas o que incumplen normas de uso.  
**Flujo principal:**
1. Ver reseñas marcadas por usuarios.
2. Revisar contenido.
3. Confirmar eliminación.

**Decisiones destacadas:**  
Solo accesible por admin para mantener integridad del contenido público.



## XSLT 
[Ver XSLT para generación de CSV](./xslt-csv.xslt)

> La hoja de estilo XSLT define la transformación de un documento XML a formato CSV, especificando plantillas y reglas para recorrer y convertir los nodos del XML en registros delimitados por comas.

## Infografía Seguridad Social
[Ver Infografias](./info-seguridad-social.pdf)

> Documento visual que consta sobre multiples apartados de la Seguridad Social.

## Subsidios de la Seguridad Social
[Ver subsidios](./subsidios_SS.pdf)

> Documento donde se presenta el cálculo de los subsidios de la Seguridad Social.

## Peligros y riesgos
[Ver peligros y riesgos](./Peligros-riesgos.pdf)
 
> Documento donde se muestran los peligros y riesgos de una actividad

## Instalación
```bash
git clone https://github.com/Rebeca467/DAM1_GRUPO4_2025
cd proyecto
npm install
```

## Uso
Aun se esta trabajando en la aplicacion...
