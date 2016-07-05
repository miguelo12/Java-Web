/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Conexion;
import Modelo.DTO.ComidaLista;
import Modelo.DTO.Comidas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ComidaDAO {
     Statement instruccion;
    
    public ComidaDAO()throws Exception
    {
       Conexion c =new Conexion();
       instruccion=c.getStatement();
    }
      
     public  ArrayList<Comidas> obtenerTodos() throws Exception{
        ResultSet res;
        ArrayList<Comidas> aResult;
        aResult = new ArrayList<Comidas>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM comidas");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new Comidas(res.getInt("idComidas") ,res.getString("descripcion"),Integer.parseInt(res.getString("valor"))));
        }
        
        return aResult;
    }
     
     public  ArrayList<ComidaLista> obtenerLista() throws Exception{
        ResultSet res;
        ArrayList<ComidaLista> aResult;
        aResult = new ArrayList<ComidaLista>();
        try
        {
            res = instruccion.executeQuery("SELECT * FROM comidas");
        }catch(SQLException ex){ 
            String msg="No se pudo ejecutar la consulta";
            throw new Exception(msg);
        }
        while(res.next()){
            aResult.add(new ComidaLista(res.getInt("idComidas"),res.getString("descripcion"),res.getInt("valor")));
        }
        
        return aResult;
    }
     
}
