/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;

/**
 *
 * @author Diego
 */
public class Comidas {
    
    int idComidas;
    String descripcion;
    int valor;

    public Comidas() {
    }
    
    public Comidas(int idComidas) {
        setIdComidas(idComidas);
    }

    public Comidas(String descripcion, int valor) {
        
        setDescripcion(descripcion);
        setValor(valor);
    }
    
    public Comidas(int idComidas ,String descripcion, int valor) {
        setIdComidas(idComidas);
        setDescripcion(descripcion);
        setValor(valor);
    }

    public int getIdComidas() {
        return idComidas;
    }

    public void setIdComidas(int idComidas) {
        this.idComidas = idComidas;
    }

    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
     
}
