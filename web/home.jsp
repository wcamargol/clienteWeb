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

<%@page import="model.beans.AmbienteBean"%>
<%@page import="model.beans.EquipamentoBean"%>
<%@page import="java.util.List"%>
<%@page import="model.beans.MoradorBean"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <title>SSHouse</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
        <link href="css/estilosHome.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload="window.scrollTo(0, 1);">
        <header id="heading">
            <h1><span class="slogan">SAFE&nbsp;and&nbsp;SMART</span>&nbsp;House</h1>
        </header>
        <nav>
        <ul class="hnavbar">
            <%
                HttpSession sessao = request.getSession(true);
                List listaAmbienteBean = (List) sessao.getAttribute("listaAmbientes");
                String strAmb, comando, operacao;
                for(Object objAmb : listaAmbienteBean){
                    AmbienteBean ambiente = (AmbienteBean) objAmb;                    
            %>            
            <li><a href="#"><%=ambiente.getDescricaoAmbiente()%></a>
                <ul><% 
                        List listaEquipamentosBean = (List) sessao.getAttribute("listaEquipamentos");
                        for(Object objEqui : listaEquipamentosBean){
                            EquipamentoBean equipamento = (EquipamentoBean) objEqui;
                            comando = equipamento.getAmbiente().getCodigoAmbiente();
                            if (equipamento.getAmbiente().getCodigoAmbiente().equals(ambiente.getCodigoAmbiente())){
                                if (equipamento.getEstado().equals("L")){
                                   comando += "D";
                                   operacao = "Desligar ";
                                }else{
                                    comando += "L";
                                    operacao = "Ligar ";
                                }
                                comando += equipamento.getPinoArduino();
                    %>
                                <li><a href="ClienteWebServlet?comando=<%=comando%>">
                                            <%=operacao %><%=equipamento.getDescricaoEquipamento()%></a></li> 
                    <%      }
                        }                                                    
                    %>
                </ul>
            <!--/li>         
            <li><a href="http://www.freeliquidtemplates.com/001.zip">Download It</a></li>
            <li><a href="#heading">Contact</a>
                <ul>
                    <li><a href="#heading">About Us</a></li>
                    <li><a href="#heading">Form</a></li>
                </ul>
            </li>-->
            <%
                }
            %>
        </ul>
        </nav>
    </body>
</html>