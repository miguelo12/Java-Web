/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.ComidaDAO;
import Modelo.DAO.DetallePedidoDAO;
import Modelo.DAO.PedidosDAO;
import Modelo.DTO.Cliente;
import Modelo.DTO.ComidaLista;
import Modelo.DTO.DetallePedido;
import Modelo.DTO.Pedidos;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author miguelA
 */
@WebServlet(name = "ServletEnvios", urlPatterns = {"/ServletEnvios"})
public class ServletEnvios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger logger = Logger.getLogger(ServletEnvios.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher despachador = request.getRequestDispatcher("/pedidosHome.jsp");
        RequestDispatcher despachadorEnvio = request.getRequestDispatcher("/pedidosTicket.jsp");
        HttpSession sesion = request.getSession();
        
        
        
        String agregar = request.getParameter("btnAgregar");
        ArrayList<ComidaLista> comida = (ArrayList<ComidaLista>)sesion.getAttribute("ListaComida");
        
        if(agregar.equals("agregar")){
            String nombre, rut,agrandar,llevar,tipo;
            
            nombre = request.getParameter("nombre");
            rut = request.getParameter("rut").trim();
            agrandar = request.getParameter("Agrandar");
            llevar = request.getParameter("Llevar");
            tipo = request.getParameter("dam");
            
            sesion.setAttribute("agrandar", agrandar);
            sesion.setAttribute("llevar", llevar);
            sesion.setAttribute("tipo1", tipo);
            sesion.setAttribute("nombre1", nombre);
            sesion.setAttribute("rut", rut);
            
            int objeto;
            ComidaDAO comita;
            
            objeto = Integer.parseInt(request.getParameter("producto"));
        
            try
            {
              if(comida == null)
              {
              comita = new ComidaDAO();
              comida = comita.obtenerLista();
              }
              
              int valortotal=0;
              for(ComidaLista comie:comida)
              {
                if(comie.getIdComida()==objeto)
                {
                    comie.setCantidad(1);
                }
                if(comie.getCantidad()>0){
                    valortotal+=(comie.getCantidad()*comie.getValor());
                }
              }           
              
              String valo = ""+valortotal;
              
              sesion.setAttribute("Total", valo);
              sesion.setAttribute("ListaComida", comida);
            }
            catch(Exception ex)
            {
              String msg = ex.getMessage();
              sesion.setAttribute("Mensaje", msg);
              logger.error(msg);
            } 
           
           despachador.forward(request, response);
        }
        
         if(agregar.equals("Envio")){
             
             String nombre,rut,metodopago,agrandar,llevar,to;
             int id,to1=0;
             
             to = (String)sesion.getAttribute("Total");
             agrandar = request.getParameter("Agrandar");
             llevar = request.getParameter("Llevar");
             metodopago = request.getParameter("dam");
             nombre = request.getParameter("nombre");
             rut = request.getParameter("rut");
             
             if(agrandar==null){
             agrandar = "false";}
             
             
             if(llevar==null){
             llevar = "false";}
             
             if(to!=null){
             to1 = Integer.parseInt(to.trim());}
             
             
            if(to1>0){
            try
            { 
                ClienteDAO clientedao = new ClienteDAO();
                PedidosDAO pedidodao = new PedidosDAO();
                
                Cliente cli = clientedao.Validar(rut);
                
                if(cli == null)
                {
                  Cliente cli1 = new Cliente(nombre, rut);
                  clientedao.guardar(cli1);
                  id = clientedao.Encontrar(rut);
                  logger.info("Acaba de Guardar un cliente( Numeroid:"+id+" ).");
                }
                else
                {
                  id = clientedao.Encontrar(rut);
                }
                
                Date date = new Date();
                DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		String convertido = fecha.format(date);
                
                int valortotal=0;
                for(ComidaLista comie:comida)
                {
                    if(comie.getCantidad()>0){
                    valortotal+=(comie.getCantidad()*comie.getValor());
                    }
                }
                
                Pedidos pedi = new Pedidos(id, convertido, agrandar, llevar, valortotal ,metodopago);
                pedidodao.guardar(pedi);
                
                int idpedido = pedidodao.Encontrar(id, convertido, valortotal);
                
                String idpe1 = ""+idpedido;
                String idpe = String.format("%04d", Integer.parseInt(idpe1.trim()));
                
                DetallePedidoDAO detalledao = new DetallePedidoDAO();
                for(ComidaLista comie:comida)
                {
                    if(comie.getCantidad()>0){
                    DetallePedido pedidooo = new DetallePedido(idpedido, comie.getCantidad(), comie.getIdComida());
                    detalledao.guardar(pedidooo);
                    }
                }
                
                if(agrandar!=null){
                valortotal+=1000;}
                
                if(agrandar.equals("false")){
                agrandar = null;}
             
                if(llevar.equals("false")){
                llevar = null;}
                
                String val = valortotal+"";
                sesion.setAttribute("Total", val);
                sesion.setAttribute("numpedido", idpe);
                sesion.setAttribute("agrandar", agrandar);
                sesion.setAttribute("llevar", llevar);
                sesion.setAttribute("tipo1", metodopago);
                sesion.setAttribute("nombre", nombre);
                sesion.setAttribute("rut", rut);
                
                logger.info("Acaba de Ingresar un cliente( Numeroid:"+id+" ) y a generado un pedido.");
            }
            catch(Exception ex)
            {
              String msg = ex.getMessage();
              sesion.setAttribute("Mensaje", msg);
              logger.error(msg);
            }
            
            despachadorEnvio.forward(request, response);
            }
            else
            {
              despachador.forward(request, response);
            }
        }
        
//        String nombre, rut;
//        nombre = request.getParameter("Nombre");
//        rut = request.getParameter("Rut");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
