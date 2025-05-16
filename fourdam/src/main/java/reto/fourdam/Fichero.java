package reto.fourdam;

import static DAOs.metodosDB.idUsuario;
import static DAOs.metodosDB.usuPorId;
import ENUMs.Clasificacion_Ruta;
import ENUMs.TipoPInteres;
import ENUMs.TipoUsuario;
import ENUMs.Estado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

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
     * Convierte una ruta a formato CSV y crea un fichero de texto.
     *
     * @param ruta Objeto Ruta a convertir.
     */
    public static void rutaToCsv(Ruta ruta) {
        try {
            File archivo = new File("CSV_rutas/Ruta."+ruta.getId()+"_"+ruta.getNombre());
            // Crear el archivo si no existe
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            // Usamos BufferedWriter en lugar de FileWriter
            try (BufferedWriter br = new BufferedWriter(new FileWriter(archivo))) {
                br.write(ruta.toString());
                br.newLine(); // Nueva línea para evitar que los datos queden en una misma línea
            }
            JOptionPane.showMessageDialog(null, "La ruta se descargo correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convierte un archivo CSV a un objeto Ruta.
     *
     * @param archivo Archivo CSV a leer.
     * @return Objeto Ruta generado a partir del archivo.
     */
    public static Ruta csvToRuta(File archivo) {
        Ruta ruta = null;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // Saltar "Ruta {"
            br.readLine(); // Saltar "ID: 1"

            // Autor
            String[] autorDatos = br.readLine().replace("  Autor: Usuario{", "").replace("},", "").split(", ");
            Usuario autor = usuPorId(idUsuario(autorDatos[2].split("=")[1]));

            // Datos principales
            String nombre = br.readLine().split(": ")[1];
            LocalDate fechaCreacion = LocalDate.parse(br.readLine().split(": ")[1]);

            // Punto inicial y final
            String[] puntoIniDatos = br.readLine().replace("  Punto inicial: Punto{", "").replace("}", "").split(",");
            Punto puntoIni = new Punto(
                    Double.parseDouble(puntoIniDatos[0].split("=")[1]),
                    Double.parseDouble(puntoIniDatos[1].split("=")[1]),
                    puntoIniDatos[2].split("=")[1]); // Extraer imagen

            String[] puntoFinDatos = br.readLine().replace("  Punto final: Punto{", "").replace("}", "").split(",");
            Punto puntoFin = new Punto(
                    Double.parseDouble(puntoFinDatos[0].split("=")[1]),
                    Double.parseDouble(puntoFinDatos[1].split("=")[1]),
                    puntoFinDatos[2].split("=")[1]); // Extraer imagen
            // Distancias y altitudes
            double distanciaTotal = Double.parseDouble(br.readLine().split(": ")[1].replace(" km", ""));
            double desnivel = Double.parseDouble(br.readLine().split(": ")[1].replace(" m", ""));
            double desnivelPositivo = Double.parseDouble(br.readLine().split(": ")[1].replace(" m", ""));
            double desnivelNegativo = Double.parseDouble(br.readLine().split(": ")[1].replace(" m", ""));
            double altMax = Double.parseDouble(br.readLine().split(": ")[1].replace(" m", ""));
            double altMin = Double.parseDouble(br.readLine().split(": ")[1].replace(" m", ""));

            // Clasificación y valores numéricos
            Clasificacion_Ruta clasificacion = Clasificacion_Ruta.valueOf(br.readLine().split(": ")[1]);
            int nivelRiesgo = Integer.parseInt(br.readLine().split(": ")[1]);
            int nivelEsfuerzo = Integer.parseInt(br.readLine().split(": ")[1]);
            int tipoTerreno = Integer.parseInt(br.readLine().split(": ")[1]);
            int indicaciones = Integer.parseInt(br.readLine().split(": ")[1]);

            // Actividad, temporada y estado
            br.readLine(); // Saltamos la línea de actividad
            String temporada = br.readLine().split(": ")[1];
            boolean familiar = Boolean.parseBoolean(br.readLine().split(": ")[1]);
            String url = br.readLine().split(": ")[1];
            Estado estado = Estado.valueOf(br.readLine().split(": ")[1]);
            String recomendaciones = br.readLine().split(": ")[1];
            String zonaGeografica = br.readLine().split(": ")[1];

            // Últimos valores
            double duracion = Double.parseDouble(br.readLine().split(": ")[1]);
            int mediaValoracion = Integer.parseInt(br.readLine().split(": ")[1]);

            // Crear objeto Ruta
            ruta = new Ruta(autor, nombre, fechaCreacion, puntoIni, puntoFin, distanciaTotal, desnivel, desnivelPositivo,
                    desnivelNegativo, altMax, altMin, clasificacion, nivelRiesgo, nivelEsfuerzo, tipoTerreno,
                    indicaciones, new Actividad("Desconocida"), temporada, familiar, url, estado, recomendaciones,
                    zonaGeografica, new LinkedHashSet<>(), duracion, mediaValoracion);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de números: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la conversión de datos: " + e.getMessage());
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
    
    
    public static void generarFichaSeguridad(String nombreRuta, String dificultadGeneral, String puntosPeligro, String recomendaciones) {
        asegurarCarpetaFichas();
        String contenido = "=== FICHA DE SEGURIDAD ("+nombreRuta+") ===\n"
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

    public static void generarFichaUsuario(String nombreRuta, String datosGenerales, String perfilRecorrido, String recomendaciones) {
        asegurarCarpetaFichas();
        String contenido = "=== FICHA DE USUARIO ("+nombreRuta+") ===\n"
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

    public static void generarFichaOrganizacion(String nombreRuta, String datosBasicos, String altitud, String tipoRuta, String opinionTecnica, String nivelEducativo) {
        asegurarCarpetaFichas();
        String contenido = "=== FICHA DE ORGANIZACIÓN ("+nombreRuta+") ===\n"
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
