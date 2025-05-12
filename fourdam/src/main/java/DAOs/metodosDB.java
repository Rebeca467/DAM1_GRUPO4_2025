package DAOs;

import ENUMs.ClasificacionRuta;
import ENUMs.Estado;
import ENUMs.TipoUsuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import reto.fourdam.AccesoBaseDatos;
import reto.fourdam.Punto;
import reto.fourdam.Resenna;
import reto.fourdam.Ruta;
import reto.fourdam.SWING.Calendario;
import reto.fourdam.Usuario;
import reto.fourdam.Valoracion;
import reto.fourdam.ValoracionTec;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DAM120
 */
public class metodosDB {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public List<Ruta> listarRutas() {
        List<Ruta> rutas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_rutas, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones FROM rutas;");) {
            while (rs.next()) {
                Ruta ruta = crearRuta(rs);
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

    public List<Punto> listarPuntos() {
       List<Punto> puntos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_rutas, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones FROM rutas;");) {
            while (rs.next()) {
                Punto punto = crearPunto(rs);
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

    public List<Valoracion> listarValoraciones() {
        List<Valoracion> valoraciones = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_rutas, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones FROM rutas;");) {
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

    public List<Resenna> listarResaennas() {
        List<Resenna> resennas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_rutas, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones FROM rutas;");) {
            while (rs.next()) {
                Resenna resenna= crearResenna(rs);
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

    //PREGUNTAR
    public List<ValoracionTec> listarValoracionesTecnicas() {
        
        List<ValoracionTec> valoracionesTecnicas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_rutas, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones FROM rutas;");) {
            while (rs.next()) {
                ValoracionTec valoracionTec= crearValoracionTecnica(rs);
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

    public void agregarRuta(Ruta r) {
        Connection con = AccesoBaseDatos.getInstance().getConn();
        boolean exito = false;
        String sql = "insert into ruta (id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int salida = -1;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
        ps.setString(2, r.getNombre());      // nombre
        ps.setDate(3, Date.valueOf(r.getFecha_creacion())); // fecha
        ps.setDouble(4, r.getPunto_ini().getLatitud());     // latitud_inicial
        ps.setDouble(5, r.getPunto_ini().getLongitud());    // longitud_inicial
        ps.setDouble(6, r.getPunto_fin().getLatitud());     // latitud_final
        ps.setDouble(7, r.getPunto_fin().getLongitud());    // longitud_final
        ps.setDouble(8, r.getDistanciaTotal());             // distancia
        ps.setDouble(9, r.getDesnivel());                   // desnivel total
        ps.setDouble(10,r.getDesnivel());                  // desnivel_positivo (ajústalo si tienes valor separado)
        ps.setDouble(11, r.getDesnivel());                  // desnivel_negativo (ídem)
        ps.setDouble(12, r.getAltMin());                    // altitud_minima
        ps.setDouble(13, r.getAltMax());                    // altitud_maxima
        ps.setString(14, r.getEstado().toString());         // estado
        ps.setString(15, r.getUrl());                       // url
        ps.setBoolean(16, r.isFamiliar());                  // familiar
        ps.setString(17, String.join(",", r.getTemporada())); // temporada
        ps.setInt(18, r.getIndicaciones());                 // indicaciones
        ps.setInt(19, r.getTipoTerreno());                  // terreno
        ps.setInt(20, r.getNivelEsfuerzo());                // esfuerzo
        ps.setInt(21, r.getNivelRiesgo());                  // riesgo
        ps.setString(22, r.getZonaGeografica());            // zona
            
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    public void agregarCalendario() {
    }

    public boolean agregarResenna(Resenna r) {
        Connection con = AccesoBaseDatos.getInstance().getConn();
        boolean exito = false;
        String sql = "insert into reseña (idReseña, comentario, fecha, id_ruta, id_usuario)values(?,?,?,?,?);";
        int resultado = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getIdResenna());
            ps.setString(2, r.getComentario());
            ps.setDate(3, Date.valueOf(r.getFecha()));
            ps.setInt(4, r.getRuta().getId());
            ps.setInt(5, r.getUsuario().getId());
            resultado = ps.executeUpdate();
            if (resultado == 1) {
                exito = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return exito;
    }

    public boolean agregarValoracionTecnica(ValoracionTec v) {
        Connection con = AccesoBaseDatos.getInstance().getConn();
        boolean exito = false;
        String sql = "insert into valoraciontetcnica (idValoracionTecnica, recomendaciones, dificultad, fecha, id_ruta, id_usuario)values(?,?,?,?,?,?);";
        int resultado = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdValoracionTecnica());
            ps.setString(2, v.getEquipoRecomendado());
            ps.setString(3, v.getDificultad());
            ps.setDate(4, Date.valueOf(v.getFecha()));
            ps.setInt(5, v.getRuta().getId());
            ps.setInt(6, v.getUsuario().getId());
            resultado = ps.executeUpdate();
            if (resultado == 1) {
                exito = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return exito;

    }

    public Ruta rutaPorId(int id) {
        return null;
    }

    public Usuario usuPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT id_usuario,nombre,apellidos,correo,contraseña,rol FROM usuarios WHERE id=?";
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

    //PENSE QUE SI LA RUTA GUARDA EN AUTOR EL CORREO DEL USUARIO SOLO NECESITARIAMOS ESTE METODO
    public Usuario usuPorCorreo(String correo) {
        return null;
    }

    public void eliminarRuta() {
    }

    public boolean eliminarResenna(int k, Resenna r) {
        boolean exito = false;
        int resultado = -1;
        String sql = "delete reseñas where idReseña=?;";
        Connection con = AccesoBaseDatos.getInstance().getConn();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, k);
            resultado = ps.executeUpdate();
            if (resultado == 1) {
                exito = true;
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: ");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
        return exito;
    }

    public void modificarRuta() {

    }

    public void modificarPunto() {
    }

    private Ruta crearRuta(final ResultSet rs) throws SQLException {
        return new Ruta(
                usuPorId(rs.getInt("id_usuario")),
                rs.getString("nombre"),
                rs.getDate("fecha").toLocalDate(),
                new Punto(
                        rs.getDouble("latitud_final"),
                        rs.getDouble("longitud_final"),
                        rs.getDouble("elevacion"),
                        rs.getTimestamp("tiempo_final").toLocalDateTime(),
                        null),
                new Punto(rs.getDouble("latitud_final"),
                        rs.getDouble("longitud_final"),
                        rs.getDouble("elevacion"),
                        rs.getTimestamp("tiempo_final").toLocalDateTime(),
                        null),
                rs.getDouble("distancia"),
                null,
                rs.getDouble("desnivel"),
                rs.getDouble("altitud_maxima"),
                rs.getDouble("altitud_minima"),
                ClasificacionRuta.valueOf(rs.getString("clasificacion")),
                rs.getInt("riesgo"),
                rs.getInt("esfuerzo"),
                rs.getInt("terreno"),
                rs.getInt("indicaciones"),
                rs.getString("actividad"),
                Set.of(rs.getString("temporada").split(",")),
                rs.getBoolean("accesibilidad"),
                rs.getBoolean("familiar"),
                rs.getString("url"),
                Estado.valueOf(rs.getString("estado")),
                rs.getString("recomendaciones"),
                rs.getString("zona"),
                rs.getDouble("duracion"),
                null
        );
    }

    // si hay numeros es porque se pueden pedir datos por index
    private Usuario crearUsuario(final ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                TipoUsuario.valueOf(rs.getString(6)));
    }

    public void insertarValoracion(Valoracion v) {

        String sql = "insert into valoraciones(id_usuario,id_ruta,fecha,dificultad,belleza,interés) values (?,?,?,?,?,?);";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {

            ps.setInt(1, v.getUsuario().getId());

            ps.setInt(2, v.getRuta().getId());

            ps.setDate(3, Date.valueOf(v.getFecha()));

            ps.setInt(4, v.getDificultad());

            ps.setInt(5, v.getBelleza());

            ps.setInt(6, v.getInteresCultural());

        } catch (SQLException e) {

        }
    }

    private Calendario crearCalendario(final ResultSet rs) throws SQLException {
        return null;
    }

    //USAR EN 'CREARRUTA'
    private Punto crearPunto(final ResultSet rs) throws SQLException {
        return null;
    }

    private Valoracion crearValoracion(final ResultSet rs) throws SQLException {
        return null;
    }

    private Resenna crearResenna(final ResultSet rs) throws SQLException {
        return null;
    }

    private ValoracionTec crearValoracionTecnica(final ResultSet rs) throws SQLException {
        return null;
    }

    public static void verificaUsuario(String email) {
        String sql = "SELECT rol FROM usuarios WHERE correo = ?";

        try (Connection conn = AccesoBaseDatos.getInstance().getConn(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String rolStr = rs.getString("rol").toUpperCase();
                    try {
                        TipoUsuario rol = TipoUsuario.valueOf(rolStr);
                        System.out.println("El usuario tiene el rol: " + rol);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Rol no reconocido en la base de datos: " + rolStr);
                    }
                } else {
                    System.out.println("Usuario no encontrado con ese correo.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
        }
    }
}
