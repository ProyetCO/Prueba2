/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2modulo2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author davidbousquet
 */
public class Conexion {
    
    private static Connection con;
    
    private Conexion() {
        
    }
    
    public static Connection getConnection() {
        
        String url = "jdbc:mysql://localhost:3306/aerolineaDB?autoReconnect=true&useSSL=false";
        String user = "root";
        String pass = "Pass1234";
        System.out.println("Conectando..");        
        try {
            con = DriverManager.getConnection(url, user, pass);
                        System.out.println("BBDD conectada");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return con;
    }
    
    
}
