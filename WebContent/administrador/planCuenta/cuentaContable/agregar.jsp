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
<link rel="stylesheet" type="text/css" href="CSS.css">
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

 <%@ include file = "../../../complementos/nav.jsp" %>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Agregar cuenta contable</h1>
			</div>
			<div class="container">
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Código</label>
					<input type="number" id="codigo" name="codigo" class="in"
						placeholder="Ingrese código" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Glosa
						general</label> <input type="text" id="glosa" name="glosa" class="in"
						placeholder="Ingrese Glosa" required="required" />
				</div>
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
							&nbsp;&nbsp; Sucursal</label>
						<div class="col-3">
							<select class="browser-default custom-select" id="sucursal">
							</select>
						</div>
					</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Clase Cuenta</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="claseCuenta">
							<option value="1">Activo</option>
						</select>
					</div>
				</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Grupo Cuenta</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="grupoCuenta">
							<option value="1">Activo circulante</option>
						</select>
					</div>
				</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Descripción</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="descripcion">
							<option value=" "></option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">&nbsp;&nbsp; Seleccione</div>
					<div class="col-sm-10">
					<!--  	<div class="form-check">
							<input class="form-check-input" type="checkbox" id="imputable">
							<label class="form-check-label" for="gridCheck1">
								Imputable </label>
						</div>-->
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="analisis">
							<label class="form-check-label" for="gridCheck1">
								Analisis </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="conciliacion">
							<label class="form-check-label" for="gridCheck1">
								Conciliación </label>
						</div>
					</div>
				</div>
				<br>							
				<div class="row collapse" id="collapse2">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Banco</label>
					<div class="col-3">
						<select class="browser-default custom-select d-block w-100" id="banco"
							required="required">
							<option value="1">Estado</option>
						</select>
					</div>
				</div>
				<div class="row collapse" id="collapse3" >
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;N° cuenta</label>
					<div class="col-3">
						<select class="browser-default custom-select "  id="cuenta"
							required="required">
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
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#claseCuenta").select2({width:'200'}),
		$("#grupoCuenta").select2();
		$("#descripcion").select2();
		$("#banco").select2({width:'200'});
		$("#empresa").select2({width:'200'});
		$("#cuenta").select2({width:'200'});
		$("#sucursal").select2({width:'200'});
      	codigo();
		
		 $('#analisis').on('change', function(){

	         if(document.getElementById("analisis").checked){
	            document.getElementById("conciliacion").disabled=true;
	         }else{
	             document.getElementById("conciliacion").disabled=false;
	             }
	     })
	     
	   	$('#conciliacion').on('change', function(){

	         if(document.getElementById("conciliacion").checked){
	             $('#collapse2').collapse('show');
	             $('#collapse3').collapse('show');
	             document.getElementById("analisis").disabled=true;
	   	}else{
	             $('#collapse2').collapse('hide');
	         	 $('#collapse3').collapse('hide');
	             document.getElementById("analisis").disabled=false;
				}
	     })
	     
	$ .post( '/byeContabilidad/rest-services/private/claseCuenta/getLista',
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].nombre
														+ "</option>";
											}
											document.getElementById("claseCuenta").innerHTML = str;
											var submitJson = {
													idGrupoCuenta : document.getElementById("grupoCuenta").value
												}
										
										}, "json");
		 	 
		 	 
		 	$.post('/byeContabilidad/rest-services/private/banco/getLista',
					function(res, code) {
						var str;
						for (var i = 0, len = res.length; i < len; i++) {
							str += "<option value="+res[i].id+">" + res[i].nombre
									+ "</option>";
						}
						document.getElementById("banco").innerHTML = str;
					}, "json");

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
	
	$('#empresa').on('change',
			function() {
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
			}); 
					

	$('#claseCuenta').on('change',function() {
						var submitJson = {
							idClaseCuenta : document.getElementById("claseCuenta").value
						}

						$
								.post(
										'/byeContabilidad/rest-services/private/grupoCuenta/getByIdClaseCuenta',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].nombre
														+ "</option>";
											}
											document
													.getElementById("grupoCuenta").innerHTML = str;
											buscaGrupo();
										}, "json");
					});

	$('#grupoCuenta').on('change',function() {
						var submitJson = {
							idGrupoCuenta : document .getElementById("grupoCuenta").value,
							idUsuario : document .getElementById("idUsuario").value
						}

						$.post('/byeContabilidad/rest-services/private/clasificacion/getByIdGrupoCuenta',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;                                           
											for (var i = 0, len = res.length; i < len; i++) {	
												str += "<option value="+res[i].id+">"
														+ res[i].nombre
														+ "</option>";
											}
											document.getElementById("descripcion").innerHTML = str;
										}, "json");
					});
	
	
	function buscaGrupo(){
		var submitJson = {
				idClaseCuenta : document.getElementById("claseCuenta").value
			}
		$
		.post( '/byeContabilidad/rest-services/private/grupoCuenta/getByIdClaseCuenta',
				JSON.stringify(submitJson),
				function(res, code) {
					var str;
					for (var i = 0, len = res.length; i < len; i++) {
						str += "<option value="+res[i].id+">"
								+ res[i].nombre
								+ "</option>";
					}
					document .getElementById("grupoCuenta").innerHTML = str;
					buscaClasificacion ();
				}, "json");
	}
	
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
	function buscaClasificacion (){
		var submitJson = {
				idGrupoCuenta : document.getElementById("grupoCuenta").value,
				idUsuario : document .getElementById("idUsuario").value
			}

			$.post('/byeContabilidad/rest-services/private/clasificacion/getByIdGrupoCuenta',
							JSON.stringify(submitJson),
							function(res, code) {
								var str;
								for (var i = 0, len = res.length; i < len; i++) {
									str += "<option value="+res[i].id+">"
											+ res[i].nombre
											+ "</option>";
								}
								document.getElementById("descripcion").innerHTML = str;
							}, "json");
	}

	function guardar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}
		

		
		if ($('#sucursal option:selected').text() == 'Seleccione sucursal') {
			alert("Debe seleccionar una sucursal");
			return;
		}
		
		if(document.getElementById("conciliacion").checked){
			if ($('#cuenta option:selected').text() == 'Seleccione cuenta') {
				alert("Debe seleccionar una cuenta");
				return;
			}
			if(document.getElementById("cuenta").value==""){
				alert('No existe cuenta asociada al banco');
				return;
			}
		}
		
		var submitJson = {
			codigo : document.getElementById("codigo").value,
			idClaseCuenta : document.getElementById("claseCuenta").value,
			idGrupoCuenta : document.getElementById("grupoCuenta").value,
			glosaGeneral : document.getElementById("glosa").value,
			descripcion : document.getElementById("descripcion").value,
			//imputable : document.getElementById("imputable").checked,
			analisis : document.getElementById("analisis").checked,
			conciliacion : document.getElementById("conciliacion").checked,
			idBanco : document.getElementById("banco").value,
		    idCuenta : document.getElementById("cuenta").value=='Seleccione cuenta' ? 0 : document.getElementById("cuenta").value,
		    idEmpresa : document.getElementById("empresa").value,
		    idSucursal : document.getElementById("sucursal").value=='Seleccione sucursal' ? 0 : document.getElementById("sucursal").value 
		  
		}

		$.post('/byeContabilidad/rest-services/private/cuentaContable/add',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardo la cuenta contable exitosamente');
				location.href = "index.jsp";
			} else {
				alert(data);
			}

		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo guardar la cuenta contable ' + ex);
		});
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);

	
	function codigo() {
		$.post('/byeContabilidad/rest-services/private/cuentaContable/getMaxCodigo')
				.done(function(data) {
					document.getElementById("codigo").value = data + 1;
				}).fail(function(jqxhr, settings, ex) {
			});
	}
	
	$("#banco").trigger('change');
	$("#analisis").trigger('change');
	$("#claseCuenta").trigger('change');
	$("#grupoCuenta").trigger('change');
	$("#descripcion").trigger('change');
</script>
</html>