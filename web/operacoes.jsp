<%-- 
    Document   : Operacoes
    Created on : 11/10/2015, 11:05:54
    Author     : lubuntu
--%>

<%@page import="model.beans.OperacaoBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id=operacoes>
<%        
            List listaOperacoesBean = (List) request.getAttribute("listaOperacoes");
%>  
            <table>
                <tr>
                    <td>Usu&aacute;rio</td>
                    <td>Data</td>
                    <td>Hora</td>
                    <td>Opera&ccedil;&atilde;o</td>
                </tr>
<%        
                for(Object obj : listaOperacoesBean){
                    //OperacaoBean operacaoBean = (OperacaoBean) obj;
%>
                <tr>
                    
                    <td><%=obj%></td>
                </tr>
<%            
                }    
%>
            </table>
        </div>
        <div id="eventos">
        <table>
            <tr>
                <td>Usu&aacute;rio</td>
                <td>Data</td>
                <td>Hora</td>
                <td>Opera&ccedil;&atilde;o</td>
            </tr>
<%        
            List listaOperacoesBean1 = (List) request.getAttribute("listaOperacoes");
            for(Object obj : listaOperacoesBean1){
                OperacaoBean operacaoBean = (OperacaoBean) obj;
                
%>
            <tr>
                <td><%=operacaoBean.getId().getMorador().getLogin()%></td>
                <td><%=operacaoBean.getHoraOperacao()%></td>
            </tr>
<%            
            }    
%>
    </table>
    </div>
    </body>
</html>
