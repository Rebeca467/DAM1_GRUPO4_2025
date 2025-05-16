DROP TRIGGER IF EXISTS `mydb`.`rutas_BEFORE_INSERT`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `rutas_BEFORE_INSERT` BEFORE INSERT ON `rutas` FOR EACH ROW BEGIN
DECLARE puntos_duracion INT;
    DECLARE puntos_distancia INT;
    DECLARE puntos_desnivel INT;
 
    -- Duración
    IF duracion < 30 THEN
        SET puntos_duracion = 2;
    ELSEIF duracion < 60 THEN
        SET puntos_duracion = 4;
    ELSEIF duracion < 120 THEN
        SET puntos_duracion = 6;
    ELSE
        SET puntos_duracion = 8;
    END IF;
 
    -- Distancia
    IF distancia < 5 THEN
        SET puntos_distancia = 2;
    ELSEIF distancia < 10 THEN
        SET puntos_distancia = 3;
    ELSEIF distancia < 15 THEN
        SET puntos_distancia = 4;
    ELSEIF distancia < 20 THEN
        SET puntos_distancia = 5;
    ELSEIF distancia < 25 THEN
        SET puntos_distancia = 6;
    ELSEIF distancia < 30 THEN
        SET puntos_distancia = 7;
    ELSE
        SET puntos_distancia = 8;
    END IF;
 
    -- Desnivel
    IF desnivel < 100 THEN
        SET puntos_desnivel = 2;
    ELSEIF desnivel < 300 THEN
        SET puntos_desnivel = 3;
    ELSEIF desnivel < 500 THEN
        SET puntos_desnivel = 4;
    ELSEIF desnivel < 600 THEN
        SET puntos_desnivel = 5;
    ELSEIF desnivel < 800 THEN
        SET puntos_desnivel = 6;
    ELSEIF desnivel < 1000 THEN
        SET puntos_desnivel = 7;
    ELSE
        SET puntos_desnivel = 8;
    END IF;
 
    -- Calcular media
    SET NEW.esfuerzo = ROUND((puntos_duracion + puntos_distancia + puntos_desnivel) / 3);
END$$
DELIMITER ;