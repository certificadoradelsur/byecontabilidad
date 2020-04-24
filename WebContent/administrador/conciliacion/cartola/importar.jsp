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

			<form name="importCartola" id="importCartola"
				action="/byeContabilidad/rest-services/private/cartola/importar"
				method="post" enctype="multipart/form-data">

				<input type="hidden" name="id" id="id" />

				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Importar Cartola</h1>
				</div>
				<div class="container">
					<br>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Empresa</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="empresa">
						</select>
					</div>
				</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; &nbsp;Banco</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="banco"
								required="required">
								<option value="1">Estado</option>
								<option value="2">Chile</option>
								<option value="3">Santander</option>
							</select>
						</div>
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; &nbsp;N° Cuenta</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="cuenta"
								required="required">
							</select>
						</div>
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Año</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="anio"
								required="required">

							</select>
						</div>
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Mes</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="numCartola"
								required="required">
								<option value="01">Enero</option>
								<option value="02">Febrero</option>
								<option value="03">Marzo</option>
								<option value="04">Abril</option>
								<option value="05">Mayo</option>
								<option value="06">Junio</option>
								<option value="07">Julio</option>
								<option value="08">Agosto</option>
								<option value="09">Septiembre</option>
								<option value="10">Octubre</option>
								<option value="11">Novimebre</option>
								<option value="12">Diciembre</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Cartola</label>
						<input type="file" id="cartola" name="cartola" class="in"
							placeholder="Ingrese cuenta" required="required" />
					</div>
				</div>
				<br> <br> <br>
				<div class="row">
					<div class="col-xs-6 col-md-2">
						<button class=" btt btn btn-primary btn-lg btn-block"
							id="btnImportCartola" type="submit">Importar</button>
					</div>
					<div class="col-xs-6 col-md-2">
						<button class=" btt btn btn-primary btn-lg btn-block"
							type="button" id="back">Cancelar</button>
					</div>
				</div>
			</form>

			<div class="modal fade" id="successModalCartola" tabindex="-1"
				role="dialog">
				<div
					class="modal-dialog modal-dialog-centered justify-content-center"
					role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Resultado importaci&oacute;n de
								Cartola</h5>
						</div>
						<div class="modal-body">
							<div id="cantidadImportados"></div>
						</div>
						<div class="modal-footer">
							<a class="btn btn-danger"
								href="/byeContabilidad/administrador/cartola/index.jsp">Cerrar</a>
						</div>
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
						$("#banco").select2({width:'200'});
						$("#cuenta").select2({width:'200'});
						$("#anio").select2({width:'200'});
						$("#numCartola").select2({width:'200'});
						$("#empresa").select2({width:'200'});

						
						var submitJson = {
								idUsuario : document.getElementById("idUsuario").value}
								
								$.post('/byeContabilidad/rest-services/private/empresa/getLista',JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">" + res[i].razonSocial
														+ "</option>";
											}
											document.getElementById("empresa").innerHTML = str;
										}, "json");	
						ComboAnio();
						$("#importCartola")
								.submit(
										function(event) {
											event.preventDefault();
											var form = $('#importCartola')[0];
									//		var banco = document
									//				.getElementById("banco")[0].value;
									//		var cuenta = document
									//				.getElementById("cuenta")[0].value;
									//		var cuenta = document
									//				.getElementById("anio")[0].value;
											var data = new FormData(form);
											$
													.ajax({
														type : "POST",

														enctype : 'multipart/form-data',
														url : '/byeContabilidad/rest-services/private/cartola/importar?banco='
																+ document
																		.getElementById('banco').value
																+ '&cuenta='
																+ document
																		.getElementById('cuenta').value
																+ '&numCartola='
																+ document
																		.getElementById('numCartola').value
																+ '&anio='
																+ document
																		.getElementById('anio').value
																+ '&idEmpresa='
																+ document
																		.getElementById('empresa').value,

														data : data,
														processData : false,
														contentType : false,
														cache : false,
														timeout : 600000,
														success : function(data) {
															var htmlSalida = "<table border=1><tr><td>Linea</td><td>Descripcion</td><td>Estado</td></tr>";
															var columnas = "";
															for (var i = 0; i < data.length; i++) {
																if (data[i].estado == "OK") {
																	columnas = columnas
																			+ "<tr style='color: green'><td>"
																			+ data[i].linea
																			+ "</td><td>"
																			+ data[i].detalle
																			+ "</td><td>"
																			+ data[i].estado
																			+ "</td></tr>";
																} else {
																	columnas = columnas
																			+ "<tr style='color: red'><td>"
																			+ data[i].linea
																			+ "</td><td>"
																			+ data[i].detalle
																			+ "</td></td><td>"
																			+ data[i].estado
																			+ "</td></tr>";
																}
															}
															htmlSalida = htmlSalida
																	+ columnas
																	+ "</table>";
															$(
																	"#cantidadImportados")
																	.html(
																			htmlSalida);
															$(
																	'#successModalCartola')
																	.modal();
														},
													});

										});

					});

	back.addEventListener("click", function() {
		window.history.back();
	}, false);

	function ComboAnio() {
		var str = "";
		var n = (new Date()).getFullYear();

		for (var i = n; i <= (n + 5); i++) {
			str += "<option value="+i+">" + i + "</option>";
		}
		document.getElementById("anio").innerHTML = str;
	};

	var submitJson = {
			idUsuario : document.getElementById("idUsuario").value}
	
// 	$.post('/byeContabilidad/rest-services/private/banco/getLista',
// 			function(res, code) {
// 				var str;
// 				for (var i = 0, len = res.length; i < len; i++) {
// 					str += "<option value="+res[i].id+">" + res[i].nombre
// 							+ "</option>";
// 				}
// 				document.getElementById("banco").innerHTML = str;
// 			}, "json");


	$('#banco').on('change',
			function() {
				var submitJson = { 

					    idUsuario : document.getElementById("idUsuario").value,
						idBanco : document.getElementById("banco").value
				}

				$.post('/byeContabilidad/rest-services/private/cuenta/getByIdBanco',
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

	$("#anio").trigger('change');
	$("#banco").trigger('change');
</script>
</html>