<%-- 
    Document   : pedidosHome
    Created on : nov 29, 2015, 11:17:44 p.m.
    Author     : miguelA
--%>
<%@page import="Modelo.DTO.ComidaLista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.DTO.Comidas"%>
<%@page import="Modelo.DAO.ComidaDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
    
    String agrandar,llevar,tipo;
    
    agrandar = (String)sesion.getAttribute("agrandar");
    llevar = (String)sesion.getAttribute("llevar");
    tipo = (String)sesion.getAttribute("tipo1");
    
    if(tipo==null)
    tipo = "Efectivo";
    else
    {
      tipo = (String)sesion.getAttribute("tipo1");
    }   
    
    ComidaDAO comidao = new ComidaDAO();
    
    ArrayList<Comidas> comida = comidao.obtenerTodos();
    
    boolean comidalistaLLena = false;
    ArrayList<ComidaLista> comida1 = (ArrayList<ComidaLista>)sesion.getAttribute("ListaComida");
    if(comida1!=null)
    comidalistaLLena=true;
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
        <form action="ServletEnvios" method="POST" autocomplete="off">
            
          <div id="menu">
          <img src="Imagenes/logo.png" width="220" height="120" alt="logo"/>
          <br/>
          <br/>
          <nav>
                  <a href="#">Home</a>
                  <br/>
                  <a href="pedidosMisPedidos.jsp">Mis Pedidos</a>
          </nav>
          <br/>
          <br/>
          <br/>
          <br/>
          <%if(agrandar!=null){%>
          <input type="checkbox" name="Agrandar" value="true" checked="checked" />
          <%}else{%>
          <input type="checkbox" name="Agrandar" value="true"  />
          <%}%>
          Agrandar Bebida y Papas
          <br/>
          <%if(llevar!=null){%>
          <input type="checkbox" name="Llevar" value="true" checked="checked" />
          <%}else{%>
          <input type="checkbox" name="Llevar" value="true" />
          <%}%>
          Pedido para llevar
          <br/>
          <br/>
          <br/>
          <strong>Medios de Pago</strong>
          <br/>
          <br/>
          <%if(tipo.equals("Efectivo")){%>
          <input type="radio" name="dam" value="Efectivo" checked="checked" />Efectivo
          <br/>
          <input type="radio" name="dam" value="Tarjeta" />Tarjeta debito / credito
          <%}else{%>
          <input type="radio" name="dam" value="Efectivo"  />Efectivo
          <br/>
          <input type="radio" name="dam" value="Tarjeta" checked="checked" />Tarjeta debito / credito
          <%}%>
          </div>
         
         
        <div class="contenido">
            <a href="ayuda.jsp" style="float: right; margin-right: 20px">Ayuda</a>
            <br/>
         <br/>
         <br/>
          <br/>
            Bienvenido a Jhon Master, aqui usted encontrará los mejores productos
            <br/>
            <br/>
            Indíquenos su Nombre y Rut para identificarla en el pedido
            <br/>
            <br/>
            Nombre: <input type="text" name="nombre" value="${nombre1}" size="50" max="25" required="1" title="Ingrese un nombre" />
            <br/>
            Rut: <input type="text" name="rut" min="8" value="${rut}" size="50" max="10" style="margin-left: 29px" onkeypress="return justNumbers(event);" required="2" title="Ingrese rut sin guion, ni punto." />
            <br/>
            <br/>
            <br/>
            Seleccione un producto o combo, indíque su cantidad y agregue al pedido
            <br/>
            
            <!--Colocar while-->
            
            <select name="producto" >
                <% if(!comida.isEmpty()){ for(Comidas comidal:comida){%>
                <option value="<%= comidal.getIdComidas() %>"><%= comidal.getDescripcion() %></option>
                <%}}%>
            </select>
            
            <input type="submit" value="agregar" name="btnAgregar" formnovalidate="formnovalidate"/>
            <br/>
        </div>
        
        
        <div class="contenido">
            <table width="600" class="sortable">
                <thead>
                    <tr>
                        <th width="300">Producto</th>
                        <th width="100">Cantidad</th>
                        <th width="50"></th>
                    </tr>
                </thead>
                <tbody>
                    <!--Colocar For o un if o un while-->
                    <% if(comidalistaLLena){ for(ComidaLista comidaL:comida1){ if(comidaL.getCantidad()>0){%>
                    <tr>
                        <td width="300" ><%= comidaL.getDetalle() %></td>
                        <td align="center" width="100" ><%= comidaL.getCantidad() %></td>
                        <td width="50" ALIGN=CENTER ><a href="ServletEliminar?id=<%= comidaL.getIdComida() %>"> <img src="Imagenes/minu.png" width="20" height="20" alt="minu"/> </a></td>
                    </tr>
                    <%}}}%>
                </tbody>
            </table>
        </div>
                    
        <div class="contenido">
            <div style="float: left">
                <strong><u>Total a pagar $${Total}</u></strong>
            </div>
            <div style="float: right; margin-right: 425px">
                <input type="image" src="Imagenes/enviar.png" onsubmit="submit();" name="btnAgregar" value="Envio" width="400" height="100" alt="enviar"/>
            </div>
        </div>
            
        </form>
    </body>
</html>
