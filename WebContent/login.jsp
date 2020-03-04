<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Inicio Bye Contabilidad Certificadora del Sur</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="/byeContabilidad/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/byeContabilidad/css/signin.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script type="text/javascript">
    $( document ).ready(function() {
    	document.getElementById('year').innerHTML ='&copy;' + new Date().getFullYear();
    });
	
	<%
	if (request.getParameter("errorLogin") != null) {
        
        if (request.getParameter("errorLogin").equals("true")){
    %>    	
    		alert("Credenciales incorrectas");
    <%   
        }
     }
	 %>
    
    </script>
  </head>

  <body class="text-center">
    <form method="POST" action="j_security_check" name="loginForm" id="loginForm">
      <img class="mb-4" src="/byeContabilidad/images/LOGO.jpg" alt="" width="300" height="72">
      <label for="inputEmail" class="sr-only">Usuario</label>
      
      <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Usuario" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      
      <input type="password" id="j_password" name="j_password" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Recordarme
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
      <p class="mt-5 mb-3 text-muted" id='year'><p>
    </form>
  </body>
</html>