# DAM1_GRUPO4_2025

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Participantes](#participantes)
3. [Base de Datos](#base-de-datos)
    - [Diagrama Entidad-Relación (E/R)](#diagrama-entidad-relación-er)
    - [Diagrama Relacional](#diagrama-relacional)
    - [Script SQL](#script-sql)
4. [Aplicación Web](#aplicación-web)
5. [Aplicación Java](#aplicación-java)
    - [Diagrama de Clases](#diagrama-de-clases)
    - [Lógica educativa Java](#lógica-educativa-java)
    - [Documentación JavaDoc](#documentación-javadoc)
6. [XSLT](#xslt)
7. [Otros Documentos](#otros-documentos)
8. [Instalación](#instalación)
9. [Uso](#uso)
10. [Despliegue y Tecnologías](#despliegue-y-tecnologías)

---

## Descripción del Proyecto

Este proyecto tiene como objetivo la gestión y validación de rutas educativas creadas por distintos tipos de usuarios. Ofrece funcionalidades como la creación y validación de rutas, valoración por parte de los usuarios, generación de fichas informativas y control administrativo sobre las rutas y reseñas. Se ha desarrollado con Java, MySQL y una interfaz web básica con HTML/CSS/JavaScript.

---

## Participantes

- Rebeca Cabo Cianca  (@Rebeca467)
- Oriol Fernández Saiz  (@MaxwellRoyers)
- Fabián Saiz Landeras  (@Napster002)
- Ciro Galán Vertiz  (@CiroGalanVertiz)
- Ana María Rodríguez Méndez  (@anarodriguezm)

---

## Base de Datos

### Diagrama Entidad-Relación (E/R)

📎 [Ver Diagrama E/R](./diagrama_e-r.pdf)

> El diagrama muestra entidades como `usuarios`, `rutas`, `valoraciones`, `puntos de interés`, etc., y sus relaciones.

### Diagrama Relacional

📎 [Ver Diagrama Relacional](./diagramaRelacional-definitivo.mwb)

> Representa claves primarias, foráneas y estructura real para el uso en MySQL.

### Script SQL

📥 [Descargar Script de Importación](./bd-script-grupo4-definitivo.sql)

> Incluye toda la estructura y datos necesarios para crear la base de datos.

---

## Aplicación Web

La aplicación web es una **página estática** compuesta por varios archivos HTML conectados entre sí. Su objetivo es presentar la información de rutas, noticias y contacto de forma clara, visual y accesible para cualquier usuario. **No requiere login ni descarga de datos.**

### Estructura de la página

- **`index.html`**  
  Página principal que introduce el sitio, presenta enlaces al resto de secciones y una breve explicación del propósito del proyecto.

- **`ruta.html`**  
  Sección con información detallada sobre rutas representativas del proyecto. Contiene imágenes, descripción, zonas y clasificación.

- **`noticias.html`**  
  Página donde se muestra un **feed de noticias actualizado automáticamente** desde Feedly (vía RSS transformado con XSLT). Las rutas publicadas por otras fuentes se integran visualmente con estilo adaptado.

- **`contacto.html`**  
  Página con formulario de contacto simulado, enlaces a perfiles de los integrantes del grupo y medios de comunicación adicionales.

### Tecnologías utilizadas

- **HTML5** para la estructura semántica.
- **CSS3** para la presentación visual (estilo sencillo, centrado en legibilidad y contraste).
- **XSLT** para convertir feeds RSS en HTML en tiempo real y mostrarlos en `noticias.html`.
- No se utiliza backend, bases de datos ni JavaScript complejo.

### Justificación del diseño

El enfoque se centra en la **simplicidad, accesibilidad y portabilidad**. Al tratarse de un entorno educativo, se evita la complejidad innecesaria. La web puede visualizarse desde cualquier navegador moderno sin instalación ni configuración previa.




---

## Aplicación Java

### Diagrama de Clases

📎 ![Ver Diagrama de Clases](./diagrama_clases-def.png)

> Muestra clases como `Ruta`, `Usuario`, `Actividad`, `Valoracion`, `Reseña`, etc., con relaciones de herencia y asociación.

### Lógica educativa Java

Para determinar el **nivel educativo recomendado** de una ruta en base a su dificultad, se ha implementado el siguiente método:

```java
public String getNivelEducativoRecomendado(Ruta r) {
    int esfuerzo = r.getNivelEsfuerzo();
    int riesgo = r.getNivelRiesgo();
    int nivel = Math.max(esfuerzo, riesgo);

    return switch (nivel) {
        case 1, 2 -> "Primaria";
        case 3 -> "Educación Secundaria Obligatoria (ESO)";
        case 4 -> "Bachillerato";
        case 5 -> "Ciclos Formativos / Adultos";
        default -> "Sin especificar";
    };
}
```

### Documentación JavaDoc

Todas las clases están documentadas con JavaDoc, incluyendo:
- Descripción de propósito
- Parámetros y retorno
- Ejemplos de uso
[Ver Documentacion JavaDoc](./fourdam/target/reports/apidocs)
