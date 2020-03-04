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


<title>Bye Contabilidad</title>
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
		
		
	</div>

	<div class="main">
		<div class="container">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Cambiar clave</h1>
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
						<label for="colFormLabel" class="col-sm-2 col-form-label">Password</label>
						<input type="password" id="password" name="password" class="in"
							placeholder="Ingrese password" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Password
						</label> <input type="password" id="password2" name="password2" class="in"
							placeholder="Repita la password" />
					</div>
					<div class="row">
						<div class="col-xs-6 col-md-2">
							<button class=" btt btn btn-primary btn-lg btn-block"
								type="button" onclick="cambiarClave()">Guardar</button>
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
	
	var datos = {id: "<%=request.getParameter("id")%>", };
			$.post('/byeContabilidad/rest-services/private/usuario/getById',JSON.stringify(datos))
								.done(function(data) {
											document.getElementById("id").value = data.id;
										})
								.fail(
										function(jqxhr, settings, ex) {
											alert('No se pudo modificar la clave'
													+ ex);
										});
					});

	function cambiarClave() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Los campos deben estar llenos");
			return;

		}
		if (document.getElementById("password").value != document
				.getElementById("password2").value) {
			alert('Las password deben coincidir');
			return;
		}
		var submitJson = {
			id : document.getElementById("id").value,
			password : document.getElementById("password").value
		}
		$
				.post('/byeContabilidad/rest-services/private/usuario/updatePass',
						JSON.stringify(submitJson)).done(function(data) {
					if (data == 'OK') {
						alert('Se guardaron la nueva clave');
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