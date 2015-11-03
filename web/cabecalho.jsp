<%-- 
    Document   : cabecalho
    Created on : 11/10/2015, 12:37:49
    Author     : lubuntu
--%>

<%@page import="model.beans.AtuadorBean"%>
<%@page import="model.beans.AmbienteBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <header id="heading">
                    <h1>Safe and Smart<span class="slogan">&nbsp;HOUSE</span></h1>
                </header>            
                <nav>
                    <ul class="hnavbar">
<%

                        List listaAmbientesBean = (List) request.getAttribute("listaAmbientes");
                        String strAmb, comando, operacao;
                        for(Object objAmb : listaAmbientesBean){
                            AmbienteBean ambiente = (AmbienteBean) objAmb;
%>
                        <li>
                            <a><%=ambiente.getDescricaoAmbiente()%></a>
                                <ul>
<%                      
                                List listaAtuadoresBean = (List) request.getAttribute("listaAtuadores");
                                for(Object objAtuador : listaAtuadoresBean){
                                    AtuadorBean atuador = (AtuadorBean) objAtuador;
                                    comando = ambiente.getCodigoAmbiente() + atuador.getCodigoAtuador();
                                    if (atuador.getAmbiente().getCodigoAmbiente().equals(ambiente.getCodigoAmbiente())
                                        && atuador.getSensor() == null){
                                        comando += atuador.getComando() + atuador.getPinoArduino();                       
%>
                                    <li><a href="ClienteWebServlet?comando=<%=comando%>">
                                            <%=atuador.getDescricaoAtuador()%></a></li>
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
