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

	<%@ include file="../../../complementos/navUsuario.jsp"%>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Lista boletas de honorario</h1>
			</div>
			<input type="hidden" name="id" id="id" />
		</form>

<div>
			<button type="button" class="btn btn-primary " onclick="agregar()">Agregar</button>
		</div>
		<br>
		<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-2">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa"
						required="required">
					</select>
				</div>
				<div class="form-group col-md-2">
					<div class="form-row">
						<label for="desde">&nbsp;&nbsp;Desde</label>
					</div>
					<input type="date" id="desde" name="desde" class="form-control"
						class="in" />
				</div>
				<div class="form-group col-md-2">
					<div class="form-row">
						<label for="hasta">&nbsp; &nbsp;Hasta</label>
					</div>
					<input type="date" id="hasta" name="hasta" class="form-control"
						class="in" />
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
	</div>
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#empresa").select2({
							width : '180'
						});

						var submitJson = {
							idUsuario : document.getElementById("idUsuario").value
						}
						$
								.post(
										'/byeContabilidad/rest-services/private/empresa/getSupervisor',
										JSON.stringify(submitJson),
										function(res, code) {
											var str;
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+">"
														+ res[i].razonSocial
														+ "</option>";
											}
											document.getElementById("empresa").innerHTML = str;
											
										}, "json");

						fecha();
					});

	function fecha() {
		var now = new Date();
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
		var today = now.getFullYear() + "-" + (month) + "-" + ("01");
		$("#desde").val(today);

		var now = new Date();
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
		var dia = new Date(now.getFullYear() || new Date().getFullYear(),
				month, 0).getDate();
		var today2 = now.getFullYear() + "-" + (month) + "-" + (dia);
		$("#hasta").val(today2);
		cargaTabla();
	}
	
	function agregar() {
		location.href = "agregar.jsp";
	}
	
	function cargaTabla(){
		grid = $('#grid')
		.grid(
				{
					primaryKey : 'ID',
					dataSource : "/byeContabilidad/rest-services/private/honorario/getAll?idUsuario="
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
								field : 'numBoleta',
								title : 'N° Boleta',
								width : 100
							},
							{
								field : 'rut',
								title : 'Rut',
								width : 100
							},
							{
								field : 'nombre',
								title : 'Nombre',
								width : 100
							},
							{
								field : 'fecha',
								title : 'Fecha',
								width : 100
							},
							{
								field : 'montoBruto',
								title : 'Monto Bruto',
								width : 100
							},
							{
								field : 'retencion',
								title : 'Retención',
								width : 100
							},
							{
								field : 'montoLiquido',
								title : 'Monto Liquido',
								width : 100
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
		busca();

	}
	
	function busca() {
		if (document.getElementById("empresa").value != "") {
			grid.reload({
				fechaDesde : $('#desde').val(),
				fechaHasta : $('#hasta').val(),
				idEmpresa : $('#empresa').val()
			});
		}
	}
	
	$('#buscar').on('click', function() {
		var fechaDesde = new Date(document.getElementById('desde').value);
		var fechaHasta = new Date(document.getElementById('hasta').value);
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;
		}
		if (document.getElementById("empresa").value != "") {
			grid.reload({
				fechaDesde : $('#desde').val(),
				fechaHasta : $('#hasta').val(),
				idEmpresa : $('#empresa').val()
			});
		}
		//clear();
	});
	
	function clear() {
		document.getElementById("filtro1").value = "";
		document.getElementById("filtro2").value = "";
	}
	

	function modificar(e) {
		document.getElementById("id").value = e.data.record.id;
		document.getElementById("formulario").action = 'modificar.jsp';
		document.getElementById("formulario").method = 'POST';
		document.getElementById("formulario").submit();
	}

	function limpia() {
		document.getElementById("banco").value = "";
		document.getElementById("cuenta").value = "";
		document.getElementById("numDocumento").value = "";
		document.getElementById("fecha").value = "";
		document.getElementById("descripcion").value = "";
		document.getElementById("tipoMovimiento").value = "";
		document.getElementById("monto").value = "";
		document.getElementById("idUsuario").value = "";

	}

	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la boleta de honorario')) {
			var submitJson = {
				id : x.data.record.id,
				idUsuario : document.getElementById("idUsuario").value
				
			}
			$
					.post(
							'/byeContabilidad/rest-services/private/honorario/delete',
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							alert('Boleta de honorario eliminada correctamente');
							grid.reload();
						} else {
							alert(data);
						}
					}).fail(function() {
						alert('Error al eliminar la boleta de honorario');
					});
		}
	}

</script>
</html>