/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

/**
 * Representa una actividad con un identificador unico y un nombre.
 * <p>
 * La clase {@code Actividad} encapsula el concepto de una actividad,
 * cuenta con un constructor para inicializar el objeto con un nombre
 * y metodos para obtener el id y el nombre asignados.
 * El identificador se establece autoincrementado en la base de datos.
 * </p>
 * 
 * @author Rebeca Cabo Cianca
 */
public class Actividad {
    private int id;
    private String nombre;
    /**
     * Crea una nueva instancia de {@code Actividad} con el nombre pasado por parametro.
     *
     * @param nombre el nombre de la actividad.
     */
    public Actividad(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return el id de la actividad.
     */
    public int getId() {
        return id;
    }
    /**
     * @return el nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }
    
    
}
