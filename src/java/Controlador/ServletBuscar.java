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
import Modelo.DTO.Comidas;
import Modelo.DTO.DetallePedido;
import Modelo.DTO.PedidosLista;
import java.io.IOException;
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
@WebServlet(name = "ServletBuscar", urlPatterns = {"/ServletBuscar"})
public class ServletBuscar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final Logger logger = Logger.getLogger(ServletBuscar.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher despachador = request.getRequestDispatcher("/pedidosMisPedidos.jsp");
        HttpSession sesion = request.getSession();
        
        String rut;
        int id;
        rut = request.getParameter("rut");
        
        try
        {
            ComidaDAO comidao = new ComidaDAO();
            ClienteDAO clientedao = new ClienteDAO();
            PedidosDAO pedidodao = new PedidosDAO();
            DetallePedidoDAO detapedidao = new DetallePedidoDAO();
            
            id = clientedao.Encontrar(rut.trim());
            if(id > 0)
            {
               ArrayList<PedidosLista> pedi = pedidodao.EncontrarPorIDLista(id);
               for(PedidosLista pe:pedi)
               {
                  ArrayList<DetallePedido> deta = detapedidao.obtenerID(pe.getIdPedidos());
                  for(DetallePedido te:deta){
                  pe.getComi().add(te);
                  }
               } 
               
               String Com3="";
               
               for(PedidosLista pe:pedi)
               {
                  for(DetallePedido te:pe.getComi()){
                      
                     ArrayList<Comidas> comida = comidao.obtenerTodos();
                     for(Comidas pi:comida)
                     {
                       if(pi.getIdComidas() == te.getIdComidas())
                       {
                           Com3+=pi.getDescripcion()+" ";
                       }
                     }
                     te.setDetalle(Com3);
                     Com3="";
                  }
               } 
               logger.info("Se pidio, encontrar todos los pedidos de un rut("+rut+")");
               sesion.setAttribute("TodoLosPedidos", pedi);
               sesion.setAttribute("nombre", pedi);
            }
            else
            {
              sesion.setAttribute("TodoLosPedidos", null);
              String msgAlerta="No se encontrado en la BDD.";
              sesion.setAttribute("MensajeAlerta", msgAlerta);
            }
        
        }
        catch(Exception ex)
        {
          String msg = ex.getMessage();
          sesion.setAttribute("Mensaje", msg);
          logger.error(msg);
        } 
        despachador.forward(request, response);
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
