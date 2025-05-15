/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import java.util.Iterator;
import java.util.Set;
import reto.fourdam.Punto;

/**
 * Clase con metodos estaticos para manejar los calculos de distancias entre puntos
 * entre otros.
 * <p>
 * Esta clase utiliza la formula de Haversine para calcular la distancia entre
 * dos coordenadas geograficas y para calcular la distancia total entre un conjunto
 * de puntos.
 * </p>
 * <p>
 * Nota: El valor de retorno se expresa en kilometros.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 * @author Rebeca Cabo Cianca  
 */
public class Calculos {
    //R almacena el radio de la tierra
    private static final double R = 6371.0;
    /**
     * Calcula la distancia entre dos coordenadas geograficas utilizando la formula de Haversine.
     *
     * @param lat1 la latitud del primer punto.
     * @param lon1 la longitud del primer punto.
     * @param lat2 la latitud del segundo punto.
     * @param lon2 la longitud del segundo punto.
     * @return la distancia en kilometros entre los dos puntos, o -1 si se produce un error.
     * @throws IllegalArgumentException si alguna de las coordenadas no es valida.
     */ 
    public static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        try {
            // Validamos que las coordenadas sean razonables
            if (!esCoordenadaValida(lat1, lon1) || !esCoordenadaValida(lat2, lon2)) {
                throw new IllegalArgumentException("Las coordenadas ingresadas no son validas");
            }
            //Almacenamos las diferencias de las latitudes y longitudes en radianes
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            //Y operamos utilizando la formula de Haversine
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            //Esto nos va a devolver las unidades en Km
            return R * c;
        } catch (Exception e) {
            System.err.println("Error al calcular la distancia: " + e.getMessage());
            return -1; // Valor de retorno indicando error
        }
    }
    /**
     * Calcula la distancia total entre un conjunto de puntos.
     * <p>
     * La distancia total se calcula sumando las distancias entre puntos consecutivos
     * en el conjunto.
     * </p>
     *
     * @param puntos el conjunto de puntos.
     * @return la distancia total en kilometros, o 0 si no se pudo calcular.
     * @throws IllegalArgumentException si el conjunto de puntos es nulo o vacio.
     */
    public static double calcularDistanciaTotal(Set<Punto> puntos) {
        double distanciaTotal = 0;

        try {
            if (puntos == null || puntos.isEmpty()) {
                throw new IllegalArgumentException("El conjunto de puntos no puede ser nulo");
            }

            Iterator<Punto> iterator = puntos.iterator();
            Punto puntoAnterior = iterator.next();

            while (iterator.hasNext()) {
                Punto puntoActual = iterator.next();
                distanciaTotal += calcularDistancia(puntoAnterior.getLatitud(), puntoAnterior.getLongitud(),
                        puntoActual.getLatitud(), puntoActual.getLongitud());
                puntoAnterior = puntoActual;
            }
        } catch (Exception e) {
            System.err.println("Error al calcular la distancia total: " + e.getMessage());
        }
        return distanciaTotal;
    }
    /**
     * Valida que una coordenada geografica sea valida.
     *
     * @param lat la latitud a validar.
     * @param lon la longitud a validar.
     * @return true si la latitud esta entre -90 y 90 y la longitud entre -180 y 180; false en caso contrario.
     */
    private static boolean esCoordenadaValida(double lat, double lon) {
        return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
    }
}
