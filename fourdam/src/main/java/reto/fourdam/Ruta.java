/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.Clasificacion_Ruta;
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
 * Representa una ruta con todos sus atributos, incluyendo identificador,
 * usuario que ha creado la ruta, y otros atributos que describen la ruta.
 * <p>
 * La clase {@code Ruta} encapsula todos los atributos y caracteristicas propias de una ruta.
 * </p>
 * 
 * @author Fabian Saiz Landeras
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
    private double desnivelPositivo;
    private double desnivelNegativo;
    private double altMax;
    private double altMin;
    private Clasificacion_Ruta clasificacion;
    private int nivelRiesgo;
    private int nivelEsfuerzo;
    private int tipoTerreno;
    private int indicaciones;
    private Actividad tipoActividad;
    private String temporada;
    //private boolean accesibilidad;
    private boolean familiar;
    private String url;
    private Estado estado;
    private String recomendaciones;
    private String zonaGeografica;
    private LinkedHashSet<Punto> puntosIntermedios;
    private double duracion;
    private int mediaValoracion;

    /**
     * Crea una nueva instancia de Ruta con todos los atributos especificados, incluyendo el identificador.
     *
     * @param id el identificador de la ruta.
     * @param autor el autor de la ruta.
     * @param nombre el nombre de la ruta.
     * @param fecha_creacion la fecha de creacion de la ruta.
     * @param punto_ini el punto inicial de la ruta.
     * @param punto_fin el punto final de la ruta.
     * @param distanciaTotal la distancia total de la ruta.
     * @param desnivel el desnivel acumulado de la ruta.
     * @param desnivelPositivo el desnivel positivo de la ruta.
     * @param desnivelNegativo el desnivel negativo de la ruta.
     * @param altMax la altitud maxima registrada en la ruta.
     * @param altMin la altitud minima registrada en la ruta.
     * @param clasificacion la clasificacion de la ruta.
     * @param nivelRiesgo el nivel de riesgo asociado a la ruta.
     * @param nivelEsfuerzo el nivel de esfuerzo requerido en la ruta.
     * @param tipoTerreno el tipo de terreno de la ruta.
     * @param indicaciones las indicaciones para la ruta.
     * @param tipoActividad el tipo de actividad asociada a la ruta.
     * @param temporada la estacion recomendada para realizar la ruta.
     * @param familiar indica si la ruta es apta para familias.
     * @param url la URL con una imagen de la ruta.
     * @param estado el estado de la ruta.
     * @param recomendaciones las recomendaciones para la ruta.
     * @param zonaGeografica la zona geografica en la que se ubica la ruta.
     * @param puntosIntermedios los waypoints que forman la ruta.
     * @param duracion la duracion estimada de la ruta.
     * @param mediaValoracion la media de las valoraciones de la ruta.
     */
    public Ruta(int id, Usuario autor, String nombre, LocalDate fecha_creacion, Punto punto_ini, Punto punto_fin, double distanciaTotal, double desnivel, double desnivelPositivo, double desnivelNegativo, double altMax, double altMin, Clasificacion_Ruta clasificacion, int nivelRiesgo, int nivelEsfuerzo, int tipoTerreno, int indicaciones, Actividad tipoActividad, String temporada, boolean familiar, String url, Estado estado, String recomendaciones, String zonaGeografica, LinkedHashSet<Punto> puntosIntermedios, double duracion, int mediaValoracion) {
        this.id = id;
        this.autor = autor;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.punto_ini = punto_ini;
        this.punto_fin = punto_fin;
        this.distanciaTotal = distanciaTotal;
        this.desnivel = desnivel;
        this.desnivelPositivo = desnivelPositivo;
        this.desnivelNegativo = desnivelNegativo;
        this.altMax = altMax;
        this.altMin = altMin;
        this.clasificacion = clasificacion;
        this.nivelRiesgo = nivelRiesgo;
        this.nivelEsfuerzo = nivelEsfuerzo;
        this.tipoTerreno = tipoTerreno;
        this.indicaciones = indicaciones;
        this.tipoActividad = tipoActividad;
        this.temporada = temporada;
        this.familiar = familiar;
        this.url = url;
        this.estado = estado;
        this.recomendaciones = recomendaciones;
        this.zonaGeografica = zonaGeografica;
        this.puntosIntermedios = puntosIntermedios;
        this.duracion = duracion;
        this.mediaValoracion = mediaValoracion;
    }
    /**
     * Crea una nueva instancia de Ruta con todos los atributos especificados, excepto el identificador 
     * que se asigna de forma autoincremental.
     *
     * @param autor el autor de la ruta.
     * @param nombre el nombre de la ruta.
     * @param fecha_creacion la fecha de creacion de la ruta.
     * @param punto_ini el punto inicial de la ruta.
     * @param punto_fin el punto final de la ruta.
     * @param distanciaTotal la distancia total de la ruta.
     * @param desnivel el desnivel acumulado de la ruta.
     * @param desnivelPositivo el desnivel positivo de la ruta.
     * @param desnivelNegativo el desnivel negativo de la ruta.
     * @param altMax la altitud maxima registrada en la ruta.
     * @param altMin la altitud minima registrada en la ruta.
     * @param clasificacion la clasificacion de la ruta.
     * @param nivelRiesgo el nivel de riesgo asociado a la ruta.
     * @param nivelEsfuerzo el nivel de esfuerzo requerido en la ruta.
     * @param tipoTerreno el tipo de terreno de la ruta.
     * @param indicaciones las indicaciones para la ruta.
     * @param tipoActividad el tipo de actividad asociada a la ruta.
     * @param temporada la estacion recomendada para realizar la ruta.
     * @param familiar indica si la ruta es apta para familias.
     * @param url la URL con una imagen de la ruta.
     * @param estado el estado de la ruta.
     * @param recomendaciones las recomendaciones para la ruta.
     * @param zonaGeografica la zona geografica en la que se ubica la ruta.
     * @param puntosIntermedios los waypoints que forman la ruta.
     * @param duracion la duracion estimada de la ruta.
     * @param mediaValoracion la media de las valoraciones de la ruta.
     */
    public Ruta(Usuario autor, String nombre, LocalDate fecha_creacion, Punto punto_ini, Punto punto_fin, double distanciaTotal, double desnivel, double desnivelPositivo, double desnivelNegativo, double altMax, double altMin, Clasificacion_Ruta clasificacion, int nivelRiesgo, int nivelEsfuerzo, int tipoTerreno, int indicaciones, Actividad tipoActividad, String temporada, boolean familiar, String url, Estado estado, String recomendaciones, String zonaGeografica, LinkedHashSet<Punto> puntosIntermedios, double duracion, int mediaValoracion) {
        this.autor = autor;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.punto_ini = punto_ini;
        this.punto_fin = punto_fin;
        this.distanciaTotal = distanciaTotal;
        this.desnivel = desnivel;
        this.desnivelPositivo = desnivelPositivo;
        this.desnivelNegativo = desnivelNegativo;
        this.altMax = altMax;
        this.altMin = altMin;
        this.clasificacion = clasificacion;
        this.nivelRiesgo = nivelRiesgo;
        this.nivelEsfuerzo = nivelEsfuerzo;
        this.tipoTerreno = tipoTerreno;
        this.indicaciones = indicaciones;
        this.tipoActividad = tipoActividad;
        this.temporada = temporada;
        this.familiar = familiar;
        this.url = url;
        this.estado = estado;
        this.recomendaciones = recomendaciones;
        this.zonaGeografica = zonaGeografica;
        this.puntosIntermedios = puntosIntermedios;
        this.duracion = duracion;
        this.mediaValoracion = mediaValoracion;
    }   

    public Ruta() {
    }

   
    

    /**
     * Obtiene el identificador de la ruta.
     * 
     * @return el identificador de la ruta.
     */
    public int getId() {
        return id;
    }
    /**
     * Establece el identificador de la ruta.
     *
     * @param id el nuevo identificador de la ruta.
     */
    public void setId(int id) {
        this.id = id;
    }
