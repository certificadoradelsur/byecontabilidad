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

 <%@ include file = "../../../complementos/nav.jsp" %>
	<div class="container-lg">
		<div class="container">
			<form name="formulario" id="formulario">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Lista de cuentas contables</h1>
				</div>
				<input type="hidden" name="id" id="id" />
				<div>
					<div class="col-xs-6 col-md-2">
						<button type="button" class="btn btn-primary " onclick="agregar()">Agregar</button>
					</div>
				</div>
			</form>
			<br>

			<div class="form-group">
				<div class="form-row">
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="glosa">&nbsp;&nbsp;Glosa</label>
						</div>
						<input type="text" id="filtro" name="filtro"
							placeholder="Filtrar por Glosa" class="form-control" />
					</div>
					<div class="form-group col-md-2">
						<label for="claseCuenta">&nbsp;Clase cuenta</label> 
						<select
							class="browser-default custom-select" id="claseCuenta"
							required="required" class="form-control">
							<option value="1">Activo</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label for="grupoCuenta">&nbsp;Grupo cuenta</label> <select
							class="browser-default custom-select" id="grupoCuenta"
							required="required">
							<option value="1">Activo circulante</option>
							<option value="2">Activo fijo</option>
							<option value="3">Otros activos</option>
						</select>
					</div>
					<div class="form-group col-md-3">
						<div class="form-row clo md 3">
							<label>&nbsp;</label>
						</div>
						<button type="button" class="btn btn-primary" id="buscar">Filtrar</button>
					</div>
				</div>

				<div class="margen margin-top-10">
					<table class="table" id="grid"></table>
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
						$("#claseCuenta").select2(),
						$("#grupoCuenta").select2();
						
						$.post('/byeContabilidad/rest-services/private/claseCuenta/getLista',
								function(res, code) {
									var str;
									for (var i = 0, len = res.length; i < len; i++) {
										str += "<option value="+res[i].id+">"
												+ res[i].nombre
												+ "</option>";
									}
									document.getElementById("claseCuenta").innerHTML = str;	
								}, "json");
						
						grid = $('#grid')
								.grid(
										{
											primaryKey : 'ID',
											dataSource : '/byeContabilidad/rest-services/private/cuentaContable/getAll',
											autoLoad : false,
											columns : [
													{
														field : 'id',
														title : 'Identificador',
														hidden : true,

													},
													{
														field : 'codigo',
														title : 'Código',
														width : 100
													},
													{
														field : 'glosaGeneral',
														title : 'Glosa',
														sortable : true
													},
													{
														field : 'nombreClaseCuenta',
														title : 'Clase Cuenta',
														width : 100
													},
													{
														field : 'nombreGrupoCuenta',
														title : 'Grupo Cuenta',
														sortable : true
													},
													{
														field : 'descripcion',
														title : 'Descripción',
														sortable : true
													},
													{
														field : 'analisis',
														title : 'Analisís',
														width : 80
													},
													{
														field : 'imputable',
														title : 'Imputable',
														width : 80
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
	
	
	$('#claseCuenta').on('change',function() {
		var submitJson = {
			idClaseCuenta : document.getElementById("claseCuenta").value
		}

		$.post('/byeContabilidad/rest-services/private/grupoCuenta/getByIdClaseCuenta',
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
							var submitJson = {
									idGrupoCuenta : document.getElementById("grupoCuenta").value
								}
						}, "json");
	});

	function agregar() {
		location.href = "agregar.jsp";
	}

	function modificar(e) {
		document.getElementById("id").value = e.data.record.id;
		document.getElementById("formulario").action = 'modificar.jsp';
		document.getElementById("formulario").method = 'POST';
		document.getElementById("formulario").submit();
	}


	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la cuenta contable?')) {
			var submitJson = {
				id : x.data.record.id
			}
			$
					.post(
							'/byeContabilidad/rest-services/private/cuentaContable/delete',
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							alert('Cuenta contable eliminada correctamente');
							grid.reload();
						} else {
							alert('Error al eliminar la cuenta contable');
						}
					}).fail(function() {
						alert('Error al eliminar la cuenta contable');
					});
		}
	}
	
	$('#buscar').on('click', function() {
		if($('#filtro').val()==''){
			alert('Debe ingresar una glosa');
			return
		}
		grid.reload({
			glosaGeneral : $('#filtro').val(),
			idClaseCuenta : $('#claseCuenta').val(),
			idGrupoCuenta : $('#grupoCuenta').val(),
		});
		clear();
	});

	function clear() {
		document.getElementById("filtro").value = "";
	}
</script>
</html>