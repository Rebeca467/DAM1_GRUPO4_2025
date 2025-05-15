/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDate;

/**
 * Representa una valoracion, la cual vincula un usuario con una ruta evaluada.
 * <p>
 * Esta clase almacena la fecha de evaluacion y criterios de dificultad, belleza e interes cultural,
 * permitiendo evaluar la experiencia en una ruta.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Valoracion valoracion = new Valoracion(usuario, ruta, LocalDate.now(), 3, 4, 2);
 * </pre>
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class Valoracion {
    private int id;
    private Usuario usuario;
    private Ruta ruta;
    private LocalDate fecha;
    private int dificultad;
    private int belleza;
    private int interesCultural;
    /**
     * Crea una nueva instancia de Valoracion con los parametros especificados.
     *
     * @param usuario el usuario que realiza la evaluacion.
     * @param ruta la ruta evaluada.
     * @param fecha la fecha en la que se realiza la evaluacion.
     * @param dificultad el nivel de dificultad evaluado.
     * @param belleza el nivel de belleza evaluado.
     * @param interesCultural el nivel de interes cultural evaluado.
     */
    public Valoracion(Usuario usuario, Ruta ruta, LocalDate fecha, int dificultad, int belleza, int interesCultural) {
        this.usuario = usuario;
        this.ruta = ruta;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.belleza = belleza;
        this.interesCultural = interesCultural;
    }
    public int getId() {
        return id;
    }
    /**
     * Obtiene el usuario que realiza la evaluacion.
     *
     * @return el usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Obtiene la ruta evaluada.
     *
     * @return la ruta.
     */
    public Ruta getRuta() {
        return ruta;
    }
    /**
     * Obtiene la fecha de la evaluacion.
     *
     * @return la fecha.
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Obtiene el nivel de dificultad evaluado.
     *
     * @return el valor de dificultad.
     */
    public int getDificultad() {
        return dificultad;
    }
    /**
     * Obtiene el nivel de belleza evaluado.
     *
     * @return el valor de belleza.
     */
    public int getBelleza() {
        return belleza;
    }
    /**
     * Obtiene el nivel de interes cultural evaluado.
     *
     * @return el valor de interes cultural.
     */
    public int getInteresCultural() {
        return interesCultural;
    }
}