/**
     * Obtiene el autor de la ruta.
     *
     * @return el autor de la ruta.
     */
    public Usuario getAutor() {
        return autor;
    }

    /**
     * Establece el autor de la ruta.
     *
     * @param autor el nuevo autor de la ruta.
     */
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el nombre de la ruta.
     *
     * @return el nombre de la ruta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la ruta.
     *
     * @param nombre el nuevo nombre de la ruta.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de creacion de la ruta.
     *
     * @return la fecha de creacion de la ruta.
     */
    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * Establece la fecha de creacion de la ruta.
     *
     * @param fecha_creacion la nueva fecha de creacion de la ruta.
     */
    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * Obtiene el punto inicial de la ruta.
     *
     * @return el punto inicial de la ruta.
     */
    public Punto getPunto_ini() {
        return punto_ini;
    }

    /**
     * Establece el punto inicial de la ruta.
     *
     * @param punto_ini el nuevo punto inicial de la ruta.
     */
    public void setPunto_ini(Punto punto_ini) {
        this.punto_ini = punto_ini;
    }

    /**
     * Obtiene el punto final de la ruta.
     *
     * @return el punto final de la ruta.
     */
    public Punto getPunto_fin() {
        return punto_fin;
    }

    /**
     * Establece el punto final de la ruta.
     *
     * @param punto_fin el nuevo punto final de la ruta.
     */
    public void setPunto_fin(Punto punto_fin) {
        this.punto_fin = punto_fin;
    }

    /**
     * Obtiene la distancia total de la ruta.
     *
     * @return la distancia total de la ruta.
     */
    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    /**
     * Establece la distancia total de la ruta.
     *
     * @param distanciaTotal la nueva distancia total de la ruta.
     */
    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    /**
     * Obtiene el desnivel total de la ruta.
     *
     * @return el desnivel total de la ruta.
     */
    public double getDesnivel() {
        return desnivel;
    }

    /**
     * Establece el desnivel total de la ruta.
     *
     * @param desnivel el nuevo desnivel de la ruta.
     */
    public void setDesnivel(double desnivel) {
        this.desnivel = desnivel;
    }

    /**
     * Obtiene el desnivel positivo de la ruta.
     *
     * @return el desnivel positivo de la ruta.
     */
    public double getDesnivelPositivo() {
        return desnivelPositivo;
    }

    /**
     * Establece el desnivel positivo de la ruta.
     *
     * @param desnivelPositivo el nuevo desnivel positivo de la ruta.
     */
    public void setDesnivelPositivo(double desnivelPositivo) {
        this.desnivelPositivo = desnivelPositivo;
    }

    /**
     * Obtiene el desnivel negativo de la ruta.
     *
     * @return el desnivel negativo de la ruta.
     */
    public double getDesnivelNegativo() {
        return desnivelNegativo;
    }

    /**
     * Establece el desnivel negativo de la ruta.
     *
     * @param desnivelNegativo el nuevo desnivel negativo de la ruta.
     */
    public void setDesnivelNegativo(double desnivelNegativo) {
        this.desnivelNegativo = desnivelNegativo;
    }

    /**
     * Obtiene la altitud maxima registrada en la ruta.
     *
     * @return la altitud maxima de la ruta.
     */
    public double getAltMax() {
        return altMax;
    }

    /**
     * Establece la altitud maxima de la ruta.
     *
     * @param altMax la nueva altitud maxima de la ruta.
     */
    public void setAltMax(double altMax) {
        this.altMax = altMax;
    }

    /**
     * Obtiene la altitud minima registrada en la ruta.
     *
     * @return la altitud minima de la ruta.
     */
    public double getAltMin() {
        return altMin;
    }

    /**
     * Establece la altitud minima de la ruta.
     *
     * @param altMin la nueva altitud minima de la ruta.
     */
    public void setAltMin(double altMin) {
        this.altMin = altMin;
    }

    /**
     * Obtiene la clasificacion de la ruta.
     *
     * @return la clasificacion de la ruta.
     */
    public Clasificacion_Ruta getClasificacion() {
        return clasificacion;
    }

    /**
     * Establece la clasificacion de la ruta.
     *
     * @param clasificacion la nueva clasificacion de la ruta.
     */
    public void setClasificacion(Clasificacion_Ruta clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * Obtiene el nivel de riesgo asociado a la ruta.
     *
     * @return el nivel de riesgo de la ruta.
     */
    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    /**
     * Establece el nivel de riesgo de la ruta.
     *
     * @param nivelRiesgo el nuevo nivel de riesgo de la ruta.
     */
    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    /**
     * Obtiene el nivel de esfuerzo requerido en la ruta.
     *
     * @return el nivel de esfuerzo de la ruta.
     */
    public int getNivelEsfuerzo() {
        return nivelEsfuerzo;
    }

    /**
     * Establece el nivel de esfuerzo de la ruta.
     *
     * @param nivelEsfuerzo el nuevo nivel de esfuerzo de la ruta.
     */
    public void setNivelEsfuerzo(int nivelEsfuerzo) {
        this.nivelEsfuerzo = nivelEsfuerzo;
    }

    /**
     * Obtiene el tipo de terreno de la ruta.
     *
     * @return el tipo de terreno de la ruta.
     */
    public int getTipoTerreno() {
        return tipoTerreno;
    }

    /**
     * Establece el tipo de terreno de la ruta.
     *
     * @param tipoTerreno el nuevo tipo de terreno de la ruta.
     */
    public void setTipoTerreno(int tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    /**
     * Obtiene las indicaciones para la ruta.
     *
     * @return las indicaciones de la ruta.
     */
    public int getIndicaciones() {
        return indicaciones;
    }

    /**
     * Establece las indicaciones para la ruta.
     *
     * @param indicaciones las nuevas indicaciones de la ruta.
     */
    public void setIndicaciones(int indicaciones) {
        this.indicaciones = indicaciones;
    }

    /**
     * Obtiene el tipo de actividad asociada a la ruta.
     *
     * @return el tipo de actividad de la ruta.
     */
    public Actividad getTipoActividad() {
        return tipoActividad;
    }

    /**
     * Establece el tipo de actividad de la ruta.
     *
     * @param tipoActividad el nuevo tipo de actividad de la ruta.
     */
    public void setTipoActividad(Actividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    /**
     * Obtiene la temporada en la que se realiza la ruta.
     *
     * @return la temporada de la ruta.
     */
    public String getTemporada() {
        return temporada;
    }

    /**
     * Establece la temporada de la ruta.
     *
     * @param temporada la nueva temporada de la ruta.
     */
    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    /**
     * Indica si la ruta es apta para familias.
     *
     * @return {@code true} si la ruta es familiar, {@code false} en caso contrario.
     */
    public boolean isFamiliar() {
        return familiar;
    }

    /**
     * Establece si la ruta es apta para familias.
     *
     * @param familiar el nuevo valor que indica si la ruta es familiar.
     */
    public void setFamiliar(boolean familiar) {
        this.familiar = familiar;
    }

    /**
     * Obtiene la URL asociada a la ruta.
     *
     * @return la URL de la ruta.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL de la ruta.
     *
     * @param url la nueva URL de la ruta.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtiene el estado de la ruta.
     *
     * @return el estado de la ruta.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la ruta.
     *
     * @param estado el nuevo estado de la ruta.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Obtiene las recomendaciones para la ruta.
     *
     * @return las recomendaciones de la ruta.
     */
    public String getRecomendaciones() {
        return recomendaciones;
    }

    /**
     * Establece las recomendaciones para la ruta.
     *
     * @param recomendaciones las nuevas recomendaciones de la ruta.
     */
    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    /**
     * Obtiene la zona geografica en la que se ubica la ruta.
     *
     * @return la zona geografica de la ruta.
     */
    public String getZonaGeografica() {
        return zonaGeografica;
    }

    /**
     * Establece la zona geografica de la ruta.
     *
     * @param zonaGeografica la nueva zona geografica de la ruta.
     */
    public void setZonaGeografica(String zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    /**
     * Obtiene los puntos intermedios que forman la ruta.
     *
     * @return los puntos intermedios de la ruta.
     */
    public LinkedHashSet<Punto> getPuntosIntermedios() {
        return puntosIntermedios;
    }

    /**
     * Establece los puntos intermedios que forman la ruta.
     *
     * @param puntosIntermedios los nuevos puntos intermedios de la ruta.
     */
    public void setPuntosIntermedios(LinkedHashSet<Punto> puntosIntermedios) {
        this.puntosIntermedios = puntosIntermedios;
    }

    /**
     * Obtiene la duracion estimada de la ruta.
     *
     * @return la duracion de la ruta.
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * Establece la duracion de la ruta.
     *
     * @param duracion la nueva duracion de la ruta.
     */
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene la media de las valoraciones de la ruta.
     *
     * @return la media de las valoraciones de la ruta.
     */
    public int getMediaValoracion() {
        return mediaValoracion;
    }

    /**
     * Establece la media de las valoraciones de la ruta.
     *
     * @param mediaValoracion la nueva media de las valoraciones de la ruta.
     */
    public void setMediaValoracion(int mediaValoracion) {
        this.mediaValoracion = mediaValoracion;
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
    
    /**
     * @return una String que representa la ruta.
     */
    @Override
    public String toString() {
        return "Ruta{" + "id=" + id + '}';
    }

    
}
