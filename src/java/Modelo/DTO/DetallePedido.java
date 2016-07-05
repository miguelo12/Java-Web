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
public class DetallePedido {
    int idPedidos;
    int cantidad;
    int idComidas;
    String Detalle;
    

    public DetallePedido() {
    }

    public DetallePedido(int idPedidos, int cantidad, int idComidas) {
        setIdPedidos(idPedidos);
        setCantidad(cantidad);
        setIdComidas(idComidas);
    }

    
    
    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdComidas() {
        return idComidas;
    }

    public void setIdComidas(int idComidas) {
        this.idComidas = idComidas;
    }
    
    
    
}
