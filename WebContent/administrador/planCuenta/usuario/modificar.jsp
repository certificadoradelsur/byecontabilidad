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
					<h1 class="h2">Modificar usuario</h1>
				</div>
				<div class="container">
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Id</label>
						<input type="text" id="id" name="id" class="in"
							placeholder="Ingrese id" readonly="readonly" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Email</label>
						<input type="text" id="email" name="email" class="in"
							placeholder="Ingrese email" />
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">&nbsp;
							Perfil</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="perfil">
								<option value="USER">Usuario</option>
								<option value="ADMIN">Administrador</option>
							</select>
						</div>
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">&nbsp;
							Estado</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="estado">
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
</body>
<script type="text/javascript">
$(document).ready(function () {

	var submitjson = {id:"<%=request.getParameter("id")%>" ,};
	
						$.post('/byeContabilidad/rest-services/private/usuario/getById',
										JSON.stringify(submitjson)).done(function(data) {
											document.getElementById("id").value = data.id;
											document.getElementById("email").value = data.email;
											document.getElementById("perfil").value = data.perfil;
											document.getElementById("estado").value = data.activo;
										}).fail(function(jqxhr, settings, ex) {
											alert('No se pudo modificar el usuario '
													+ ex);});
						
						$("#estado").select2();
						$("#perfil").select2();
						
						
					});

	function modificar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Los campos deben estar llenos");
			return;
		}
		var submitJson = {
			id : document.getElementById("id").value,
			email : document.getElementById("email").value,
			activo : document.getElementById("estado").value,
			perfil : document.getElementById("perfil").value
		}
		$.post('/byeContabilidad/rest-services/private/usuario/update',
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