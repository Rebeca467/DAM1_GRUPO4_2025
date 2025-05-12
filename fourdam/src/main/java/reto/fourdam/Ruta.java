/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.ClasificacionRuta;
import ENUMs.Estado;
import ENUMs.Temporada;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import validaciones.Teclado;

/**
 *
 * @author DAM124
 */
public class Ruta {
    private int id;
    private Usuario autor;
    private String nombre;
    private LocalDate fecha_creacion;
    private Punto punto_ini;
    private Punto punto_fin;
    private double distanciaTotal;
    private double desnivel;
    private double altMax;
    private double altMin;
    private ClasificacionRuta clasificacion;
    private int nivelRiesgo;
    private int nivelEsfuerzo;
    private int tipoTerreno;
    private int indicaciones;
    private String tipoActividad;//Enum
    private Set<String> temporada;
    private boolean accesibilidad;
    private boolean familiar;
    private String url;
    private Estado estado;
    private String recomendaciones;
    private String zonaGeografica;
    private File puntosIntermedios;
    private double duracion;

    public Ruta() {
        
    }
    // quito el id porque es autoincremental
    public Ruta(Usuario autor, String nombre, LocalDate fecha_creacion, Punto punto_ini, Punto punto_fin, double distanciaTotal, ClasificacionRuta clasificacion, double desnivel, double altMax, double altMin, ClasificacionRuta valueOf, int nivelRiesgo, int nivelEsfuerzo, int tipoTerreno, int indicaciones, String tipoActividad, Set<String> temporada, boolean accesibilidad, boolean familiar, String url, Estado estado, String recomendaciones, String zonaGeografica, double duracion, File puntosIntermedios) {        
        this.autor = autor;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.punto_ini = punto_ini;
        this.punto_fin = punto_fin;
        this.distanciaTotal = distanciaTotal;
        this.desnivel = desnivel;
        this.altMax = altMax;
        this.altMin = altMin;
        this.clasificacion = clasificacion;
        this.nivelRiesgo = nivelRiesgo;
        this.nivelEsfuerzo = nivelEsfuerzo;
        this.tipoTerreno = tipoTerreno;
        this.indicaciones = indicaciones;
        this.tipoActividad = tipoActividad;
        this.temporada = temporada;
        this.accesibilidad = accesibilidad;
        this.familiar = familiar;
        this.url = url;
        this.estado = estado;
        this.recomendaciones = recomendaciones;
        this.zonaGeografica = zonaGeografica;
        this.puntosIntermedios = puntosIntermedios;
        this.duracion = duracion;
    }

    //añado el get de id

    public int getId() {
        return id;
    }
        
    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Punto getPunto_ini() {
        return punto_ini;
    }

    public void setPunto_ini(PuntoInteres punto_ini) {
        this.punto_ini = punto_ini;
    }

    public Punto getPunto_fin() {
        return punto_fin;
    }

    public void setPunto_fin(PuntoInteres punto_fin) {
        this.punto_fin = punto_fin;
    }

   public double getDistanciaTotal() {
      return distanciaTotal;
    }

    public void setDistanciaTotal(float distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getDesnivel() {
        return desnivel;
    }

    public void setDesnivel(float desnivel) {
        this.desnivel = desnivel;
    }

   public double getAltMax() {
        return altMax;
    }

    public void setAltMax(float altMax) {
        this.altMax = altMax;
    }

    public double getAltMin() {
        return altMin;
    }

    public void setAltMin(float altMin) {
        this.altMin = altMin;
    }

    public ClasificacionRuta getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionRuta clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public int getNivelEsfuerzo() {
        return nivelEsfuerzo;
    }

    public void setNivelEsfuerzo(int nivelEsfuerzo) {
        this.nivelEsfuerzo = nivelEsfuerzo;
    }

    public int getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(int tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public int getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(int indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Set<String> getTemporada() {
        return temporada;
    }

    public void setTemporada(Set<String> temporada) {
        this.temporada = temporada;
    }

    public boolean isAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(boolean accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public boolean isFamiliar() {
        return familiar;
    }

    public void setFamiliar(boolean familiar) {
        this.familiar = familiar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getZonaGeografica() {
        return zonaGeografica;
    }

    public void setZonaGeografica(String zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    public File getPuntosIntermedios() {
        return puntosIntermedios;
    }

    public void setPuntosIntermedios(File puntosIntermedios) {
        this.puntosIntermedios = puntosIntermedios;
    }
    
    /*public File fichaSeguridad(){
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(puntosIntermedios));) {
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        File fichaSeg = new File("fichaSeguridad.txt");
        String aux;
        aux = Teclado.intervalos("Dificultad general",1,5);
        
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fichero, true));) {
            br.write(aux);
            br.write(System.lineSeparator());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        return fichaSeg;
    }
    
    public File fichaUsuario(){
        
    }
    
    public File fichaOrganizacion(){
        
    }*/

    @Override
    public String toString() {
        return "Ruta{" + "id=" + id + ", autor=" + autor + ", nombre=" + nombre + ", fecha_creacion=" + fecha_creacion + ", punto_ini=" + punto_ini + ", punto_fin=" + punto_fin + ", distanciaTotal=" + distanciaTotal + ", duracion=" + duracion + ", desnivel=" + desnivel + ", altMax=" + altMax + ", altMin=" + altMin + ", clasificacion=" + clasificacion + ", nivelRiesgo=" + nivelRiesgo + ", nivelEsfuerzo=" + nivelEsfuerzo + ", tipoTerreno=" + tipoTerreno + ", indicaciones=" + indicaciones + ", tipoActividad=" + tipoActividad + ", temporada=" + temporada + ", accesibilidad=" + accesibilidad + ", familiar=" + familiar + ", url=" + url + ", estado=" + estado + ", recomendaciones=" + recomendaciones + ", zonaGeografica=" + zonaGeografica + '}';
    }
}
