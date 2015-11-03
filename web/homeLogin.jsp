<%-- 
    Código adaptado do original disponível em http://codepen.io/Tushkiz/pen/xqfsy
    Document   : homeLogin
    Created on : 08/10/2015, 09:36:05
    Author     : Walter Luiz de Camargo
--%>

<%@page import="model.beans.MoradorBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
        <link href="css/estilosHomeLogin.css" rel="stylesheet" type="text/css" />
        <title>Login SSHouse</title>
    </head>
    <body onload="window.scrollTo(0, 100);">
        
	<div class="login">
            <div class="login-screen">
                <div class="app-title">
                    <h1><span class="slogan">SS</span>&nbsp;House</h1>
                </div>
                <div class="erro">
                <% if (request.getAttribute("erro")!=null) {%>      
                <h2><%= request.getAttribute("erro") %></h2>
                <%}%>
                </div>
                <form class="login-form" method="post" action="LoginServlet">
                    <div class="control-group">
                    <input type="text" class="login-field" name="login" value="" placeholder="login" id="nomeLogin">
                    <label class="login-field-icon fui-user" for="nomeLogin"></label>
                    </div>

                    <div class="control-group">
                    <input type="password" class="login-field" name="senha" value="" placeholder="senha" id="senhaLogin">
                    <label class="login-field-icon fui-lock" for="senhaLogin"></label>
                    </div>

                    <input class="btn" type="submit" name="btLogin" value="Entrar"> 
                    <div><br></div>
                    <a href="AtualizaServlet">
                        <input class="btn" type="button" name="btCancelar" value="Cancelar">
                    </a>
                </form>
            </div>
	</div>
</body>
</html>
