<%-- 
    Document   : Operacoes
    Created on : 11/10/2015, 11:05:54
    Author     : lubuntu
--%>

<%@page import="model.beans.EventoBean"%>
<%@page import="model.beans.OperacaoBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

                <div class="tabela">
<%        
                    List listaOperacoesBean = (List) request.getAttribute("listaOperacoes");
                    List listaEventosBean = (List) request.getAttribute("listaEventos");
                    String data, nome;
                    boolean cinza = false;
%>  
                    <table class="tituloTabela">                
                        <tr>
                            <td class="coluna1">Evento</td>
                            <td class="coluna2">Data</td>
                            <td class="coluna3">Hora</td>
                        </tr>
                    </table>
                    <div class="conteudoTabela">
                        <table>                    
<%              
                            if (listaEventosBean != null){
                                for(Object obj : listaEventosBean){
                                    EventoBean eventoBean = (EventoBean) obj;
                                    data = eventoBean.getDataEvento().toString().substring(8)
                                        +"/"+eventoBean.getDataEvento().toString().substring(5,7)
                                        +"/"+eventoBean.getDataEvento().toString().substring(0,4);
%>
                                    <tr>
                                        <td class="coluna1"><%=eventoBean.getId().getAlarme().getDescricaoAlarme()%></td>
                                        <td class="coluna2"><%=data%>teste</td>
                                        <td class="coluna3"><%=eventoBean.getHoraEvento()%></td>
                                    </tr>
<%            
                                    cinza = !cinza;
                                }
                            } else {
                                for(int i=0; i < 14;i++){                            
%>
                                    <tr>
                                        <td class="coluna1">teste</td>
                                        <td class="coluna2">teste</td>
                                        <td class="coluna3">teste</td>
                                        <td class="coluna4">teste</td>
                                    </tr>
<%            
                                    cinza = !cinza;
                                }                        
                            }
%>
                        </table>    
                    </div>
                </div>