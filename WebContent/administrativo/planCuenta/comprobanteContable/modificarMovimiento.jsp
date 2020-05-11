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
		<div class="container">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Modificar movimiento</h1>
				</div>

				<div class="row">
					<div class="col-sm-3" hidden="true">
						<select class="browser-default custom-select" id="empresa"
							required="required">
						</select>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-1">
						<label> Cuenta</label>
					</div>
					<div class="col-sm-3">
						<select class="browser-default custom-select" id="cuentaContable">
						</select>
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
				<div class="row">
					<div class="col-sm-1">
						<label>Monto</label>
					</div>
					<div class="col-sm-3">
						<input type="number" id="monto" name="monto" class="in"
							placeholder="Ingrese monto" required="required" min="0"
							pattern="^[0-9]+" />
					</div>
					<div class="col-sm-1">
						<label>Fecha</label>
					</div>
					<div class="col-sm-3">
						<input type="date" id="fecha" name="fecha" class="in"
							required="required" disabled />
					</div>
				</div>
				<br>
				<div class="form-row collapse" id="sin">
					<div class="col-sm-1">
						<label>Glosa</label>
					</div>
					<div class="col-sm-3">
						<input type="text" id="glosaSin" name="glosaSin"
							placeholder="Ingrese glosa" required="required" />
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
						<label>Glosa</label>
					</div>
					<div class="col-sm-3">
						<input type="text" id="glosaAnalisis" name="glosaAnalisis"
							placeholder="Ingrese glosa" required="required" />
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
				</div>
				<br>
				<div class="row collapse" id="conciliacion1">
					<div class="col-sm-1">
						<label>Documento</label>
					</div>
					<div class="col-sm-3">
						<input type="number" id="numDocumento" name="numDocumento"
							placeholder="Ingrese Número" required="required" min="0"
							pattern="^[0-9]+" />
					</div>
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

			</form>
		</div>
	</div>
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
$(document).ready(function () {
	$("#cuentaContable").select2({width : '200'});
	$("#banco").select2({width : '200'});
	$("#cuenta").select2({width : '200'});
	$("#tipoMovimiento").select2({width : '200'});
	$("#tipoDocumento").select2({width : '200'});
	$("#estado").select2({width : '200'});
	$("#empresa").select2({width : '200'});
	$("#cliente").select2({width : '200'});
	
	var varAnalisis;
	var varConciliacion;
	var varIdBanco;
	var varIdCuenta;
	var varCodigo;
	var varIdEmpresa;
	var glosa;
	var cliente;
	var varIdCuentaContable;
	
	
	var submitjson = {id:"<%=request.getParameter("id")%> " };
						
	                            $ .post('/byeContabilidad/rest-services/private/movimiento/getById',
										JSON.stringify(submitjson))
								.done(
										function(data) {
											document.getElementById("numDocumento").value = data.numDocumento;
											document.getElementById("monto").value = data.monto;
											document.getElementById("banco").value = data.idBanco;
											document.getElementById("cuenta").value = data.idCuenta;
											document.getElementById("tipoMovimiento").value = data.tipoMovimiento;
											document.getElementById("numDocumento").value = data.numDocumento;
											document.getElementById("estado").value = data.estado;
											document.getElementById("fecha").value = data.fecha;

											cargaEmpresa(data.idEmpresa,data.idCuentaContable);
											cargaCliente(data.idCliente);
											
											if (data.analisis == 'true' && data.conciliacion == 'false') {
												$('#analisable').collapse('show');
												$('#sin').collapse('hide');
												$('#conciliacion').collapse('hide');
												$('#conciliacion1').collapse('hide');
												$('#conciliacion2').collapse('hide');
												$('#conciliacion3').collapse('hide');
												document.getElementById("glosaAnalisis").value = data.glosa;
											} else if (data.analisis  == 'false'
													&& data.conciliacion == 'true') {
												$('#analisable').collapse('hide');
												$('#sin').collapse('hide');
												$('#conciliacion').collapse('show');
												$('#conciliacion1').collapse('show');
												$('#conciliacion2').collapse('show');
												$('#conciliacion3').collapse('show');
												document.getElementById("glosaConciliacion").value = data.glosa;
												cargaBanco(data.idBanco, data.idCuenta);
											} else if (data.analisis == 'false'
													&& data.conciliacion == 'false') {
												$('#sin').collapse('show');
												$('#analisable').collapse('hide');
												$('#conciliacion').collapse('hide');
												$('#conciliacion1').collapse('hide');
												$('#conciliacion2').collapse('hide');
												$('#conciliacion3').collapse('hide');
												document.getElementById("glosaSin").value = data.glosa;
											} else {
												$('#analisable').collapse('hide');
												$('#sin').collapse('hide');
												$('#conciliacion').collapse('hide');
												$('#conciliacion1').collapse('hide');
												$('#conciliacion2').collapse('hide');
												$('#conciliacion3').collapse('hide');
											}
										})
								.fail(
										function(jqxhr, settings, ex) {
											alert('No se pudo modificar el movimiento'
													+ ex);
										});

					});

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
		}, "json");
	}

	function cargaCuentaContable(idEmpresa, idCuentaContable) {

		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value,
			idEmpresa : idEmpresa
		}
		var idCuentaCon = idCuentaContable;

		$
				.post(
						'/byeContabilidad/rest-services/private/cuentaContable/getByIdEmpresa',
						JSON.stringify(submitJson),
						function(res, code) {
							var str;
							for (var i = 0; i < res.length; i++) {
								if (idCuentaCon == res[i].id) {
									str += "<option value="+res[i].id+"/"+res[i].analisis+"/"
					+res[i].conciliacion+"/"+res[i].idBanco+"/" +res[i].idCuenta+"/"+res[i].codigo+ 
					"/"+res[i].idEmpresa+" selected>"
											+ res[i].glosaGeneral + "</option>";
								} else {
									str += "<option value="+res[i].id+"/"+res[i].analisis+"/"
		+res[i].conciliacion+"/"+res[i].idBanco+"/"
		+res[i].idCuenta+"/"+res[i].codigo+ "/"+res[i].idEmpresa+">"
											+ res[i].glosaGeneral + "</option>";
								}
							}

							document.getElementById("cuentaContable").innerHTML = str;
							var idCuentaContable = document.getElementById("cuentaContable").value.split("/");
							varIdCuentaContable = idCuentaContable[0];
							var analisis = document.getElementById("cuentaContable").value.split("/");
							varAnalisis = analisis[1];
							var coinciliacion = document.getElementById("cuentaContable").value.split("/");
							varConciliacion = coinciliacion[2];
							var banco = document.getElementById("cuentaContable").value.split("/");
							varIdBanco = banco[3];
							var cuenta = document.getElementById("cuentaContable").value.split("/");
							varIdCuenta = cuenta[4];
							var codigo = document.getElementById("cuentaContable").value.split("/");
							varCodigo = codigo[5];
							var idEmpresa = document.getElementById("cuentaContable").value.split("/");
							varIdEmpresa = idEmpresa[6];
						}, "json");

	}

	function cargaCliente(idCliente) {
		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}
		$.post('/byeContabilidad/rest-services/private/cliente/getLista', JSON
				.stringify(submitJson), function(res, code) {
			var str;
			for (var i = 0, len = res.length; i < len; i++) {
				if (idCliente == res[i].id) {
					str += "<option value="+res[i].id+" selected>" + res[i].rut
							+ "</option>";

				} else {
					str += "<option value="+res[i].id+">" + res[i].rut
							+ "</option>";
				}
			}
			document.getElementById("cliente").innerHTML = str;

		}, "json");
	}

	$('#cuentaContable') .on( 'change',
					function() {
						var idCuentaContable = document .getElementById("cuentaContable").value.split("/");
						varIdCuentaContable = idCuentaContable[0];
						var analisis = document .getElementById("cuentaContable").value.split("/");
						varAnalisis = analisis[1];
						var coinciliacion = document.getElementById("cuentaContable").value.split("/");
						varConciliacion = coinciliacion[2];
						var banco = document.getElementById("cuentaContable").value.split("/");
						varIdBanco = banco[3];
						var cuenta = document.getElementById("cuentaContable").value.split("/");
						varIdCuenta = cuenta[4];
						var codigo = document.getElementById("cuentaContable").value.split("/");
						varCodigo = codigo[5];
						var idEmpresa = document.getElementById("cuentaContable").value.split("/");
						varIdEmpresa = idEmpresa[6];

						if (varAnalisis == 'true' && varConciliacion == 'false') {
							$('#analisable').collapse('show');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
						} else if (varAnalisis == 'false'
								&& varConciliacion == 'true') {
							$('#analisable').collapse('hide');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('show');
							$('#conciliacion1').collapse('show');
							$('#conciliacion2').collapse('show');
							$('#conciliacion3').collapse('show');
							cargaBanco(varIdBanco, varIdCuenta);
						} else if (varAnalisis == 'false'
								&& varConciliacion == 'false') {
							$('#sin').collapse('show');
							$('#analisable').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
						} else {
							$('#analisable').collapse('hide');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
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

	function cargaCuenta(idCuenta) {

		var submitJson = {
			idBanco : document.getElementById("banco").value,
			idUsuario : document.getElementById("idUsuario").value
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

	function modificar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (bool) {
			alert("Los campos deben estar llenos");
			return;
		}
		
		if (varAnalisis == 'true' && varConciliacion == 'false') {
			glosa = document.getElementById("glosaAnalisis").value,
					cliente = document.getElementById("cliente").value
		} else if (varAnalisis == 'false' && varConciliacion == 'true') {
			glosa = document.getElementById("glosaConciliacion").value,
			        cliente = ""
		} else if (varAnalisis == 'false' && varConciliacion == 'false') {
			glosa = document.getElementById("glosaSin").value, cliente = ""
		}
		var submitJson = {
			id : <%=request.getParameter("id")%> ,
			glosa : glosa,
			monto : document.getElementById("monto").value == '' ? 0 : document.getElementById("monto").value,
			tipoMovimiento : document.getElementById("tipoMovimiento").value,
			tipoDocumento : document.getElementById("tipoDocumento").value,
			estado : document.getElementById("estado").value,
			idCuenta : document.getElementById("cuenta").value == '' ? 0 : document.getElementById("cuenta").value,
			numDocumento :document.getElementById("numDocumento").value == '' ? 0 : document.getElementById("numDocumento").value,
			idUsuario : document.getElementById("idUsuario").value == '' ? 0 : document.getElementById("idUsuario").value,
			idCuentaContable : varIdCuentaContable == '' ? 0 : varIdCuentaContable,
			idCliente: cliente == '' ? 0 : cliente,
			idUsuario : document.getElementById("idUsuario").value
 
		}
		$
				.post(
						'/byeContabilidad/rest-services/private/movimiento/update',
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