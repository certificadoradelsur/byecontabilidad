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
					<h1 class="h2">Modificar clasificación</h1>
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
										<div class="row">
						<label for="colFormLabel" class="col-sm-2 col-form-label">
							&nbsp;&nbsp; Empresa</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="empresa">
							</select>
						</div>
					</div>		
					<br> <br>
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
		<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
$(document).ready(function() {
	$("#claseCuenta").select2(),
	$("#grupoCuenta").select2();
	$("#empresa").select2();
	
	$.post('/byeContabilidad/rest-services/private/claseCuenta/getLista',
			function(res, code) {
				var str;
				for (var i = 0, len = res.length; i < len; i++) {
					str += "<option value="+res[i].id+">" + res[i].nombre
							+ "</option>";
				}
				document.getElementById("claseCuenta").innerHTML = str;
				busca ();								
			}, "json");	
});


	function busca (){
			var submitjson = {id: "<%=request.getParameter("id")%>",
				};

		$.post('/byeContabilidad/rest-services/private/clasificacion/getById',
						JSON.stringify(submitjson))
				.done(
						function(data) {	
							document.getElementById("nombre").value = data.nombre;
							document.getElementById("claseCuenta").value = data.idClaseCuenta;
							document.getElementById("empresa").value = data.idEmpresa;
							cargaEmpresa(data.idEmpresa);
							cargaselect(data.idGrupoCuenta);
						})

				.fail(
						function(jqxhr, settings, ex) {
							alert('No se pudo modificar la clasificación '+ ex);
						});
	}	
	
	function cargaselect(id){
		var submitJson = {
				idClaseCuenta : document.getElementById("claseCuenta").value
				}
			$.post('/byeContabilidad/rest-services/private/grupoCuenta/getByIdClaseCuenta',
							JSON.stringify(submitJson),
							function(res, code) {
								var str;
								for (var i = 0, len = res.length; i < len; i++) {
							if(id==res[i].id){
								str += "<option value="+res[i].id+" selected>"
								+ res[i].nombre
								+ "</option>";
			
							}else{
									str += "<option value="+res[i].id+">"
											+ res[i].nombre
											+ "</option>";
							}}
					document.getElementById("grupoCuenta").innerHTML = str;
			}, "json");
	}
	
	function cargaEmpresa(idEmpresa) {
		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}

		$.post('/byeContabilidad/rest-services/private/empresa/getLista', JSON
				.stringify(submitJson), function(res, code) {
			var str;
			for (var i = 0, len = res.length; i < len; i++) {
				if (idEmpresa == res[i].id) {
					str += "<option value="+res[i].id+" selected>"
							+ res[i].razonSocial + "</option>";
				} else {
					str += "<option value="+res[i].id+">" + res[i].razonSocial
							+ "</option>";
				}
			}
			document.getElementById("empresa").innerHTML = str;
		}, "json");
	}
	
	$('#claseCuenta').on('change',
					function() {
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
	
	function modificar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Los campos deben estar llenos");
			return;
		}
		
		if ($('#empresa option:selected').text() == '') {
			alert("Debe seleccionar una empresa valida");
			return;
		}
			
		var submitJson = {
			id : <%=request.getParameter("id")%>,
			idClaseCuenta : document.getElementById("claseCuenta").value,	
			idGrupoCuenta : document.getElementById("grupoCuenta").value,
			idEmpresa : document.getElementById("empresa").value,
			nombre : document.getElementById("nombre").value,
			idUsuario : document.getElementById("idUsuario").value
		}

		$.post('/byeContabilidad/rest-services/private/clasificacion/update',
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
	$("#grupoCuenta").trigger('change');
	$("#claseCuenta").trigger('change');
</script>
</html>