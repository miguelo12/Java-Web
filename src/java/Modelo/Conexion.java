/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Miguelo
 */
public class Conexion {
    
    Connection conn;
    Statement instruccion;
    String baseDatos="jhonmaster";
    String servidor="Localhost:3306";
    String usuario="root";
    String password=""; //cambiar password
    
    public Conexion() throws Exception
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://"
                    + servidor
                    + "/"
                    + baseDatos,
                    usuario,
                    password);
        }
        catch(SQLException ex)
        {
            throw new Exception("No se puede conectar a la base de datos");
        }
    }
    
    public Statement getStatement() throws Exception
    {
        try
        {
            instruccion=conn.createStatement();
        }
        catch(SQLException ex)
        {
            throw new Exception ("No se puede crear el Statement");
        }
        return instruccion;
        
    }
    
    
}
