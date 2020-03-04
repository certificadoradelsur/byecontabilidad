<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
$(document).ready(function () {
	var submitJson = {
		 id: document.getElementById("idUsuario").value
	}
    $.post('/byeContabilidad/rest-services/private/usuario/getById', JSON.stringify(submitJson)).done(function(data) {
		if(data.perfil == "ADMIN"){			
			location.href="/byeContabilidad/administrador/index.jsp";			
		}else if(data.perfil == "USER") {
			location.href="/byeContabilidad/usuario/index.jsp";
		}
		
		})
		.fail(function(jqxhr, settings, ex) { 
		    	alert('No se pudo obtener usuario ' + ex); 
	});
});

</script>
</html>