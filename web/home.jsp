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
<%@page import="model.beans.EquipamentoBean"%>
<%@page import="model.beans.AmbienteBean"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Free Liquid Web Template #001 - Responsive Web Design</title>
        <meta charset="UTF-8"><!-- Remove if defined in .htaccess -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/estilosHome.css" type="text/css" >
    </head>
    <body onload="window.scrollTo(0, 1);">
        <header id="heading">
            <h1>Safe and Smart<span class="slogan">&nbsp;HOUSE</span></h1>
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
                <li><a><%=ambiente.getDescricaoAmbiente()%></a>
                    <ul>
<%                  List listaEquipamentosBean = (List) sessao.getAttribute("listaEquipamentos");
                    for(Object objEqui : listaEquipamentosBean){
                        EquipamentoBean equipamento = (EquipamentoBean) objEqui;
                        comando = ambiente.getCodigoAmbiente() + equipamento.getCodigoEquipamento();
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
                        <li><a href="ClienteWebServlet?comando=<%=comando%>"><%=operacao%><%=equipamento.getDescricaoEquipamento()%></a></li>
<%
                        }
                    }
%>
                   </ul>
                </li>  
<%                  
                }
%>
            </ul>
        </nav>
    </body>
</html>