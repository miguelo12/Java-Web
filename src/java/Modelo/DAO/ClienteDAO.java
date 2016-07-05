/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Conexion;
import Modelo.DTO.Cliente;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ClienteDAO {
    Statement instruccion;
    
    public ClienteDAO()throws Exception
    {
       Conexion c =new Conexion();
       instruccion=c.getStatement();
    }
    
    public Cliente obtenerCliente(Cliente nuevoCliente)throws Exception
    {
        ResultSet res;
        Cliente cliente;
        try
        {
           res=instruccion.executeQuery("SELECT * FROM cliente WHERE rut="+nuevoCliente.getRut());
           cliente=new Cliente(res.getString("nombre"),res.getString("rut"));
        }
        catch(SQLException ex)
        {
            String msg="No se puede ejecutar la consulta";
            throw new Exception(msg);
        }
        return cliente; 
    }
    
     public  ArrayList<Cliente> obtenerTodos() throws Exception{
        ResultSet res;
        ArrayList<Cliente> aResult;
        aResult = new ArrayList<Cliente>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM cliente");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new Cliente(res.getString("nombre"),res.getString("rut")));
        }
        
        return aResult;
        
    }
     
     public Cliente Validar(String rut) throws Exception{
        ResultSet res;
        Cliente aResult = null;
        
        try
        {
            res = instruccion.executeQuery("SELECT * FROM cliente WHERE (rut='"+rut+"')");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        
        while(res.next()){
            aResult = new Cliente(res.getString("nombre"),res.getString("rut"));
        }
        
        return aResult;
        
    }
     
     public int Encontrar(String rut) throws Exception{
        ResultSet res;
        int aResult = -1;
        
        try
        {
            res = instruccion.executeQuery("SELECT * FROM cliente WHERE (rut='"+rut+"')");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        
        while(res.next()){
            aResult = res.getInt("idCliente");
        }
        
        return aResult;
        
    }
     
    public String DarNombre(int id) throws Exception{
        ResultSet res;
        String aResult = "";
        
        try
        {
            res = instruccion.executeQuery("SELECT * FROM cliente WHERE (idCliente="+id+")");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        
        while(res.next()){
            aResult = res.getString("nombre");
        }
        
        return aResult;
        
    }
     
      public int guardar(Cliente nuevoCliente) throws Exception
    {
        int res=0;
        String sql="insert into cliente (nombre,rut) values(' " +nuevoCliente.getNombre()+ "',"+
                                                             "'"+nuevoCliente.getRut()+"')";
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
