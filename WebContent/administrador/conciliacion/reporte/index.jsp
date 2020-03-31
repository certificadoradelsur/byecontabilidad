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
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" />


<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.js"
	type="text/javascript">
	$('.dropdown-toggle').dropdown()
</script>

</head>
<body>
<%@ include file = "../../../complementos/nav.jsp" %>
	<div class="container-lg">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Reportes</h1>
				</div>
				<input type="hidden" name="id" id="id" />
			</form>

			<div class="form-group">
				<div class="form-row">
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="desde">&nbsp;&nbsp;Desde</label>
						</div>
						<input type="date" id="filtro1" name="filtro1"
							placeholder="Filtrar por fecha" class="form-control" />
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="hasta">&nbsp; &nbsp;Hasta</label>
						</div>
						<input type="date" id="filtro2" name="filtro2"
							class="form-control" />
					</div>

					<div class="form-group col-md-2">
						<label for="banco">&nbsp;Banco</label> <select
							class="browser-default custom-select" id="banco"
							required="required">
							<option value="1">Estado</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label for="cuenta">&nbsp;N° Cuenta</label> <select
							class="browser-default custom-select" id="cuenta"
							required="required">
						</select>
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label>&nbsp;</label>
						</div>
						<button type="button" class="btn btn-primary" onclick="reporte()">Generar</button>
					</div>
				</div>
			</div>
		</div>


	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#banco").select2();
						$("#cuenta").select2();

						var submitJson = {
								idUsuario : document.getElementById("idUsuario").value
							}
						$
								.post(
										'/byeContabilidad/rest-services/private/banco/getLista',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].nombre
														+ "</option>";
											}
											document.getElementById("banco").innerHTML = str;
										}, "json");

						var submitJson = {
							idBanco : document.getElementById("banco").value,
							idUsuario : document.getElementById("idUsuario").value
						}

						$
								.post(
										'/byeContabilidad/rest-services/private/cuenta/getByIdBanco',
										JSON.stringify(submitJson),
										function(res, code) {
											var str = "<option>Seleccione cuenta</option>";
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].numCuenta
														+ "</option>";
											}
											document.getElementById("cuenta").innerHTML = str;
										}, "json");

					});

	$('#banco')
			.on(
					'change',
					function() {
						var submitJson = {
							idBanco : document.getElementById("banco").value,
							idUsuario : document.getElementById("idUsuario").value

						}

						$
								.post(
										'/byeContabilidad/rest-services/private/cuenta/getByIdBanco',
										JSON.stringify(submitJson),
										function(res, code) {
											var str = "<option>Seleccione cuenta</option>";
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].numCuenta
														+ "</option>";
											}
											document.getElementById("cuenta").innerHTML = str;
										}, "json");
					});

	function reporte() {

		if (document.getElementById('filtro1').value == ''
				|| document.getElementById('filtro2').value == '') {
			alert('Debe ingresar fecha inicio y fecha termino');
			return;
		}

		if ($('#cuenta option:selected').text() == 'Seleccione cuenta') {
			alert("Debe seleccionar una cuenta");
			return;
		}
		var fechaDesde = new Date(document.getElementById('filtro1').value);
		var fechaHasta = new Date(document.getElementById('filtro2').value);
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;
		}

		location.href = "/byeContabilidad/rest-services/private/reporte/getByIdReporteBancoCuenta?fechaDesde="
				+ document.getElementById('filtro1').value
				+ "&fechaHasta="
				+ document.getElementById('filtro2').value
				+ "&idBanco="
				+ document.getElementById('banco').value
				+ "&idCuenta="
				+ document.getElementById('cuenta').value;

	}

	//	back.addEventListener("click", function() {
	//		window.history.back();
	//	}, false);

	$("#banco").trigger('change');
</script>
</html>