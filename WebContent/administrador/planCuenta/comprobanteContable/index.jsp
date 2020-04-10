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
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" />


<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.js"
	type="text/javascript">
	$('.dropdown-toggle').dropdown()
</script>

</head>
<body>
	<%@ include file="../../../complementos/nav.jsp"%>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<input type="hidden" name="id" id="id" />
		</form>
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
			<h1 class="h2">Lista de comprobantes contable</h1>
		</div>
		<div>
			<button type="button" class="btn btn-primary " onclick="agregar()">Agregar</button>
		</div>
		<br>
		<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-2">
					<div class="form-row">
						<label for="glosa">&nbsp;&nbsp;Glosa</label>
					</div>
					<input type="text" id="filtro" name="filtro"
						placeholder="Filtrar por glosa" class="form-control" />
				</div>
				<div class="form-group col-md-2">
						<div class="form-row">
							<label for="desde">&nbsp;&nbsp;Desde</label>
						</div>
						<input type="date" id="desde" name="desde"
							class="form-control" class="in" />
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="hasta">&nbsp; &nbsp;Hasta</label>
						</div>
						<input type="date" id="hasta" name="hasta"
							class="form-control" class="in" />
					</div>
				<div class="form-group col-md-3">
					<div class="form-row clo md 3">
						<label>&nbsp;</label>
					</div>
					<button type="button" class="btn btn-primary " id="buscar">Filtrar</button>
				</div>
		<div class="table-responsive">
			<table id="grid"></table>
		</div> 
    </div>
    </div>

		<div class="container">
			<!-- Trigger the modal with a button -->
			<button hidden="true" type="button" class="btn btn-info btn-lg"
				id="modal" data-toggle="modal" data-target="#myModal">Open
				Modal</button>
			<!-- Modal -->
			<div class="modal fade bd-example-modal-xl" id="myModal"
				tabindex="-1" role="dialog"
				aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-centered"
					role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalCenterTitle">Comprobante
								contable</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close" id="modalclose">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">

							<div class="row">
									<div class="col-sm-1">
									<input type="text" id="idComprobante" hidden=true />
								</div>
								<div class="col-sm-9"></div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-primary" onclick="modificarComprobante()">Modificar</button>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-1"></div>
								<div class="col-sm-1">
									<label>Número</label>
								</div>
								<div class="col-sm-3">
									<input type="text" id="numero" readonly />
								</div>
								<div class="col-sm-1">
									<label>Glosa</label>
								</div>
								<div class="col-sm-3">
									<input type="text" id="glosaGeneral" readonly />
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-sm-1"></div>
								<div class="col-sm-1">
									<label>Fecha</label>
								</div>
								<div class="col-sm-3">
									<input type="date" id="fecha" readonly />
								</div>
								<div class="col-sm-1">
									<label>Empresa</label>
								</div>
								<div class="col-sm-3">
									<input type="text" id="empresa" readonly />
								</div>
							</div>
						</div>

						<br>
						<h6 class="modal-title" id="exampleModalCenterTitle">&nbsp;&nbsp;&nbsp;Movimientos</h6>
						<br>
						<div class="table-responsive">
							<table id="grid2"></table>

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
	$(document)
			.ready(
					function() {
						tablaP();	
						
						fecha();
						
					});

	function fecha(){
		var now = new Date();
	    var month = ("0" + (now.getMonth() + 1)).slice(-2);
	    var today = now.getFullYear()+"-"+(month)+"-"+("01") ;
	    $("#desde").val(today);
	    
		var now = new Date();  
	    var month = ("0" + (now.getMonth() + 1)).slice(-2);
	    var dia =new Date(now.getFullYear() || new Date().getFullYear(), month, 0).getDate();
	    var today2 = now.getFullYear()+"-"+(month)+"-"+(dia) ;
	    $("#hasta").val(today2);
	    cargaTabla();
	}
	function cargaTabla(){
		grid = $('#grid')
		.grid(
				{
					primaryKey : 'ID',
					dataSource : "/byeContabilidad/rest-services/private/comprobanteContable/getAll?idUsuario="
							+ document
									.getElementById('idUsuario').value
							+ "",
					autoLoad : false,
					columns : [
							{
								field : 'id',
								title : 'Identificador',
								width : 100,
								hidden : true

							},
							{
								field : 'numero',
								title : 'N° Comprobante',
								width : 140
							},

							{
								field : 'glosaGeneral',
								title : 'Glosa',
								sortable : true,
								width : 140
							},
							{
								field : 'fecha',
								title : 'Fecha',
								width : 140
							},
							{
								field : 'nombreEmpresa',
								title : 'Empresa',
								width : 160
							},
							{
								field : 'borrador',
								title : 'Borrador',
								width : 140
							},
							{
								width : 100,
								title : 'Ver más',
								tmpl : '<span class="material-icons gj-cursor-pointer">remove_red_eye</span>',
								align : 'center',
								events : {
									'click' : ver
								}
							},
							{
								width : 160,
								title : 'Modificar encabezado',
								tmpl : '<span class="material-icons gj-cursor-pointer">edit</span>',
								align : 'center',
								events : {
									'click' : modificar
								}
							},
							{
								width : 100,
								title : 'Eliminar',
								tmpl : '<span class="material-icons gj-cursor-pointer">delete</span>',
								align : 'center',
								events : {
									'click' : eliminar
								}
							}, ],
					pager : {
						limit : 10
					}
				});
		grid.reload({
			glosaGeneral : $('#filtro').val(),
			fechaDesde : $('#desde').val(),
			fechaHasta :$('#hasta').val()
		});
	}
	
	function agregar() {
		location.href = "agregar.jsp";
	}

	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la el comprobante? Perdera todos los movimientos contenidos en el')) {
			var submitJson = {
				id : x.data.record.id,
				idTransaccion : x.data.record.id
			}
			$
					.post(
							'/byeContabilidad/rest-services/private/movimiento/eliminarMovimientosComprobante',
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							('Comprobante contable eliminado correctamente');
							grid.reload();
						} else {
							alert(data);
						}
					}).fail(function() {
						alert('Error al eliminar el comprobante contable');
					});
		}

	}

	function tablaP() {
		gridComprobante = $('#grid2')
				.grid(
						{
							primaryKey : 'ID',
							dataSource : '/byeContabilidad/rest-services/private/movimiento/getAllM',

							autoLoad : false,
							columns : [
									{
										field : 'numComprobante',
										title : 'Comprobante',
										sortable : true
									},
									{
										field : 'glosa',
										title : 'Glosa',
										width : 140
									},
									{
										field : 'monto',
										title : 'Monto',
										width : 140
									},
									{
										field : 'fecha',
										title : 'Fecha',
										width : 140
									},
									{
										field : 'nombreBanco',
										title : 'Banco',
										width : 140
									},
									{
										field : 'numCuenta',
										title : 'N° Cuenta',
										width : 140
									},
									{
										field : 'tipoMovimiento',
										title : 'Movimiento',
										width : 140
									},
									{
										field : 'numDocumento',
										title : 'N° Documento',
										width : 140
									},
									{
										field : 'tipoDocumento',
										title : 'Documento',
										width : 140
									},
// 									{
// 										width : 160,
// 										title : 'Modificar movimiento',
// 										tmpl : '<span class="material-icons gj-cursor-pointer">edit</span>',
// 										align : 'center',
// 										events : {
// 											'click' : modificarMovimiento
// 										}
// 									},
									{
										width : 80,
										title : 'Eliminar',
										tmpl : '<span class="material-icons gj-cursor-pointer">delete</span>',
										align : 'center',
										events : {
											'click' : eliminarMovimiento
										}
									}, ],
							pager : {
								limit : 5
							}
						})
	}

	function eliminarMovimiento(x) {
		if (confirm('¿Esta seguro desea eliminar el movimiento?')) {
			var submitJson = {
				id : x.data.record.id
			}
			$.post('/byeContabilidad/rest-services/private/movimiento/delete',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Movimiento eliminado correctamente');
					gridComprobante.reload();
					//document.getElementById('modalclose').click(); 
				} else {
					alert(data);
				}
			}).fail(function() {
				alert('Error al eliminar el movimiento');
			});
		}
	}

	function ver(x) {

		var submitjson = {
			id : x.data.record.id
		};
		
		$
				.post(
						'/byeContabilidad/rest-services/private/comprobanteContable/getById',
						JSON.stringify(submitjson))
				.done(
						function(data) {
							document.getElementById("idComprobante").value = data.id;
							document.getElementById("numero").value = data.numero;
							document.getElementById("glosaGeneral").value = data.glosaGeneral;
							document.getElementById("fecha").value = data.fecha;
							document.getElementById("empresa").value = data.nombreEmpresa;
						})
				.fail(
						function(jqxhr, settings, ex) {
							alert('No se pudo cargar el comprobante contable '
									+ ex);
						});

		gridComprobante.reload({
			id : x.data.record.id
		});
		document.getElementById('modal').click();
	}

	$('#buscar').on('click', function() {
		var fechaDesde = new Date(document.getElementById('desde').value);
		var fechaHasta = new Date(document.getElementById('hasta').value);
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;}
		grid.reload({
			glosaGeneral : $('#filtro').val(),
			fechaDesde : $('#desde').val(),
			fechaHasta :$('#hasta').val()
		});
		clear();
	});
	
	function modificarComprobante(e) {
		document.getElementById("id").value = document.getElementById("idComprobante").value;
		document.getElementById("formulario").action = 'modificarComprobante.jsp';
		document.getElementById("formulario").method = 'POST';
		document.getElementById("formulario").submit();
	}
	
	function modificar(e) {
		document.getElementById("id").value = e.data.record.id;
		document.getElementById("formulario").action = 'modificar.jsp';
		document.getElementById("formulario").method = 'POST';
		document.getElementById("formulario").submit();
	}

	function modificarMovimiento(e) {
		document.getElementById("id").value = e.data.record.id;
		document.getElementById("formulario").action = 'modificarMovimiento.jsp';
		document.getElementById("formulario").method = 'POST';
		document.getElementById("formulario").submit();
	}

	function clear() {
		document.getElementById("filtro").value = "";
	}
</script>
</html>