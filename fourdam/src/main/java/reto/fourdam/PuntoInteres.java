/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoPInteres;
import java.time.LocalDateTime;

/**
 *
 * @author DAM124
 */
public class PuntoInteres extends Punto {

    private TipoPInteres tipo;
    private String caracteristicasEsp;

    public PuntoInteres(double latitud, double longitud, double elevacion, LocalDateTime tiempo, String imagen, TipoPInteres tipo, String caracteristicasEsp) {
        super(latitud, longitud, elevacion, tiempo, imagen);
        this.tipo = tipo;
        this.caracteristicasEsp = caracteristicasEsp;
    }

    @Override
    public String getImagen() {
        return super.getImagen();
    }

    @Override
    public double getLatitud() {
        return super.getLatitud();
    }

    @Override
    public double getLongitud() {
        return super.getLongitud();
    }

    @Override
    public String toString() {
        return "PuntoInteres{" + "tipo=" + tipo + ", caracteristicasEsp=" + caracteristicasEsp + '}';
    }
}
