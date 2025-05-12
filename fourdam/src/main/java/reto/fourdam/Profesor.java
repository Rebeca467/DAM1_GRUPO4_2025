/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;

/**
 *
 * @author DAM124
 */
public class Profesor extends DisennadorRuta {

    public Profesor(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }

    public Profesor() {
    }
    /*
    @Override
    public Resenna resennar(Ruta ruta){
        return super.resennar(ruta);
    }*/
    
    @Override
    public Valoracion valorar(Ruta ruta){
        return super.valorar(ruta);
    }
    /*
    @Override
    public ValoracionTec vTec(Ruta ruta){
        return super.vTec(ruta);
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
