<%-- 
    Document   : pedidosMisPedidos
    Created on : nov 30, 2015, 4:16:16 a.m.
    Author     : miguelA
--%>

<%@page import="Modelo.DTO.DetallePedido"%>
<%@page import="Modelo.DTO.PedidosLista"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    HttpSession sesion = request.getSession();

    ArrayList<PedidosLista> pedi = (ArrayList<PedidosLista>)sesion.getAttribute("TodoLosPedidos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Pedidos</title>
        <style>
            table {border-collapse: collapse;}
            td {border: #ff9933 solid 2px;}
            th {border: orange solid 2px;}
            a:hover {color: #cc0000;}
            a {color: darkorange;}
            #menu {width: 22%; float: left; height: 100%; margin-left: 20px ;}
            .contenido {width: 76%; height: 100% ;float: right;}
            .Titulo {text-align: center;}
            .Titulo u {font-size: 2.5em; color: #391313;}
            .button {display:block; width: 100px;}
            /* Sortable tables */
            table.sortable thead {
                background-color:#ffd580;
                font-weight: bold;
                cursor: default;
            }
            tbody tr:nth-child(odd) {
            background-color: #ffeecc;
            }
        </style>
        <script>
            function justNumbers(e)
            {
            var keynum = window.event ? window.event.keyCode : e.which;
            if ((keynum === 8) || (keynum === 46))
            return true;

            return /\d/.test(String.fromCharCode(keynum));
            }
        </script>
        <script src="Scripts/sorttable.js"></script>
    </head>
    <body>
        <br/>
        <div id="menu">
          <img src="Imagenes/logo.png" width="220" height="120" alt="logo"/>
          <br/>
          <br/>
          <nav>
                  <a href="pedidosHome.jsp">Home</a>
                  <br/>
                  <a href="#">Mis Pedidos</a>
          </nav>
          <br/>
          <br/>
          <br/>
          <br/>
        </div>
            
        <div class="contenido">
            
            <br/>
            <br/>
            <br/>
            <br/>
            Busca tus ultimos pedidos y vuelve a solicitarlos inmediato.
            <br/>
            <br/>
     
            <form action="ServletBuscar" method="POST">
            Rut: <input type="text" name="rut" value="" min="8" onkeypress="return justNumbers(event);" max="10" size="50" style="margin-left: 29px" required="1" title="Ingrese rut sin guion, ni punto." />
            <input type="submit" value="Buscar" />
            </form>
            <br/>
            <br/>
        </div>
        
        <div class="contenido">
            <table width="600" class="sortable">
                <thead>
                    <tr>
                        <th width="300">Producto</th>
                        <th width="100">Valor</th>
                        <th width="50"></th>
                    </tr>
                </thead>
                <tbody>
                    <!--Colocar For o un if o un while-->
                    <% String pro="";
                      if(pedi!=null){ for(PedidosLista ped1:pedi){%>
                    <tr>
            
                        <td width="300" ><% for(int i = 0; i < ped1.getComi().size(); i++) { 
                                            {%> <%= ped1.getComi().get(i).getDetalle() %> <% if(i+1<ped1.getComi().size()){%>+<%} %> <%}}%></td>
                        <td width="100" align="center" > $<%= ped1.getValor() %></td>
                        <td width="50" ALIGN=CENTER><a href="ServletPedir?idP=<%= ped1.getIdPedidos() %>"> <img src="Imagenes/pedir.png" width="60" height="30" alt="minu"/> </a></td>
                        
                    </tr>
                    <%}}%>
                </tbody>
            </table>
        </div>
        
    </body>
</html>
