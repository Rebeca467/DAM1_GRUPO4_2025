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
        return null;
    }

    public List<Valoracion> listarValoraciones() {
        return null;
    }

    public List<Resenna> listarResaennas() {
        return null;
    }

    //PREGUNTAR
    public List<ValoracionTec> listarValoracionesTecnicas() {
        return null;
    }

    public void agregarRuta() {
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

    public static Ruta rutaPorId(int id) {
        return null;
    }

    public static Usuario usuPorId(int id) {
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
    private static Usuario crearUsuario(final ResultSet rs) throws SQLException {
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

    //USAR EN 'CREARRUTA'
    private Punto crearPunto(final ResultSet rs) throws SQLException {
        return new Punto(
            rs.getDouble("latitud"),
            rs.getDouble("longitud"),
            rs.getDouble("elevacion"),
            rs.getTimestamp("tiempo") != null ? rs.getTimestamp("tiempo").toLocalDateTime() : null,
            rs.getString("imagen")
        );
    }

    public static Valoracion crearValoracion(final ResultSet rs) throws SQLException {
        Usuario usuario = usuPorId(rs.getInt("id_usuario"));
        Ruta ruta = rutaPorId(rs.getInt("id_ruta"));
        
        if (usuario != null && ruta != null) {
            return new Valoracion(
                usuario,
                ruta,
                rs.getDate("fecha").toLocalDate(),
                rs.getInt("dificultad"),
                rs.getInt("belleza"),
                rs.getInt("interes")
            );
        }
        return null;
    }
    
    public static void guardarValoracionEnDB(Valoracion valoracion) {
    String sql = "INSERT INTO valoraciones (id_usuario, id_ruta, fecha, dificultad, belleza, interes) " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = AccesoBaseDatos.getInstance().getConn();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, valoracion.getUsuario().getId());
        stmt.setInt(2, valoracion.getRuta().getId());
        stmt.setDate(3, Date.valueOf(valoracion.getFecha()));
        stmt.setInt(4, valoracion.getDificultad());
        stmt.setInt(5, valoracion.getBelleza());
        stmt.setInt(6, valoracion.getInteresCultural());

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar valoración en la base de datos: " + e.getMessage());
    }
}


    private Resenna crearResenna(final ResultSet rs) throws SQLException {
        Usuario usuario = usuPorId(rs.getInt("id_usuario"));
        Ruta ruta = rutaPorId(rs.getInt("id_ruta"));
        
        if (usuario != null && ruta != null) {
            return new Resenna(
                rs.getInt("idReseña"),
                usuario,
                ruta,
                rs.getDate("fecha").toLocalDate(),
                rs.getString("comentario")
            );
        }
        return null;
    }

    private ValoracionTec crearValoracionTecnica(final ResultSet rs) throws SQLException {
        Usuario usuario = usuPorId(rs.getInt("id_usuario"));
        Ruta ruta = rutaPorId(rs.getInt("id_ruta"));
        
        if (usuario != null && ruta != null) {
            return new ValoracionTec(
                rs.getInt("idValoracionTecnica"),
                usuario,
                ruta,
                rs.getDate("fecha").toLocalDate(),
                rs.getString("dificultad"),
                rs.getString("recomendaciones")
            );
        }
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
