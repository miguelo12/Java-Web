/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Conexion;
import Modelo.DTO.Pedidos;
import Modelo.DTO.PedidosLista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class PedidosDAO {
      Statement instruccion;
    
    public PedidosDAO()throws Exception
    {
       Conexion c =new Conexion();
       instruccion=c.getStatement();
    }
      
     public  ArrayList<Pedidos> obtenerTodos() throws Exception{
        ResultSet res;
        ArrayList<Pedidos> aResult;
        aResult = new ArrayList<Pedidos>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM pedidos");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new Pedidos(Integer.parseInt(res.getString("idCliente")),res.getString("fecha"),
                                    res.getString("agrandar"),res.getString("llevar"), res.getInt("valor"), res.getString("tipodepago")));
        }
        
        return aResult;
    }
     
     public  ArrayList<Pedidos> EncontrarPorID(int id) throws Exception{
        ResultSet res;
        ArrayList<Pedidos> aResult;
        aResult = new ArrayList<Pedidos>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM pedidos WHERE idCliente="+id);
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new Pedidos(Integer.parseInt(res.getString("idCliente")),res.getString("fecha"),
                                    res.getString("agrandar"),res.getString("llevar"), res.getInt("valor"), res.getString("tipodepago")));
        }
        
        return aResult;
    }
     
     public  ArrayList<PedidosLista> EncontrarPorIDLista(int id) throws Exception{
        ResultSet res;
        ArrayList<PedidosLista> aResult;
        aResult = new ArrayList<PedidosLista>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM pedidos WHERE idCliente="+id);
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new PedidosLista( Integer.parseInt(res.getString("idPedidos")), Integer.parseInt(res.getString("idCliente")), res.getString("fecha"),
                                    res.getString("agrandar"), res.getString("llevar"), res.getInt("valor"), res.getString("tipodepago")));
        }
        
        return aResult;
    }
     
     public int Encontrar(int idCliente,String fecha,int valor) throws Exception{
        ResultSet res;
        int aResult = -1;
        try
        {
            res = instruccion.executeQuery("SELECT * FROM pedidos WHERE idCliente="+idCliente+" && fecha='"+fecha+"' && valor="+valor+"");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult = res.getInt("idPedidos");
        }
        
        return aResult;
    }
     
      public int guardar(Pedidos nuevoPedidos) throws Exception
    {
        
        int res=0;
        String sql="insert into pedidos (idCliente,fecha,agrandar,llevar,valor,tipodepago) values("+nuevoPedidos.getIdCliente()+","+
                                                                                                 "'"+nuevoPedidos.getFecha()+"',"+
                                                                                                 ""+nuevoPedidos.getAgrandar()+","+
                                                                                                 ""+nuevoPedidos.getLlevar()+","+
                                                                                                 ""+nuevoPedidos.getValor()+","+
                                                                                                 "'"+nuevoPedidos.getTipodepago()+"')";
        try{
            res= instruccion.executeUpdate(sql);
        }catch(SQLException ex){
            String msg="No se pudo guardar el dato";
            if(ex.getErrorCode()==1062){ 
                msg="El registro ya existe";
            }else{
                throw new Exception(msg);
            }
        }
        return res;
    }
    
}
