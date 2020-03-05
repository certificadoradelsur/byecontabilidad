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

	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Certificadora
			del Sur</a>

		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="/byeContabilidad/logout.jsp"><img
					src="../../images/exit.ico" alt="Icono" /> Salir</a></li>
		</ul>
	</nav>

	<div class="sidenav">

		<a href="../usuario/index.jsp"><img src="../../images/user.ico"
			alt="Icono" />&nbsp;Usuarios </a> 
		<a href="../clasificacion/index.jsp"><img
			src="../../images/comprobante.ico" alt="Icono" />&nbsp;Clasificación </a>
		<a href="../cuentaContable/index.jsp"><img
			src="../../images/comprobante.ico" alt="Icono" />&nbsp;Cuenta </a>	


	</div>

	<div class="main">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Agregar cuenta contable</h1>
				</div>
				<div class="container">
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Glosa general</label>
						<input type="text" id="glosa" name="glosa" class="in"
							placeholder="Ingrese Glosa" required="required" />
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; Clase Cuenta</label>
						<div class="col-4">
							<select class="browser-default custom-select" id="claseCuenta">
							<option value="1">Activo</option>
							</select>
						</div>
					</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; Grupo Cuenta</label>
					<div class="col-4">
						<select class="browser-default custom-select" id="grupoCuenta">
						</select>
					</div>
				</div>
				<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Descripción</label>
						<textarea id="descripcion" class="col-sm-3" name="descripcion"
							class="in" placeholder="Ingrese descripción" required="required"></textarea>
					</div>

				<div class="row">
					<div class="col-sm-2">&nbsp;&nbsp; Seleccione</div>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="imputable">
							<label class="form-check-label" for="gridCheck1"> Imputable </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="analisis">
							<label class="form-check-label" for="gridCheck1"> Analisis </label>
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
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#claseCuenta").select2(),
		$("#grupoCuenta").select2();
		
		$.post('/byeContabilidad/rest-services/private/claseCuenta/getLista',
				function(res, code) {
					var str;
					for (var i = 0, len = res.length; i < len; i++) {
						str += "<option value="+res[i].id+">" + res[i].nombre
								+ "</option>";
					}
					document.getElementById("claseCuenta").innerHTML = str;
				}, "json");
	})
	
	$('#claseCuenta').on('change', function() {
						var submitJson = {
							idClaseCuenta : document.getElementById("claseCuenta").value
						}

						$.post('/byeContabilidad/rest-services/private/grupoCuenta/getByIdClaseCuenta',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].nombre
														+ "</option>";
											}
											document.getElementById("grupoCuenta").innerHTML = str;
										}, "json");
					});
	

	function guardar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		var submitJson = {
			idClaseCuenta : document.getElementById("claseCuenta").value,	
			idGrupoCuenta : document.getElementById("grupoCuenta").value,
			glosaGeneral : document.getElementById("glosa").value,
			descripcion : document.getElementById("descripcion").value,
			imputable:document.getElementById("imputable").checked,
			analisis :document.getElementById("analisis").checked    
		}

		$.post('/byeContabilidad/rest-services/private/cuentaContable/add',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardo la clasificación exitosamente');
				location.href = "index.jsp";
			} else {
				alert(data);
			}

		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo guardar la clasificación ' + ex);
		});
	}
	


	back.addEventListener("click", function() {
		window.history.back();
	}, false);
	
	$("#grupoCuenta").trigger('change');
	$("#claseCuenta").trigger('change');
</script>
</html>