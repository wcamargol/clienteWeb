<%@page import="model.beans.OperacaoBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

                <div class="tabela">
<%        
                    List listaOperacoesBean = (List) request.getAttribute("listaOperacoes");
                    List listaEventosbean = (List) request.getAttribute("listaEventos");
                    String dataO, nome;
                    boolean cinzaO = false;
%>  
                    <table class="tituloTabela">                
                        <tr>
                            <td class="coluna1">Usu&aacute;rio</td>
                            <td class="coluna2">Data</td>
                            <td class="coluna3">Hora</td>
                            <td class="coluna4">Opera&ccedil;&atilde;o</td>
                        </tr>
                    </table>
                    <div class="conteudoTabela">
                        <table>                    
<%                                  
                            if (listaOperacoesBean != null){
                                for(Object obj : listaOperacoesBean){
                                    OperacaoBean operacaoBean = (OperacaoBean) obj;
                                    dataO = operacaoBean.getDataOperacao().toString().substring(8)
                                        +"/"+operacaoBean.getDataOperacao().toString().substring(5,7)
                                        +"/"+operacaoBean.getDataOperacao().toString().substring(0,4);
                                    nome = operacaoBean.getId().getMorador().getNome();
%>
                            <tr<%=cinzaO?" id=\"cinza\"":""%>>
                                <td class="coluna1"><%=nome.substring(0, nome.indexOf(" "))%></td>
                                <td class="coluna2"><%=dataO%></td>
                                <td class="coluna3"><%=operacaoBean.getHoraOperacao()%></td>
                                <td class="coluna4"><%=operacaoBean.getDescricaoOperacao()%></td>
                            </tr>
<%            
                                    cinzaO= !cinzaO;
                                }
                            }
%>                            
                        </table>    
                    </div>
                </div>