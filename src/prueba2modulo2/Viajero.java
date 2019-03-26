/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2modulo2;

/**
 *
 * @author davidbousquet
 */
public class Viajero {
    
    String nombre, apellido, direccion, codigoReserva, equipajeEspecial;
    int rut, telefono, cantidadMaleta, idVuelo;
    char sexo;
    
    private static Viajero viajero;

    public Viajero() {
    }
    
        public static Viajero getInstance() {
        if (viajero == null ){
            viajero = new Viajero();
        }
        return viajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getEquipajeEspecial() {
        return equipajeEspecial;
    }

    public void setEquipajeEspecial(String equipajeEspecial) {
        this.equipajeEspecial = equipajeEspecial;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCantidadMaleta() {
        return cantidadMaleta;
    }

    public void setCantidadMaleta(int cantidadMaleta) {
        this.cantidadMaleta = cantidadMaleta;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public static Viajero getViajero() {
        return viajero;
    }

    public static void setViajero(Viajero viajero) {
        Viajero.viajero = viajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }
    

    
    
    
    
}
