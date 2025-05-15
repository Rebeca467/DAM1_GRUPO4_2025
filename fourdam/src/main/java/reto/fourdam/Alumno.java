/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;
import java.time.LocalDate;
import validaciones.Teclado;

/**
 * Representa a un alumno, que es un tipo de usuario, con funcionalidades
 * adicionales para valorar y crear rutas.
 * <p>
 * La clase Alumno extiende de Usuario, por lo que hereda sus propiedades y metodos basicos.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class Alumno extends Usuario{
    /**
     * Crea una nueva instancia de Alumno sin especificar el id, ya que este se asigna de 
     * forma autoincremental.
     * 
     * @param nombre el nombre del alumno.
     * @param apellidos los apellidos del alumno.
     * @param email el correo electronico del alumno.
     * @param cnna el contraseña del alumno.
     * @param rol el rol asignado al alumno.
     */
    public Alumno(String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(nombre, apellidos, email, cnna, rol);
    }
    /**
     * Crea una nueva instancia de Alumno con el identificador especificado y los demas parametros.
     *
     * @param id el identificador del alumno.
     * @param nombre el nombre del alumno.
     * @param apellidos los apellidos del alumno.
     * @param email el correo electronico del alumno.
     * @param cnna el contraseña del alumno.
     * @param rol el rol asignado al alumno.
     */
    public Alumno(int id, String nombre, String apellidos, String email, String cnna, TipoUsuario rol) {
        super(id, nombre, apellidos, email, cnna, rol);
    }
    /**
     * @return una cadena que representa al alumno.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
