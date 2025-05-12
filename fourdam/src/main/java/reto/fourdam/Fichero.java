/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reto.fourdam;

import ENUMs.TipoPInteres;
import ENUMs.TipoUsuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAM106
 */
public class Fichero {

    public static String rutaToCsv(Ruta ruta) {
        String linea = "";
        linea += ruta.getNombre() + ";" + ruta.getAutor().getNombre() + ";" + ruta.getUrl() + "\n";
        linea += "Tipo;Latitud;Longitud;Elevación;Tiempo;Nombre\n";
        if (ruta.getPunto_ini() != null) {
            Punto ini = ruta.getPunto_ini();
            linea += "waypoint;" + ini.getLatitud() + ";" + ini.getLongitud() + ";" + ruta.getNombre() + "\n";
        }

        if (ruta.getPunto_fin() != null) {
            Punto fin = ruta.getPunto_fin();
            linea += "waypoint;" + fin.getLatitud() + ";" + fin.getLongitud() + ";" + ruta.getNombre() + "\n";
        }

        return linea;
    }

    public static Ruta csvToRuta(File file) {
        Ruta ruta = new Ruta();
        List<PuntoInteres> waypoints = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] parts = linea.split(";");
                String nombreRuta = parts[0];
                String nombreAutor = parts[1];
                String email = parts[2];
                String url = parts[3];
                TipoUsuario rol=null;
                
                boolean usuarioExiste = false;
                String sql = "SELECT * FROM usuarios WHERE correo=?";
                Connection conn = AccesoBaseDatos.getInstance().getConn();
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, email);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String rolStr = rs.getString("rol").toUpperCase();
                            try {
                                rol = TipoUsuario.valueOf(rolStr);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Rol desconocido: " + rolStr);
                                ruta = null;
                            }
                        } else {
                            System.out.println("Usuario no encontrado en la base de datos: " + email);
                            ruta = null;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al consultar usuario: " + ex.getMessage());
                    ruta = null;
                }

                ruta.setNombre(nombreRuta);
                String apellidos = "";
                String cnna = "";
                
                ruta.setAutor(new Usuario(nombreAutor, apellidos, email, cnna, rol));
                ruta.setUrl(url);
                ruta.setFecha_creacion(LocalDate.now());
                //ruta.setDuracion(LocalTime.of(0, 0));

                br.readLine();

                String puntoLinea;
                while ((puntoLinea = br.readLine()) != null) {
                    String[] puntoParts = puntoLinea.split(";");
                    if (puntoParts.length >= 6) {
                        String tipoStr = puntoParts[0];
                        double lat = Double.parseDouble(puntoParts[1]);
                        double lon = Double.parseDouble(puntoParts[2]);
                        float elev = puntoParts[3].equals("-") ? 0 : Float.parseFloat(puntoParts[3]);
                        String tiempo = puntoParts[4]; // no usado
                        String nombre = puntoParts[5];

                        TipoPInteres tipo = TipoPInteres.valueOf(tipoStr.toUpperCase());

                        PuntoInteres punto = new PuntoInteres(lat, lon, "imagen.jpg", tipo, nombre);
                        waypoints.add(punto);
                    }
                }

                if (!waypoints.isEmpty()) {
                    ruta.setPunto_ini(waypoints.get(0));
                    ruta.setPunto_fin(waypoints.get(waypoints.size() - 1));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return ruta;
    }

}
