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
		<form name="formulario" id="formulario"><input type="hidden" name="id" id="id" /></form>
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Lista de usuario</h1>
				</div>
				<div>
						<button type="button" class="btn btn-primary " onclick="agregar()">Agregar</button>
				</div>
			<br>
			<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-3">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa"
						required="required">
					</select>
				</div>
				<div class="form-group col-md-3">
					<label for="empresa">&nbsp;Sucursal</label> <select
						class="browser-default custom-select" id="sucursal"
						required="required">
					</select>
				</div>
				<div class="form-group col-md-2">
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
	$(document).ready(function() {
		$("#empresa").select2({width:'200'});
		$("#sucursal").select2({width:'200'});
		
		cargaTabla();
		var submitJson = {
				idUsuario : document.getElementById("idUsuario").value}
				
				$.post('/byeContabilidad/rest-services/private/empresa/getLista',JSON.stringify(submitJson),
						function(res, code) {
							var str;
							for (var i = 0, len = res.length; i < len; i++) {
								str += "<option value="+res[i].id+">" + res[i].razonSocial
										+ "</option>";
							}
							document.getElementById("empresa").innerHTML = str;
							cargaSucursal(document.getElementById("empresa").value);
						}, "json");	 	
	 
	})
	
	$('#empresa').on('change',function() {
				var submitJson = { 

					    idUsuario : document.getElementById("idUsuario").value,
						idEmpresa : document.getElementById("empresa").value
				}

				$.post('/byeContabilidad/rest-services/private/sucursal/getByIdEmpresa',
								JSON.stringify(submitJson),
								function(res, code) {
					               var str = "";
									for (var i = 0, len = res.length; i < len; i++) {
									str += "<option value="+res[i].codigo+">"
												+ res[i].direccion
												+ "</option>";
									}
									document.getElementById("sucursal").innerHTML = str;
								}, "json");
	})
		
	
	function cargaTabla(){
	grid = $('#grid').grid({
		primaryKey : 'ID',
		dataSource : '/byeContabilidad/rest-services/private/CentroResultado/getAll',
				autoLoad : false,
				columns : [
					{
						field : 'id',
						title : 'Identificador',
						width : 150														
						},
						{
							field : 'nombre',
							title : 'Nombre',
							width : 140
							},
							{
								field : 'nombreSucursal',
								title : 'Sucursal',
								width : 140
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
		if (confirm('¿Esta seguro desea eliminar el centro resultado?')) {
			var submitJson = {
				id : x.data.record.id,
				idUsuario : document.getElementById("idUsuario").value
			}
			$
					.post(
							'/byeContabilidad/rest-services/private/CentroResultado/delete',
							JSON.stringify(submitJson)).done(function(data) {
						if (data == 'OK') {
							alert('Centro resultado eliminada correctamente');
							grid.reload();
						} else {
							alert('Error al eliminar centro resultado');
						}
					}).fail(function() {
						alert('Error al eliminar centro resultado');
					});
		}
	}
	
	$('#buscar').on('click', function() {
		if(document.getElementById("sucursal").value!=""){
			grid.reload({
				idSucursal : $('#sucursal').val()
			});
		}
	});
	
	function cargaSucursal(idEmpresa){
		var submitJson = { 

			    idUsuario : document.getElementById("idUsuario").value,
				idEmpresa : idEmpresa
		}

		$.post('/byeContabilidad/rest-services/private/sucursal/getByIdEmpresa',
						JSON.stringify(submitJson),
						function(res, code) {
			               var str = "";
							for (var i = 0, len = res.length; i < len; i++) {
							str += "<option value="+res[i].codigo+">"
										+ res[i].direccion
										+ "</option>";
							}
							document.getElementById("sucursal").innerHTML = str;
						
		if(document.getElementById("sucursal").value!=""){
			grid.reload({
				idSucursal : $('#sucursal').val()
			});
		}
		}, "json");	
	}

	
</script>
</html>