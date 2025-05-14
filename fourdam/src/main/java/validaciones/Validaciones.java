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
 *
 * @author DAM106
 */
public class Validaciones {

    public static double stringToDouble(String titulo, String input) throws IllegalArgumentException{
        double num = -1;
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El/La " + titulo + " debe ser un número válido.");
        }
    }

    public static int stringToInt(String titulo, String input) throws IllegalArgumentException{
        int num = -1;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El/La " + titulo + " debe ser un numero entero.");
        }
    }


    // Método para validar el correo electrónico
    public static boolean validarCorreo(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }

}
