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
    private String comentario;
    private LocalDate fecha;
    private Ruta ruta;
    private Usuario usuario;

    public Resenna(String comentario, LocalDate fecha, Ruta ruta, Usuario usuario) {
        this.comentario = comentario;
        this.fecha = fecha;
        this.ruta = ruta;
        this.usuario = usuario;
    }

    public Resenna(int idResenna, String comentario, LocalDate fecha, Ruta ruta, Usuario usuario) {
        this.idResenna = idResenna;
        this.comentario = comentario;
        this.fecha = fecha;
        this.ruta = ruta;
        this.usuario = usuario;
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
