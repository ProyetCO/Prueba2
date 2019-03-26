/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2modulo2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author davidbousquet
 */
public class Controlador {
    
    public static ResultSet obtenerVuelos(Connection conexion) {
        Statement statement;
        String query;
        ResultSet resultSet = null;
        query = "select `idVuelo`, `origen`, `destino` from vuelo";
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        
        return resultSet;
        
    }
    
    public static ResultSet obtenerAsientosDisponibles(Connection conexion, int idVuelo) {
       Statement statement;
       String query;
       ResultSet resultSet = null;
       query = "select cantidadAsientos from vuelo where idVuelo=\""+idVuelo+"\"";
       try {
           statement = conexion.createStatement();
           resultSet = statement.executeQuery(query);
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
       return resultSet;
    }
    
    public static void insertarPasajero(Connection conexion, Viajero viajero) {
       Statement statement;
       String query;
       ResultSet resultSet;
       int asientosDisponibles=0;
       viajero.setCantidadMaleta(0);
       
       try {
                resultSet = obtenerAsientosDisponibles(conexion, viajero.getIdVuelo());
                resultSet.next();
                asientosDisponibles = Integer.valueOf(resultSet.getObject("cantidadAsientos").toString());

       } catch(SQLException ex) {
                System.out.println(ex.getMessage());
       }
                if (asientosDisponibles < 1) {
                    JOptionPane.showMessageDialog(null, "No quedan suficientes asientos, favor seleccionar otro vuelo.");
                } else {
                       query = "INSERT INTO `pasajero` (`rut`, `nombre`, `apellido`, `direccion`, `telefono`, `sexo`, `codigoReserva`, `vuelo_idVuelo`, `cantidadMaleta`) VALUES ('"+viajero.getRut()+"', '"+viajero.getNombre()+"', '"+viajero.getApellido()+"', '"+viajero.getDireccion()+"', '"+viajero.getTelefono()+"', '"+viajero.getSexo()+"', '"+viajero.getCodigoReserva()+"', '"+viajero.getIdVuelo()+"','"+viajero.getCantidadMaleta()+"')";
                       try {
                           statement = conexion.createStatement();
                           statement.executeUpdate(query);
                           JOptionPane.showMessageDialog(null, "Pasajero Agregado Correctamente!");
                       } catch (SQLException ex) {
                           System.out.println(ex.getMessage());
                       }
                       query = "UPDATE `vuelo` SET `cantidadAsientos` = \""+(asientosDisponibles-1)+"\" WHERE (`idVuelo` = '"+viajero.getIdVuelo()+"');";
                       try {
                           statement = conexion.createStatement();
                           statement.executeUpdate(query);
                       } catch (SQLException ex) {
                           System.out.println(ex.getMessage());
                       }
                 }


    }
    
    public static ResultSet buscarReserva(Connection conexion, String codigoReserva) {
       Statement statement;
       String query;
       ResultSet resultSet = null;
       query = "select * from pasajero where codigoReserva=\""+codigoReserva+"\";";
       try {
           statement = conexion.createStatement();
           resultSet = statement.executeQuery(query);
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
       return resultSet;
        
    }
    
    public static boolean eliminarPasajero(Connection conexion, int rutPasajero) {
       Statement statement;
       String query;
       query = "DELETE FROM `pasajero` WHERE (`rut` = '"+rutPasajero+"');";
       try {
           statement = conexion.createStatement();
           statement.executeUpdate(query);
           return true;
       } catch(SQLException ex) {
         JOptionPane.showMessageDialog(null, "Pasajero que ya ha sido chequeado no puede ser eliminado");
         System.out.println(ex.getMessage());
         return false;
           
       }

    }
    
    public static boolean agregarMaleta(Connection conexion, Maleta maleta, Viajero viajero) {
       Statement statement;
       String query;
       boolean equipajeEspecial;
       viajero.setCantidadMaleta(viajero.getCantidadMaleta()+1);
       query = "INSERT INTO `maleta` (`peso`, `dimension`, `pasajero_rut`, `equipajeEspecial`) VALUES ('"+maleta.getPeso()+"', '"+maleta.getDimension()+"', '"+maleta.getPasajero()+"','"+maleta.getEquipajeEspecial()+"');";
       try {
           statement = conexion.createStatement();
           statement.executeUpdate(query);

       } catch(SQLException ex) {
         System.out.println(ex.getMessage());
         return false;
       }
       
       equipajeEspecial = (viajero.getEquipajeEspecial() == null);

            if (equipajeEspecial) {
                  query = "UPDATE `pasajero` SET `cantidadMaleta` = '"+viajero.getCantidadMaleta()+"' WHERE (`rut` = '"+viajero.getRut()+"');";
            } else {
                  query = "UPDATE `pasajero` SET `cantidadMaleta` = '"+viajero.getCantidadMaleta()+"', `equipajeEspecial` = 'si' WHERE (`rut` = '"+viajero.getRut()+"');";
            }
     
       try {
           statement = conexion.createStatement();
           statement.executeUpdate(query);
           System.out.println("datos actualizados");
       } catch (SQLException ex) {
                    System.out.println("Falló al actualizar cantidad maletas del pasajero");
                    System.out.println(ex.getMessage());
       }
       return true;
       

    }
    
    public static void actualizarPasajero(Connection conexion, Viajero viajero) {
       Statement statement;
       String query;
       query = "UPDATE `pasajero` SET `rut` = '"+viajero.getRut()+"', `nombre` = '"+viajero.getNombre()+"', `apellido` = '"+viajero.getApellido()+"', `direccion` = '"+viajero.getDireccion()+"', `telefono` = '"+viajero.getTelefono()+"', `sexo` = '"+viajero.getSexo()+"' WHERE (`rut` = '"+viajero.getRut()+"');";
       
       try {
           statement = conexion.createStatement();
           statement.executeUpdate(query);
           JOptionPane.showMessageDialog(null, "Pasajero Chequeado");
       } catch (SQLException ex){
           System.out.println(ex.getMessage());
       }
       
    }
    
}
