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

	<%@ include file="../../../complementos/nav2.jsp"%>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Cartolas no conciliadas (BANCO)</h1>
			</div>
			<input type="hidden" name="id" id="id" />
		</form>

		<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-2">
					<div class="form-row">
						<label for="desde">&nbsp;&nbsp;Desde</label>
					</div>
					<input type="date" id="filtro1" name="filtro1"
						placeholder="Filtrar por fecha" class="form-control" />
				</div>
				<div class="form-group col-md-2">
					<div class="form-row">
						<label for="hasta">&nbsp; &nbsp;Hasta</label>
					</div>
					<input type="date" id="filtro2" name="filtro2" class="form-control" />
				</div>

				<div class="form-group col-md-2">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa"
						required="required">
					</select>
				</div>

				<div class="form-group col-md-2">
					<label for="banco">&nbsp;Banco</label> <select
						class="browser-default custom-select" id="banco"
						required="required">
						<option value="1">Estado</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<label for="cuenta">&nbsp;N° Cuenta</label> <select
						class="browser-default custom-select" id="cuenta"
						required="required">
					</select>
				</div>
				<div class="form-group col-md-2">
					<div class="form-row">
						<label>&nbsp;</label>
					</div>
					<button type="button" class="btn btn-primary" id="buscar">Filtrar</button>
				</div>
			</div>
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
			<div class="modal fade bd-example-modal-xl" id="myModal"
				tabindex="-1" role="dialog"
				aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-centered"
					role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalCenterTitle">Movimientos
								no conciliados</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
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
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#banco").select2();
						$("#cuenta").select2();
						$("#empresa").select2({
							width : '180'
						});

						var submitJson = {
							idUsuario : document.getElementById("idUsuario").value
						}
						$
								.post(
										'/byeContabilidad/rest-services/private/empresa/getLista',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].razonSocial
														+ "</option>";
											}
											document.getElementById("empresa").innerHTML = str;

											// 											if (document.getElementById("empresa").value != "") {
											// 												grid.reload({
											// 													idEmpresa : $('#empresa').val(),
											// 													idBanco : $('#banco').val()
											// 												});
											// 											}
										}, "json");
						var submitJson = {
							idUsuario : document.getElementById("idUsuario").value
						}

						$
								.post(
										'/byeContabilidad/rest-services/private/banco/getLista',
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].nombre
														+ "</option>";
											}
											document.getElementById("banco").innerHTML = str;
										}, "json");

						fecha();

					});

	function fecha() {
		var now = new Date();
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
		var today = now.getFullYear() + "-" + (month) + "-" + ("01");
		$("#filtro1").val(today);

		var now = new Date();
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
		var dia = new Date(now.getFullYear() || new Date().getFullYear(),
				month, 0).getDate();
		var today2 = now.getFullYear() + "-" + (month) + "-" + (dia);
		$("#filtro2").val(today2);
		cargaTabla();
	}

	function cargaTabla() {
		grid = $('#grid')
				.grid(
						{
							primaryKey : 'ID',
							dataSource : "/byeContabilidad/rest-services/private/noConciliadoCartola/getAll?idUsuario="
									+ document.getElementById('idUsuario').value
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
										field : 'fechaT',
										title : 'Fecha',
										sortable : true,
										hidden : true
									},
									{
										field : 'numCartola',
										title : 'N° Cartola',
										sortable : true

									},
									{
										field : 'descripcion',
										title : 'Descripcion',
										sortable : true
									},
									{
										field : 'monto',
										title : 'Monto',
										sortable : true
									},
									{
										field : 'tipoMovimiento',
										title : 'T. Movimiento',
										sortable : true
									},
									{
										field : 'numDocumento',
										title : 'N° documento',
										sortable : true
									},

									{
										field : 'fecha',
										title : 'Fecha',
										sortable : true
									},
									{

										width : 100,
										title : 'Conciliar',
										tmpl : '<span class="material-icons gj-cursor-pointer">swap_horiz</span>',
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

		busca();

	}

	function busca() {
		if (document.getElementById("empresa").value != "") {
			grid.reload({
				idEmpresa : $('#empresa').val(),
				fechaInicial : $('#filtro1').val(),
				fechaFinal : $('#filtro2').val(),
				idBanco : $('#banco').val()
			});
		}
	}

	$('#buscar')
			.on(
					'click',
					function() {
						if (document.getElementById('filtro1').value == ''
								|| document.getElementById('filtro2').value == '') {
							alert('Debe ingresar fecha de inicia y hasta');
							return;
						}
						var fechaInicial = new Date(document
								.getElementById('filtro1').value);
						var fechaFinal = new Date(document
								.getElementById('filtro2').value);
						var idBanco = document.getElementById('banco').value;
						var idCuenta = document.getElementById('cuenta').value;
						if (fechaInicial > fechaFinal) {
							alert('Fecha inicial no debe ser mayor que fecha final');
							return;
						}

						if ($('#cuenta option:selected').text() == 'Seleccione cuenta') {
							alert("Debe seleccionar una cuenta");
							return;
						}

						if (document.getElementById("empresa").value != "") {
							grid.reload({
								idEmpresa : $('#empresa').val(),
								fechaInicial : $('#filtro1').val(),
								fechaFinal : $('#filtro2').val(),
								idBanco : $('#banco').val(),
								idCuenta : $('#cuenta').val()
							});
							//clear();
						}
					});

	function ver(x) {
		tablaP();
		gridMovimientos.reload({
			monto : x.data.record.monto,
			idNoConciliadoCartola : x.data.record.id
		});
		document.getElementById('modal').click();
	}

	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la cartola no Conciliada?')) {
			var submitJson = {
				id : x.data.record.id
			}
			$
					.post(
							'/byeContabilidad/rest-services/private/noConciliadoCartola/delete',
							JSON.stringify(submitJson))
					.done(
							function(data) {
								if (data == 'OK') {
									alert('Cartola no conciliada eliminada correctamente');
									grid.reload();
								} else {
									alert(data);
								}
							}).fail(function() {
						alert('Error al eliminar la cartola no conciliada');
					});
		}
	}

	function tablaP() {
		gridMovimientos = $('#grid2')
				.grid(
						{
							primaryKey : 'ID',
							dataSource : '/byeContabilidad/rest-services/private/noConciliado/getNoConciliadoMonto',
							autoLoad : false,
							columns : [
									{
										field : 'idNoConciliadoCartola',
										title : 'NoConciliadoCartola',
										width : 100,
										hidden : true
									},
									{
										field : 'id',
										title : 'Identificador',
										width : 100,
										hidden : true
									},
									{
										field : 'numMovimiento',
										title : 'N° Movimiento',
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
										field : 'tipoDocumento',
										title : 'T. Documento',
										sortable : true
									},
									{
										field : 'tipoMovimiento',
										title : 'T. Movimiento',
										sortable : true
									},
									{
										field : 'fecha',
										title : 'Fecha',
										sortable : true
									},
									{

										width : 150,
										title : 'Conciliar',
										tmpl : '<button type="button" class="btn btn-primary"  onclick="conciliar()" id="buscar">Conciliar</button>',
										align : 'center',
										events : {
											'click' : conciliar
										}
									}, ],
							pager : {
								limit : 5
							}
						})
	}

	function conciliar(x) {

		var submitJson = {
			idNoConciliadoCartola : x.data.record.idNoConciliadoCartola,
			idNoConciliado : x.data.record.id
		}
		if (window
				.confirm('¿Esta seguro desea realizar una conciliación entre estos movimientos?')) {

			$.post('/byeContabilidad/rest-services/private/conciliacion/add',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Se guardo la conciliación se guardo exitosamente');
					location.href = "index.jsp";
				} else {
					alert(data);
				}

			}).fail(function(jqxhr, settings, ex) {
				alert('No se pudo guardar la conciliación ' + ex);
			});
		}
	}

	function clear() {
		document.getElementById("filtro1").value = "";
		document.getElementById("filtro2").value = "";
	}

	$('#banco')
			.on(
					'change',
					function() {
						var submitJson = {

							idUsuario : document.getElementById("idUsuario").value,
							idBanco : document.getElementById("banco").value
						}

						$
								.post(
										'/byeContabilidad/rest-services/private/cuenta/getByIdBanco',
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

	$("#banco").trigger('change');
</script>
</html>