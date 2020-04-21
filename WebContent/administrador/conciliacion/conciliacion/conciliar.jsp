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

 <%@ include file = "../../../complementos/nav2.jsp" %>
	<div class="container-lg">
			<form name="formulario" id="formulario">
				<input type="hidden" name="id" id="id" />
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Realizar conciliación</h1>
				</div>
				<div class="container">
					<br>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; &nbsp;Banco</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="banco"
								required="required">
								<option value="1">Estado</option>
							</select>
						</div>
					</div>
 					<!--<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; &nbsp;N° Cuenta</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="cuenta"
								required="required">
							</select>
						</div>
					</div>  -->
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Elija año</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="anio"
								required="required">

							</select>
						</div>
					</div>

					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Elija mes</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="fecha"
								required="required">
								<option value="1">Enero</option>
								<option value="2">Febrero</option>
								<option value="3">Marzo</option>
								<option value="4">Abril</option>
								<option value="5">Mayo</option>
								<option value="6">Junio</option>
								<option value="7">Julio</option>
								<option value="8">Agosto</option>
								<option value="9">Septiembre</option>
								<option value="10">Octubre</option>
								<option value="11">Novimebre</option>
								<option value="12">Diciembre</option>
							</select>
						</div>
					</div>
					<br> <br>

					<div class="row">
						<div class="col-xs-6 col-md-2">
							<button class=" btt btn btn-primary btn-lg btn-block"
								type="button" onclick="conciliar()">Conciliar</button>
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
		$("#banco").select2();
		//$("#cuenta").select2();
		$("#fecha").select2();
		$("#anio").select2();
		ComboAnio();
	});

	function ComboAnio() {
		var str="";
		var n = (new Date()).getFullYear();

		for (var i = n; i <= (n + 5); i++) {
			str += "<option value="+i+">" + i + "</option>";
		}
		document.getElementById("anio").innerHTML = str;
		fechaI = n + "-01-01 00:00:00";
		fechaF = n + "-01-31 23:59:59";
	};

	$('#anio').on('change', function() {
		anio = document.getElementById("anio").value;
		switch (document.getElementById("fecha").value) {
		case "1":
			fechaI = anio + "-01-01 00:00:00";
			fechaF = anio + "-01-31 23:59:59";
			break;
		case "2":
			fechaI = anio + "-02-01 00:00:00";
			fechaF = anio + "-02-29 23:59:59";
			break;
		case "3":
			fechaI = anio + "-03-01 00:00:00";
			fechaF = anio + "-03-31 23:59:59";
			break;
		case "4":
			fechaI = anio + "-04-01 00:00:00";
			fechaF = anio + "-04-31 23:59:59";
			break;
		case "5":
			fechaI = anio + "-05-01 00:00:00";
			fechaF = anio + "-05-31 23:59:59";
			break;
		case "6":
			fechaI = anio + "-06-01 00:00:00";
			fechaF = anio + "-06-31 23:59:59";
			break;
		case "7":
			fechaI = anio + "-07-01 00:00:00";
			fechaF = anio + "-07-31 23:59:59";
			break;
		case "8":
			fechaI = anio + "-08-01 00:00:00";
			fechaF = anio + "-08-31 23:59:59";
			break;
		case "9":
			fechaI = anio + "-09-01 00:00:00";
			fechaF = anio + "-09-31 23:59:59";
			break;
		case "10":
			fechaI = anio + "-10-01 00:00:00";
			fechaF = anio + "-10-31 23:59:59";
			break;
		case "11":
			fechaI = anio + "-11-01 00:00:00";
			fechaF = anio + "-11-31 23:59:59";
			break;
		case "12":
			fechaI = anio + "-12-01 00:00:00";
			fechaF = anio + "-12-31 23:59:59";
			break;
		default:
			break;
		}
	});

	$('#fecha').on('change', function() {
		anio = document.getElementById("anio").value;
		switch (document.getElementById("fecha").value) {
		case "1":
			fechaI = anio + "-01-01 00:00:00";
			fechaF = anio + "-01-31 23:59:59";
			break;
		case "2":
			fechaI = anio + "-02-01 00:00:00";
			fechaF = anio + "-02-29 23:59:59";
			break;
		case "3":
			fechaI = anio + "-03-01 00:00:00";
			fechaF = anio + "-03-31 23:59:59";
			break;
		case "4":
			fechaI = anio + "-04-01 00:00:00";
			fechaF = anio + "-04-31 23:59:59";
			break;
		case "5":
			fechaI = anio + "-05-01 00:00:00";
			fechaF = anio + "-05-31 23:59:59";
			break;
		case "6":
			fechaI = anio + "-06-01 00:00:00";
			fechaF = anio + "-06-31 23:59:59";
			break;
		case "7":
			fechaI = anio + "-07-01 00:00:00";
			fechaF = anio + "-07-31 23:59:59";
			break;
		case "8":
			fechaI = anio + "-08-01 00:00:00";
			fechaF = anio + "-08-31 23:59:59";
			break;
		case "9":
			fechaI = anio + "-09-01 00:00:00";
			fechaF = anio + "-09-31 23:59:59";
			break;
		case "10":
			fechaI = anio + "-10-01 00:00:00";
			fechaF = anio + "-10-31 23:59:59";
			break;
		case "11":
			fechaI = anio + "-11-01 00:00:00";
			fechaF = anio + "-11-31 23:59:59";
			break;
		case "12":
			fechaI = anio + "-12-01 00:00:00";
			fechaF = anio + "-12-31 23:59:59";
			break;
		default:
			break;
		}
	})
	
	
	function conciliar() {

		var submitJson = {
			fechaI : fechaI,
			fechaF : fechaF,
			idBanco : document.getElementById("banco").value
		//	idCuenta : document.getElementById("cuenta").value
		}

		if (confirm('¿Seguro que desea conciliar los movimientos en esta fecha?')) {
			$.post("/conciliacionBancaria/rest-services/private/movimiento/getAllLista",
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							alert('Se realizo exitosamente el proceso de conciliación');
							location.href = "index.jsp";
						} else if (data == 'FAIL'){
							alert('No se encontraron registros asociados a las selecciones');
						}
					}).fail(function(jqxhr, settings, ex) {
						alert('No se pudo realizar la operación ' + ex);
					});
		}
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);
	
	$.post('/conciliacionBancaria/rest-services/private/banco/getLista', JSON
			.stringify(),
			function(res, code) {
				var str;
				for (var i = 0, len = res.length; i < len; i++) {
					str += "<option value="+res[i].id+">" + res[i].nombre
							+ "</option>";
				}
				document.getElementById("banco").innerHTML = str;
			}, "json");

	$('#banco')
			.on(
					'change',
					function() {
						var submitJson = {
							idBanco : document.getElementById("banco").value
						}

						$
								.post(
										'/conciliacionBancaria/rest-services/private/cuenta/getByIdBanco',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].numCuenta
														+ "</option>";
											}
										//	document.getElementById("cuenta").innerHTML = str;
										}, "json");
					});

	$("#banco").trigger('change');
	$("#anio").trigger('change');
	$("#fecha").trigger('change');
</script>
</html>