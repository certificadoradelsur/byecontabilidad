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


<title>Conciliaciones Bancarias</title>
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
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Certificadora
			del Sur</a>

		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="/conciliacionBancaria/logout.jsp"><img
					src="../../images/exit.ico" alt="Icono" /> Salir</a></li>
		</ul>
	</nav>

	<div class="sidenav">
		<div class="dropdown">
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <img src="../../images/banco.ico"
				alt="Icono" />&nbsp;Banco
			</a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="../banco/index.jsp">Bancos</a> <a
					class="dropdown-item" href="../cuenta/index.jsp">Cuentas</a>
			</div>
		</div>
		<a href="../usuario/index.jsp"><img src="../../images/user.ico"
			alt="Icono" />&nbsp;Usuarios </a>
		<div class="dropdown">
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <img src="../../images/comprobante.ico"
				alt="Icono" />&nbsp;Transacción
			</a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="../transaccion/index.jsp">Lista</a> <a
					class="dropdown-item" href="../transaccion/ingresos.jsp">Ingreso</a>
				<a class="dropdown-item" href="../transaccion/egresos.jsp">Egreso</a>
				<a class="dropdown-item" href="../transaccion/traspasos.jsp">Traspaso</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <img src="../../images/comprobante.ico"
				alt="Icono" />&nbsp;Cartola
			</a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="../cartola/agregar.jsp">Digitar</a> <a
					class="dropdown-item" href="../cartola/importar.jsp">Importar</a> <a
					class="dropdown-item" href="../cartola/index.jsp">Lista</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <img src="../../images/movi.ico"
				alt="Icono" />&nbsp;Conciliación
			</a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="../conciliacion/conciliar.jsp">Conciliar</a>
				<a class="dropdown-item" href="../conciliacion/index.jsp">Ver
					conciliación</a>
			</div>
		</div>
		<div class="dropdown">
			<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
				id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <img src="../../images/movi.ico"
				alt="Icono" />&nbsp;No Conciliados
			</a>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
				<a class="dropdown-item" href="../conciliacion/empresa.jsp">Empresa</a>
				<a class="dropdown-item" href="../conciliacion/banco.jsp">Banco</a>
			</div>
		</div>
		<a href="../reporte/index.jsp"><img src="../../images/reporte.ico"
			alt="Icono" />&nbsp;Reporte </a>
	</div>


	<div class="main">
		<div class="container">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Lista de Transacciones</h1>
				</div>
				<input type="hidden" name="id" id="id" />
			</form>
			<div class="form-group">
				<div class="col-1"></div>
				<input type="text" id="filtro" name="filtro"
					placeholder="Filtrar por glosa" />
				<button type="button" class="btn btn-primary " id="buscar">Filtrar</button>
			</div>
			<div class="margen margin-top-10">
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
							<div class="modal-body">
								<table id="grid2"></table>
							</div>
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
											dataSource : '/conciliacionBancaria/rest-services/private/transaccion/getAll'
													,
											autoLoad : false,
											columns : [
													{
														field : 'id',
														title : 'Identificador',
														width : 100,
														hidden : true

													},
													{
														field : 'numTransaccion',
														title : 'N° Transacción',
														width : 140
													},

													{
														field : 'glosaTransaccion',
														title : 'Glosa',
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

	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la transacción? Perdera todos los movimientos contenidos en ella')) {
			var submitJson = {
				id : x.data.record.id,
				idTransaccion : x.data.record.id
			}
			$
					.post(
							'/conciliacionBancaria/rest-services/private/movimiento/eliminarMovimientosTransaccion',
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
		gridTransaccion = $('#grid2')
				.grid(
						{
							primaryKey : 'ID',
							dataSource : '/conciliacionBancaria/rest-services/private/movimiento/getAllM',

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
							'/conciliacionBancaria/rest-services/private/movimiento/delete',
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							alert('Movimiento eliminado correctamente');
							gridTransaccion.reload();
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
		gridTransaccion.reload({
			id : x.data.record.id
		});
		 document.getElementById('modal').click(); 
	}

	$('#buscar').on('click', function() {
		grid.reload({
			glosaTransaccion : $('#filtro').val(),
		});
		clear();
	});

	function clear() {
		document.getElementById("filtro").value = "";
	}
</script>
</html>