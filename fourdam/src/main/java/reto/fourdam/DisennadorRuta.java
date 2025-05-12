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
public class DisennadorRuta extends Alumno {

    public DisennadorRuta(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }

    public DisennadorRuta() {
    }
     
    /*@Override
    public Resenna resennar(Ruta ruta){
        return super.resennar(ruta);
    }*/
    
    @Override
    public Valoracion valorar(Ruta ruta){
        return super.valorar(ruta);
    }
    // ------------------------------------------ AÑADIR ID ----------------------------
    /*public ValoracionTec vTec(Ruta ruta){
        return new ValoracionTec(DisennadorRuta.this, ruta, LocalDate.now(), Teclado.intervalos("Nivel de dificultad entre 1-5", 1, 5), Teclado.texto("Equipo recomendado para la ruta"));
    }*/
    
    @Override
    public Ruta crearRuta(){
        return super.crearRuta();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
