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
<style type="text/css">
.collapsing {
	-webkit-transition-delay: 0s;
	transition-delay: 0s;
	visibility: hidden;
}

.collapse.show {
	-webkit-transition-delay: 0s;
	transition-delay: 0s;
	visibility: visible;
}
</style>
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
						<label for="colFormLabel" class="col-sm-2 col-form-label">Nombre</label>
						<input type="text" id="nombre" name="nombre" class="in"
							placeholder="Ingrese Nombre" required="required" />
					</div>
					
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Empresa</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="empresa">
							<option value="1"></option>
							</select>
						</div>
					</div>
					
					<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Sucursal</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="sucursal">
							</select>
						</div>
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
	</div>
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#empresa").select2({width:'200'});
		$("#sucursal").select2({width:'200'});

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
							cargaSucursal(document.getElementById("empresa").value);
						}, "json");	 	
	 
	})
	
	$('#empresa').on('change',function() {
				var submitJson = { 

					    idUsuario : document.getElementById("idUsuario").value,
						idEmpresa : document.getElementById("empresa").value
				}

				$.post('/byeContabilidad/rest-services/private/sucursal/getByIdEmpresa',
								JSON.stringify(submitJson),
								function(res, code) {
					               var str = "<option>Seleccione sucursal</option>";
									for (var i = 0, len = res.length; i < len; i++) {
									str += "<option value="+res[i].codigo+">"
												+ res[i].direccion
												+ "</option>";
									}
									document.getElementById("sucursal").innerHTML = str;
								}, "json");
	})
				
				
				
				function cargaSucursal(idEmpresa){
					var submitJson = { 

						    idUsuario : document.getElementById("idUsuario").value,
							idEmpresa : idEmpresa
					}

					$.post('/byeContabilidad/rest-services/private/sucursal/getByIdEmpresa',
									JSON.stringify(submitJson),
									function(res, code) {
						               var str = "<option>Seleccione sucursal</option>";
										for (var i = 0, len = res.length; i < len; i++) {
										str += "<option value="+res[i].codigo+">"
													+ res[i].direccion
													+ "</option>";
										}
										document.getElementById("sucursal").innerHTML = str;
									}, "json");	
				}
	
	
	function guardar() {
		var submitJson = {
			nombre : document.getElementById("nombre").value,
			idSucursal : document.getElementById("sucursal").value
			
		}

		$.post('/byeContabilidad/rest-services/private/CentroResultado/add',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardo el usuario exitosamente');
				location.href = "index.jsp";
			} else {
				alert(data);
			}

		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo guardar el Centro de Resultado ' + ex);
		});
	}

	
	

	back.addEventListener("click", function() {
		window.history.back();
	}, false);
</script>
</html>