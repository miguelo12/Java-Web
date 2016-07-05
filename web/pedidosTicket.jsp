<%-- 
    Document   : pedidosTicket
    Created on : nov 30, 2015, 4:15:59 a.m.
    Author     : miguelA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        </style>
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
                  <a href="pedidosMisPedidos.jsp">Mis Pedidos</a>
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
            <br/>
            <br/>
            <br/>
                <u><strong style="margin-left: 230px">Muchas Gracias! ${nombre}</strong></u>
                <br/>
                <br/>
                <p style="margin-left: 210px">Tu Numero de pedido es: ${numpedido}</p>
                <u><strong style="margin-left: 220px">Monto de su pedido: $${Total}</strong></u>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <p style="margin-left: 100px">Por favor, pase por caja para que luego pueda esperar comodamente su pedido.</p>
        </div>
        
    </body>
</html>
