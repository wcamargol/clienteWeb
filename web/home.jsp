<%--
Código adaptado do original disponível em http://www.freeliquidtemplates.com/001.zip/ e adaptado 
para jsp.
--%>

<%@page import="model.beans.MoradorBean"%>
<%@page import="model.beans.AtuadorBean"%>
<%@page import="model.beans.AmbienteBean"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <title>SSHouse</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/estilosHome.css" type="text/css" >
    </head>
    <body onload="window.scrollTo(0, 1);">
        <div id="pagina">
            <div id="cabecalho">
                <%@include file="cabecalho.jsp" %>
            </div>
            <div id="conteudo">
                <%@include file="operacoes.jsp" %>
                <%@include file="eventos.jsp" %>
            </div>
            <div id="rodape">
                <p><br></p>
            </div>
        </div>
    </body>
</html>