/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.ClasificacionRuta;
import ENUMs.TipoPInteres;
import ENUMs.TipoUsuario;
import ENUMs.Estado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para manejar la conversión de rutas a CSV y viceversa.
 *
 * @author DAM106
 */
public class Fichero {

    /**
     * Convierte una ruta a formato CSV.
     *
     * @param ruta Objeto Ruta a convertir.
     * @return String en formato CSV.
     */
    /*public static String rutaToCsv(Ruta ruta) {
        String linea = ruta.getNombre() + ";"
                + ruta.getAutor().getNombre() + ";"
                + ruta.getAutor().getEmail() + ";"
                + ruta.getFecha_creacion() + ";"
                + ruta.getDistanciaTotal() + ";"
                + ruta.getDesnivel() + ";"
                + ruta.getAltMax() + ";"
                + ruta.getAltMin() + ";"
                + ruta.getClasificacion() + ";"
                + ruta.getNivelRiesgo() + ";"
                + ruta.getNivelEsfuerzo() + ";"
                + ruta.getTipoTerreno() + ";"
                + ruta.getIndicaciones() + ";"
                + ruta.getTipoActividad().getNombre() + ";"
                + ruta.getTemporada() + ";"
                + ruta.isFamiliar() + ";"
                + ruta.getUrl() + ";"
                + ruta.getEstado() + ";"
                + ruta.getRecomendaciones() + ";"
                + ruta.getZonaGeografica() + ";"
                + ruta.getDuracion() + "\n";

        linea += "Tipo;Latitud;Longitud;Elevación;Tiempo;Nombre\n";

        if (ruta.getPunto_ini() != null) {
            Punto ini = ruta.getPunto_ini();
            linea += "waypoint;" + ini.getLatitud() + ";" + ini.getLongitud() + ";" + ini.getElevacion() + ";" + ini.getTiempo() + ";" + ruta.getNombre() + "\n";
        }

        if (ruta.getPunto_fin() != null) {
            Punto fin = ruta.getPunto_fin();
            linea += "waypoint;" + fin.getLatitud() + ";" + fin.getLongitud() + ";" + fin.getElevacion() + ";" + fin.getTiempo() + ";" + ruta.getNombre() + "\n";
        }

        return linea;
    }

    /**
     * Convierte un archivo CSV a un objeto Ruta.
     *
     * @param file Archivo CSV a leer.
     * @return Objeto Ruta generado a partir del archivo.
     */
    /*public static Ruta csvToRuta(File file) {
        Ruta ruta = new Ruta();
        List<PuntoInteres> waypoints = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] parts = linea.split(";");

                if (parts.length < 22) {
                    System.out.println("Cabecera incompleta. Se esperaban al menos 22 campos.");
                    return null;
                }
                // Parsear los datos de la ruta

                if (!waypoints.isEmpty()) {
                    ruta.setPunto_ini(waypoints.get(0));
                    ruta.setPunto_fin(waypoints.get(waypoints.size() - 1));

                    String nombreRuta = parts[0];
                    String nombreAutor = parts[1];
                    String email = parts[2];
                    LocalDate fecha = LocalDate.parse(parts[3]);
                    double distancia = Double.parseDouble(parts[4]);
                    double desnivel = Double.parseDouble(parts[5]);
                    double altMax = Double.parseDouble(parts[6]);
                    double altMin = Double.parseDouble(parts[7]);
                    ClasificacionRuta clasificacion = ClasificacionRuta.valueOf(parts[8]);
                    int nivelRiesgo = Integer.parseInt(parts[9]);
                    int nivelEsfuerzo = Integer.parseInt(parts[10]);
                    int tipoTerreno = Integer.parseInt(parts[11]);
                    int indicaciones = Integer.parseInt(parts[12]);
                    String nombreActividad = parts[13]; // Ahora es el nombre de la actividad
                    Set<String> temporada = Set.of(parts[14].split(","));
                    boolean familiar = Boolean.parseBoolean(parts[15]);
                    String url = parts[16];
                    Estado estado = Estado.valueOf(parts[17]);
                    String recomendaciones = parts[18];
                    String zonaGeografica = parts[19];
                    double duracion = Double.parseDouble(parts[20]);

                    // Crear la actividad a partir del nombre
                    Actividad actividad = new Actividad(nombreActividad);

                    // Comprobar usuario en la base de datos
                    TipoUsuario rol = null;
                    String sql = "SELECT * FROM usuarios WHERE correo=?";
                    Connection conn = AccesoBaseDatos.getInstance().getConn();
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, email);
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                String rolStr = rs.getString("rol").toUpperCase();
                                rol = TipoUsuario.valueOf(rolStr);
                            } else {
                                System.out.println("Usuario no encontrado en la base de datos: " + email);
                                return null;
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error al consultar usuario: " + ex.getMessage());
                        return null;
                    }

                    Usuario autor = new Usuario(nombreAutor, "", email, "", rol);
                    ruta.setAutor(autor);
                    ruta.setNombre(nombreRuta);
                    ruta.setFecha_creacion(fecha);
                    ruta.setDistanciaTotal((float) distancia);
                    ruta.setDesnivel((float) desnivel);
                    ruta.setAltMax((float) altMax);
                    ruta.setAltMin((float) altMin);
                    ruta.setClasificacion(clasificacion);
                    ruta.setNivelRiesgo(nivelRiesgo);
                    ruta.setNivelEsfuerzo(nivelEsfuerzo);
                    ruta.setTipoTerreno(tipoTerreno);
                    ruta.setIndicaciones(indicaciones);
                    ruta.setTipoActividad(actividad); // Asignar la actividad
                    ruta.setTemporada(temporada);

                    ruta.setFamiliar(familiar);
                    ruta.setUrl(url);
                    ruta.setEstado(estado);
                    ruta.setRecomendaciones(recomendaciones);
                    ruta.setZonaGeografica(zonaGeografica);
                    ruta.setDuracion(duracion);
                }

                // Leer puntos de interés
                br.readLine(); // Saltar la cabecera de puntos

                String puntoLinea;
                while ((puntoLinea = br.readLine()) != null) {
                    String[] puntoParts = puntoLinea.split(";");
                    if (puntoParts.length >= 6) {
                        String tipoStr = puntoParts[0];
                        double lat = Double.parseDouble(puntoParts[1]);

                        double lon = Double.parseDouble(puntoParts[3]);
                        String nombre = puntoParts[4];
                        TipoPInteres tipo = null;
                        try {
                            tipo = TipoPInteres.valueOf(tipoStr.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tipo de punto no reconocido: " + tipoStr + ". Se usará el primer valor del enum.");
                            if (TipoPInteres.values().length > 0) {
                                tipo = TipoPInteres.values()[0];
                            } else {
                                System.out.println("No hay valores en el enum TipoPInteres. No se puede crear el punto.");
                                continue;
                            }
                        }

                        PuntoInteres punto = new PuntoInteres(lat, lon, "imagen.jpg", tipo, nombre);
                        waypoints.add(punto);

                    }
                }

                if (!waypoints.isEmpty()) {
                    ruta.setPunto_ini(waypoints.get(0));
                    ruta.setPunto_fin(waypoints.get(waypoints.size() - 1));
                    PuntoInteres punto = new PuntoInteres(lat, lon, "imagen.jpg", tipo, nombre);
                    waypoints.add(punto);

                }

                
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
            return null;
        }
        return ruta;

    }*/
}
