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

	<%@ include file="../../../complementos/nav.jsp"%>
	<div class="container-lg">
		<div class="container">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Agregar usuario</h1>
				</div>
				<div class="container">
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Id</label>
						<input type="text" id="id" name="id" class="in"
							placeholder="Ingrese id" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Password</label>
						<input type="password" id="password" name="password" class="in"
							placeholder="Ingrese password" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Password
						</label> <input type="password" id="password2" name="password2" class="in"
							placeholder="Repita la password" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Email</label>
						<input type="email" id="email" name="email" class="in"
							placeholder="Ingrese email" required="required" />
					</div>
				    <div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Estado</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="estado">
								<option selected value="true">Activo</option>
								<option value="false">Inactivo</option>
							</select>
						</div>
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Perfil</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="perfil">
								<option selected value="ADMIN">Administrador</option>
								<option value="USER">Usuario</option>
							</select>
						</div>
					</div>
					<div class="row collapse" id="collapse">
					  <div class="col-sm-2">&nbsp;&nbsp; Empresas</div>
					<div class="col-sm-10" id="empresas">
						<div class="form-check">

						</div>
					</div>
					</div>
					<br>
					<br>
					<div class="row">
						<div class="col-xs-6 col-md-2">
							<button class=" btt btn btn-primary btn-lg btn-block"
								type="button" onclick="guardar()">Guardar</button>
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
	$(document).ready(function() {
		$("#estado").select2();
		$("#perfil").select2();
	})

	var list = [];
	var indice = 0;
	var listCheck = [];
	var ind = 0;
	
	function guardar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}
		if (document.getElementById("password").value != document
				.getElementById("password2").value) {
			alert('Las password deben coincidir');
			return;
		}

		var correo = document.getElementById("email").value

		if (!validar_email(correo)) {
			alert("El email debe ser valido");
			return;
		}
		
		for(var i = 0, len = list.length; i < len; i++){
			if (document.getElementById(list[i]).checked){
				listCheck[ind]=list[i];
				ind++
			}
		}

		var submitJson = {
			id : document.getElementById("id").value,
			password : document.getElementById("password").value,
			email : document.getElementById("email").value,
			activo : document.getElementById("estado").value,
			perfil : document.getElementById("perfil").value,
			idUsuario : document.getElementById("idUsuario").value,
			empresas : listCheck.map(function(value) {
				return {
					id : value
				}
			}),
		}

		$.post('/byeContabilidad/rest-services/private/usuario/add',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardo el usuario exitosamente');
				location.href = "index.jsp";
			} else {
				alert(data);
			}

		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo guardar el usuario ' + ex);
		});
	}

	$('#perfil').on('change',function() {
				if (document.getElementById("perfil").value=="USER") {
					cargaEmpresas();
					$('#collapse').collapse('show');
				} else if (document.getElementById("perfil").value=="ADMIN"){
					$('#collapse').collapse('hide');
					list = [];
					indice = 0;
				}
	});


	function cargaEmpresas() {
		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}
		$.post('/byeContabilidad/rest-services/private/empresa/getLista',JSON.stringify(submitJson),
						function(res, code) {
							var str = "";
							for (var i = 0, len = res.length; i < len; i++) {
								str += "<div class='form-check'><input class='form-check-input' type='checkbox' id='"+res[i].id+"'>"
								+ res[i].razonSocial+"</div>";
								
								list[indice]=res[i].id;
								indice++
							}
							document.getElementById("empresas").innerHTML = str;
						}, "json");
	}

	function validar_email(email) {
		var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		return regex.test(email) ? true : false;
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);
</script>
</html>