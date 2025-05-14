/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;
import java.time.LocalDate;
import validaciones.Teclado;

/**
 *
 * @author DAM124
 */
public class Alumno extends Usuario{

    public Alumno(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }

    public Alumno(int id, String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(id, nombre, apellidos, email, cnna, rol);
    }
    // -------------------------------- MIRAR ---------------------------
    /*@Override
    public Resenna resennar(Ruta ruta){
        return super.resennar(ruta);
    }*/
    
    public Valoracion valorar(Ruta ruta){
        return new Valoracion(Alumno.this, ruta, LocalDate.now(), Teclado.intervalos("Nivel de dificultad entre 1-5", 1, 5), Teclado.intervalos("Belleza de la ruta entre 1-5", 1, 5), Teclado.intervalos("Interes cultural entre 1-5", 1, 5));
    }
    
    public Ruta crearRuta(){
        return new Ruta();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
