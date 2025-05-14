package DAOs;

import ENUMs.ClasificacionRuta;
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
 *
 * @author DAM120
 */
public class metodosDB {

    private static Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }

    public ArrayList<Ruta> listarRutas() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery("SELECT id_ruta, id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones, duracion FROM rutas");) {
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

    // este metodo tiene la consulta igual que listar rutas
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

    // este metodo tiene la consulta igual que listar rutas
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

    // este metodo tiene la consulta igual que listar rutas
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

    // este metodo tiene la consulta igual que listar rutas
    //PREGUNTAR
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

    public void agregarRuta(Ruta r) {
        Connection con = AccesoBaseDatos.getInstance().getConn();
        boolean exito = false;
        String sql = "insert into ruta (id_usuario, nombre, fecha, latitud_inicial, longitud_inicial, latitud_final, longitud_final, distancia, desnivel, desnivel_positivo, desnivel_negativo, altitud_minima, altitud_maxima, estado, url, familiar, temporada, indicaciones, terreno, esfuerzo, riesgo, zona, recomendaciones, clasificacion, nombre_inicial, nombre_final, media_valoraciones)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int salida = -1;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(2, r.getNombre());      // nombre
            ps.setDate(3, Date.valueOf(r.getFecha_creacion())); // fecha
            ps.setDouble(4, r.getPunto_ini().getLatitud());     // latitud_inicial
            ps.setDouble(5, r.getPunto_ini().getLongitud());    // longitud_inicial
            ps.setDouble(6, r.getPunto_fin().getLatitud());     // latitud_final
            ps.setDouble(7, r.getPunto_fin().getLongitud());    // longitud_final
            ps.setDouble(8, r.getDistanciaTotal());             // distancia
            ps.setDouble(9, r.getDesnivel());                   // desnivel total
            ps.setDouble(10, r.getDesnivelPositivo());                  // desnivel_positivo (ajústalo si tienes valor separado)
            ps.setDouble(11, r.getDesnivelNegativo());                  // desnivel_negativo (ídem)
            ps.setDouble(12, r.getAltMin());                    // altitud_minima
            ps.setDouble(13, r.getAltMax());                    // altitud_maxima
            ps.setString(14, r.getEstado().toString());         // estado
            ps.setString(15, r.getUrl());                       // url
            ps.setBoolean(16, r.isFamiliar());
            ps.setObject(17, r.getTemporada());
            ps.setInt(18, r.getIndicaciones());                 // indicaciones
            ps.setInt(19, r.getTipoTerreno());                  // terreno
            ps.setInt(20, r.getNivelEsfuerzo());                // esfuerzo
            ps.setInt(21, r.getNivelRiesgo());                  // riesgo
            ps.setString(22, r.getZonaGeografica());            // zona
            ps.setString(23, r.getRecomendaciones());            // zona
            ps.setString(24, r.getClasificacion().name());            // zona
            ps.setString(25, null);            // zona
            ps.setString(26, null);
            ps.setObject(27, r.getMediaValoracion());
            salida = ps.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }

    }

    public boolean agregarResenna(Resenna r) {
        Connection con = AccesoBaseDatos.getInstance().getConn();
        boolean exito = false;
        String sql = "insert into reseña (idReseña, comentario, fecha, id_ruta, id_usuario)values(?,?,?,?,?);";
        int salida = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getIdResenna());
            ps.setString(2, r.getComentario());
            ps.setDate(3, Date.valueOf(r.getFecha()));
            ps.setInt(4, r.getRuta().getId());
            ps.setInt(5, r.getUsuario().getId());
            salida = ps.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return exito;
    }

    public boolean agregarValoracionTecnica(ValoracionTec v) {
        Connection con = AccesoBaseDatos.getInstance().getConn();
        boolean exito = false;
        String sql = "insert into valoraciontecnica (idValoracionTecnica, recomendaciones, dificultad, fecha, id_ruta, id_usuario)values(?,?,?,?,?,?);";
        int salida = -1;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdValoracionTecnica());
            ps.setString(2, v.getEquipoRecomendado());
            ps.setString(3, v.getDificultad());
            ps.setDate(4, Date.valueOf(v.getFecha()));
            ps.setInt(5, v.getRuta().getId());
            ps.setInt(6, v.getUsuario().getId());
            salida = ps.executeUpdate();
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return exito;

    }

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

    //PENSE QUE SI LA RUTA GUARDA EN AUTOR EL CORREO DEL USUARIO SOLO NECESITARIAMOS ESTE METODO
    public Usuario usuPorCorreo(String correo) {
        return null;
    }

    public boolean eliminarRuta(int k, Ruta r) {
        boolean exito = false;
        int resultado = -1;
        String sql = "delete reseñas where idReseña=?;";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
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

    public boolean eliminarResenna(int k, Resenna r) {
        boolean exito = false;
        int resultado = -1;
        String sql = "delete reseñas where idReseña=?;";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
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

    public void modificarRuta(int k, Ruta r) {

        String sql = "update rutas set nombre=?, fecha=?,"

                + "distancia=?, estado=?, url=?, familiar=?, "

                + "temporada=?, indicaciones=?, terreno=?, esfuerzo=?, riesgo=?, zona=?, recomendaciones=?, clasificacion=?,"

                + "media_valoracion=?, duracion=? where id_ruta=?;";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setDouble(3, r.getDuracion());
            ps.setObject(4, r.getEstado());
            ps.setString(5, r.getUrl());
            ps.setBoolean(6, r.isFamiliar());
            ps.setObject(7,r.getTemporada());
            ps.setInt(8, r.getIndicaciones());
            ps.setInt(9, r.getTipoTerreno());
            ps.setInt(10, r.getNivelEsfuerzo());
            ps.setInt(11, r.getNivelRiesgo());
            ps.setString(12, r.getZonaGeografica());
            ps.setString(13, r.getRecomendaciones());
            ps.setObject(14, r.getClasificacion());
            ps.setInt(15, r.getMediaValoracion()); 
            ps.setDouble(16, r.getDuracion());
            int resultado = ps.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente el taller");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: no se ha modificado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
 
        

    }

    public void modificarPuntoInteres(double latitud, double longitud, String imagen, TipoPInteres tipo, String caracteristicasEsp) {
        String sql = "UPDATE puntos_interes SET url = ?, tipo = ?, caracteristicas = ? WHERE longitud = ? AND latitud = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setString(1, imagen);
            ps.setString(2, tipo.name());
            ps.setString(3, caracteristicasEsp);
            ps.setDouble(4, longitud);
            ps.setDouble(5, latitud);
            ps.executeUpdate();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            
        }
    }

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

    private Ruta crearRuta(final ResultSet rs) throws SQLException {
        return new Ruta(
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
                ClasificacionRuta.valueOf(rs.getString("clasificacion")),
                rs.getInt("riesgo"),
                rs.getInt("esfuerzo"),
                rs.getInt("terreno"),
                rs.getInt("indicaciones"),
                new Actividad(
                        rs.getString("nombre")
                ),
                Set.of(rs.getString("temporada").split(",")),
                rs.getBoolean("familiar"),
                rs.getString("url"),
                Estado.valueOf(rs.getString("estado")),
                rs.getString("recomendaciones"),
                rs.getString("zona"),
                null,
                rs.getDouble("duracion"),
                rs.getInt("media_valoraciones")
        );
    }

    // si hay numeros es porque se pueden pedir datos por index
    private static Usuario crearUsuario(final ResultSet rs) throws SQLException {
        Usuario u = null;
        switch(verificaUsuario(rs.getString(4)).toString()){
            case "INVITADO" -> {
                u = new Usuario(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),TipoUsuario.valueOf(rs.getString(6)));
            }
            case "ALUMNO" -> {
                u = new Alumno(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),TipoUsuario.valueOf(rs.getString(6)));
            }
            case "DISENADOR_DE_RUTAS" -> {
                u = new DisennadorRuta(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),TipoUsuario.valueOf(rs.getString(6)));
            }
            case "PROFESOR" -> {
                u = new Profesor(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),TipoUsuario.valueOf(rs.getString(6)));
            }
            case "ADMIN" -> {
                u = new Administrador(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),TipoUsuario.valueOf(rs.getString(6)));
            }
            default -> {
                System.out.println("Error: tipo de usuario no soportado");
            }
        }
        return u;
    }

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
            if (salida != 1) {
                throw new Exception(" No se ha insertado/modificado un solo registro");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
    }

    //USAR EN 'CREARRUTA'
    private PuntoPeligro crearPuntoPeligro(final ResultSet rs) throws SQLException {
        return new PuntoPeligro(
                rs.getDouble("latitud"),
                rs.getDouble("longitud"),
                rs.getString("imagen"),
                rs.getInt("km"),
                rs.getInt("nivelgravedad"),
                rs.getString("justificacion")
        );
    }

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

    private Resenna crearResenna(final ResultSet rs) throws SQLException {

        return new Resenna(
                rs.getString("comentario"),
                rs.getDate("fecha").toLocalDate(),
                rutaPorId(rs.getInt("id_ruta")),
                usuPorId(rs.getInt("id_usuario"))
        );

    }

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

    public static TipoUsuario verificaUsuario(String email) {
        String sql = "SELECT rol FROM usuarios WHERE correo = ?";

        TipoUsuario rol = null;
        try ( PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String rolStr = rs.getString("rol").toUpperCase();
                    try {
                        rol = TipoUsuario.valueOf(rolStr);

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
        return rol;
    }


    private PuntoInteres crearPuntoInteres(final ResultSet rs) throws SQLException {
        return new PuntoInteres(
                rs.getDouble("latitud"),
                rs.getDouble("longitud"),
                rs.getString("imagen"),
                TipoPInteres.valueOf(rs.getString("tipo")),
                rs.getString("caracteristicasEsp")
        );
    }


    public static int idUsuario(String email) {
        String sql = "SELECT id_usuario FROM usuarios WHERE correo = ?";
        int id=1;
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idrs = rs.getInt("id_usuario");
                    try {
                        id = idrs;
                        System.out.println("Id del usuario: " + id);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Id no reconocido en la base de datos: " + idrs);
                    }
                } else {
                    System.out.println("Usuario no encontrado con ese id.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al acceder a la base de datos: " + e.getMessage());
        }
        return id;
    }

    // ============================================================== ENUMS ===================================================
    public static ArrayList<String> Clasificacion() {
        ArrayList<String> lista = new ArrayList<>();
        for (ClasificacionRuta c : ClasificacionRuta.values()) {
            lista.add(c.name());
        }
        return lista;
    }

    public static ArrayList<String> Estado() {
        ArrayList<String> lista = new ArrayList<>();
        for (Estado e : Estado.values()) {
            lista.add(e.name());
        }
        return lista;
    }

    public static ArrayList<String> Temporada() {
        ArrayList<String> lista = new ArrayList<>();
        for (Temporada t : Temporada.values()) {
            lista.add(t.name());
        }
        return lista;
    }

}
