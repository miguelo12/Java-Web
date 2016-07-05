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
public class Pedidos {
    int idPedidos;
    int idCliente;
    String fecha;
    String agrandar;
    String llevar;
    int valor;
    String tipodepago;

    public Pedidos() {
    }

    public Pedidos(int idCliente, String fecha, String agrandar, String llevar, int valor, String tipodepago) {
        setIdCliente(idCliente);
        setFecha(fecha);
        setAgrandar(agrandar);
        setLlevar(llevar);
        setValor(valor);
        setTipodepago(tipodepago);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipodepago() {
        return tipodepago;
    }

    public void setTipodepago(String tipodepago) {
        this.tipodepago = tipodepago;
    }
    
    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAgrandar() {
        return agrandar;
    }

    public void setAgrandar(String agrandar) {
        this.agrandar = agrandar;
    }

    public String getLlevar() {
        return llevar;
    }

    public void setLlevar(String llevar) {
        this.llevar = llevar;
    }

    
      
}
