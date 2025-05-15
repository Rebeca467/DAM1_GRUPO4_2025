/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoUsuario;
import java.time.LocalDate;
import validaciones.Teclado;

/**
 * Representa un usuario del sistema, con atributos basicos como el id, nombre, 
 * apellidos, email y contraseña.
 * <p>
 * Esta clase permite crear instancias de usuario mediante diferentes constructores.
 * </p>
 * 
 * @author Fabian Saiz Landeras
 */
public class Usuario{
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String cnna;
    private TipoUsuario rol;

    /**
     * Crea una nueva instancia de Usuario con el id especificado, junto con el resto de 
     * atributos.
     *
     * @param id el identificador del usuario.
     * @param nombre el nombre del usuario.
     * @param apellidos los apellidos del usuario.
     * @param email el correo electronico del usuario.
     * @param cnna el cnna del usuario.
     * @param rol el rol del usuario.
     */
    public Usuario(int id, String nombre, String apellidos, String email, String cnna,TipoUsuario rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.cnna = cnna;
        this.rol= rol;

    }
    /**
     * Crea una nueva instancia de Usuario sin especificar el id, ya que este se asigna de 
     * forma autoincremental.
     *
     * @param nombre el nombre del usuario.
     * @param apellidos los apellidos del usuario.
     * @param email el correo electronico del usuario.
     * @param cnna el cnna del usuario.
     * @param rol el rol del usuario.
     */
    public Usuario(String nombre, String apellidos, String email, String cnna,TipoUsuario rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.cnna = cnna;
        this.rol= rol;

    }
    
    public Usuario() {
        this.nombre ="Invitado";
        this.apellidos = "";
        this.email = "";
        this.cnna = "";
        this.rol = TipoUsuario.INVITADO;
    }
    /**
     * Obtiene el identificador del usuario.
     *
     * @return el id del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return los apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el correo electronico del usuario.
     *
     * @return el email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el cnna del usuario.
     *
     * @return el cnna del usuario.
     */
    public String getCnna() {
        return cnna;
    }
    
    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario.
     */
    public TipoUsuario getRol() {
        return rol;
    }
    /////////////////////// MIRAR METODO
//    public Resenna resennar(Ruta ruta){
//        return new Resenna(1,Usuario.this, ruta, LocalDate.now(), Teclado.texto("Comentario respecto a la ruta"));
//    }
    /**
     * @return una String que representa al usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + '}';
    }
}
