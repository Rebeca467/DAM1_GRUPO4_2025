/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDateTime;

/**
 *
 * @author DAM124
 */
public class PuntoPeligro extends Punto {

    private int km;
    private int nivelgravedad;
    private String justificacion;

    public PuntoPeligro(double latitud, double longitud, String imagen, int km, int nivelgravedad, String justificacion) {
        super(latitud, longitud, imagen);
        this.km = km;
        this.nivelgravedad = nivelgravedad;
        this.justificacion = justificacion;
    }

    @Override
    public String toString() {
        return "PuntoPeligro{" + "km=" + km + ", nivelgravedad=" + nivelgravedad + ", justificacion=" + justificacion + '}';
    }
}
