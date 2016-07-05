/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;

/**
 *
 * @author miguelA
 */
public class Cliente {
    String nombre;
    String rut;

    public Cliente() {
    }

    public Cliente( String nombre, String rut) {
        setNombre(nombre);
        setRut(rut);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    
    
    
    
}
