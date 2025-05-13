/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import java.util.Iterator;
import java.util.Set;
import reto.fourdam.Punto;

/**
 *
 * @author DAM124
 */
public class Calculos {
    //R almacena el radio de la tierra
    private static final double R = 6371.0;

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

    private static boolean esCoordenadaValida(double lat, double lon) {
        return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
    }
}
