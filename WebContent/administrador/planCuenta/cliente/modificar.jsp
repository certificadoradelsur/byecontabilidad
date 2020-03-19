<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>ByeContabilidad</title>
<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../css/dashboard.css" rel="stylesheet">
<link href="../../css/micss.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" type="text/css" />
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.css"
	rel="stylesheet" type="text/css" />

<!-- Bootstrap 4.4.1-->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- Select 2-->
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.0.12/dist/css/select2.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.0.12/dist/js/select2.min.js"></script>

<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.js"
	type="text/javascript">
	$('.dropdown-toggle').dropdown()
</script>

</head>
<body>


 <%@ include file = "../../../complementos/nav.jsp" %>
	<div class="container-lg">
		<div class="container">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Modificar cliente</h1>
				</div>
				<div class="container">
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Rut</label>
						<input type="text" id="rut" name="rut" class="in"
							placeholder="Ingrese el rut" required="required" />
					</div>
					 <div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Nombre</label>
						<input type="text" id="nombre" name="nombre" class="in"
							placeholder="Ingrese el nombre" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Ciudad</label>
						<input type="text" id="ciudad" name="ciudad" class="in"
							placeholder="Ingrese la ciudad" required="required" />
					</div>					
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Dirección</label>
						<input type="text" id="direccion" name="direccion" class="in"
							placeholder="Ingrese la dirección" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Giro</label>
						<input type="text" id="giro" name="giro" class="in"
							placeholder="Ingrese el giro" required="required" />
					</div>	
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Email</label>
						<input type="email" id="email" name="email" class="in"
							placeholder="Ingrese email" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Telefono</label>
						<input type="text" id="telefono" name="telefono" class="in"
							placeholder="Ingrese el telefono" required="required" />
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Empresa</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="empresa">
							</select>
						</div>
					</div>						
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">&nbsp;&nbsp;
							Estado</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="activo">
								<option value="true">Activo</option>
								<option value="false">Inactivo</option>
							</select>
						</div>
					</div>	
					<br><br>
					<div class="row">
						<div class="col-xs-6 col-md-2">
							<button class=" btt btn btn-primary btn-lg btn-block"
								type="button" onclick="modificar()">Modificar</button>
						</div>
						<div class="col-xs-6 col-md-2">
							<button class=" btt btn btn-primary btn-lg btn-block"
								type="button" id="back">Cancelar</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
		<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
$(document).ready(function () {

	$("#activo").select2();
	$("#empresa").select2();

	var submit = {id:"<%=request.getParameter("id")%>" ,};
	
	$.post('/byeContabilidad/rest-services/private/cliente/getById',
					JSON.stringify(submit)).done(function(data) {
						document.getElementById("rut").value = data.rut;
						document.getElementById("nombre").value = data.nombre;
						document.getElementById("ciudad").value = data.ciudad;
						document.getElementById("direccion").value = data.direccion;
						document.getElementById("email").value = data.email;
						document.getElementById("giro").value = data.giro;
						document.getElementById("telefono").value = data.telefono;
						document.getElementById("activo").value = data.activo;
						document.getElementById("empresa").value = data.idEmpresa;
						cargaEmpresa(data.idEmpresa);
						
 }).fail(function(jqxhr, settings, ex) {
						alert('No se pudo modificar el cliente '
								+ ex);});	
});
   

	function cargaEmpresa(idEmpresa) {
		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}

		$.post('/byeContabilidad/rest-services/private/empresa/getLista', JSON
				.stringify(submitJson), function(res, code) {
			var str;
			for (var i = 0, len = res.length; i < len; i++) {
				if (idEmpresa == res[i].id) {
					str += "<option value="+res[i].id+" selected>"
							+ res[i].razonSocial + "</option>";
				} else {
					str += "<option value="+res[i].id+">" + res[i].razonSocial
							+ "</option>";
				}
			}
			document.getElementById("empresa").innerHTML = str;
		}, "json");
	}

	function modificar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Los campos deben estar llenos");
			return;
		}

		var submitJson = {
			id : <%=request.getParameter("id")%>,
				rut : document.getElementById("rut").value,
				nombre : document.getElementById("nombre").value,
				ciudad : document.getElementById("ciudad").value,
				direccion : document.getElementById("direccion").value,
				giro : document.getElementById("giro").value,
				telefono : document.getElementById("telefono").value,
				email : document.getElementById("email").value,
				activo : document.getElementById("activo").value,
				idEmpresa : document.getElementById("empresa").value
		}
		
		$.post('/byeContabilidad/rest-services/private/cliente/update',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardaron los cambios');
				location.href = "index.jsp";
			} else {
				alert(data);
			}
		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo modificar ' + ex);
		});
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);
</script>
</html>