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
public class ComidaLista {
    int Cantidad;
    String Detalle;
    int idComida;
    int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad += Cantidad;
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    
    public ComidaLista(int idComida, String Detalle, int valor) {
        setCantidad(0);
        setDetalle(Detalle);
        setIdComida(idComida);
        setValor(valor);
    }
    
    public void suprimir()
    {
       this.Cantidad-=1;
    }
}
