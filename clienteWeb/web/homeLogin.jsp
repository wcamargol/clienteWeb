<%-- 
    Código adaptado do original disponível em http://codepen.io/Tushkiz/pen/xqfsy
    Document   : homeLogin
    Created on : 08/10/2015, 09:36:05
    Author     : Walter Luiz de Camargo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
        <link href="css/estilosHomeLogin.css" rel="stylesheet" type="text/css" />
        <title>Login SSHouse</title>
    </head>
    <body onload="window.scrollTo(0, 1);">
	<div class="login">
		<div class="login-screen">
			<div class="app-title">
                                <h1><span class="slogan">SS</span>&nbsp;House</h1>
			</div>
			<form class="login-form" method="post" action="ServletLogin?pagina=home.jsp">
				<div class="control-group">
				<input type="text" class="login-field" value="" placeholder="login" id="nomeLogin">
				<label class="login-field-icon fui-user" for="nomeLogin"></label>
				</div>

				<div class="control-group">
				<input type="password" class="login-field" value="" placeholder="senha" id="senhaLogin">
				<label class="login-field-icon fui-lock" for="senhaLogin"></label>
				</div>

				<input class="btn" type="submit" name="btLogin" value="Entrar">                                
				<a class="login-link" href="#"></a>
			</form>
		</div>
	</div>
</body>
</html>
