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
public class Resenna {

    private int idResenna;
    private Usuario usuario;
    private Ruta ruta;
    private LocalDate fecha;
    private String comentario;

    public Resenna(Usuario usuario, Ruta ruta, LocalDate fecha, String comentario) {
        this.idResenna = 1;
        this.usuario = usuario;
        this.ruta = ruta;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public int getIdResenna() {
        return idResenna;
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

    public String getComentario() {
        return comentario;
    }
    
    
}
