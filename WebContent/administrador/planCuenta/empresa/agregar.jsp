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
					<h1 class="h2">Agregar empresa</h1>
				</div>
				<div class="container">
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Rut</label>
						<input type="text" id="rut" name="rut" class="in"
							placeholder="Ingrese rut" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Razon
							social</label> <input type="text" id="razonSocial" name="razonSocial"
							class="in" placeholder="Ingrese razon social" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Giro</label>
						<input type="text" id="giro" name="giro" class="in"
							placeholder="Ingrese giro" required="required" />
					</div>
					<div class="form-group">
						<div class="col-1"></div>
						<label for="colFormLabel" class="col-sm-2 col-form-label">Dirección
						</label> <input type="text" id="direccion" name="direccion" class="in"
							placeholder="Ingrese Dirección" required="required" />
					</div>
					<br> <br>
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

		<div class="container">
			<!-- Trigger the modal with a button -->
			<button hidden="true" type="button" class="btn btn-info btn-lg"
				id="modal" data-toggle="modal" data-target="#myModal">Open
				Modal</button>
			<!-- Modal -->
			<div class="modal fade bd-example-modal-sm" id="myModal"
				tabindex="-1" role="dialog"
				aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalCenterTitle">Seleccione
								una empresa</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close" id="modalclose">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>

						<div class="container">
							<div class="row"></div>
							<div class="form-group col-md-2">
								<div class="col-sm-2"></div>
								<label for="empresa">&nbsp;Empresa</label> <select
									class="browser-default custom-select" id="empresa"
									required="required">
								</select>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary" onclick="copiar()">Copiar</button>
							</div>
							<br>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#empresa").select2({
			width : '180'
		});

	})

	document.getElementById('rut')
			.addEventListener(
					'input',
					function(evt) {
						let value = this.value.replace(/\./g, '').replace('-',
								'');

						if (value.match(/^(\d{2})(\d{3}){2}(\w{1})$/)) {
							value = value.replace(
									/^(\d{2})(\d{3})(\d{3})(\w{1})$/,
									'$1.$2.$3-$4');
						} else if (value.match(/^(\d)(\d{3}){2}(\w{0,1})$/)) {
							value = value.replace(
									/^(\d)(\d{3})(\d{3})(\w{0,1})$/,
									'$1.$2.$3-$4');
						} else if (value.match(/^(\d)(\d{3})(\d{0,2})$/)) {
							value = value.replace(/^(\d)(\d{3})(\d{0,2})$/,
									'$1.$2.$3');
						} else if (value.match(/^(\d)(\d{0,2})$/)) {
							value = value.replace(/^(\d)(\d{0,2})$/, '$1.$2');
						}
						this.value = value;
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
			rut : document.getElementById("rut").value,
			razonSocial : document.getElementById("razonSocial").value,
			giro : document.getElementById("giro").value,
			idUsuario : document.getElementById("idUsuario").value,
			direccion : document.getElementById("direccion").value
		}

		$.post('/byeContabilidad/rest-services/private/empresa/add',
						JSON.stringify(submitJson)).done(function(data) {
							if (data == 'OK') {
								//alert('Se guardo la empresa exitosamente');
								clear();
								if (confirm('¿Desea copiar el plan de cuentas de alguna empresa existente?')) {
									ver();
								} else {
									location.href = "index.jsp";
								}
							} else {
								alert(data);
							}

						}).fail(function(jqxhr, settings, ex) {
					alert('No se pudo guardar la empresa ' + ex);
				});
	}

	function ver() {

		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}
		$.post('/byeContabilidad/rest-services/private/empresa/getLista', JSON
				.stringify(submitJson), function(res, code) {
			var str;
			for (var i = 0, len = res.length; i < len; i++) {
				str += "<option value="+res[i].id+">" + res[i].razonSocial
						+ "</option>";
			}
			document.getElementById("empresa").innerHTML = str;
		}, "json");

		document.getElementById('modal').click();
	}


	function copiar() {
		var submitJson = {
			idEmpresa : document.getElementById("empresa").value,
			idUsuario : document.getElementById("idUsuario").value
		}

		$.post('/byeContabilidad/rest-services/private/cuentaContable/copiar',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				location.href = "index.jsp";
			} else {
				alert(data);
			}
		}).fail(function(jqxhr, settings, ex) {
			alert('Error al copiar el plan de cuentas '+ ex);
		});
	}

	function clear() {
		document.getElementById("rut").value = "";
		document.getElementById("razonSocial").value = "";
		document.getElementById("giro").value = "";
		document.getElementById("direccion").value = "";
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);
</script>
</html>