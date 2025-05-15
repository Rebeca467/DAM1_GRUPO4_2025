package reto.fourdam;

import ENUMs.Clasificacion_Ruta;
import ENUMs.TipoPInteres;
import ENUMs.TipoUsuario;
import ENUMs.Estado;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Clase para manejar la conversion de rutas a CSV y viceversa.
 * <p>
 * Esta clase proporciona metodos para convertir una ruta a una cadena en
 * formato CSV, asi como para generar un objeto Ruta a partir de un archivo CSV.
 * </p>
 *
 * @author Oriol Fernandez Saiz
 */
public class Fichero {

    private static final String rutaFichas = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "FichasRutas";


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
    public static Ruta csvToRuta(File file) {
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
                String nombreRuta = parts[0];
                String nombreAutor = parts[1];
                String email = parts[2];
                LocalDate fecha = LocalDate.parse(parts[3]);
                double distancia = Double.parseDouble(parts[4]);
                double desnivel = Double.parseDouble(parts[5]);
                double altMax = Double.parseDouble(parts[6]);
                double altMin = Double.parseDouble(parts[7]);
                Clasificacion_Ruta clasificacion = Clasificacion_Ruta.valueOf(parts[8]);
                int nivelRiesgo = Integer.parseInt(parts[9]);
                int nivelEsfuerzo = Integer.parseInt(parts[10]);
                int tipoTerreno = Integer.parseInt(parts[11]);
                int indicaciones = Integer.parseInt(parts[12]);
                String nombreActividad = parts[13]; // Ahora es el nombre de la actividad
                String temporada = parts[14];
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

                // Leer puntos de interés
                br.readLine(); // Saltar la cabecera de puntos

                String puntoLinea;
                while ((puntoLinea = br.readLine()) != null) {
                    String[] puntoParts = puntoLinea.split(";");
                    if (puntoParts.length >= 6) {
                        String tipoStr = puntoParts[0];
                        double lat = Double.parseDouble(puntoParts[1]);
                        double lon = Double.parseDouble(puntoParts[2]);
                        double elevacion = Double.parseDouble(puntoParts[3]);
                        String tiempo = puntoParts[4];
                        String nombre = puntoParts[5];

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
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
            return null;
        }
        return ruta;
    }

    
    // Método para asegurar que la carpeta FichasRutas existe
    private static void asegurarCarpetaFichas() {
        File carpeta = new File(rutaFichas);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // crea la carpeta si no existe
        }
    }
    
    
    public static void generarFichaSeguridad(String dificultadGeneral, String puntosPeligro, String recomendaciones) {
        asegurarCarpetaFichas();
        String contenido = "=== FICHA DE SEGURIDAD ===\n"
                + "Dificultad general: " + dificultadGeneral + "\n"
                + "Puntos de peligro: " + puntosPeligro + "\n"
                + "Recomendaciones: " + recomendaciones + "\n";

        File archivo = new File(rutaFichas + File.separator + "ficha_seguridad.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Ficha de seguridad generada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la ficha de seguridad: " + e.getMessage());
        }
    }

    public static void generarFichaUsuario(String datosGenerales, String perfilRecorrido, String recomendaciones) {
        asegurarCarpetaFichas();
        String contenido = "=== FICHA DE USUARIO ===\n"
                + "Datos generales: " + datosGenerales + "\n"
                + "Perfil del recorrido: " + perfilRecorrido + "\n"
                + "Recomendaciones: " + recomendaciones + "\n";

        File archivo = new File(rutaFichas + File.separator + "ficha_usuario.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Ficha de usuario generada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la ficha de usuario: " + e.getMessage());
        }
    }

    public static void generarFichaOrganizacion(String datosBasicos, String altitud, String tipoRuta, String opinionTecnica, String nivelEducativo) {
        asegurarCarpetaFichas();
        String contenido = "=== FICHA DE ORGANIZACIÓN ===\n"
                + "Datos básicos: " + datosBasicos + "\n"
                + "Altitud: " + altitud + "\n"
                + "Tipo de ruta: " + tipoRuta + "\n"
                + "Opinión técnica: " + opinionTecnica + "\n"
                + "Nivel educativo recomendado: " + nivelEducativo + "\n";

        File archivo = new File(rutaFichas + File.separator + "ficha_organizacion.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Ficha de organización generada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la ficha de organización: " + e.getMessage());
        }
    }
}
