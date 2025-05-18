/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoPInteres;
import java.time.LocalDateTime;

/**
 * Representa un punto de interes que extiende de Punto y agrega informacion adicional.
 * <p>
 * Esta clase incluye propiedades especificas como el tipo de punto de interes y sus 
 * caracteristicas especiales.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class PuntoInteres extends Punto {

    private TipoPInteres tipo;
    private String caracteristicasEsp;
    private String nombre;
    /**
     * Crea una nueva instancia de PuntoInteres con los parametros especificados.
     *
     * @param latitud la latitud del punto.
     * @param longitud la longitud del punto.
     * @param imagen la imagen asociada al punto.
     * @param tipo el tipo de punto de interes.
     * @param caracteristicasEsp las caracteristicas especiales del punto.
     */
    public PuntoInteres(String nombre, double latitud, double longitud, String imagen, TipoPInteres tipo, String caracteristicasEsp) {
        super(latitud, longitud, imagen);
        this.nombre = nombre;
        this.tipo = tipo;
        this.caracteristicasEsp = caracteristicasEsp;
    }
     /**
     * Obtiene el nombre del punto.
     *
     * @return el nombre.
     */
    
    public String getNombre() {
        return nombre;
    }
    
     /**
     * Obtiene el tipo del punto.
     *
     * @return el tipo.
     */

    public TipoPInteres getTipo() {
        return tipo;
    }
    
    /**
     * Obtiene la caracteristica del punto.
     *
     * @return la caracteristica.
     */
    
    public String getCaracteristicasEsp() {
        return caracteristicasEsp;
    }
    
/**
     * Retorna la imagen asociada al punto de interes.
     *
     * @return la imagen.
     */
    @Override
    public String getImagen() {
        return super.getImagen();
    }

    /**
     * Retorna la latitud del punto de interes.
     *
     * @return la latitud.
     */
    @Override
    public double getLatitud() {
        return super.getLatitud();
    }

    /**
     * Retorna la longitud del punto de interes.
     *
     * @return la longitud.
     */
    @Override
    public double getLongitud() {
        return super.getLongitud();
    }

    /**
     * @return una cadena que representa al punto de interes.
     */
    @Override
    public String toString() {
        return "PuntoInteres{" + "tipo=" + tipo + ", caracteristicasEsp=" + caracteristicasEsp + '}';
    }
}
