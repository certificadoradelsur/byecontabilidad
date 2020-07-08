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
	<link rel="shortcut icon" type="image/x-icon" href="../../images/logo.ai.png" />
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
						<button type="button" class="btn btn-primary " onclick="crear()">Crear Periodo para este año</button>
						
				</div>
			<br>
			<div class="form-group">
							<div class="form-row">
					<label for="año">&nbsp;Empresa</label>
					<div class="col-3">
					<select class="browser-default custom-select" id="empresa"
						required="required">
					</select>
				</div>
				</div>
				</div>
			<div class="form-group">
				<div class="col-1"></div>
					<div class="form-row">
					<label for="año">&nbsp;Año</label>
					<div class="col-3">
					<select class="browser-default custom-select" id="anios"
										required="required">
									</select>
									</div>
					</div>
				
			</div>
			<div class="table-responsive">
				<table class="table" id="grid"></table>
			</div>

	</div>
  	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
		
	
</body>
<script type="text/javascript">
	$(document).ready(function() {
		cargarGrilla();
		comboAnio();
		comboEmpresa();
		
		
		if (document.getElementById("empresa").value != "") {
			grid.reload({
				anio : $('#anios').val(),
				idEmpresa : $('#empresa').val()
			});
		}
	});


	function cargarGrilla(){
		grid = $('#grid').grid(
				{
					primaryKey : 'ID',
					dataSource : '/byeContabilidad/rest-services/private/periodo/getAll?idUsuario='
							+ document.getElementById('idUsuario').value
							+"",
							autoLoad : false,
							columns : [
								{
									field : 'id',
									title : 'Identificador',
									hidden : true														
									},
									{
										field : 'anio',
										title : 'Año',
										width : 140
										},
										{
											field : 'mes',
											title : 'Mes',
											width : 140
											},
											{
												field : 'estado',
												title : 'estado',
												width : 140
												},
													{
														width : 100,
														title : 'Bloquear/Desbloquear',
														tmpl : '<span class="material-icons gj-cursor-pointer">border_color</span>',
														align : 'center',
														events : {
															'click' : cambiarEstado
														}
													}, ],
											pager : {
												limit : 12
											}
										});
	}
	
	function cambiarEstado(e){
		var ids = e.data.record.id;
		var submitJson = {
				id : ids,
				estado : e.data.record.estado,
				idUsuario : document.getElementById("idUsuario").value,
				idEmpresa : document.getElementById("empresa").value
		}
		$.post('/byeContabilidad/rest-services/private/periodo/updateEstado',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Estado cambiado exitosamente');
				grid.reload({
					anio : $('#anios').val(),
					idEmpresa : $('#empresa').val()
					
				});
			} else {
				alert(data);
			}
		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo modificar ' + ex);
		});
		
	}
	function comboAnio() {
		var str = "";
		var n = (new Date()).getFullYear();
		for (var i = n - 5; i <= (n + 5); i++) {
			if (i == n) {
				str += "<option value="+i+" selected>" + i + "</option>";
			} else {
				str += "<option value="+i+">" + i + "</option>";
			}
		}
		document.getElementById("anios").innerHTML = str;
		
		
		
	};
	
	
	
	$("#anios").on('change',function() {
		grid.reload({
			anio : $('#anios').val(),
			idEmpresa : $('#empresa').val()
		});
	})
	
	$("#empresa").on('change',function(){
		grid.reload({
			anio : $('#anios').val(),
			idEmpresa : $('#empresa').val()
		});
	})
	
	function comboEmpresa(){
		var submitJson = {
				idUsuario : document.getElementById("idUsuario").value
			}
		$.post('/byeContabilidad/rest-services/private/empresa/getLista',
				JSON.stringify(submitJson),
				function(res, code) {
					var str;
					for (var i = 0, len = res.length; i < len; i++) {
						str += "<option value="+res[i].id+">"
								+ res[i].razonSocial
								+ "</option>";
					}
					
					document.getElementById("empresa").innerHTML = str;
					grid.reload({
						anio : $('#anios').val(),
						idEmpresa : $('#empresa').val()
					});
					
		}, "json");
	}
	
	function crear(){
		var submitJson = {
				anio : document.getElementById("anios").value,
				idEmpresa : document.getElementById("empresa").value
			}
			
			$.post('/byeContabilidad/rest-services/private/periodo/periodoAnio',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Se guardo el periodo exitosamente');
					location.href = "index.jsp";
				} else {
					alert(data);
				}

			}).fail(function(jqxhr, settings, ex) {
				alert('No se pudo realizar la operacion ' + ex);
			});
	}
	
</script>
</html>