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

	<%@ include file="../../../complementos/navAdministrativo.jsp"%>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Modificar comprobante contable</h1>
			</div>

			<div class="card border-secondary mb-3">
				<div class="card-header"></div>
				<div class="card-body text-secondary">
					<div class="row">
						<div class="col-sm-1">
							<label>Número</label>
						</div>
						<div class="col-sm-3">
							<input type="number" id="numero" name="numero"
								placeholder="Ingrese Número" required="required" class="on" />
						</div>
						<div class="col-sm-1">
							<label>Glosa</label>
						</div>
						<div class="col-sm-3">
							<input type="text" id="glosaGeneral" name="glosaGeneral"
								placeholder="Ingrese glosa general" required="required"
								class="on" />
						</div>
						<div class="col-sm-1">
							<label>Fecha</label>
						</div>
						<div class="col-sm-3">
							<input type="date" id="fecha" name="fecha" class="in"
								required="required" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-1">
							<label>Empresa</label>
						</div>
						<div class="col-sm-3">
							<select class="browser-default custom-select" id="empresa"
								required="required">
							</select>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-1">
					<label> Cuenta</label>
				</div>
				<div class="col-sm-3">
					<select class="browser-default custom-select" id="cuentaContable">
					</select>
				</div>
				<div class="col-sm-1">
					<label>Monto</label>
				</div>
				<div class="col-sm-3">
					<input type="number" id="monto" name="monto" class="in"
						placeholder="Ingrese monto" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
			</div>
			<br>
			<div class="form-row collapse" id="sin">
				<div class="col-sm-1">
					<label>Glosa</label>
				</div>
				<div class="col-sm-3">
					<input type="text" id="glosaSin" name="glosaSin"
						placeholder="Ingrese glosa"  required="required" />
				</div>
				<div class="col-sm-1">
					<label>&nbsp;Tipo</label>
				</div>
				<div class="col-sm-3">
					&nbsp; <select class="browser-default custom-select" id="tipoSin"
						required="required">
						<option value="DEBE">Debe</option>
						<option value="HABER">Haber</option>
					</select>
				</div>
			</div>
			<div class="form-row collapse" id="analisable">
				<div class="col-sm-1">
					<label>Cliente</label>
				</div>
				<div class="col-sm-3">
					<select class="browser-default custom-select d-block w-100"
						id="cliente" required="required">
					</select>
				</div>
				<div class="col-sm-1">
					<label>&nbsp;Glosa</label>
				</div>
				<div class="col-sm-3">
					&nbsp; <input type="text" id="glosaAnalisis" name="glosaAnalisis"
						placeholder="Ingrese glosa" required="required" />
				</div>
				<div class="col-sm-1">
					<label>Tipo</label>
				</div>
				<div class="col-sm-3">
					<select class="browser-default custom-select" id="tipoAnalisis"
						required="required">
						<option value="DEBE">Debe</option>
						<option value="HABER">Haber</option>
					</select>
				</div>
			</div>
			<div class="row collapse" id="conciliacion">
				<div class="col-sm-1">
					<label>Glosa</label>
				</div>
				<div class="col-sm-3">
					<input type="text" id="glosaConciliacion" name="glosaConciliacion"
						placeholder="Ingrese glosa" required="required" />
				</div>
				<div class="col-sm-1">
					<label>Tipo</label>
				</div>
				<div class="col-sm-3">
					<select class="browser-default custom-select" id="tipoMovimiento"
						required="required">
						<option value="INGRESO">Ingreso</option>
						<option value="EGRESO">Egreso</option>
						<option value="TRASPASO">Traspaso</option>
					</select>
				</div>
				<div class="col-sm-1">
					<label>Documento</label>
				</div>
				<div class="col-2">
					<select class="browser-default custom-select" id="tipoDocumento"
						required="required">
					</select>
				</div>
			</div>
			<br>
			<div class="row collapse" id="conciliacion1">
				<div class="col-sm-1">
					<label>Banco</label>
				</div>
				<div class="col-3">
					<select class="browser-default custom-select d-block w-100"
						id="banco" required="required" disabled>
						<option value="1">Estado</option>
					</select>
				</div>
				<div class="col-sm-1">
					<label>Cuenta</label>
				</div>
				<div class="col-3">
					<select class="browser-default custom-select " id="cuenta"
						required="required" disabled>
					</select>
				</div>
				<div class="col-sm-1">
					<label>Documento</label>
				</div>
				<div class="col-sm-3">
					<input type="number" id="numDocumento" name="numDocumento"
						placeholder="Ingrese Número" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
			</div>
			<br>
			<div class="row collapse" id="conciliacion2">
				<div class="col-sm-1">
					<label>Estado</label>
				</div>
				<div class="col-3">
					<select class="browser-default custom-select" id="estado"
						required="required">
						<option selected value="false">Pendiente</option>
						<option value="true">Al día</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-xs-6 col-md-2">
					<button class=" btt btn btn-primary btn-lg btn-block" type="button"
						onclick="Add()">Agregar</button>
				</div>
			</div>
			<br>
			<div class="table-responsive">
				<table class="table" id="grid"></table>
			</div>
			<br> <br>
			<div class="row">
				<div class="col-xs-6 col-md-2">
					<button type="button" class="btn btn-primary btn-lg btn-block"
						onclick="Modificar()">Modificar</button>
				</div>

				<div class="col-xs-6 col-md-2">
					<button class=" btt btn btn-primary btn-lg btn-block" type="button"
						id="back">Cancelar</button>
				</div>
			</div>
			<br>
		</form>
	</div>

	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	function setWidths(){
		$("#tipoMovimiento").select2({
			width : '160'
		});
		$("#empresa").select2({
			width : '200'
		});
		$("#cuentaContable").select2({
			width : '200'
		});
		$("#banco").select2({
			width : '200'
		});
		$("#cuenta").select2({
			width : '200'
		});
		$("#tipoDocumento").select2({
			width : '160'
		});
		$("#estado").select2({
			width : '200'
		});
		$("#cliente").select2({
			width : '200'
		});
		$("#tipoAnalisis").select2({
			width : '200'
		});
		$("#tipoSin").select2({
			width : '200'
		});
	}

	$(document).ready(function() {
		
		var submitjson = {id:"<%=request.getParameter("id")%> " };
						$
								.post(
										'/byeContabilidad/rest-services/private/comprobanteContable/getById',
										JSON.stringify(submitjson))
								.done(
										function(data) {
											document.getElementById("numero").value = data.numero;
											document
													.getElementById("glosaGeneral").value = data.glosaGeneral;
											document.getElementById("fecha").value = data.fecha;
											document.getElementById("empresa").value = data.idEmpresa;
											cargaEmpresa(data.idEmpresa)
										})
								.fail(
										function(jqxhr, settings, ex) {
											alert('No se pudo modificar el comprobante contable '
													+ ex);
										});

						setWidths();
						var varAnalisis;
						var varConciliacion;
						var varIdBanco;
						var varIdCuenta;
						var varCodigo;
						var varIdEmpresa;
						var glosa;
						var cliente;
						var varIdCuentaContable;
						var tipo;
						var documento;

						var submitJson = {
								idUsuario : document.getElementById("idUsuario").value
							}
						$
								.post(
										'/byeContabilidad/rest-services/private/empresa/getSupervisor',
										JSON.stringify(submitJson),
										function(res, code) {
											var str = "<option>Seleccione empresa</option>";
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].razonSocial
														+ "</option>";
											}
											document.getElementById("empresa").innerHTML = str;
										}, "json");
						

						grid = $('#grid')
								.grid(
										{
											primaryKey : 'ID',
											dataSource : [],
											columns : [
													{
														field : 'codigo',
														title : 'Codigo',
														width : 100
													},
													{
														field : 'descripcion',
														title : 'Descripción',
														width : 160
													},
													{
														field : 'glosa',
														title : 'Glosa',
														width : 160
													},
													{
														field : 'debe',
														title : 'Debe',
														width : 160
													},
													{
														field : 'haber',
														title : 'Haber',
														width : 160
													},
													{
														field : 'estado',
														title : 'ESTADO',
														width : 160,
														hidden : true
													},
													{
														field : 'tipoMovimiento',
														title : 'TIPO MOVIMIENTO',
														width : 160,
														hidden : true
													},
													{
														field : 'tipoDocumento',
														title : 'TIPO DOCUMENTO',
														width : 160,
														hidden : true
													},
													{
														field : 'numDocumento',
														title : 'NUMERO DOCUMENTO',
														width : 160,
														hidden : true
													},
													{
														field : 'fecha',
														title : 'FECHA',
														width : 160,
														hidden : true
													},
													{
														field : 'idUsuario',
														title : 'USER',
														width : 160,
														hidden : true
													},
													{
														field : 'monto',
														title : 'MONTO',
														width : 160,
														hidden : true
													},
													{
														field : 'idCliente',
														title : 'CLIENTE',
														width : 160,
														hidden : true
													},
													{
														field : 'numCuenta',
														title : 'CUENTA',
														width : 160,
														hidden : true
													},
													{
														field : 'idCuentaContable',
														title : 'CUENTACONTABLE',
														width : 160,
														hidden : true
													},
													{
														width : 100,
														title : 'Eliminar',
														tmpl : '<span class="material-icons gj-cursor-pointer">delete</span>',
														align : 'center',
														events : {
															'click' : Remove
														}
													}, ],
											pager : {
												limit : 100
											}

										});

						tabla();

					});

	function tabla() {

		var submitJson = {
			id : <%=request.getParameter("id")%>
	}

		$.post('/byeContabilidad/rest-services/private/movimiento/getMovById',
				JSON.stringify(submitJson), function(res, code) {
					for (var i = 0, len = res.length; i < len; i++) {
						grid.addRow({
							'ID' : grid.count(true) + 1,
							'codigo' : res[i].codigo,
							'descripcion' : res[i].descripcion,
							'debe' : res[i].debe,
							'haber' : res[i].haber,
							'numDocumento' : res[i].numDocumento,
							'glosa' : res[i].glosa,
							'monto' : res[i].monto,
							'tipoMovimiento' : res[i].tipoMovimiento,
							'tipoDocumento' : res[i].tipoDocumento,
							'estado' : res[i].estado,
							'fecha' : res[i].fecha,
							'idCuenta' : res[i].idCuenta,
							'numCuenta' : res[i].numCuenta,
							'idCliente' : res[i].idCliente,
							'idEmpresa' : res[i].idEmpresa,
							'idUsuario' : res[i].idUsuario,
							'numComprobante' : res[i].numComprobante,
							'idCuentaContable' : res[i].idCuentaContable,
						})
					}
				}, "json");
	}

	$('#cuentaContable').on('change',
			function() {
				var submitJson = {

					idUsuario : document.getElementById("idUsuario").value,
					idEmpresa : document.getElementById("empresa").value
				}
				
				$
				.post(
						'/byeContabilidad/rest-services/private/cliente/getLista',
						JSON.stringify(submitJson),
						function(res, code) {
							var str;
							for (var i = 0, len = res.length; i < len; i++) {
								str += "<option value="+res[i].id+">"
										+ res[i].rut
										+ "</option>";
							}
							document.getElementById("cliente").innerHTML = str;

						}, "json");

			});
	
	$('#empresa')
			.on(
					'change',
					function() {
						var submitJson = {

							idUsuario : document.getElementById("idUsuario").value,
							idEmpresa : document.getElementById("empresa").value
						}

						$
								.post(
										'/byeContabilidad/rest-services/private/cuentaContable/getByIdEmpresa',
										JSON.stringify(submitJson),
										function(res, code) {
											var str = "<option>Seleccione cuenta</option>";
											for (var i = 0; i < res.length; i++) {
												str += "<option value="+res[i].id+"/"+res[i].analisis+"/"
					+res[i].conciliacion+"/"+res[i].idBanco+"/"
					+res[i].idCuenta+"/"+res[i].codigo+ "/"+res[i].idEmpresa+">"
														+ res[i].glosaGeneral
														+ "</option>";
											}

											document
													.getElementById("cuentaContable").innerHTML = str;
											var idCuentaContable = document
													.getElementById("cuentaContable").value
													.split("/");
											varIdCuentaContable = idCuentaContable[0];
											var analisis = document
													.getElementById("cuentaContable").value
													.split("/");
											varAnalisis = analisis[1];
											var coinciliacion = document
													.getElementById("cuentaContable").value
													.split("/");
											varConciliacion = coinciliacion[2];
											var banco = document
													.getElementById("cuentaContable").value
													.split("/");
											varIdBanco = banco[3];
											var cuenta = document
													.getElementById("cuentaContable").value
													.split("/");
											varIdCuenta = cuenta[4];
											var codigo = document
													.getElementById("cuentaContable").value
													.split("/");
											varCodigo = codigo[5];
											var idEmpresa = document
													.getElementById("cuentaContable").value
													.split("/");
											varIdEmpresa = idEmpresa[6];
										}, "json");

					});
	
	$('#empresa').on('change',
			function() {
				var submitJson = {
					idUsuario : document.getElementById("idUsuario").value,
					idEmpresa : document.getElementById("empresa").value
				}
				
				$
				.post(
						'/byeContabilidad/rest-services/private/cliente/getLista',
						JSON.stringify(submitJson),
						function(res, code) {
							var str;
							for (var i = 0, len = res.length; i < len; i++) {
								str += "<option value="+res[i].id+">"
										+ res[i].rut
										+ "</option>";
							}
							document.getElementById("cliente").innerHTML = str;

						}, "json");

			});

	$('#cuentaContable')
			.on(
					'change',
					function() {
						var idCuentaContable = document
								.getElementById("cuentaContable").value
								.split("/");
						varIdCuentaContable = idCuentaContable[0];
						var analisis = document
								.getElementById("cuentaContable").value
								.split("/");
						varAnalisis = analisis[1];
						var coinciliacion = document
								.getElementById("cuentaContable").value
								.split("/");
						varConciliacion = coinciliacion[2];
						var banco = document.getElementById("cuentaContable").value
								.split("/");
						varIdBanco = banco[3];
						var cuenta = document.getElementById("cuentaContable").value
								.split("/");
						varIdCuenta = cuenta[4];
						var codigo = document.getElementById("cuentaContable").value
								.split("/");
						varCodigo = codigo[5];
						var idEmpresa = document
								.getElementById("cuentaContable").value
								.split("/");
						varIdEmpresa = idEmpresa[6];

						if (varAnalisis == 'true' && varConciliacion == 'false') {
							$('#analisable').collapse('show');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
						} else if (varAnalisis == 'false'
								&& varConciliacion == 'true') {
							$('#analisable').collapse('hide');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('show');
							$('#conciliacion1').collapse('show');
							$('#conciliacion2').collapse('show');
							cargaBanco(varIdBanco, varIdCuenta);
						} else if (varAnalisis == 'false'
								&& varConciliacion == 'false') {
							$('#sin').collapse('show');
							$('#analisable').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
						} else {
							$('#analisable').collapse('hide');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
						}
					});

	function cargaBanco(idBanco, idCuenta) {
		$.post('/byeContabilidad/rest-services/private/banco/getLista',
				function(res, code) {
					var str;
					for (var i = 0, len = res.length; i < len; i++) {
						if (idBanco == res[i].id) {
							str += "<option value="+res[i].id+" selected>"
									+ res[i].nombre + "</option>";

						} else {
							str += "<option value="+res[i].id+">"
									+ res[i].nombre + "</option>";
						}
					}
					document.getElementById("banco").innerHTML = str;
					cargaCuenta(idCuenta);
				}, "json");
	}

	function cargaEmpresa(idEmpresa, idCuentaContable) {

		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}
		$.post('/byeContabilidad/rest-services/private/empresa/getSupervisor', JSON
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
			cargaCuentaContable(idEmpresa, idCuentaContable);
			document.getElementById("empresa").innerHTML = str;
			$("#empresa").prop("disabled", true);
		}, "json");
	}

	function cargaCuentaContable(idEmpresa) {

		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value,
			idEmpresa : idEmpresa
		}

		$
				.post(
						'/byeContabilidad/rest-services/private/cuentaContable/getByIdEmpresa',
						JSON.stringify(submitJson),
						function(res, code) {
							var str = "<option>Seleccione cuenta</option>";
							for (var i = 0; i < res.length; i++) {
								str += "<option value="+res[i].id+"/"+res[i].analisis+"/"
+res[i].conciliacion+"/"+res[i].idBanco+"/"
+res[i].idCuenta+"/"+res[i].codigo+ "/"+res[i].idEmpresa+">"
										+ res[i].glosaGeneral + "</option>";
							}

							document.getElementById("cuentaContable").innerHTML = str;
							var idCuentaContable = document
									.getElementById("cuentaContable").value
									.split("/");
							varIdCuentaContable = idCuentaContable[0];
							var analisis = document
									.getElementById("cuentaContable").value
									.split("/");
							varAnalisis = analisis[1];
							var coinciliacion = document
									.getElementById("cuentaContable").value
									.split("/");
							varConciliacion = coinciliacion[2];
							var banco = document
									.getElementById("cuentaContable").value
									.split("/");
							varIdBanco = banco[3];
							var cuenta = document
									.getElementById("cuentaContable").value
									.split("/");
							varIdCuenta = cuenta[4];
							var codigo = document
									.getElementById("cuentaContable").value
									.split("/");
							varCodigo = codigo[5];
							var idEmpresa = document
									.getElementById("cuentaContable").value
									.split("/");
							varIdEmpresa = idEmpresa[6];
						}, "json");

	}

	function Add() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});
		
		if(document.getElementById("glosaAnalisis").value=="" && document.getElementById("glosaConciliacion").value=="" &&
				document.getElementById("glosaSin").value==""){
			alert("Debe ingresar una glosa valida");
			return;
		}
		
		if ($('#empresa option:selected').text() == 'Seleccione empresa') {
			alert("Debe seleccionar una empresa");
			return;
		}

		if ($('#cuentaContable option:selected').text() == 'Seleccione cuenta') {
			alert("Debe seleccionar una cuenta contable");
			return;
		}

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		var debe;
		var haber;

		if (varAnalisis == 'true' && varConciliacion == 'false') {
			glosa = document.getElementById("glosaAnalisis").value,
					cliente = document.getElementById("cliente").value,
					documento = "", tipo = document
							.getElementById("tipoAnalisis").value
			if ($('#tipoAnalisis option:selected').text() == 'Debe') {
				debe = document.getElementById("monto").value
			} else if ($('#tipoAnalisis option:selected').text() == 'Haber') {
				haber = document.getElementById("monto").value
			}
		} else if (varAnalisis == 'false' && varConciliacion == 'true') {
			glosa = document.getElementById("glosaConciliacion").value,
					tipo = document.getElementById("tipoMovimiento").value,
					documento = document.getElementById("tipoDocumento").value,
					cliente = ""
			if ($('#tipoMovimiento option:selected').text() == 'Ingreso') {
				debe = document.getElementById("monto").value
			} else if ($('#tipoMovimiento option:selected').text() == 'Egreso') {
				haber = document.getElementById("monto").value
			}
			if ($('#tipoMovimiento option:selected').text() == 'Traspaso') {
				if ($('#tipoDocumento option:selected').text() == 'Ajuste Ingreso (+)') {
					debe = document.getElementById("monto").value
				} else if ($('#tipoDocumento option:selected').text() == 'Ajuste Egreso (-)') {
					haber = document.getElementById("monto").value
				}
			}
		} else if (varAnalisis == 'false' && varConciliacion == 'false') {
			glosa = document.getElementById("glosaSin").value, documento = "";
			tipo = document.getElementById("tipoSin").value, cliente = ""
			if ($('#tipoSin option:selected').text() == 'Debe') {
				debe = document.getElementById("monto").value
			} else if ($('#tipoSin option:selected').text() == 'Haber') {
				haber = document.getElementById("monto").value
			}
		}

		var texto = document.getElementById("cuenta").value.split('/');
		grid.addRow({
			'ID' : grid.count(true) + 1,
			'codigo' : varCodigo,
			'descripcion' : $('#cuentaContable option:selected').text(),
			'debe' : debe,
			'haber' : haber,
			'numDocumento' : document.getElementById("numDocumento").value,
			'glosa' : glosa,
			'monto' : document.getElementById("monto").value,
			'tipoMovimiento' : tipo,
			'tipoDocumento' : documento,
			'estado' : document.getElementById("estado").value,
			'fecha' : document.getElementById("fecha").value,
			'idCuenta' : document.getElementById("cuenta").value,
			'numCuenta' : $('#cuenta option:selected').text(),
			'idCliente' : cliente,
			'idEmpresa' : varIdEmpresa,
			'idUsuario' : document.getElementById("idUsuario").value,
			'numComprobante' : document.getElementById("numero").value,
			'idCuentaContable' : varIdCuentaContable
		})
		$("#empresa").prop("disabled", true);
		$("#fecha").prop("disabled", true);
		$("#glosaGeneral").prop("disabled", true);
		limpiaAdd();
		var sDebe=0;
		var sHaber=0;
		
		grid.getAll().forEach(function(item, index){
			  if(item.debe!=""){
			    sDebe=sDebe+parseInt(item.debe);
			  }else if(item.haber!=""){
			    sHaber=sHaber+parseInt(item.haber);
			  }
		})
				
		if (sHaber > sDebe) {
// 			if($('#tipoAnalisis option:selected').text() == 'Debe'|| 
// 					$('#tipoMovimiento option:selected').text() == 'Ingreso'||
// 					$('#tipoMovimiento option:selected').text() == 'Traspaso'&&
// 					$('#tipoDocumento option:selected').text() == 'Ajuste Ingreso (+)'){
				var newMonto=sHaber-sDebe;
				document.getElementById("monto").value = newMonto;	
//			}
		} else if(sDebe > sHaber){
// 			if($('#tipoAnalisis option:selected').text() == 'Haber'||
// 					$('#tipoMovimiento option:selected').text() == 'Egreso'||
// 					$('#tipoMovimiento option:selected').text() == 'Traspaso'&&
// 					$('#tipoDocumento option:selected').text() == 'Ajuste Egreso (-)'){
				var newMonto=sDebe-sHaber;
				document.getElementById("monto").value = newMonto;

//			}
		}
	}

	function limpiaAdd() {
		document.getElementById("numDocumento").value = "";
		//document.getElementById("glosaSin").value = "";
		//document.getElementById("glosaConciliacion").value = "";
		//document.getElementById("glosaAnalisis").value = "";
		document.getElementById("monto").value = "";
	}

	function Modificar() {
		var bool = $('.on').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		var sDebe = 0;
		var sHaber = 0;

		grid.getAll().forEach(function(item, index) {
			if (item.debe != "") {
				sDebe = sDebe + parseInt(item.debe);
			} else if (item.haber != "") {
				sHaber = sHaber + parseInt(item.haber);
			}
		})

		if (sDebe != sHaber) {
			if (confirm('El comprobante esta descuadrado. ¿desea guardar igualmente?')) {
				var list = grid.getAll(true)
				var submitJson = {
					id : <%=request.getParameter("id")%> ,
					numero : document.getElementById("numero").value,
					glosaGeneral : document.getElementById("glosaGeneral").value,
					fecha : document.getElementById("fecha").value,
					idEmpresa : document.getElementById("empresa").value,
					idUsuario : document.getElementById("idUsuario").value,
					borrador : true,
					movimientos : list.map(function(value) {
						return {
							id : value.ID,
							codigo : value.codigo,
							descripcion : value.descripcion,
							glosa : value.glosa,
							monto : value.monto == '' ? 0 : value.monto,
							tipoMovimiento : value.tipoMovimiento,
							tipoDocumento : value.tipoDocumento,
							estado : value.estado,
							fecha : value.fecha,
							idCuenta : value.idCuenta == '' ? 0
									: value.idCuenta,
							numComprobante : value.numComprobante,
							debe : value.debe == '' ? 0 : value.debe,
							haber : value.haber == '' ? 0 : value.haber,
							numDocumento : value.numDocumento == '' ? 0
									: value.numDocumento,
							idUsuario : value.idUsuario == '' ? 0
									: value.idUsuario,
							idCliente : value.idCliente == '' ? 0
									: value.idCliente,
							numCuenta : value.numCuenta == '' ? 0
									: value.numCuenta,
							idCuentaContable : value.idCuentaContable == '' ? 0
									: value.idCuentaContable
						}

					}),
				}
				$
						.post(
								'/byeContabilidad/rest-services/private/comprobanteContable/modificar',
								JSON.stringify(submitJson))
						.done(
								function(data) {
									if (data == 'OK') {
										alert('Se modifico exitosamente el comprobante contable');
										grid.reload();
										location.href = "index.jsp";
									} else {
										alert(data);
									}

								})
						.fail(
								function(jqxhr, settings, ex) {
									alert('No se pudo guardar el comprobante contable '
											+ ex);
								});
			} else {
				return;
			}
		} else {
			var list = grid.getAll(true)
			var submitJson = {
				id : <%=request.getParameter("id")%>,
				numero : document.getElementById("numero").value,
				glosaGeneral : document.getElementById("glosaGeneral").value,
				fecha : document.getElementById("fecha").value,
				idEmpresa : document.getElementById("empresa").value,
				idUsuario : document.getElementById("idUsuario").value,
				borrador : false,
				movimientos : list
						.map(function(value) {
							return {
								id : value.ID,
								codigo : value.codigo,
								descripcion : value.descripcion,
								glosa : value.glosa,
								monto : value.monto == '' ? 0 : value.monto,
								tipoMovimiento : value.tipoMovimiento,
								tipoDocumento : value.tipoDocumento,
								estado : value.estado,
								fecha : value.fecha,
								idCuenta : value.idCuenta == '' ? 0
										: value.idCuenta,
								numComprobante : value.numComprobante,
								debe : value.debe == '' ? 0 : value.debe,
								haber : value.haber == '' ? 0 : value.haber,
								numDocumento : value.numDocumento == '' ? 0
										: value.numDocumento,
								idUsuario : value.idUsuario == '' ? 0
										: value.idUsuario,
								idCliente : value.idCliente == '' ? 0
										: value.idCliente,
								numCuenta : value.numCuenta == '' ? 0
										: value.numCuenta,
								idCuentaContable : value.idCuentaContable == '' ? 0
										: value.idCuentaContable
							}

						}),
			}

			$
					.post(
							'/byeContabilidad/rest-services/private/comprobanteContable/modificar',
							JSON.stringify(submitJson))
					.done(
							function(data) {
								if (data == 'OK') {
									alert('Se modifico exitosamente el comprobante contable');
									grid.reload();
									location.href = "index.jsp";
								} else {
									alert(data);
								}

							})
					.fail(
							function(jqxhr, settings, ex) {
								alert('No se pudo guardar el comprobante contable '
										+ ex);
							});

		}
	}

	function Remove(x) {
		if (confirm('¿Seguro que quiere descartar el movimiento?')) {
			grid.removeRow(x.data.id);
		}

		// 		if (confirm('El movimiento se eliminara definitivamente ¿Esta seguro desea eliminar el movimiento?')) {
		// 			var submitJson = {
		// 				id : x.data.record.id
		// 			}
		// 			$.post('/byeContabilidad/rest-services/private/movimiento/delete',
		// 					JSON.stringify(submitJson)).done(function(data) {
		// 				if (data == 'OK') {
		// 					alert('Movimiento eliminado correctamente');
		// 					grid.reload();
		// 					//document.getElementById('modalclose').click(); 
		// 				} else {
		// 					alert(data);
		// 				}
		// 			}).fail(function() {
		// 				alert('Error al eliminar el movimiento');
		// 			});
		// 		}
	}

	function cargaCuenta(idCuenta) {

		var submitJson = {
			idBanco : document.getElementById("banco").value,
			idUsuario : document.getElementById("idUsuario").value,
			idEmpresa : document.getElementById("empresa").value
		}
		$.post('/byeContabilidad/rest-services/private/cuenta/getByIdBanco',
				JSON.stringify(submitJson), function(res, code) {
					var str;
					for (var i = 0, len = res.length; i < len; i++) {
						if (idCuenta == res[i].id) {
							str += "<option value="+res[i].id+" selected>"
									+ res[i].numCuenta + "</option>";

						} else {
							str += "<option value="+res[i].id+">"
									+ res[i].numCuenta + "</option>";
						}
					}
					document.getElementById("cuenta").innerHTML = str;
				}, "json");
	}

	var tipoDocumentoIngreso = [ {
		id : 'DEPOSITO',
		text : 'Depósito (+)'
	}, {
		id : 'CARGO',
		text : 'Cargo (+)'
	}, {
		id : 'AJUSTE INGRESO',
		text : 'Ajuste (+)'
	}, ]

	var tipoDocumentoEgreso = [ {
		id : 'CHEQUE',
		text : 'Cheque (-)'
	}, {
		id : 'ABONO',
		text : 'Abono (-)'
	}, {
		id : 'AJUSTE EGRESO',
		text : 'Ajuste (-)'
	}, ]
	var tipoDocumentoTraspaso = [ {

		id : 'AJUSTE INGRESO',
		text : 'Ajuste Ingreso (+)'
	}, {
		id : 'AJUSTE EGRESO',
		text : 'Ajuste Egreso (-)'
	}, ]

	$('#tipoMovimiento').on('change', function() {
		switch ($(this).val()) {
		case 'INGRESO':
			$('#tipoDocumento').html("");
			$('#tipoDocumento').select2({
				data : tipoDocumentoIngreso
			});
			break;
		case 'EGRESO':
			$('#tipoDocumento').html("");
			$('#tipoDocumento').select2({
				data : tipoDocumentoEgreso
			});
			break;

		case 'TRASPASO':
			$('#tipoDocumento').html("");
			$('#tipoDocumento').select2({
				data : tipoDocumentoTraspaso
			});
			break;
		default:
			$('#tipoDocumento').html("");
			break;
		}
	})

	back.addEventListener("click", function() {
		window.history.back();
	}, false);
	$("#tipoMovimiento").trigger('change');
</script>
</html>