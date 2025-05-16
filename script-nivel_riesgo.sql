DROP TRIGGER IF EXISTS `mydb`.`rutas_BEFORE_INSERT`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `rutas_BEFORE_INSERT` BEFORE INSERT ON `rutas` FOR EACH ROW BEGIN
UPDATE rutas
    SET riesgo = (
        SELECT ROUND(AVG(gravedad), 0)
        FROM puntos_peligro
        WHERE id_ruta = id_ruta
    )
    WHERE id = id_ruta;
END$$
DELIMITER ;