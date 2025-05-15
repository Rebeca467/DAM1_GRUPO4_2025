/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Clase para validar entradas y realizar conversiones de fechas y tiempos.
 * <p>
 * Esta clase contiene metodos para leer y validar textos, numeros e intervalos, 
 * convertir objetos LocalDate y LocalTime a cadenas, ademas de validar temporadas.
 * </p>
 * 
 * @author Rebeca Cabo Cianca
 * @author Fabian Saiz Landeras
 */
public class Teclado {
    /**
     * Lee y valida un texto desde la entrada estandar.
     * <p>
     * El texto debe contener solo letras y numeros. Si el texto no cumple el patron,
     * se muestra un mensaje de error.
     * </p>
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return El texto ingresado que cumple el patron.
     */
    public static String texto(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        boolean exito = false;
        String texto = "";
        try {
            while (!exito) {
                System.out.println(mensaje);
                texto = teclado.nextLine();
                if (!texto.matches("[A-Z a-z 0-9]*")) {
                    throw new Exception("No has introducido correctamente el texto");
                }
                exito = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return texto;
    }
    /**
     * Lee y valida un numero entero dentro de un intervalo especificado.
     * <p>
     * Se solicita al usuario que ingrese un numero. Si el numero no esta en el 
     * intervalo [inicio, fin], se lanza una excepcion y se muestra un mensaje de error.
     * </p>
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @param inicio Valor minimo permitido.
     * @param fin Valor maximo permitido.
     * @return El numero entero valido ingresado.
     */
    public static int intervalos(String mensaje, int inicio, int fin) {
        Scanner teclado = new Scanner(System.in);
        boolean exito = false;
        int num = 0;
        try {
            while (!exito) {
                System.out.println(mensaje);
                num = teclado.nextInt();
                if (num < inicio || num > fin) {
                    throw new Exception("Introduce un numero válido");
                }
                exito = true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Debes meter un numero ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return num;
    }
    /**
     * Lee y valida un numero de tipo double desde la entrada estandar.
     * <p>
     * Se solicita al usuario que ingrese un numero; si ocurre un error, se muestra
     * un mensaje de error.
     * </p>
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return El numero double ingresado.
     */
    public static double numeros(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        boolean exito = false;
        double num = 0;
        try {
            while (!exito) {
                System.out.println(mensaje);
                num = teclado.nextDouble();
                exito = true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Debes meter un numero ");
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return num;
    }
    /**
     * Convierte un objeto LocalDate a una cadena con el formato "dd/MM/yyyy".
     *
     * @param fecha Objeto LocalDate a convertir.
     * @return Cadena que representa la fecha.
     */
    public static String localDateToString(LocalDate fecha) {
        String fechaString = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaString = fecha.format(formato);
        return fechaString;
    }
    /**
     * Convierte una cadena en formato "dd/MM/yyyy" a un objeto LocalDate.
     * <p>
     * Si la fecha resultante es posterior a la fecha actual, se lanza una excepcion.
     * </p>
     *
     * @param fecha Cadena que representa la fecha.
     * @return Objeto LocalDate correspondiente o null si ocurre un error.
     */
    public static LocalDate stringToLocalDate(String fecha) {
        LocalDate fechaLocalDate = null;
        try {
            String trozos[] = fecha.split("/");
            if (trozos.length == 3) {
                int dia = Integer.parseInt(trozos[0]);
                int mes = Integer.parseInt(trozos[1]);
                int anio = Integer.parseInt(trozos[2]);
                fechaLocalDate = LocalDate.of(anio, mes, dia);
                if (fechaLocalDate.isAfter(LocalDate.now())) {
                    throw new Exception("Fecha no válida.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Escribe un numero válido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        return fechaLocalDate;
    }
    /**
     * Convierte un objeto LocalTime a una cadena con el formato "hh:mm:ss".
     *
     * @param tiempo Objeto LocalTime a convertir.
     * @return Cadena que representa el tiempo.
     */
    public static String localTimeToString(LocalTime tiempo) {
        String tiempoString = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm:ss");
        tiempoString = tiempo.format(formato);
        return tiempoString;
    }
    /**
     * Valida que la cadena de temporadas sea valida y este en el orden correcto.
     * <p>
     * Se espera que las temporadas esten separadas por coma y sin espacios adicionales. 
     * Las temporadas validas son: "primavera", "verano", "otono" e "invierno". Se valida 
     * que las temporadas ingresadas existan y que sigan el orden correcto.
     * </p>
     *
     * @param texto Mensaje o instruccion para el usuario.
     * @return Un conjunto con las temporadas validas en orden.
     */
    public static Set<String> validarTemporada(String texto) {
        List<String> temporadasValidas = List.of("primavera", "verano", "otoño", "invierno");
        boolean valido = false;
        Set<String> temporadas = new LinkedHashSet<>();
        System.out.println(texto);
        do {
            String temp = new Scanner(System.in).nextLine();
            temporadas.clear();
            String[] partes = temp.split(",");
            for (String parte : partes) {
                temporadas.add(parte.trim().toLowerCase());
            }
            
            Set<String> noValidas = new LinkedHashSet<>(temporadas);
            noValidas.removeAll(temporadasValidas);
            
            if (noValidas.isEmpty()) {
                
                if (comprobarOrden(temporadas, temporadasValidas)) {
                    valido = true;
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR: debes introducir las temporadas en orden");
                }
            } else {
                JOptionPane.showMessageDialog(null,"ERROR: temporada no valida (primavera,verano,otono,invierno)");                
            }
        } while (!valido);
        return temporadas;
    }
    /**
     * Comprueba si el conjunto de temporadas esta en el orden correcto.
     *
     * @param temporadas Conjunto de temporadas a validar.
     * @param ordenCorrecto Lista con el orden correcto de las temporadas.
     * @return true si las temporadas estan en el orden correcto; false en caso contrario.
     */
    private static boolean comprobarOrden(Set<String> temporadas, List<String> ordenCorrecto) {
        int posicionAnterior = -1;
        boolean ordenado = true;
        for (String temporada : temporadas) {
            int posicionActual = ordenCorrecto.indexOf(temporada);
            if (posicionActual < posicionAnterior) {
                ordenado = false;
            }
            posicionAnterior = posicionActual;
        }
        return ordenado;
    }
    
    
}
