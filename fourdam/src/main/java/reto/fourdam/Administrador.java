/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;

/**
 * Representa a un administrador que es un tipo de usuario con funcionalidades
 * adicionales para para valorar, crear rutas, generar valoraciones tecnicas,
 * administrar calendarios y administrar la aplicacion.
 * <p>
 * La clase Administrador extiende de Profesor, heredando sus propiedades y metodos basicos,
 * es el tipo de usuario con mas privilegios.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class Administrador extends Profesor {

    public Administrador(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }

    public Administrador(int id, String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(id, nombre, apellidos, email, cnna, rol);
    }
    /**
     * @return una cadena que representa al administrador.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
