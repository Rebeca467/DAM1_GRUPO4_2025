/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;

/**
 * Representa a un profesor, que es un tipo de usuario con funcionalidades
 * adicionales para para valorar, crear rutas, generar valoraciones tecnicas y administrar calendarios.
 * <p>
 * La clase Profesor extiende de DisennadorRuta, heredando sus propiedades y metodos basicos.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class Profesor extends DisennadorRuta {
    /**
     * Crea una nueva instancia de Profesor sin especificar el id, ya que este se asigna de 
     * forma autoincremental.
     * 
     * @param nombre el nombre del profesor.
     * @param apellidos los apellidos del profesor.
     * @param email el correo electronico del profesor.
     * @param cnna el cnna del profesor.
     * @param rol el rol asignado al profesor.
     */
    public Profesor(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }
    /**
     * Crea una nueva instancia de Profesor con el identificador especificado y demas parametros.
     *
     * @param id el identificador del profesor.
     * @param nombre el nombre del profesor.
     * @param apellidos los apellidos del profesor.
     * @param email el correo electronico del profesor.
     * @param cnna el cnna del profesor.
     * @param rol el rol asignado al profesor.
     */
    public Profesor(int id, String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(id, nombre, apellidos, email, cnna, rol);
    }
    /**
     * @return una cadena que representa al profesor.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
