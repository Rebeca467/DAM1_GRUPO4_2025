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

La interfaz web está desarrollada con HTML, CSS y JavaScript. La estructura está organizada por roles, y las páginas más destacadas son:

- Página de inicio y login
- Listado y filtros de rutas
- Crear nueva ruta (manual o desde CSV)
- Visualización y descarga de fichas informativas
- Validación de rutas (solo admin)
- Valoración y reseñas
- Eliminación de reseñas por parte de administradores

### Estilos y diseño

- Estilos definidos en hojas CSS externas.
- Uso de variables y clases reutilizables.
- Diseño limpio, enfocado a la claridad y accesibilidad.
- Responsive: adaptado a diferentes dispositivos con `media queries`.

### Enlace al proyecto

🌐 [Repositorio GitHub](https://github.com/Rebeca467/DAM1_GRUPO4_2025)

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