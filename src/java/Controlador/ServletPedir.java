/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.ClienteDAO;
import Modelo.DAO.DetallePedidoDAO;
import Modelo.DAO.PedidosDAO;
import Modelo.DTO.DetallePedido;
import Modelo.DTO.Pedidos;
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
@WebServlet(name = "ServletPedir", urlPatterns = {"/ServletPedir"})
public class ServletPedir extends HttpServlet {

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
        
        RequestDispatcher despachadorEnvio = request.getRequestDispatcher("/pedidosTicket.jsp");
        HttpSession sesion = request.getSession();
         int idP;
         idP = Integer.parseInt(request.getParameter("idP").trim());
        
        try
        {
          PedidosDAO pedidodao = new PedidosDAO();
          ClienteDAO clien = new ClienteDAO();
          DetallePedidoDAO detapedido = new DetallePedidoDAO();
          ArrayList<PedidosLista> pedi = (ArrayList<PedidosLista>)sesion.getAttribute("TodoLosPedidos");
          
          for(PedidosLista pe:pedi)
          {
            if(pe.getIdPedidos()==idP)
            {
                Pedidos pedi1 = new Pedidos(pe.getIdCliente(), pe.getFecha(), pe.getAgrandar(), pe.getLlevar(), pe.getValor() , pe.getTipodepago());
                pedidodao.guardar(pedi1);
                
                int idpedido = pedidodao.Encontrar(pe.getIdCliente(), pe.getFecha(), pe.getValor());
                
                String idpe1 = ""+idpedido;
                String idpe = String.format("%04d", Integer.parseInt(idpe1.trim()));
                String name = clien.DarNombre(pe.getIdCliente()).trim();
                
                String Total = pe.getValor()+"";
                sesion.setAttribute("Total", Total);
                sesion.setAttribute("numpedido", idpe);
                sesion.setAttribute("nombre", name);
                
               for(DetallePedido co:pe.getComi())
               {
                  DetallePedido pedidooo = new DetallePedido(idpedido, co.getCantidad(),co.getIdComidas());
                  detapedido.guardar(pedidooo);
               }
            }
            
          }  
          
        }
        catch(Exception ex)
        {
          String msg = ex.getMessage();
          sesion.setAttribute("Mensaje", msg);
          logger.error(msg);
        } 
           
        despachadorEnvio.forward(request, response);
        
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
