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

	<%@ include file="../../../complementos/nav.jsp"%>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<input type="hidden" name="id" id="id" />
		</form>
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
			<h1 class="h2">Lista de sucursales</h1>
		</div>
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
				<div class="form-group col-md-3">
					<div class="form-row clo md 3">
						<label>&nbsp;</label>			
					</div>
					<button type="button" class="btn btn-primary" id="buscar">Filtrar</button>
				</div>
			</div>
			<div class="table-responsive">
				<table class="table" id="grid"></table>
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
												
						cargaTabla();
						
						var submitJson = {
								idUsuario : document.getElementById("idUsuario").value
							}
							$
									.post(
											'/byeContabilidad/rest-services/private/empresa/getLista',
											JSON.stringify(submitJson),
											function(res, code) {
												var str ;
												for (var i = 0, len = res.length; i < len; i++) {
													str += "<option value="+res[i].id+">"
															+ res[i].razonSocial
															+ "</option>";
												}
												document.getElementById("empresa").innerHTML = str;
											
												if(document.getElementById("empresa").value!=""){
													grid.reload({
														idEmpresa : $('#empresa').val()
													});
												}

											}, "json");
						
					});

	function agregar() {
		location.href = "agregar.jsp";
	}

	function cargaTabla(){
		

		grid = $('#grid')
		.grid(
				{
					primaryKey : 'ID',
					dataSource : '/byeContabilidad/rest-services/private/sucursal/getAll?idUsuario='
							+ document
									.getElementById('idUsuario').value,
					autoLoad : false,
					columns : [
							{
								field : 'codigo',
								title : 'Código',
								hidden : true

							},
							{
								field : 'direccion',
								title : 'Dirección',
								width : 200

							},
							{
								field : 'nombreEmpresa',
								title : 'Empresa',
								width : 200
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
	}
	
	function modificar(e) {
		document.getElementById("id").value = e.data.record.codigo;
		document.getElementById("formulario").action = 'modificar.jsp';
		document.getElementById("formulario").method = 'POST';
		document.getElementById("formulario").submit();
	}

	function eliminar(x) {
		if (confirm('¿Esta seguro desea eliminar la sucursal?')) {
			var submitJson = {
				codigo : x.data.record.codigo,
				idUsuario : document.getElementById("idUsuario").value
			}
			$.post('/byeContabilidad/rest-services/private/sucursal/delete',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Sucursal eliminada correctamente');
					grid.reload();
				} else {
					alert(data);
				}
			}).fail(function() {
				alert('Error al eliminar la sucursal');
			});
		}
	}

	$('#buscar').on('click', function() {
		if(document.getElementById("empresa").value!=""){
			grid.reload({
				idEmpresa : $('#empresa').val()
			});
		}
	});


</script>
</html>