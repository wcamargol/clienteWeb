<%-- 
    Document   : cabecalho
    Created on : 11/10/2015, 12:37:49
    Author     : lubuntu
--%>

<%@page import="model.beans.EquipamentoBean"%>
<%@page import="model.beans.AmbienteBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <header id="heading">
                    <h1>Safe and Smart<span class="slogan">&nbsp;HOUSE</span></h1>
                    <p><a href="LogoffServlet">Sair</a></p>
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
                                List listaEquipamentosBean = (List) request.getAttribute("listaEquipamentos");
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
                                    <li><a href="ClienteWebServlet?comando=<%=comando%>">
                                            <%=operacao%><%=equipamento.getDescricaoEquipamento()%></a></li>
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
