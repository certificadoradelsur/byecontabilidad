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
<%@ include file = "../../../complementos/nav.jsp" %>
	<div class="container-lg">
		<form name="formulario" id="formulario"><input type="hidden" name="id" id="id" /></form>
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Lista de comprobantes contable</h1>
				</div>
				<div>
				<button type="button" class="btn btn-primary " onclick="agregar()">Agregar</button>
		</div>
		<br>
<div class="form-group">
				<div class="col-1"></div>
				<input type="text" id="filtro" name="filtro"
					placeholder="Filtrar por glosa" />
				<button type="button" class="btn btn-primary " id="buscar">Filtrar</button>
			</div>
			<div class="table-responsive">
				<table id="grid"></table>
			</div>


               <div class="container">
				<!-- Trigger the modal with a button -->
				<button hidden="true" type="button" class="btn btn-info btn-lg"
					id="modal" data-toggle="modal" data-target="#myModal">Open
					Modal</button>
				<!-- Modal -->
				<div class="modal fade bd-example-modal-xl" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalCenterTitle">Movimientos</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" id="modalclose">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
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
						grid = $('#grid')
								.grid(
										{
											primaryKey : 'ID',
											dataSource : "/byeContabilidad/rest-services/private/comprobanteContable/getAll?idUsuario="+ document.getElementById('idUsuario').value+"",
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
														sortable : true
													},
													{
														field : 'fecha',
														title : 'Fecha',
														sortable : true
													},
													{
														field : 'nombreEmpresa',
														title : 'Empresa',
														sortable : true
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
														width : 100,
														title : 'Modificar',
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

					});

	function agregar() {
		location.href = "agregar.jsp";
	}
	
	
	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la transacción? Perdera todos los movimientos contenidos en ella')) {
			var submitJson = {
				id : x.data.record.id,
				idTransaccion : x.data.record.id
			}
			$
					.post(
							'/byeContabilidad/rest-services/private/movimiento/eliminarMovimientosTransaccion',
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							('Transacción eliminada correctamente');
							grid.reload();
						} else {
							alert(data);
						}
					}).fail(function() {
						alert('Error al eliminar la Transacción');
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
										sortable : true
									},
									{
										field : 'monto',
										title : 'Monto',
										sortable : true
									},
									{
										field : 'fecha',
										title : 'Fecha',
										sortable : true
									},
									{
										field : 'nombreBanco',
										title : 'Banco',
										sortable : true
									},
									{
										field : 'numCuenta',
										title : 'N° Cuenta',
										sortable : true
									},
									{
										field : 'tipoMovimiento',
										title : 'Movimiento',
										sortable : true
									},
									{
										field : 'numDocumento',
										title : 'N° Documento',
										sortable : true
									},
									{
										field : 'tipoDocumento',
										title : 'Documento',
										sortable : true
									},
									{
										width : 100,
										title : 'Modificar',
										tmpl : '<span class="material-icons gj-cursor-pointer">edit</span>',
										align : 'center',
										events : {
											'click' : modificarMovimiento
										}
									},
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
			$
					.post(
							'/byeContabilidad/rest-services/private/movimiento/delete',
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
		gridComprobante.reload({
			id : x.data.record.id
		});
		 document.getElementById('modal').click(); 
	}

	$('#buscar').on('click', function() {
		grid.reload({
			glosaGeneral : $('#filtro').val(),
		});
		clear();
	});
	
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