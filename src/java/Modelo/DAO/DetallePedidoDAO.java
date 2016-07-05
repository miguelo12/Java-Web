/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Conexion;
import Modelo.DTO.DetallePedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class DetallePedidoDAO {
    Statement instruccion;
    
    public DetallePedidoDAO()throws Exception
    {
       Conexion c =new Conexion();
       instruccion=c.getStatement();
    }
    
    public  ArrayList<DetallePedido> obtenerTodos() throws Exception{
        ResultSet res;
        ArrayList<DetallePedido> aResult;
        aResult = new ArrayList<DetallePedido>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM detallePedido");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new DetallePedido(Integer.parseInt(res.getString("idPedidos")),Integer.parseInt(res.getString("cantidad")),
                                           Integer.parseInt(res.getString("idComidas")) ));
        }
        
        return aResult;
    }
     
    public  ArrayList<DetallePedido> obtenerID(int pedidoid) throws Exception{
        ResultSet res;
        ArrayList<DetallePedido> aResult;
        aResult = new ArrayList<DetallePedido>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM detallePedido WHERE idPedidos="+pedidoid+"");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new DetallePedido(Integer.parseInt(res.getString("idPedidos")),Integer.parseInt(res.getString("cantidad")),
                                           Integer.parseInt(res.getString("idComidas"))));
        }
        
        return aResult;
    }
    
      public int guardar(DetallePedido nuevaDetallePedido) throws Exception
    {
        int res=0;
        String sql="insert into detallePedido (idPedidos,cantidad,idComidas) values('"+nuevaDetallePedido.getIdPedidos()+"',"+
                                                                                                       "'"+nuevaDetallePedido.getCantidad()+"',"+
                                                                                                       "'"+nuevaDetallePedido.getIdComidas()+"')";
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
