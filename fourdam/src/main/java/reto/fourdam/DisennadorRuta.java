/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;

/**
 * Representa a un diseñador de ruta, que es un tipo de usuario con funcionalidades
 * adicionales para para valorar, crear rutas y generar valoraciones tecnicas.
 * <p>
 * La clase DisennadorRuta extiende de Alumno, heredando sus propiedades y metodos basicos.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class DisennadorRuta extends Alumno {
    /**
     * Crea una nueva instancia de DisennadorRuta sin especificar el id, ya que este se asigna de 
     * forma autoincremental.
     * 
     * @param nombre el nombre del disennador.
     * @param apellidos los apellidos del disennador.
     * @param email el correo electronico del disennador.
     * @param cnna el contraseña del diseñador.
     * @param rol el rol asignado al diseñador.
     */
    public DisennadorRuta(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }
    /**
     * Crea una nueva instancia de DisennadorRuta con el identificador especificado y demas parametros.
     *
     * @param id el identificador del disennador.
     * @param nombre el nombre del disennador.
     * @param apellidos los apellidos del disennador.
     * @param email el correo electronico del disennador.
     * @param cnna el contraseña del diseñador.
     * @param rol el rol asignado al diseñador.
     */
    public DisennadorRuta(int id, String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(id, nombre, apellidos, email, cnna, rol);
    }
    /**
     * @return una cadena que representa al disennador.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
