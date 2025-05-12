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
        //Almacenamos las diferencias de las latitudes y longitudes en radianes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        //Y operamos utilizando la formula de Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //Esto nos va a devolver las unidades en Km
        return R * c;
    }
    
    public static double calcularDistanciaTotal(Set<Punto> puntos) {
        double distanciaTotal = 0.0;
        Iterator<Punto> iterator = puntos.iterator();
        if (!iterator.hasNext()) return distanciaTotal;

        Punto puntoAnterior = iterator.next();

        while (iterator.hasNext()) {
            Punto puntoActual = iterator.next();
            distanciaTotal += calcularDistancia(puntoAnterior.getLatitud(), puntoAnterior.getLongitud(), puntoActual.getLatitud(), puntoActual.getLongitud());
            puntoAnterior = puntoActual;
        }

        return distanciaTotal;
    }
}
