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
			<div class="container">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
					<h1 class="h2">Agregar comprobante Contable</h1>
				</div>


				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Número</label>
					<input type="number" id="numero" name="numero"
						placeholder="Ingrese Número" required="required" class="on" />
				</div>

				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Fecha</label>
					<input type="date" id="fecha" name="fecha" class="in"
						required="required" />
				</div>

				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Glosa
						general</label> <input type="text" id="glosaTransaccion" class="col-sm-5"
						name="glosaTransaccion" placeholder="Ingrese glosa general"
						required="required" class="on" />
				</div>

				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Tipo</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="tipoMovimiento"
							required="required">
							<option value="INGRESO">Ingreso</option>
							<option value="EGRESO">Egreso</option>
							<option value="TRASPASO">Traspaso</option>
						</select>
					</div>
				</div>
				<br>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp;&nbsp; Cuenta Contable</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="cuentaContable">
						</select>
					</div>
				</div>

				<br> <br>

				<div class="row">
					<div class="col-xs-6 col-md-2">
						<button type="button" class="btn btn-primary btn-lg btn-block"
							onclick="Save()">Guardar</button>
					</div>

					<div class="col-xs-6 col-md-2">
						<button class=" btt btn btn-primary btn-lg btn-block"
							type="button" id="back">Cancelar</button>
					</div>
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
	$("#tipoMovimiento").select2();
	$("#cuentaContable").select2();
	numero();

	var varAnalisis;
	var varConciliacion;
	$.post('/byeContabilidad/rest-services/private/cuentaContable/getLista',
			function(res, code) {
				var str;
				for (var i = 0; i < res.length; i++) {
					str += "<option value="+res[i].id+"/"+res[i].analisis+"/"+res[i].conciliacion+">" + res[i].descripcion
					+"</option>";
				}
				
				document.getElementById("cuentaContable").innerHTML = str;
				var analisis= document.getElementById("cuentaContable").value.split("/");
				varAnalisis = analisis [1];
				var coinciliacion = document.getElementById("cuentaContable").value.split("/");
				varConciliacion = coinciliacion [2];
			}, "json");
	


});

$('#cuentaContable').on('change',function() {
				var analisis= document.getElementById("cuentaContable").value.split("/");
				varAnalisis = analisis [1];
				var coinciliacion = document.getElementById("cuentaContable").value.split("/");
				varConciliacion = coinciliacion [2];
				//alert(varAnalisis+" "+varConciliacion);
				
		if(varAnalisis==true && varConciliacion==false){
			
		}
});
				

function numero() {
	$.post('/byeContabilidad/rest-services/private/comprobanteContable/getMaxNumero')
			.done(function(data) {
				document.getElementById("numero").value = data + 1;
			}).fail(function(jqxhr, settings, ex) {
		});
}
</script>
</html>