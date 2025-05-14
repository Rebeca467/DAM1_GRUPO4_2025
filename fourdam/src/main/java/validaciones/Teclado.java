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
 *
 * @author DAM104
 */
public class Teclado {

    // metodo que valida los textos
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

    // metodo que valida los intervalos
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

    // metodo que valida que no se puedan meter letras
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
    
    public static String localDateToString(LocalDate fecha) {
        String fechaString = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fechaString = fecha.format(formato);
        return fechaString;
    }

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
    
    public static String localTimeToString(LocalTime tiempo) {
        String tiempoString = "";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm:ss");
        tiempoString = tiempo.format(formato);
        return tiempoString;
    }
    
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
