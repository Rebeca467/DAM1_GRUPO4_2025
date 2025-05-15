/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDateTime;

/**
 * Representa un punto de peligro que extiende de Punto.
 * <p>
 * Esta clase almacena informacion adicional para definir un punto de peligro,
 * incluyendo los kilometros asociados, el nivel de gravedad y una justificacion.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class PuntoPeligro extends Punto {

    private int km;
    private int nivelgravedad;
    private String justificacion;
    /**
     * Crea una nueva instancia de PuntoPeligro con los parametros especificados.
     *
     * @param latitud la latitud del punto.
     * @param longitud la longitud del punto.
     * @param imagen la imagen asociada al punto.
     * @param km los kilometros asociados al punto de peligro.
     * @param nivelgravedad el nivel de gravedad del peligro.
     * @param justificacion la justificacion del peligro.
     */
    public PuntoPeligro(double latitud, double longitud, String imagen, int km, int nivelgravedad, String justificacion) {
        super(latitud, longitud, imagen);
        this.km = km;
        this.nivelgravedad = nivelgravedad;
        this.justificacion = justificacion;
    }
    /**
     * @return una cadena que representa al punto de peligro.
     */
    @Override
    public String toString() {
        return "PuntoPeligro{" + "km=" + km + ", nivelgravedad=" + nivelgravedad + ", justificacion=" + justificacion + '}';
    }
}
