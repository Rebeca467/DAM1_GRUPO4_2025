/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDate;

/**
 * Representa una valoracion tecnica, que vincula un usuario con una ruta y una evaluacion mas precisa.
 * <p>
 * Esta clase almacena la fecha de evaluacion, el nivel de dificultad expresado en forma de cadena
 * y el equipo recomendado para la ruta.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class ValoracionTec {
    
    private int idValoracionTecnica;
    private Usuario usuario;
    private Ruta ruta;
    private LocalDate fecha;
    private String dificultad;
    private String equipoRecomendado;
    /**
     * Crea una nueva instancia de ValoracionTec con los parametros especificados.
     *
     * @param usuario el usuario que realiza la evaluacion tecnica.
     * @param ruta la ruta evaluada.
     * @param fecha la fecha de la evaluacion.
     * @param dificultad el nivel de dificultad expresado en forma de cadena.
     * @param equipoRecomendado el equipo recomendado para la ruta.
     */
    public ValoracionTec(Usuario usuario, Ruta ruta, LocalDate fecha, String dificultad, String equipoRecomendado) {
        this.usuario = usuario;
        this.ruta = ruta;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.equipoRecomendado = equipoRecomendado;
    }
    /**
     * Obtiene el identificador de la evaluacion tecnica.
     *
     * @return el id de la valoracion tecnica.
     */
    public int getIdValoracionTecnica() {
        return idValoracionTecnica;
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
     * @return la ruta evaluada.
     */
    public Ruta getRuta() {
        return ruta;
    }
    /**
     * Obtiene la fecha de la evaluacion.
     *
     * @return la fecha de evaluacion.
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Obtiene el nivel de dificultad expresado en forma de cadena.
     *
     * @return la dificultad.
     */
    public String getDificultad() {
        return dificultad;
    }
    /**
     * Obtiene el equipo recomendado para la ruta.
     *
     * @return el equipo recomendado.
     */
    public String getEquipoRecomendado() {
        return equipoRecomendado;
    }
}
