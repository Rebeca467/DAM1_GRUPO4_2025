package reto.fourdam;

import static DAOs.metodosDB.idUsuario;
import static DAOs.metodosDB.usuPorId;
import ENUMs.Clasificacion_Ruta;
import ENUMs.Estado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import static validaciones.Teclado.stringToLocalDate;

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
    private static final String rutaRutas = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "CSVRutas";


    /**
     * Convierte una ruta a formato CSV y crea un fichero de texto.
     *
     * @param ruta Objeto Ruta a convertir.
     */
    public static void rutaToCsv(Ruta ruta) {
        asegurarCarpeta();
        try {
            File archivo = new File(rutaRutas + File.separator + "Ruta."+ruta.getId()+"_"+ruta.getNombre());
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
            String[] autorDatos = br.readLine().replace("  Autor: Usuario{", "").replace("}", "").split(", ");
            System.out.println(autorDatos[2].split("=")[1]);
            Usuario autor = usuPorId(idUsuario(autorDatos[2].split("=")[1]));

            // Datos principales
            String nombre = br.readLine().split(": ")[1];
            LocalDate fechaCreacion = stringToLocalDate(br.readLine().split(": ")[1]);

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
            String[] actividadDatos = br.readLine().replace("  Tipo de actividad:  Actividad{", "").replace("}", "").split(", ");
            Actividad tipoActividad = new Actividad(actividadDatos[1].split("=")[1]);
            String temporada = br.readLine().split(": ")[1];
            boolean familiar = Boolean.parseBoolean(br.readLine().split(": ")[1]);
            String url = br.readLine().split(": ")[1];
            Estado estado = Estado.valueOf(br.readLine().split(": ")[1]);
            String recomendaciones = br.readLine().split(": ")[1];
            String zonaGeografica = br.readLine().split(": ")[1];

            // Últimos valores
            br.readLine();// salta puntosIntermedios
            double duracion = Double.parseDouble(br.readLine().split(": ")[1]);
            int mediaValoracion = Integer.parseInt(br.readLine().split(": ")[1]);

            // Crear objeto Ruta
            ruta = new Ruta(autor, nombre, fechaCreacion, puntoIni, puntoFin, distanciaTotal, desnivel, desnivelPositivo,
                    desnivelNegativo, altMax, altMin, clasificacion, nivelRiesgo, nivelEsfuerzo, tipoTerreno,
                    indicaciones, tipoActividad, temporada, familiar, url, estado, recomendaciones,
                    zonaGeografica, duracion, mediaValoracion);
            System.out.println(ruta.toString());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de números: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la conversión de datos: " + e.getMessage());
        }
        return ruta;
    }

    /**
     * Verifica si la carpeta de fichas existe y la crea en caso contrario.
     *
     * Este método revisa la ruta especificada en la variable `rutaFichas`. Si
     * la carpeta no existe, la crea utilizando `mkdirs()`, asegurando que todos
     * los directorios necesarios sean generados.
     *
     * @throws SecurityException Si no se tienen permisos para crear la carpeta.
     */
    private static void asegurarCarpeta() {
        File carpeta1 = new File(rutaFichas);
        File carpeta2 = new File(rutaRutas);
        if (!carpeta1.exists()) {
            carpeta1.mkdirs(); // crea la carpeta si no existe
        }
        if (!carpeta2.exists()) {
            carpeta2.mkdirs(); // crea la carpeta si no existe
        }
    }
    
    /**
     * Genera un archivo de ficha de seguridad para una ruta específica.
     *
     * Este método crea una ficha de seguridad en formato de texto dentro de la
     * carpeta definida en `rutaFichas`. La ficha incluye detalles sobre la
     * dificultad general, puntos de peligro y recomendaciones.
     *
     * @param nombreRuta Nombre de la ruta que se documentará en la ficha.
     * @param dificultadGeneral Nivel de dificultad general de la ruta.
     * @param puntosPeligro Descripción de los puntos de peligro en la ruta.
     * @param recomendaciones Consejos y recomendaciones para la ruta.
     * @throws IOException Si ocurre un error al escribir en el archivo de la ficha.
     */
    public static void generarFichaSeguridad(String nombreRuta, String dificultadGeneral, String puntosPeligro, String recomendaciones) {
        asegurarCarpeta();
        String contenido = "=== FICHA DE SEGURIDAD ("+nombreRuta+") ===\n"
                + "Dificultad general: " + dificultadGeneral + "\n"
                + "Puntos de peligro: " + puntosPeligro + "\n"
                + "Recomendaciones: " + recomendaciones + "\n";

        File archivo = new File(rutaFichas + File.separator + "ficha_seguridad-"+nombreRuta+".txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Ficha de seguridad generada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la ficha de seguridad: " + e.getMessage());
        }
    }
    /**
     * Genera un archivo de ficha de usuario para una ruta especifica.
     *
     * Este metodo crea una ficha de usuario en formato de texto dentro de la
     * carpeta definida en `rutaFichas`. La ficha incluye datos generales, el
     * perfil del recorrido y recomendaciones.
     *
     * @param nombreRuta Nombre de la ruta que se documentara en la ficha.
     * @param datosGenerales Informacion general sobre la ruta.
     * @param perfilRecorrido Descripcion del perfil del recorrido.
     * @param recomendaciones Consejos y recomendaciones para la ruta.
     * @throws IOException Si ocurre un error al escribir en el archivo de la ficha.
     */
    public static void generarFichaUsuario(String nombreRuta, String datosGenerales, String perfilRecorrido, String recomendaciones) {
        asegurarCarpeta();
        String contenido = "=== FICHA DE USUARIO ("+nombreRuta+") ===\n"
                + "Datos generales: " + datosGenerales + "\n"
                + "Perfil del recorrido: " + perfilRecorrido + "\n"
                + "Recomendaciones: " + recomendaciones + "\n";

        File archivo = new File(rutaFichas + File.separator + "ficha_usuario-"+nombreRuta+".txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Ficha de usuario generada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la ficha de usuario: " + e.getMessage());
        }
    }
    /**
     * Genera un archivo de ficha de organizacion para una ruta especifica.
     *
     * Este metodo crea una ficha de organizacion en formato de texto dentro de
     * la carpeta definida en `rutaFichas`. La ficha incluye datos basicos,
     * altitud, tipo de ruta, opinion tecnica y nivel educativo recomendado.
     *
     * @param nombreRuta Nombre de la ruta que se documentara en la ficha.
     * @param datosBasicos Informacion general sobre la ruta.
     * @param altitud Altitud de la ruta.
     * @param tipoRuta Tipo de recorrido de la ruta.
     * @param opinionTecnica Evaluacion tecnica sobre la ruta.
     * @param nivelEducativo Nivel educativo recomendado para la ruta.
     * @throws IOException Si ocurre un error al escribir en el archivo de la ficha.
     */
    public static void generarFichaOrganizacion(String nombreRuta, String datosBasicos, String altitud, String tipoRuta, String opinionTecnica, String nivelEducativo) {
        asegurarCarpeta();
        String contenido = "=== FICHA DE ORGANIZACIÓN ("+nombreRuta+") ===\n"
                + "Datos básicos: " + datosBasicos + "\n"
                + "Altitud: " + altitud + "\n"
                + "Tipo de ruta: " + tipoRuta + "\n"
                + "Opinión técnica: " + opinionTecnica + "\n"
                + "Nivel educativo recomendado: " + nivelEducativo + "\n";

        File archivo = new File(rutaFichas + File.separator + "ficha_organizacion-"+nombreRuta+".txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
            System.out.println("Ficha de organización generada en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear la ficha de organización: " + e.getMessage());
        }
    }
}
