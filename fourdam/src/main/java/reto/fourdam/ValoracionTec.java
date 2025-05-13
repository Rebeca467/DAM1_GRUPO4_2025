/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDate;

/**
 *
 * @author DAM124
 */
public class ValoracionTec {
    
    private int idValoracionTecnica;
    private Usuario usuario;
    private Ruta ruta;
    private LocalDate fecha;
    private String dificultad;
    private String equipoRecomendado;

    public ValoracionTec(Usuario usuario, Ruta ruta, LocalDate fecha, String dificultad, String equipoRecomendado) {
    
        this.usuario = usuario;
        this.ruta = ruta;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.equipoRecomendado = equipoRecomendado;
    }

    public ValoracionTec() {
        this.idValoracionTecnica=idValoracionTecnica;
        this.usuario = usuario;
        this.ruta = ruta;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.equipoRecomendado = equipoRecomendado;
    }

    public int getIdValoracionTecnica() {
        return idValoracionTecnica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getEquipoRecomendado() {
        return equipoRecomendado;
    }
    

}
