/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDateTime;

/**
 * Representa un punto geografico con latitud, longitud,
 * una imagen, elevacion y el tiempo.
 * <p>
 * La clase Punto permite almacenar las coordenadas geograficas junto a informacion
 * adicional, como la elevacion y el tiempo en que se registro el punto.
 * </p>
 * 
 * @author Oriol Fernandez Saiz 
 */
public class Punto {
    private double latitud;
    private double longitud;
    private String imagen;
    private double elevacion;
    private LocalDateTime tiempo;
    /**
     * Crea un nuevo punto con los valores de latitud, longitud e imagen especificados.
     *
     * @param latitud la latitud del punto.
     * @param longitud la longitud del punto.
     * @param imagen la ruta de la imagen asociada al punto.
     */
    public Punto(double latitud, double longitud, String imagen) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
    }

    /**
     * Obtiene la latitud del punto.
     *
     * @return la latitud.
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Obtiene la longitud del punto.
     *
     * @return la longitud.
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Obtiene la imagen asociada al punto.
     *
     * @return la imagen.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Obtiene la elevacion del punto.
     *
     * @return la elevacion.
     */
    public double getElevacion() {
        return elevacion;
    }

    /**
     * Obtiene el instante de tiempo asociado al punto.
     *
     * @return el tiempo.
     */
    public LocalDateTime getTiempo() {
        return tiempo;
    }
    
    /**
     * @return una cadena que representa al punto.
     */
    @Override
    public String toString() {
        return "Punto{" + "latitud=" + latitud + ", longitud=" + longitud + ", imagen=" + imagen + '}';
    }
}
