-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`actividades` (
  `idActividades` INT NOT NULL AUTO_INCREMENT,
  `nombre_actividad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idActividades`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `rol` ENUM('DISEÑADOR', 'ADMINISTRADOR', 'PROFESOR', 'ALUMNO', 'INVITADO') NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`rutas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`rutas` (
  `id_ruta` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `idActividades` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `fecha` DATE NOT NULL,
  `latitud_inicial` DOUBLE NOT NULL,
  `longitud_inicial` DOUBLE NOT NULL,
  `latitud_final` DOUBLE NOT NULL,
  `longitud_final` DOUBLE NOT NULL,
  `distancia` DOUBLE NOT NULL,
  `desnivel` DOUBLE NOT NULL,
  `desnivel_positivo` DOUBLE NOT NULL,
  `desnivel_negativo` DOUBLE NOT NULL,
  `altitud_minima` DOUBLE NOT NULL,
  `altitud_maxima` DOUBLE NOT NULL,
  `estado` ENUM('PENDIENTE', 'VALIDADA') NOT NULL,
  `url` TEXT NOT NULL,
  `familiar` TINYINT NOT NULL,
  `temporada` ENUM('PRIMAVERA', 'VERANO', 'OTOÑO', 'INVIERNO') NOT NULL,
  `indicaciones` TINYINT NULL DEFAULT NULL,
  `terreno` TINYINT NOT NULL,
  `esfuerzo` TINYINT NULL DEFAULT NULL,
  `riesgo` TINYINT NULL DEFAULT NULL,
  `zona` VARCHAR(45) NULL DEFAULT NULL,
  `recomendaciones` TEXT NULL DEFAULT NULL,
  `clasificacion` ENUM('CIRCULAR', 'LINEAL') NULL DEFAULT NULL,
  `nombre_inicial` VARCHAR(45) NULL DEFAULT NULL,
  `nombre_final` VARCHAR(45) NULL DEFAULT NULL,
  `media_valoraciones` INT NOT NULL,
  `duracion` DOUBLE NOT NULL,
  PRIMARY KEY (`id_ruta`),
  INDEX `fk_Rutas_Usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_Rutas_Actividades1_idx` (`idActividades` ASC) VISIBLE,
  CONSTRAINT `fk_Rutas_Actividades1`
    FOREIGN KEY (`idActividades`)
    REFERENCES `mydb`.`actividades` (`idActividades`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Rutas_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`calendario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`calendario` (
  `idCalendario` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_ruta` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `recomendaciones` TEXT NOT NULL,
  PRIMARY KEY (`idCalendario`, `id_ruta`),
  INDEX `fk_Calendario_Usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_Calendario_Rutas1_idx` (`id_ruta` ASC) VISIBLE,
  CONSTRAINT `fk_Calendario_Rutas1`
    FOREIGN KEY (`id_ruta`)
    REFERENCES `mydb`.`rutas` (`id_ruta`),
  CONSTRAINT `fk_Calendario_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`puntos_interes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`puntos_interes` (
  `idPuntos_interes` INT NOT NULL AUTO_INCREMENT,
  `id_ruta` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` ENUM('HISTÓRICO-ARQUEOLÓGICO', 'NATURALEZA', 'MIRADOR', 'AREA DE DESCANSO', 'PUNTO DE AGUA', 'REFUGIO', 'CULTURAL', 'GEOLOGICO', 'FAUNA ESPECIFICA', 'BOTANICO') NOT NULL,
  `caracteristicas` TEXT NOT NULL,
  `url` TEXT NOT NULL,
  `longitud` DOUBLE NOT NULL,
  `latitud` DOUBLE NOT NULL,
  PRIMARY KEY (`idPuntos_interes`),
  INDEX `fk_Puntos_interes_Rutas1_idx` (`id_ruta` ASC) VISIBLE,
  CONSTRAINT `fk_Puntos_interes_Rutas1`
    FOREIGN KEY (`id_ruta`)
    REFERENCES `mydb`.`rutas` (`id_ruta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`puntos_peligro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`puntos_peligro` (
  `idPuntos_peligro` INT NOT NULL AUTO_INCREMENT,
  `id_ruta` INT NOT NULL,
  `descripcion` TEXT NOT NULL,
  `km` DOUBLE NOT NULL,
  `imagen` VARCHAR(45) NOT NULL,
  `longitud` DOUBLE NOT NULL,
  `latitud` DOUBLE NOT NULL,
  PRIMARY KEY (`idPuntos_peligro`),
  INDEX `fk_Puntos_peligro_Rutas1_idx` (`id_ruta` ASC) VISIBLE,
  CONSTRAINT `fk_Puntos_peligro_Rutas1`
    FOREIGN KEY (`id_ruta`)
    REFERENCES `mydb`.`rutas` (`id_ruta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`fotos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`fotos` (
  `idFotos` INT NOT NULL AUTO_INCREMENT,
  `idPuntos_interes` INT NOT NULL,
  `idPuntos_peligro` INT NOT NULL,
  `URL` TEXT NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idFotos`, `idPuntos_interes`, `idPuntos_peligro`),
  INDEX `fk_Fotos_Puntos_interes1_idx` (`idPuntos_interes` ASC) VISIBLE,
  INDEX `fk_Fotos_Puntos_peligro1_idx` (`idPuntos_peligro` ASC) VISIBLE,
  CONSTRAINT `fk_Fotos_Puntos_interes1`
    FOREIGN KEY (`idPuntos_interes`)
    REFERENCES `mydb`.`puntos_interes` (`idPuntos_interes`),
  CONSTRAINT `fk_Fotos_Puntos_peligro1`
    FOREIGN KEY (`idPuntos_peligro`)
    REFERENCES `mydb`.`puntos_peligro` (`idPuntos_peligro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`reseña`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reseña` (
  `idReseña` INT NOT NULL AUTO_INCREMENT,
  `comentario` VARCHAR(45) NOT NULL,
  `fecha` DATE NOT NULL,
  `id_ruta` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idReseña`),
  INDEX `fk_Reseña_Rutas1_idx` (`id_ruta` ASC) VISIBLE,
  INDEX `fk_Reseña_Usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Reseña_Rutas1`
    FOREIGN KEY (`id_ruta`)
    REFERENCES `mydb`.`rutas` (`id_ruta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Reseña_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`valoraciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`valoraciones` (
  `idValoraciones` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_ruta` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `dificultad` TINYINT NOT NULL,
  `belleza` TINYINT NOT NULL,
  `interés` TINYINT NOT NULL,
  PRIMARY KEY (`idValoraciones`, `id_ruta`),
  INDEX `fk_Valoraciones_Usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_Valoraciones_Rutas1_idx` (`id_ruta` ASC) VISIBLE,
  CONSTRAINT `fk_Valoraciones_Rutas1`
    FOREIGN KEY (`id_ruta`)
    REFERENCES `mydb`.`rutas` (`id_ruta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Valoraciones_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`valoraciontecnica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`valoraciontecnica` (
  `idValoracionTecnica` INT NOT NULL AUTO_INCREMENT,
  `recomendaciones` VARCHAR(45) NOT NULL,
  `dificultad` VARCHAR(45) NOT NULL,
  `fecha` DATE NOT NULL,
  `id_ruta` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`idValoracionTecnica`),
  INDEX `fk_ValoracionTecnica_Rutas1_idx` (`id_ruta` ASC) VISIBLE,
  INDEX `fk_ValoracionTecnica_Usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_ValoracionTecnica_Rutas1`
    FOREIGN KEY (`id_ruta`)
    REFERENCES `mydb`.`rutas` (`id_ruta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ValoracionTecnica_Usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id_usuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
