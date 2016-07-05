/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DTO;

import java.util.ArrayList;

/**
 *
 * @author miguelA
 */
public class PedidosLista {
    int idPedidos;
    int idCliente;
    String fecha;
    String agrandar;
    String llevar;
    int valor;
    String tipodepago;
    ArrayList<DetallePedido> comi;

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

    public ArrayList<DetallePedido> getComi() {
        return comi;
    }

    public void setComi(ArrayList<DetallePedido> comi) {
        this.comi = comi;
    }

    public PedidosLista(int idPedidos, int idCliente, String fecha, String agrandar, String llevar, int valor, String tipodepago) {
        this.idPedidos = idPedidos;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.agrandar = agrandar;
        this.llevar = llevar;
        this.valor = valor;
        this.tipodepago = tipodepago;
        comi = new ArrayList<DetallePedido>();
    }
    
       
    
}
