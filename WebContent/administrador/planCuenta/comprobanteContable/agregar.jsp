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
		<form name="formulario" id="formulario">
			<div class="container">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Agregar comprobante Contable</h1>
				</div>

				<div class="row" >
				<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Número</label>
					<input type="number" id="numero" name="numero"
						placeholder="Ingrese Número" required="required" class="on" />
				</div>
				<br>
				<div class="row">
								<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Fecha</label>
					<input type="date" id="fecha" name="fecha" class="in"
						required="required" />
				</div>
				<br>
				<div class="row">
								<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Glosa
						general</label> <input type="text" id="glosaTransaccion" class="col-sm-4"
						name="glosaTransaccion" placeholder="Ingrese glosa general"
						required="required" class="on" />
				</div>
				<br>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Tipo</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="tipoMovimiento"
							required="required">
							<option value="INGRESO">Ingreso</option>
							<option value="EGRESO">Egreso</option>
							<option value="TRASPASO">Traspaso</option>
						</select>
					</div>
				</div>
				<br>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Cuenta Contable</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="cuentaContable">
						</select>
					</div>
				</div>
				<br>
					
				<div class="row collapse" id="analisable">
								<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Analizable</label>
					<input type="text" id="analizable" name="analizable"
						placeholder="Ingrese rut" required="required" />
				</div>
				
				<div class="row collapse" id="conciliacion">
								<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Glosa</label>
					<input type="text" id="glosa" class="col-sm-4" name="glosa"
						class="in" placeholder="Ingrese glosa" required="required" />
				</div>
				<br>
				<div class="row collapse" id="conciliacion1">
								<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">N°
						Documento</label> <input type="number" id="numDocumento"
						name="numDocumento" class="in" placeholder="Ingrese Número"
						required="required" min="0" pattern="^[0-9]+" />
				</div>
				<br>
				<div class="row collapse" id="conciliacion2">
				<label>&nbsp;&nbsp;&nbsp;</label>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Monto</label>
					<input type="number" id="monto" name="monto" class="in"
						placeholder="Ingrese monto" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
				<br>
				<div class="row collapse" id="conciliacion3">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Banco</label>
					<div class="col-3">
						<select class="browser-default custom-select d-block w-100"
							id="banco" required="required">
							<option value="1">Estado</option>
						</select>
					</div>
				</div>
				<br>
				<div class="row collapse" id="conciliacion4">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;N° cuenta</label>
					<div class="col-3">
						<select class="browser-default custom-select " id="cuenta"
							required="required">
						</select>
					</div>
				</div>
<!-- 				<br> -->
<!-- 				<div class="row collapse" id="conciliacion5"> -->
<!-- 					<label for="colFormLabel" class="col-sm-2 col-form-label"> -->
<!-- 						&nbsp; &nbsp;Movimiento</label> -->
<!-- 					<div class="col-3"> -->
<!-- 						<select class="browser-default custom-select" id="tipoMovimiento" -->
<!-- 							required="required"> -->
<!-- 							<option value="INGRESO">Ingreso</option> -->
<!-- 						</select> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<br>
				<div class="row collapse" id="conciliacion6">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Documento</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="tipoDocumento"
							required="required">
						</select>
					</div>
				</div>
				<br>
				<div class="row collapse " id="conciliacion7">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Estado</label>
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
						<button type="button" class="btn btn-primary btn-lg btn-block"
							onclick="Save()">Guardar</button>
					</div>

					<div class="col-xs-6 col-md-2">
						<button class=" btt btn btn-primary btn-lg btn-block"
							type="button" id="back">Cancelar</button>
					</div>
				</div>
			</div>
			<br>
		</form>
	</div>

	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#tipoMovimiento").select2({width:'200'});
						$("#cuentaContable").select2({width:'200'});
						$("#banco").select2({width:'200'});
						$("#cuenta").select2({width:'200'});
						$("#tipoDocumento").select2({width:'200'});
						$("#estado").select2({width:'200'});
						numero();

						var varAnalisis;
						var varConciliacion;
						var varIdBanco;
						var varIdCuenta;
						$.post('/byeContabilidad/rest-services/private/cuentaContable/getLista',
										function(res, code) {
							          var str="<option>Seleccione cuenta</option>";
											for (var i = 0; i < res.length; i++) {
												str += "<option value="+res[i].id+"/"+res[i].analisis+"/"
												+res[i].conciliacion+"/"+res[i].idBanco+"/"
												+res[i].idCuenta+">" + res[i].glosaGeneral + "</option>";
											}

					document.getElementById("cuentaContable").innerHTML = str;
					var analisis = document.getElementById("cuentaContable").value.split("/");
					varAnalisis = analisis[1];
					var coinciliacion = document.getElementById("cuentaContable").value.split("/");
					varConciliacion = coinciliacion[2];
					var banco = document.getElementById("cuentaContable").value.split("/");
					varIdBanco = banco[3];
					var cuenta = document.getElementById("cuentaContable").value.split("/");
					varIdCuenta = cuenta[4]; 
					}, "json");
	});
	
	
	

	$('#cuentaContable').on('change',function() {

						var analisis = document.getElementById("cuentaContable").value.split("/");
						varAnalisis = analisis[1];
						var coinciliacion = document.getElementById("cuentaContable").value.split("/");
						varConciliacion = coinciliacion[2];
						var banco = document.getElementById("cuentaContable").value.split("/");
						varIdBanco = banco[3];
						var cuenta = document.getElementById("cuentaContable").value.split("/");
						varIdCuenta = cuenta[4];

						if (varAnalisis == 'true' && varConciliacion == 'false') {
							$('#analisable').collapse('show');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
							$('#conciliacion4').collapse('hide');
							$('#conciliacion6').collapse('hide');
							$('#conciliacion7').collapse('hide');
						} else if (varAnalisis == 'false' && varConciliacion == 'true') {
							$('#analisable').collapse('hide');
							$('#conciliacion').collapse('show');
							$('#conciliacion1').collapse('show');
							$('#conciliacion2').collapse('show');
							$('#conciliacion3').collapse('show');
							$('#conciliacion4').collapse('show');
							$('#conciliacion6').collapse('show');
							$('#conciliacion7').collapse('show');
							cargaBanco(varIdBanco,varIdCuenta);	
						} else{
							$('#analisable').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
							$('#conciliacion4').collapse('hide');
							$('#conciliacion6').collapse('hide');
							$('#conciliacion7').collapse('hide');
						}
	 });

	 function cargaBanco(idBanco,idCuenta){
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
	
	 function cargaCuenta(idCuenta){
			
			var submitJson = {
					idBanco : document.getElementById("banco").value
				}
				$.post('/byeContabilidad/rest-services/private/cuenta/getByIdBanco',
								JSON.stringify(submitJson),
								function(res, code) {
									var str ;
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
	
	function numero() {
		$
				.post(
						'/byeContabilidad/rest-services/private/comprobanteContable/getMaxNumero')
				.done(function(data) {
					document.getElementById("numero").value = data + 1;
				}).fail(function(jqxhr, settings, ex) {
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
		id : 'CHEQUE',
		text : 'Cheque (-)'
	}, {
		id : 'CARGO',
		text : 'Cargo (+)'
	}, {
		id : 'DEPOSITO',
		text : 'Depósito (+)'
	}, {
		id : 'ABONO',
		text : 'Abono (-)'
	}, {
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
	$("#tipoMovimiento").trigger('change');
</script>
</html>