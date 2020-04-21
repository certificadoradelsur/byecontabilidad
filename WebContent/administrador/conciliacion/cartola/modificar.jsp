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
					<h1 class="h2">Modificar Cartola</h1>
				</div>
				<div class="container">

					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; &nbsp;Banco</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="banco"
								required="required">
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Cuenta</label>
						<input type="text" id="cuenta" name="cuenta" class="in"
							placeholder="Ingrese cuenta" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">N°
							documento</label> <input type="number" id="numDocumento"
							name="numDocumento" class="in" placeholder="Ingrese monto"
							required="required" min="0" pattern="^[0-9]+" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Fecha</label>
						<input type="date" id="fecha" name="fecha" class="in"
							required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Descripción</label>
						<textarea id="descripcion" class="col-sm-3" name="descripcion"
							class="in" placeholder="Ingrese descripción" required="required"></textarea>
					</div>
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp; &nbsp;Movimiento</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="tipoMovimiento"
								required="required">
								<option value="ABONO">Abono</option>
								<option value="CARGO">Cargo</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Monto</label>
						<input type="number" id="monto" name="monto" class="in"
							placeholder="Ingrese monto" required="required" min="0"
							pattern="^[0-9]+" />
					</div>
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
</body>
<script type="text/javascript">
$(document).ready(function () {
	$("#tipoMovimiento").select2();
	$("#banco").select2();
	
	$.post('/conciliacionBancaria/rest-services/private/banco/getLista',
			function(res, code) {
				var str;
				for (var i = 0, len = res.length; i < len; i++) {
					str += "<option value="+res[i].id+">" + res[i].nombre
							+ "</option>";
				}
				document.getElementById("banco").innerHTML = str;
			}, "json");


	
	var datos = {"id": "<%=request.getParameter("id")%>"};
						$
								.post(
										'/conciliacionBancaria/rest-services/private/cartola/getById',
										JSON.stringify(datos))
								.done(
										function(data) {
											document.getElementById("banco").value = data.idBanco;
											document.getElementById("cuenta").value = data.cuenta;
											document
													.getElementById("numDocumento").value = data.numDocumento;
											document.getElementById("fecha").value = data.fecha
													.substring(0, 10);
											document
													.getElementById("descripcion").value = data.descripcion;
											document
													.getElementById("tipoMovimiento").value = data.tipoMovimiento;
											document.getElementById("monto").value = data.monto;

										})
								.fail(
										function(jqxhr, settings, ex) {
											alert('No se pudo modificar la cartola '
													+ ex);
										});

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
			id :
<%=request.getParameter("id")%>
	,
			idBanco : document.getElementById("banco").value,
			cuenta : document.getElementById("cuenta").value,
			numDocumento : document.getElementById("numDocumento").value,
			fecha : document.getElementById("fecha").value,
			descripcion : document.getElementById("descripcion").value,
			tipoMovimiento : document.getElementById("tipoMovimiento").value,
			monto : document.getElementById("monto").value
		}
		$.post('/conciliacionBancaria/rest-services/private/cartola/update',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardaron los cambios');
				location.href = "index.jsp";
			} else {
				alert(data);
			}
		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo modificar' + ex);
		});
	}
	back.addEventListener("click", function() {
		window.history.back();
	}, false);
</script>
</html>