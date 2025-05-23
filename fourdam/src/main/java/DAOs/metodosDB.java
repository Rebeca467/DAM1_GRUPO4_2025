package DAOs;

import ENUMs.Clasificacion_Ruta;
import ENUMs.Estado;
import ENUMs.Temporada;
import ENUMs.TipoPInteres;
import ENUMs.TipoUsuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import reto.fourdam.AccesoBaseDatos;
import reto.fourdam.Actividad;
import reto.fourdam.Administrador;
import reto.fourdam.Alumno;
import reto.fourdam.DisennadorRuta;
import reto.fourdam.Profesor;
import reto.fourdam.Punto;
import reto.fourdam.PuntoInteres;
import reto.fourdam.PuntoPeligro;
import reto.fourdam.Resenna;
import reto.fourdam.Ruta;
import reto.fourdam.Usuario;
import reto.fourdam.Valoracion;
import reto.fourdam.ValoracionTec;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Clase metodosDB.
 * <p>
 * Esta clase contiene métodos para interactuar con la base de datos,
 * permitiendo listar rutas, puntos, reseñas y valoraciones, así como agregar,
 * modificar y eliminar registros. Emplea la conexion proporcionada por la clase
 * AccesoBaseDatos y utiliza diversos metodos auxiliares para construir objetos
 * a partir de ResultSet.
 * </p>
 *
 * @author Rebeca Cabo Cianca
 * @author Fabian Saiz Landeras
 * @author Oriol Fernandez Saiz
 * @author Ciro Galan Vertiz
 */
public class metodosDB {

