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
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Agregar comprobante Contable</h1>
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
					<label>Fecha</label>
				</div>
				<div class="col-sm-3">
					<input type="date" id="fechaComprobante" name="fecha" class="in"
						required="required" />
				</div>
				<div class="col-sm-1">
					<label>Glosa</label>
				</div>
				<div class="col-sm-3">
					<input type="text" id="glosaTransaccion" name="glosaTransaccion"
						placeholder="Ingrese glosa general" required="required" class="on" />
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
					<label>Fecha</label>
				</div>
				<div class="col-sm-3">
					<input type="date" id="fecha" name="fecha"
						required="required" />
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
						onclick="Save()">Guardar</button>
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
	$(document).ready(function() {
						$("#tipoMovimiento").select2({width:'160'});
						$("#cuentaContable").select2({width:'200'});
						$("#banco").select2({width:'200'});
						$("#cuenta").select2({width:'200'});
						$("#tipoDocumento").select2({width:'160'});
						$("#estado").select2({width:'200'});
						$("#cliente").select2({width:'200'});
						numero();

						var varAnalisis;
						var varConciliacion;
						var varIdBanco;
						var varIdCuenta;
						var varCodigo;
						var varDescripcion;
						var glosa;
						var cliente;

					var submitJson = {
						idUsuario : document.getElementById("idUsuario").value}
					$.post('/byeContabilidad/rest-services/private/cuentaContable/getLista',JSON.stringify(submitJson),
									function(res, code) {
							          var str="<option>Seleccione cuenta</option>";
											for (var i = 0; i < res.length; i++) {
												str += "<option value="+res[i].id+"/"+res[i].analisis+"/"
												+res[i].conciliacion+"/"+res[i].idBanco+"/"
												+res[i].idCuenta+"/"+res[i].codigo+ "/"+res[i].descripcion+">" 
												+res[i].glosaGeneral+"</option>";
											//alert(res[i].codigo);
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
					var codigo = document.getElementById("cuentaContable").value.split("/");
					varCodigo = codigo[5]; 
					var descripcion = document.getElementById("cuentaContable").value.split("/");
					varDescripcion = descripcion[6]; 
					}, "json");
						
					
					$.post('/byeContabilidad/rest-services/private/cliente/getLista',JSON.stringify(submitJson),
								function(res, code) {
									var str;
									for (var i = 0, len = res.length; i < len; i++) {
										str += "<option value="+res[i].id+">" + res[i].rut
												+ "</option>";
									}
									document.getElementById("cliente").innerHTML = str;
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
											field : 'numCuenta',
											title : 'Cuenta',
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
											width : 160
										},
										{
											field : 'tipoMovimiento',
											title : 'TIPO MOVIMIENTO',
											width : 160
										},
										{
											field : 'tipoDocumento',
											title : 'TIPO DOCUMENTO',
											width : 160
										},
										{
											field : 'numDocumento',
											title : 'NUMERO DOCUMENTO',
											width : 160
										},
										{
											field : 'fecha',
											title : 'FECHA',
											width : 160
										},
										{
											field : 'idUsuario',
											title : 'USER',
											width : 160
										},
										{
											field : 'monto',
											title : 'MONTO',
											width : 160
										},
										{
											field : 'idCliente',
											title : 'CLIENTE',
											width : 160
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
						var codigo = document.getElementById("cuentaContable").value.split("/");
						varCodigo = codigo[5]; 
						var descripcion = document.getElementById("cuentaContable").value.split("/");
						varDescripcion = descripcion[6]; 

						if (varAnalisis == 'true' && varConciliacion == 'false') {
							$('#analisable').collapse('show');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
						} else if (varAnalisis == 'false' && varConciliacion == 'true') {
							$('#analisable').collapse('hide');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('show');
							$('#conciliacion1').collapse('show');
							$('#conciliacion2').collapse('show');
							$('#conciliacion3').collapse('show');
							cargaBanco(varIdBanco,varIdCuenta);	
						} else if(varAnalisis == 'false' && varConciliacion == 'false'){
							$('#sin').collapse('show');
							$('#analisable').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
						}else{
							$('#analisable').collapse('hide');
							$('#sin').collapse('hide');
							$('#conciliacion').collapse('hide');
							$('#conciliacion1').collapse('hide');
							$('#conciliacion2').collapse('hide');
							$('#conciliacion3').collapse('hide');
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
	 
		function Add() {
			var bool = $('.in').toArray().some(function(el) {
				return $(el).val().length < 1
			});
			
			if($('#cuentaContable option:selected').text()=='Seleccione cuenta'){
				alert("Debe seleccionar una cuenta contable");
				return;
			}
			
			if (bool) {
				alert("Todos los campos deben estar llenos");
				return;
			}
			var debe;
			var haber;

			if($('#tipoMovimiento option:selected').text()=='Ingreso'){
				debe =document.getElementById("monto").value;
			} else if($('#tipoMovimiento option:selected').text()=='Egreso'){
				haber=document.getElementById("monto").value;
			}if($('#tipoMovimiento option:selected').text()=='Traspaso'){
				if($('#tipoDocumento option:selected').text()=='Ajuste Ingreso (+)'){
					debe=document.getElementById("monto").value;
				} else if($('#tipoDocumento option:selected').text()=='Ajuste Egreso (-)'){
					haber=document.getElementById("monto").value;
				}					
			}
			
			if (varAnalisis == 'true' && varConciliacion == 'false') {
				glosa = document.getElementById("glosaAnalisis").value,
				cliente =  document.getElementById("cliente").value
			} else if (varAnalisis == 'false' && varConciliacion == 'true') {
				glosa = document.getElementById("glosaConciliacion").value,
				cliente=""
			} else if(varAnalisis == 'false' && varConciliacion == 'false'){
				glosa = document.getElementById("glosaSin").value,
				cliente=""
			}
			
			var texto = document.getElementById("cuenta").value.split('/');
			grid.addRow({
				'ID' : grid.count(true) + 1,
				'codigo' : varCodigo,
				'descripcion':$('#cuentaContable option:selected').text(),
				'debe': debe,
				'haber': haber,	
			//	'numComprobante' : document.getElementById("numComprobante").value,
				'numDocumento' : document.getElementById("numDocumento").value,
				'glosa' : glosa,
				'monto' : document.getElementById("monto").value,
				'tipoMovimiento' : document.getElementById("tipoMovimiento").value,
				'tipoDocumento' : document.getElementById("tipoDocumento").value,
				'estado' : document.getElementById("estado").value,
				'fecha' : document.getElementById("fecha").value,
				'idCuenta' : document.getElementById("cuenta").value,
				'numCuenta' : $('#cuenta option:selected').text(),
				'idCliente' : cliente,
				'idUsuario' : document.getElementById("idUsuario").value,
				'numComprobante' : document.getElementById("numero").value
			})
			limpiaAdd();
		}
		
		function limpiaAdd() {
			document.getElementById("numDocumento").value = "";
			document.getElementById("glosaSin").value = "";
			document.getElementById("glosaConciliacion").value = "";
			document.getElementById("glosaAnalisis").value = "";
			document.getElementById("monto").value = "";
			document.getElementById("fecha").value = "";
		}

		function Save() {
			if (varAnalisis == 'false' && varConciliacion == 'true'){ 
				 alert(document.getElementById("banco").value + document.getElementById("cuenta").value)
				 }
			var bool = $('.on').toArray().some(function(el) {
				return $(el).val().length < 1
			});

			if (bool) {
				alert("Todos los campos deben estar llenos");
				return;
			}
			var list = grid.getAll(true)
			var submitJson = {
				glosaGeneral : document.getElementById("glosaGeneral").value,
				fechaComprobante : document.getElementById("fecha").value,
				numTransaccion : document.getElementById("numTransaccion").value,
				glosaTransaccion : document.getElementById("glosaTransaccion").value,
				movimientos : list.map(function(value) {
					return {
						id : value.ID,
						numComprobante : value.numComprobante,
						numDocumento : value.numDocumento,
						glosa : value.glosa,
						monto : value.monto,
						tipoMovimiento : value.tipoMovimiento,
						tipoDocumento : value.tipoDocumento,
						estado : value.estado,
						fecha : value.fecha,
						idCuenta : value.idCuenta,
						idUsuario : document.getElementById("idUsuario").value
					}
				})

			}

			$.post('/conciliacionBancaria/rest-services/private/transaccion/add',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Se guardo exitosamente la transacción');
					grid.reload();
					limpiaT();
					location.href = "index.jsp";
				} else {
					alert(data);
				}

			}).fail(function(jqxhr, settings, ex) {
				alert('No se pudo guardar la Transacción ' + ex);
			});

		}
	 

		function Remove(e) {
			if (confirm('¿Seguro que quiere descartar el movimiento?')) {
				grid.removeRow(e.data.id)
			}
		}
	 function cargaCuenta(idCuenta){
			
			var submitJson = {
					idBanco : document.getElementById("banco").value,
					idUsuario :document.getElementById("idUsuario").value
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