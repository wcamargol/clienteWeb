<%@page import="model.beans.EventoBean"%>
<%@page import="model.beans.OperacaoBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

                <div class="tabela">
<%        
                    List listaEventosBean = (List) request.getAttribute("listaEventos");
                    boolean cinzaE = false;
                    String dataE;
%>  
                    <table class="tituloTabela">                
                        <tr>
                            <td class="coluna11">Evento</td>
                            <td class="coluna2">Data</td>
                            <td class="coluna3">Hora</td>
                            <td class="coluna41">Local</td>
                        </tr>
                    </table>
                    <div class="conteudoTabela">
                        <table>                    
<%              
                            if (listaEventosBean != null){
                                for(Object obj : listaEventosBean){
                                    EventoBean eventoBean = (EventoBean) obj;
                                    dataE = eventoBean.getDataEvento().toString().substring(8)
                                        +"/"+eventoBean.getDataEvento().toString().substring(5,7)
                                        +"/"+eventoBean.getDataEvento().toString().substring(0,4);
                                    
%>
                            <tr<%=cinzaE ? " id=\"cinza\"":""%>>
                                <td class="coluna11"><%=eventoBean.getId().getAlarme().getDescricaoAlarme()%></td>
                                <td class="coluna2"><%=dataE%></td>
                                <td class="coluna3"><%=eventoBean.getHoraEvento()%></td>
                                <td class="coluna41"><%=eventoBean.getId().getSensor().getAmbiente().getDescricaoAmbiente()%></td>
                            </tr>
<%            
                                    cinzaE = !cinzaE;
                                }
                            }
%>                                    
                        </table>    
                    </div>
                </div>