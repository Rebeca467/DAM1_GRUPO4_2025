/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package reto.fourdam;

import java.sql.Connection;
import pruebas.SWING.vPrincipal;

/**
 *
 * @author DAM124
 */
public class Fourdam {

    public static void main(String[] args) {
        new vPrincipal().setVisible(true);
        Connection conn = AccesoBaseDatos.getInstance().getConn();
        
    }
    
}
