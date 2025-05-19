/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * La clase {@code AccesoBaseDatos} implementa el patron Singleton para gestionar
 * la conexion a una base de datos MySQL local.
 * <p>
 * Esta clase establece la conexion a la base de datos, 
 * muestra mensajes en consola segun el estado de la conexion y
 * ofrece metodos para obtener la conexion activa y para cerrarla de manera segura.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     AccesoBaseDatos acceso = AccesoBaseDatos.getInstance();
 *     Connection connection = acceso.getConn();
 *     // Uso de la conexion...
 *     
 *     if (acceso.cerrar()) {
 *         System.out.println("Conexion cerrada correctamente");
 *     }
 * </pre>
 * </p>
 *
 * @author Fabian Saiz Landeras
 */
public class AccesoBaseDatos {
    private Connection conn = null;
    private static final String BD = "mydb";
    private static final String USUARIO = "fourdam";
    private static final String CLAVE = "usuario@1";
    private static final String URL = "jdbc:mysql://10.0.16.36:3306/" + BD;
    /**
     * Constructor privado que establece la conexion a la base de datos MySQL
     * mediante las credenciales definidas.
     * <p>
     * Se configuran propiedades como el usuario, la contraseña, el uso de SSL y
     * la reconexion automatica. En caso de error, se muestran los detalles del
     * error por consola.
     * </p>
     */
    private AccesoBaseDatos() {

        try {
            Properties properties = new Properties();
            properties.setProperty("user", USUARIO);
            properties.setProperty("password", CLAVE);
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            conn = (Connection) DriverManager.getConnection(URL, properties);
            if (conn == null) {
                System.out.println("Error en conexion");
            } else {
                System.out.println("Conexion correcta a: " + URL);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    /**
     * @return la instancia unica de {@code AccesoBaseDatos}.
     */
    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }
    /**
     * Clase estatica que contiene la instancia unica de {@code AccesoBaseDatos}.
     */
    private static class AccesoBaseDatosHolder {
        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();
    }
    /**
     * Obtiene la conexion activa a la base de datos.
     * @return el objeto {@code Connection} si la conexion se establecio correctamente, o {@code null} en el caso contrario.
     */
    public Connection getConn() {
        return conn;
    }
    /**
     * Cierra la conexion actual a la base de datos.
     *
     * @return {@code true} si la conexion fue cerrada correctamente, {@code false} en caso de producirse un error.
     */
    public boolean cerrar() {
        boolean siCerrada = false;
        try {
            conn.close();
            if (conn.isClosed()) {
                siCerrada = true;
            }
        } catch (SQLException sqe) {
            System.out.println("Se produjo un error en el cierre");
        }
        return siCerrada;
    }
}
