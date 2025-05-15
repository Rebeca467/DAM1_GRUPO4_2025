/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Clase para validar entradas numericas y correos electronicos.
 * <p>
 * Esta clase provee metodos para convertir cadenas a double e int, y para validar
 * que un correo electronico tenga un formato correcto.
 * </p>
 * 
 * @author Oriol Fernandez Saiz
 */
public class Validaciones {
    /**
     * Convierte una cadena a un valor de tipo double.
     * <p>
     * Si la cadena no representa un numero valido, se lanza una excepcion.
     * </p>
     *
     * @param titulo El titulo o descripcion del valor a parsear.
     * @param input La cadena que se desea convertir.
     * @return El valor double obtenido de la conversion.
     * @throws IllegalArgumentException Si la cadena no es un numero valido.
     */
    public static double stringToDouble(String titulo, String input) throws IllegalArgumentException{
        double num = -1;
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El/La " + titulo + " debe ser un número válido.");
        }
    }
    /**
     * Convierte una cadena a un valor de tipo int.
     * <p>
     * Si la cadena no representa un numero entero valido, se lanza una excepcion.
     * </p>
     *
     * @param titulo El titulo o descripcion del valor a parsear.
     * @param input La cadena que se desea convertir.
     * @return El valor int obtenido de la conversion.
     * @throws IllegalArgumentException Si la cadena no es un numero entero valido.
     */
    public static int stringToInt(String titulo, String input) throws IllegalArgumentException{
        int num = -1;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El/La " + titulo + " debe ser un numero entero.");
        }
    }
    /**
     * Valida que el correo electronico tenga un formato correcto.
     * <p>
     * Se utiliza una expresion regular para comprobar que el correo electronico cumple con 
     * el formato estandar.
     * </p>
     *
     * @param correo El correo electronico a validar.
     * @return true si el correo cumple con el formato; false en caso contrario.
     */
    public static boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }
}