    /**
     * Obtiene la conexion a la base de datos.
     *
     * @return Objeto Connection obtenido de AccesoBaseDatos.
     */
    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public ArrayList<PuntoInteres> listarPInteres() {
        ArrayList<PuntoInteres> pi = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT idPuntos_interes, id_ruta, nombre, tipo, caracteristicas, url, longitud, latitud FROM puntos_interes");) {
            while (rs.next()) {
                PuntoInteres p = pInteresPorId(rs.getInt("idPuntos_interes"));
                pi.add(p);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return pi;
    }

    /**
     * Lista todas las rutas almacenadas en la base de datos.
     * <p>
     * Se ejecuta una consulta SQL para obtener los registros de rutas. Para
     * cada registro se invoca el metodo {@code rutaPorId} para generar el
     * objeto Ruta correspondiente.
     * </p>
     *
     * @return ArrayList de objetos Ruta.
     */
    public ArrayList<Ruta> listarRutas() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_ruta, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones, duracion FROM rutas");) {
            while (rs.next()) {
                Ruta ruta = rutaPorId(rs.getInt("id_ruta"));
                rutas.add(ruta);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return rutas;
    }

    /**
     * Lista todos los puntos almacenados en la base de datos.
     * <p>
     * Se ejecutan dos consultas SQL: una para los puntos de interes y otra para
     * los puntos de peligro. Para cada registro se invocan los metodos
     * {@code crearPuntoInteres} o {@code crearPuntoPeligro}, segun corresponda.
     * </p>
     *
     * @return Lista de objetos Punto.
     */
    public List<Punto> listarPuntos() {
        List<Punto> puntos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT idPuntos_interes, id_ruta, nombre, tipo, caracteristicas, url, longitud, latitud FROM puntos_interes");) {
            while (rs.next()) {
                PuntoInteres punto = crearPuntoInteres(rs);
                puntos.add(punto);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT idPuntos_peligro, id_ruta, descripcion, km, imagen, longitud, latitud FROM puntos_peligro");) {
            while (rs.next()) {
                PuntoPeligro punto = crearPuntoPeligro(rs);
                puntos.add(punto);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return puntos;
    }

    /**
     * Lista todas las valoraciones almacenadas en la base de datos.
     * <p>
     * Se ejecuta una consulta SQL para obtener los registros de valoraciones.
     * Por cada registro se invoca el metodo {@code crearValoracion} para
     * generar el objeto Valoracion.
     * </p>
     *
     * @return ArrayList de objetos Valoracion.
     */
    public ArrayList<Valoracion> listarValoraciones() {
        ArrayList<Valoracion> valoraciones = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT idValoraciones, id_usuario, id_ruta, fecha, dificultad, belleza, interés FROM valoraciones")) {
            while (rs.next()) {
                Valoracion valoracion = crearValoracion(rs);
                valoraciones.add(valoracion);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return valoraciones;
    }

    public String getEquipoRecomendadoPorRuta(int idRuta) {
        String equipo = "";
        String sql = "SELECT recomendaciones FROM valoraciontecnica WHERE id_ruta = ? LIMIT 1";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idRuta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipo = rs.getString("recomendaciones");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipo;
    }

    /**
     * Valida una ruta cambiando su estado a "VALIDADA" en la base de datos.
     *
     * @param idRuta El ID de la ruta a validar
     * @return true si la validación fue exitosa, false en caso contrario
     */
    public static boolean validaRuta(int idRuta) {
        // Variable para almacenar el resultado de la operación
        boolean resultado = false;

        // Corregir la consulta SQL añadiendo el nombre de la tabla (asumiendo que es "rutas")
        String sql = "UPDATE rutas SET estado='VALIDADA' WHERE id_ruta=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idRuta);
            int filasAfectadas = stmt.executeUpdate();
            resultado = filasAfectadas > 0;

        } catch (SQLException ex) {
            System.err.println("Error al validar la ruta con ID " + idRuta + ": " + ex.getMessage());
            ex.printStackTrace();
            // resultado ya es false por defecto, no es necesario asignarlo de nuevo
        }
        return resultado;
    }

    /**
     * Comprueba si una ruta ya está validada en la base de datos.
     *
     * @param idRuta El ID de la ruta a comprobar
     * @return true si la ruta ya está validada, false en caso contrario o si
     * ocurre un error
     */
    public static boolean compruebaValidacion(int idRuta) {
        boolean resultado = false;
        // Corregir la consulta SQL añadiendo la cláusula FROM y el nombre de la tabla
        String sql = "SELECT estado FROM rutas WHERE id_ruta=?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            // Establecer el parámetro ID de la ruta
            stmt.setInt(1, idRuta);

            // Ejecutar la consulta y obtener el resultado
            try (ResultSet rs = stmt.executeQuery()) {
                // Si hay un resultado y el estado es "VALIDADA", devolver true
                if (rs.next()) {
                    String estado = rs.getString("estado");
                    resultado = "VALIDADA".equals(estado);
                }
                // Si no hay resultados o el estado no es "VALIDADA", resultado sigue siendo false
            }
        } catch (SQLException ex) {
            System.err.println("Error al comprobar la validación de la ruta con ID " + idRuta + ": " + ex.getMessage());
            ex.printStackTrace();
            // resultado ya es false por defecto, no es necesario asignarlo de nuevo
        }

        return resultado;
    }

    /**
     * Lista todas las resennas almacenadas en la base de datos.
     * <p>
     * Se ejecuta una consulta SQL para obtener los registros de resennas y,
     * para cada registro, se invoca el metodo {@code crearResenna(rs)} para
     * generar el objeto Resenna correspondiente.
     * </p>
     *
     * @return ArrayList de objetos Resenna.
     */
    public ArrayList<Resenna> listarResennas() {
        ArrayList<Resenna> resennas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT idReseña, comentario, fecha, id_ruta, id_usuario FROM reseña");) {
            while (rs.next()) {
                Resenna resenna = crearResenna(rs);
                resennas.add(resenna);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return resennas;

    }

    /**
     * Lista todas las valoraciones tecnicas almacenadas en la base de datos.
     * <p>
     * Se ejecuta una consulta SQL para obtener los registros de valoraciones
     * tecnicas y, para cada registro, se invoca el metodo
     * {@code crearValoracionTecnica(rs)} para generar el objeto ValoracionTec.
     * </p>
     *
     * @return ArrayList de objetos ValoracionTec.
     */
    public ArrayList<ValoracionTec> listarValoracionesTecnicas() {

        ArrayList<ValoracionTec> valoracionesTecnicas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_ruta, id_usuario, fecha, recomendaciones, dificultad from valoraciontecnica");) {
            while (rs.next()) {
                ValoracionTec valoracionTec = crearValoracionTecnica(rs);
                valoracionesTecnicas.add(valoracionTec);
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return valoracionesTecnicas;
    }

    /**
     * Agrega una nueva ruta a la base de datos.
     * <p>
     * Este metodo procede con las siguientes acciones:
     * <ol>
     * <li>Inicia una transaccion y verifica si el usuario autor existe en la
     * base de datos.</li>
     * <li>Verifica si la actividad asociada a la ruta existe; de lo contrario,
     * la crea.</li>
     * <li>Inserta la ruta en la tabla 'rutas' utilizando un
     * PreparedStatement.</li>
     * <li>Confirma la transaccion si la insercion fue exitosa; en caso de
     * error, revierte la transaccion.</li>
     * </ol>
     * </p>
     *
     * @param r Objeto Ruta que se desea agregar.
     */
    public void agregarRuta(Ruta r) {
        Connection conn = null;
        PreparedStatement checkUserPs = null;
        PreparedStatement checkActPs = null;
        PreparedStatement insertActPs = null;
        PreparedStatement ps = null;
        ResultSet userRs = null;
        ResultSet actRs = null;
        ResultSet generatedKeys = null;

        try {
            conn = getConnection();
            // Iniciar transacción
            conn.setAutoCommit(false);

            // Verificar si el usuario existe
            String checkUserSql = "SELECT id_usuario FROM usuarios WHERE id_usuario = ?";
            checkUserPs = conn.prepareStatement(checkUserSql);
            checkUserPs.setInt(1, r.getAutor().getId());
            userRs = checkUserPs.executeQuery();

            // Verificar si la actividad existe
            int actividadId;
            String checkActividadSql = "SELECT idActividades FROM actividades WHERE nombre_actividad = ?";
            checkActPs = conn.prepareStatement(checkActividadSql);
            checkActPs.setString(1, r.getTipoActividad().getNombre());
            actRs = checkActPs.executeQuery();

            if (actRs.next()) {
                actividadId = actRs.getInt("idActividades");
                System.out.println("Actividad encontrada con ID: " + actividadId);
            } else {
                // La actividad no existe, crearla
                String insertActSql = "INSERT INTO actividades (nombre_actividad) VALUES (?)";
                insertActPs = conn.prepareStatement(insertActSql, Statement.RETURN_GENERATED_KEYS);
                insertActPs.setString(1, r.getTipoActividad().getNombre());
                insertActPs.executeUpdate();
                generatedKeys = insertActPs.getGeneratedKeys();

                if (generatedKeys.next()) {
                    actividadId = generatedKeys.getInt(1);
                    System.out.println("Actividad creada con ID: " + actividadId);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la actividad creada");
                }
            }

            // Insertar la ruta
            String sql = "INSERT INTO rutas (id_usuario, idActividades, nombre, fecha, latitud_inicial, longitud_inicial, "
                    + "latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, "
                    + "altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, "
                    + "esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, "
                    + "media_valoraciones, duracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, r.getAutor().getId());                      // id_usuario
            ps.setInt(2, actividadId);                               // idActividades
            ps.setString(3, r.getNombre());                          // nombre
            ps.setDate(4, Date.valueOf(r.getFecha_creacion()));      // fecha
            ps.setDouble(5, r.getPunto_ini().getLatitud());          // latitud_inicial
            ps.setDouble(6, r.getPunto_ini().getLongitud());         // longitud_inicial
            ps.setDouble(7, r.getPunto_fin().getLatitud());          // latitud_final
            ps.setDouble(8, r.getPunto_fin().getLongitud());         // longitud_final
            ps.setDouble(9, r.getDistanciaTotal());                  // distancia
            ps.setDouble(10, r.getDesnivel());                       // desnivel
            ps.setDouble(11, r.getDesnivelPositivo());               // desnivel_positivo
            ps.setDouble(12, r.getDesnivelNegativo());               // desnivel_negativo
            ps.setDouble(13, r.getAltMin());                         // altitud_minima
            ps.setDouble(14, r.getAltMax());                         // altitud_maxima
            ps.setString(15, r.getEstado().toString());              // estado
            ps.setString(16, r.getUrl());                            // url
            ps.setBoolean(17, r.isFamiliar());                       // familiar
            ps.setString(18, r.getTemporada());                      // temporada
            ps.setInt(19, r.getIndicaciones());                      // indicaciones
            ps.setInt(20, r.getTipoTerreno());                       // terreno
            ps.setInt(21, r.getNivelEsfuerzo());                     // esfuerzo
            ps.setInt(22, r.getNivelRiesgo());                       // riesgo
            ps.setString(23, r.getZonaGeografica());                 // zona
            ps.setString(24, r.getRecomendaciones());                // recomendaciones
            ps.setString(25, r.getClasificacion().name());           // clasificacion
            ps.setString(26, "Inicio");                              // nombre_inicial (no puede ser null)
            ps.setString(27, "Fin");                                 // nombre_final (no puede ser null)
            ps.setInt(28, r.getMediaValoracion());                   // media_valoraciones
            ps.setDouble(29, r.getDuracion());                       // duracion

            int salida = ps.executeUpdate();

            // Confirmar la transacción si todo salió bien
            conn.commit();

            if (salida == 1) {
                JOptionPane.showMessageDialog(null, "Ruta creada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha insertado la ruta correctamente");
            }

        } catch (SQLException e) {
            // Revertir la transacción en caso de error
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "ERROR SQL al insertar ruta: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Revertir la transacción en caso de error
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "ERROR general: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar todos los recursos
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (actRs != null) {
                    actRs.close();
                }
                if (userRs != null) {
                    userRs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (insertActPs != null) {
                    insertActPs.close();
                }
                if (checkActPs != null) {
                    checkActPs.close();
                }
                if (checkUserPs != null) {
                    checkUserPs.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                    // No cerramos la conexión aquí porque es compartida
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Agrega una nueva resenna a la base de datos.
     * <p>
     * Ejecuta una instruccion INSERT en la tabla "reseña" para insertar la
     * reseña. Se espera que la operacion afecte a un solo registro.
     * </p>
     *
     * @param r Objeto Resenna que se va a agregar.
     * @return true si la insercion fue exitosa, false en caso contrario.
     */
    public boolean agregarResenna(Resenna r) {
        boolean exito = false;
        String sql = "insert into reseña (comentario, fecha, id_ruta, id_usuario)values(?,?,?,?);";
        int salida = -1;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, r.getComentario());
            ps.setDate(2, Date.valueOf(r.getFecha()));
            ps.setInt(3, r.getRuta().getId());
            ps.setInt(4, r.getUsuario().getId());
            salida = ps.executeUpdate();
            if (salida == 1) {
                exito = true;
                JOptionPane.showMessageDialog(null, "Reseña insertada");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return exito;
    }

    /**
     * Agrega una nueva valoracion tecnica a la base de datos.
     * <p>
     * Ejecuta una instruccion INSERT en la tabla "valoraciontecnica" para
     * insertar la valoracion tecnica.
     * </p>
     *
     * @param v Objeto ValoracionTec que se va a agregar.
     * @return true si la insercion fue exitosa, false en caso contrario.
     */
    public boolean agregarValoracionTecnica(ValoracionTec v) {
        boolean exito = false;
        String sql = "insert into valoraciontecnica (recomendaciones, dificultad, fecha, id_ruta, id_usuario)values(?,?,?,?,?);";
        int salida = -1;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, v.getEquipoRecomendado());
            ps.setString(2, v.getDificultad());
            ps.setDate(3, Date.valueOf(v.getFecha()));
            ps.setInt(4, v.getRuta().getId());
            ps.setInt(5, v.getUsuario().getId());
            salida = ps.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado un solo registro");
            } else {
                exito = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());

            JOptionPane.showMessageDialog(null, v.getUsuario().getId());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return exito;

    }

    /**
     * Obtiene una ruta de la base de datos por su ID.
     * <p>
     * Ejecuta una consulta SELECT en la tabla "rutas" usando el id
     * proporcionado. Se invoca el metodo {@code crearRuta(rs)} para construir
     * el objeto Ruta a partir de los datos obtenidos.
     * </p>
     *
     * @param id El id de la ruta.
     * @return Objeto Ruta que concuerda con el id, o null si no se encuentra.
     */
    public Ruta rutaPorId(int id) {
        Ruta ruta = null;
        String sql = "SELECT id_ruta, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones, duracion FROM rutas WHERE id_ruta=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    ruta = crearRuta(rs);
                }
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return ruta;
    }

    public PuntoInteres pInteresPorId(int id) {
        PuntoInteres pi = null;
        String sql = "SELECT idPuntos_interes, id_ruta, nombre, tipo, caracteristicas, url, longitud, latitud FROM puntos_interes  WHERE idPuntos_interes=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    pi = crearPuntoInteres(rs);
                }
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return pi;
    }

    /**
     * Obtiene un punto de interes a partir de sus coordenadas.
     * <p>
     * Ejecuta una consulta SELECT en la tabla "puntos_interes" donde se
     * comparan la longitud y latitud.
     * </p>
     *
     * @param longitud La longitud del punto.
     * @param latitud La latitud del punto.
     * @return Objeto PuntoInteres que concuerda con las coordenadas, o null si
     * no se encuentra.
     */
    public PuntoInteres pInteresPorCoordenadas(double longitud, double latitud) {
        PuntoInteres punto = null;
        String sql = "SELECT idPuntos_interes, id_ruta, nombre, tipo, caracteristicas, url, longitud, latitud FROM puntos_interes WHERE longitud=? AND latitud=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setDouble(1, longitud);
            stmt.setDouble(2, latitud);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    punto = crearPuntoInteres(rs);
                }
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return punto;

    }

    /**
     * Obtiene un punto de peligro a partir de sus coordenadas.
     * <p>
     * Ejecuta una consulta SELECT en la tabla "puntos_peligro" donde se
     * comparan la longitud y latitud.
     * </p>
     *
     * @param longitud La longitud del punto.
     * @param latitud La latitud del punto.
     * @return Objeto PuntoPeligro que concuerda con las coordenadas, o null si
     * no se encuentra.
     */
    public PuntoPeligro pPeligroPorCoordenadas(double longitud, double latitud) {
        PuntoPeligro punto = null;
        String sql = "SELECT idPuntos_peligro, id_ruta, descripcion, km, imagen, longitud, latitud FROM puntos_peligro WHERE longitud=? AND latitud=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setDouble(1, longitud);
            stmt.setDouble(2, latitud);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    punto = crearPuntoPeligro(rs);
                }
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return punto;

    }

    /**
     * Obtiene un usuario a partir de su id.
     * <p>
     * Ejecuta una consulta SELECT en la tabla "usuarios" donde se busca el
     * usuario con el id especificado.
     * </p>
     *
     * @param id El id del usuario.
     * @return Objeto Usuario que concuerda con el id, o null si no se
     * encuentra.
     */
    public static Usuario usuPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT id_usuario,nombre,apellidos,correo,contraseña,rol FROM usuarios WHERE id_usuario=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    usuario = crearUsuario(rs);
                }
            }

        } catch (SQLException ex) {
            // errores
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return usuario;
    }

    public Resenna resennaPorId(int idResenna) {
        Resenna resenna = null;
        String sql = "SELECT idReseña, comentario, fecha, id_ruta, id_usuario FROM reseña WHERE idReseña = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idResenna);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    resenna = crearResenna(rs);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar reseña: " + ex.getMessage());
        }

        return resenna;
    }

    /**
     * Elimina una resenna de la base de datos identificada por su id.
     * <p>
     * Ejecuta una instruccion DELETE en la tabla "reseñas" donde se elimina la
     * reseña con el id proporcionado.
     * </p>
     *
     * @param idRuta El id de la ruta que se va a eliminar.
     * @return true si la eliminacion fue exitosa, false en caso contrario.
     */
    public boolean eliminarRuta(int idRuta) {
        boolean exito = false;
        String sql = "DELETE FROM rutas WHERE id_ruta = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, idRuta);
            int resultado = ps.executeUpdate();

            if (resultado == 1) {
                exito = true;
            } else {
                JOptionPane.showMessageDialog(null, "No se eliminó ninguna ruta. ID: " + idRuta);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL al eliminar ruta: " + ex.getMessage());
        }

        return exito;
    }

    /**
     * Elimina una reseña de la base de datos.
     * <p>
     * Ejecuta una instruccion DELETE en la tabla "reseñas" para eliminar la
     * reseña que tenga el id especificado.
     * </p>
     *
     * @param k El id de la resenna a eliminar.
     * @return true si la eliminacion fue exitosa, false en caso contrario.
     */
    public boolean eliminarResenna(int k) {
        boolean exito = false;
        int resultado = -1;
        String sql = "delete from reseña where idReseña=?;";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, k);
            resultado = ps.executeUpdate();
            if (resultado == 1) {
                exito = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return exito;
    }

    /**
     * Modifica una ruta en la base de datos.
     * <p>
     * Ejecuta una instruccion UPDATE en la tabla "rutas" para actualizar los
     * datos de la ruta identificada por su id.
     * </p>
     *
     * @param k El id de la ruta a modificar.
     * @param r Objeto Ruta con los nuevos datos.
     */
    public void modificarRuta(int k, Ruta r) {

        String sql = "update rutas set id_usuario=?, nombre=?, fecha=?, latitud_inicial=?, longitud_inicial=?,"
                + "latitud_final=?, longitud_final=?, distancia=?, estado=?, url=?, familiar=?, altitud_minima=?, altitud_maxima=?, "
                + "temporada=?, indicaciones=?, terreno=?, esfuerzo=?, riesgo=?, zona=?, recomendaciones=?, clasificacion=?,"
                + "media_valoraciones=?, duracion=?, idActividades=?, desnivel=?, desnivel_positivo=?, desnivel_negativo=? where id_ruta=?;";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, r.getAutor().getId());
            ps.setString(2, r.getNombre());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setDouble(4, r.getPunto_ini().getLatitud());
            ps.setDouble(5, r.getPunto_ini().getLongitud());
            ps.setDouble(6, r.getPunto_fin().getLatitud());
            ps.setDouble(7, r.getPunto_fin().getLongitud());
            ps.setDouble(8, r.getDistanciaTotal());
            ps.setObject(9, r.getEstado().toString());
            ps.setString(10, r.getUrl());
            ps.setBoolean(11, r.isFamiliar());
            ps.setDouble(12, r.getAltMin());
            ps.setDouble(13, r.getAltMax());
            ps.setObject(14, r.getTemporada().toString());
            ps.setInt(15, r.getIndicaciones());
            ps.setInt(16, r.getTipoTerreno());
            ps.setInt(17, r.getNivelEsfuerzo());
            ps.setInt(18, r.getNivelRiesgo());
            ps.setString(19, r.getZonaGeografica());
            ps.setString(20, r.getRecomendaciones());
            ps.setObject(21, r.getClasificacion().toString());
            ps.setInt(22, r.getMediaValoracion());
            ps.setDouble(23, r.getDuracion());
            ps.setInt(24, obtenerOInsertarActividad(r.getTipoActividad().getNombre()));
            ps.setDouble(25, r.getDesnivel());
            ps.setDouble(26, r.getDesnivelPositivo());
            ps.setDouble(27, r.getDesnivelNegativo());
            ps.setInt(28, k);
            int resultado = ps.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente la ruta");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: no se ha modificado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }

    }

    /**
     * Modifica un punto de interes en la base de datos.
     * <p>
     * Ejecuta una instruccion UPDATE en la tabla "puntos_interes", actualizando
     * la url, el tipo y las caracteristicas del punto que se identifica por su
     * longitud y latitud.
     * </p>
     *
     * @param latitud La latitud del punto.
     * @param longitud La longitud del punto.
     * @param imagen La nueva url o ruta de la imagen.
     * @param tipo El nuevo tipo de punto de interes.
     * @param caracteristicasEsp Las nuevas caracteristicas especiales.
     */
    public void modificarPuntoInteres(double latitud, double longitud, String imagen, TipoPInteres tipo, String caracteristicasEsp) {
        String sql = "UPDATE puntos_interes SET url = ?, tipo = ?, caracteristicas = ? WHERE longitud = ? AND latitud = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, imagen);
            ps.setString(2, tipo.name());
            ps.setString(3, caracteristicasEsp);
            ps.setDouble(4, longitud);
            ps.setDouble(5, latitud);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());

        }
    }

    /**
     * Modifica un punto de peligro en la base de datos.
     * <p>
     * Ejecuta una instruccion UPDATE en la tabla "puntos_peligro", actualizando
     * la url, los kilometros, el nivel de gravedad y la descripcion
     * (justificacion) del punto, identificandolo mediante su longitud y
     * latitud.
     * </p>
     *
     * @param latitud La latitud del punto.
     * @param longitud La longitud del punto.
     * @param imagen La nueva url o ruta de la imagen.
     * @param km Los nuevos kilometros asociados.
     * @param nivelgravedad El nuevo nivel de gravedad.
     * @param justificacion La nueva justificacion o descripcion.
     */
    public void modificarPuntoPeligro(double latitud, double longitud, String imagen, int km, int nivelgravedad, String justificacion) {
        String sql = "UPDATE puntos_peligro SET url = ?, km = ?, nivelgravedad = ?, descripcion = ? WHERE longitud = ? AND latitud = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, imagen);
            ps.setInt(2, km);
            ps.setInt(3, nivelgravedad);
            ps.setString(4, justificacion);
            ps.setDouble(5, longitud);
            ps.setDouble(6, latitud);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    /**
     * Crea un objeto Ruta a partir de un ResultSet.
     * <p>
     * Extrae los datos de la consulta y construye una instancia de Ruta,
     * utilizando tambien el metodo 'usuPorId' para obtener el usuario autor.
     * </p>
     *
     * @param rs ResultSet que contiene los datos de la ruta.
     * @return Objeto Ruta construido a partir de los datos.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    private Ruta crearRuta(final ResultSet rs) throws SQLException {
        return new Ruta(
                rs.getInt("id_ruta"),
                usuPorId(rs.getInt("id_usuario")),
                rs.getString("nombre"),
                rs.getDate("fecha").toLocalDate(),
                new Punto(
                        rs.getDouble("latitud_final"),
                        rs.getDouble("longitud_final"),
                        null),
                new Punto(rs.getDouble("latitud_final"),
                        rs.getDouble("longitud_final"),
                        null),
                rs.getDouble("distancia"),
                rs.getDouble("desnivel"),
                rs.getDouble("desnivel_positivo"),
                rs.getDouble("desnivel_negativo"),
                rs.getDouble("altitud_maxima"),
                rs.getDouble("altitud_minima"),
                Clasificacion_Ruta.valueOf(rs.getString("clasificacion")),
                rs.getInt("riesgo"),
                rs.getInt("esfuerzo"),
                rs.getInt("terreno"),
                rs.getInt("indicaciones"),
                new Actividad(
                        rs.getString("nombre")
                ),
                rs.getString("temporada"),
                rs.getBoolean("familiar"),
                rs.getString("url"),
                Estado.valueOf(rs.getString("estado")),
                rs.getString("recomendaciones"),
                rs.getString("zona"),
                rs.getDouble("duracion"),
                rs.getInt("media_valoraciones")
        );
    }

    /**
     * Crea un objeto Usuario a partir de un ResultSet.
     * <p>
     * Determina el tipo de usuario mediante el metodo 'verificaUsuario' y segun
     * el valor, construye el objeto correspondiente (Alumno, DisennadorRuta,
     * Profesor o Administrador).
     * </p>
     *
     * @param rs ResultSet que contiene los datos del usuario.
     * @return Objeto Usuario construido a partir de los datos.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    private static Usuario crearUsuario(final ResultSet rs) throws SQLException {
        Usuario u = null;

        switch (verificaUsuario(rs.getString(4), rs.getString(5)).toString()) {
            case "ALUMNO" -> {
                u = new Alumno(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), TipoUsuario.valueOf(rs.getString(6)));
            }
            case "DISEÑADOR" -> {
                u = new DisennadorRuta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), TipoUsuario.valueOf(rs.getString(6)));
            }
            case "PROFESOR" -> {
                u = new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), TipoUsuario.valueOf(rs.getString(6)));
            }
            case "ADMINISTRADOR" -> {
                u = new Administrador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), TipoUsuario.valueOf(rs.getString(6)));

            }
            default -> {
                System.out.println("Error: tipo de usuario no soportado");
            }
        }
        return u;
    }

    /**
     * Agrega una nueva valoracion a la base de datos.
     * <p>
     * Ejecuta una instruccion INSERT en la tabla "valoraciones" para insertar
     * la valoracion. Se espera que la operacion afecte a un solo registro.
     * </p>
     *
     * @param v Objeto Valoracion que se va a agregar.
     */
    public void agregarValoracion(Valoracion v) {

        String sql = "insert into valoraciones(id_usuario,id_ruta,fecha,dificultad,belleza,interés) values (?,?,?,?,?,?);";
        int salida = -1;
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, v.getUsuario().getId());
            ps.setInt(2, v.getRuta().getId());
            ps.setDate(3, Date.valueOf(v.getFecha()));
            ps.setInt(4, v.getDificultad());
            ps.setInt(5, v.getBelleza());
            ps.setInt(6, v.getInteresCultural());
            salida = ps.executeUpdate();
            if (salida == 1) {
                JOptionPane.showMessageDialog(null, "Valoracion insertada");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Crea un objeto PuntoPeligro a partir de un ResultSet.
     * <p>
     * Extrae los datos del ResultSet y construye una instancia de PuntoPeligro,
     * utilizando los campos "latitud", "longitud", "imagen", "km",
     * "nivelgravedad" y "justificacion".
     * </p>
     *
     * @param rs ResultSet que contiene los datos del punto de peligro.
     * @return Objeto PuntoPeligro construido a partir de los datos.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    private PuntoPeligro crearPuntoPeligro(final ResultSet rs) throws SQLException {
        return new PuntoPeligro(
                rutaPorId(rs.getInt("id_ruta")),
                rs.getDouble("latitud"),
                rs.getDouble("longitud"),
                rs.getString("url"),
                rs.getInt("km"),
                rs.getInt("nivelgravedad"),
                rs.getString("justificacion")
        );
    }

    /**
     * Crea una objeto Valoracion a partir de un ResultSet.
     * <p>
     * Se obtiene el usuario y la ruta mediante los metodos usuPorId y
     * rutaPorId, respectivamente.
     * </p>
     *
     * @param rs ResultSet que contiene los datos de la valoracion.
     * @return Objeto Valoracion construido a partir de los datos, o null si el
     * usuario o la ruta son null.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    public Valoracion crearValoracion(final ResultSet rs) throws SQLException {
        Usuario usuario = usuPorId(rs.getInt("id_usuario"));
        Ruta ruta = rutaPorId(rs.getInt("id_ruta"));

        if (usuario != null && ruta != null) {
            return new Valoracion(
                    usuario,
                    ruta,
                    rs.getDate("fecha").toLocalDate(),
                    rs.getInt("dificultad"),
                    rs.getInt("belleza"),
                    rs.getInt("interés")
            );
        }
        return null;
    }

    /**
     * Crea un objeto Resenna a partir de un ResultSet.
     * <p>
     * Extrae el comentario, la fecha, y utiliza los metodos rutaPorId y
     * usuPorId para obtener la ruta y el usuario asociados a la reseña.
     * </p>
     *
     * @param rs ResultSet que contiene los datos de la resenna.
     * @return Objeto Resenna construido a partir de los datos.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    private Resenna crearResenna(final ResultSet rs) throws SQLException {

        return new Resenna(
                rs.getInt("idReseña"),
                rs.getString("comentario"),
                rs.getDate("fecha").toLocalDate(),
                rutaPorId(rs.getInt("id_ruta")),
                usuPorId(rs.getInt("id_usuario"))
        );

    }

    /**
     * Crea un objeto ValoracionTec a partir de un ResultSet.
     * <p>
     * Obtiene el usuario y la ruta mediante los metodos usuPorId y rutaPorId.
     * Posteriormente, extrae el campo "dificultad", "recomendaciones" y la
     * fecha para construir el objeto.
     * </p>
     *
     * @param rs ResultSet que contiene los datos de la valoracion tecnica.
     * @return Objeto ValoracionTec construido a partir de los datos, o null si
     * el usuario o la ruta son null.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    private ValoracionTec crearValoracionTecnica(final ResultSet rs) throws SQLException {
        Usuario usuario = usuPorId(rs.getInt("id_usuario"));
        Ruta ruta = rutaPorId(rs.getInt("id_ruta"));

        if (usuario != null && ruta != null) {
            return new ValoracionTec(
                    usuario,
                    ruta,
                    rs.getDate("fecha").toLocalDate(),
                    rs.getString("dificultad"),
                    rs.getString("recomendaciones")
            );
        }
        return null;
    }

    /**
     * Verifica el rol de un usuario a partir de su correo y contrasena.
     * <p>
     * Ejecuta una consulta SELECT en la tabla "usuarios" y, si el usuario es
     * encontrado, retorna su rol como un valor del enum TipoUsuario.
     * </p>
     *
     * @param email El correo del usuario.
     * @param password La contrasena del usuario.
     * @return El TipoUsuario correspondiente, o null si no se encuentra o hay
     * error.
     */
    public static TipoUsuario verificaUsuario(String email, String password) {
        String sql = "SELECT rol FROM usuarios WHERE correo = ? AND contraseña = ?";

        TipoUsuario rol = null;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String rolStr = rs.getString("rol").toUpperCase();
                    rol = TipoUsuario.valueOf(rolStr);
                } else {
                    System.out.println("Usuario no encontrado con ese correo.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
        }
        return rol;
    }

    /**
     * Crea un objeto PuntoInteres a partir de un ResultSet.
     * <p>
     * Extrae los datos "latitud", "longitud", "imagen", "tipo" y
     * "caracteristicasEsp" del ResultSet para construir una instancia de
     * PuntoInteres.
     * </p>
     *
     * @param rs ResultSet que contiene los datos del punto de interes.
     * @return Objeto PuntoInteres construido a partir de los datos.
     * @throws SQLException Si ocurre un error en la extraccion de datos.
     */
    private PuntoInteres crearPuntoInteres(final ResultSet rs) throws SQLException {
        return new PuntoInteres(
                rs.getInt("idPuntos_Interes"),
                rutaPorId(rs.getInt("id_ruta")),
                rs.getDouble("latitud"),
                rs.getDouble("longitud"),
                rs.getString("url"),
                rs.getString("nombre"),
                TipoPInteres.valueOf(rs.getString("tipo")),
                rs.getString("caracteristicasEsp")
                
        )
        ;
    }

    public int obtenerOInsertarActividad(String nombreActividad) {
        int idActividad = -1;

        try {
            // 1. Buscar si ya existe
            String sqlBuscar = "SELECT idActividades FROM actividades WHERE nombre_actividad = ?";
            try (PreparedStatement stmtBuscar = getConnection().prepareStatement(sqlBuscar)) {
                stmtBuscar.setString(1, nombreActividad);
                ResultSet rs = stmtBuscar.executeQuery();

                if (rs.next()) {
                    return rs.getInt("idActividades");
                }
            }

            // 2. Si no existe, insertamos
            String sqlInsertar = "INSERT INTO actividades (nombre_actividad) VALUES (?)";
            try (PreparedStatement stmtInsertar = getConnection().prepareStatement(sqlInsertar, Statement.RETURN_GENERATED_KEYS)) {
                stmtInsertar.setString(1, nombreActividad);
                stmtInsertar.executeUpdate();

                ResultSet rs = stmtInsertar.getGeneratedKeys();
                if (rs.next()) {
                    idActividad = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idActividad;
    }

    /**
     * Obtiene el id de un usuario a partir de su correo electronico.
     * <p>
     * Ejecuta una consulta SELECT en la tabla "usuarios" para obtener el
     * id_usuario y retorna dicho valor.
     * </p>
     *
     * @param email El correo electronico del usuario.
     * @return El id del usuario, o 1 si no se encuentra.
     */
    public static int idUsuario(String email) {
        String sql = "SELECT id_usuario FROM usuarios WHERE correo = ?";
        int id = -1;

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id_usuario");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
        }

        if (id == -1) {
            JOptionPane.showMessageDialog(null, "ERROR: Usuario no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return id;
    }

    // ============================================================== ENUMS ===================================================
    /**
     * Retorna una lista de cadenas con los valores del enum Clasificacion_Ruta.
     *
     * @return ArrayList con los nombres de las clasificaciones.
     */
    public static ArrayList<String> Clasificacion() {
        ArrayList<String> lista = new ArrayList<>();
        for (Clasificacion_Ruta c : Clasificacion_Ruta.values()) {
            lista.add(c.name());
        }
        return lista;
    }

    /**
     * Retorna una lista de cadenas con los valores del enum Estado.
     *
     * @return ArrayList con los nombres de los estados.
     */
    public static ArrayList<String> Estado() {
        ArrayList<String> lista = new ArrayList<>();
        for (Estado e : Estado.values()) {
            lista.add(e.name());
        }
        return lista;
    }

    /**
     * Retorna una lista de cadenas con los valores del enum Temporada.
     *
     * @return ArrayList con los nombres de las temporadas.
     */
    public static ArrayList<String> Temporada() {
        ArrayList<String> lista = new ArrayList<>();
        for (Temporada t : Temporada.values()) {
            lista.add(t.name());
        }
        return lista;
    }
}
