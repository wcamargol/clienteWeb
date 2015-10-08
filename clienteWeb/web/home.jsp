<%--
********************************************************************************

    Document   : home
    Created on : 09/09/2015, 06:46:55
    Author     : Walter Luiz de Camargo - 434094

********************************************************************************

Código adaptado do original disponível em http://www.freeliquidtemplates.com/001.zip/ e adaptado 
para jsp.

Esta página permitira o acesso ao sistema de automação Safe and Smart House

--%>

<%@page import="model.beans.MoradorBean"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%
String comando,operacao, estado;
%>

<!DOCTYPE html>
<html>
    <head>
        <title>SSHouse</title>
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
        <link href="css/estilosHome.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload="window.scrollTo(0, 1);">
        <header id="heading">
            <h1><span class="slogan">SAFE&nbsp;and&nbsp;SMART</span>&nbsp;House</h1>
        </header>
        <%
            HttpSession sessao = request.getSession(true);
            MoradorBean moradorBean = (MoradorBean) sessao.getAttribute("loginSSHouse");
        %>
        bem vindo <%= moradorBean.getNome()%>
        <nav>
        <ul class="hnavbar">
            <li><a href="#">Sala</a>
                <ul>
                    <li><a href="ClienteWebServlet?comando=">Ilumina&ccedil;&atilde;o</a></li>                     
                    <li><a href="#heading">Restructure</a></li>
                    <li><a href="#heading">Drop down</a></li>
                </ul>
            <!--/li>         
            <li><a href="http://www.freeliquidtemplates.com/001.zip">Download It</a></li>
            <li><a href="#heading">Contact</a>
                <ul>
                    <li><a href="#heading">About Us</a></li>
                    <li><a href="#heading">Form</a></li>
                </ul>
            </li>-->
        </ul>
        </nav>
    </body>
</html>