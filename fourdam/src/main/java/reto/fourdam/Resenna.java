/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import java.time.LocalDate;

/**
 * Representa una reseña que asocia un comentario con una ruta y su usuario.
 * <p>
 * Esta clase permite almacenar el comentario, la fecha de realizacion, la ruta evaluada
 * y el usuario que ha realizado la reseña.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Resenna resenna = new Resenna("Comentario de prueba", LocalDate.now(), ruta, usuario);
 * </pre>
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class Resenna {

    private int idResenna;
    private String comentario;
    private LocalDate fecha;
    private Ruta ruta;
    private Usuario usuario;
    /**
     * Crea una nueva instancia de Resenna con los parametros especificados.
     *
     * @param comentario el comentario de la resenna.
     * @param fecha la fecha en la que se realizo la reseña.
     * @param ruta la ruta a la cual se asocia la reseña.
     * @param usuario el usuario que crea la reseña.
     */
    public Resenna(String comentario, LocalDate fecha, Ruta ruta, Usuario usuario) {
        this.comentario = comentario;
        this.fecha = fecha;
        this.ruta = ruta;
        this.usuario = usuario;
    }
    /**
     * Obtiene el identificador de la reseña.
     *
     * @return el id de la resenna.
     */
    public int getIdResenna() {
        return idResenna;
    }
    /**
     * Obtiene el usuario que ha creado la reseña.
     *
     * @return el usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Obtiene la ruta a la cual se asocia la reseña.
     *
     * @return la ruta.
     */
    public Ruta getRuta() {
        return ruta;
    }
    /**
     * Obtiene la fecha en la que se realizo la reseña.
     *
     * @return la fecha de la resenna.
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Obtiene el comentario de la reseña.
     *
     * @return el comentario.
     */
    public String getComentario() {
        return comentario;
    }
}
