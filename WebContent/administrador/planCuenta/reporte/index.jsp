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
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Reportes</h1>
			</div>
			<input type="hidden" name="id" id="id" />
		</form>
		<div class="card border-secondary mb-3">
			<div class="card-header">Libro diario</div>
			<div class="card-body text-secondary">
				<div class="form-row">
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="desde">&nbsp;&nbsp;Desde</label>
						</div>
						<input type="date" id="filtro1" name="filtro1"
							class="form-control" class="in" />
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="hasta">&nbsp; &nbsp;Hasta</label>
						</div>
						<input type="date" id="filtro2" name="filtro2"
							class="form-control" class="in" />
					</div>
					<div class="form-group col-md-2">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa"
						required="required">
					</select>
				   </div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label>&nbsp;</label>
						</div>
						<button type="button" class="btn btn-primary" onclick="reporte()">Generar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="card border-secondary mb-3">
			<div class="card-header">Libro mayor</div>
			<div class="card-body text-secondary">
				<div class="form-row">
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="desde">&nbsp;&nbsp;Desde</label>
						</div>
						<input type="date" id="filtro1Mayor" name="filtro1Mayor"
							class="form-control" class="in" />
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="hasta">&nbsp; &nbsp;Hasta</label>
						</div>
						<input type="date" id="filtro2Mayor" name="filtro2Mayor"
							class="form-control" class="in" />
					</div>
					<div class="form-group col-md-2">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa1"
						required="required">
					</select>
				   </div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="desde">&nbsp;&nbsp;Inicial</label>
						</div>
						<input type="number" id="inicialMayor" name="inicialMayor"
							placeholder="N° Cuenta" class="form-control" min="1"
							pattern="^[0-9]+" class="in" />
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="hasta">&nbsp; &nbsp;Final</label>
						</div>
						<input type="number" id="finalMayor" name="finalMayor"
							placeholder="N° Cuenta" class="form-control" min="1"
							pattern="^[0-9]+" class="in" />
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label>&nbsp;</label>
						</div>
						<button type="button" class="btn btn-primary"
							onclick="reporteMayor()">Generar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="card border-secondary mb-3">
			<div class="card-header">Balance general</div>
			<div class="card-body text-secondary">
				<div class="form-row">
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="año">&nbsp;&nbsp;Año</label>
						</div>
						<select class="browser-default custom-select" id="anio"
							required="required">
						</select>
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="colFormLabel">&nbsp; &nbsp; Inicial</label>
						</div>
						<select class="browser-default custom-select" id="mesInicio"
							required="required">
							<option value="01">Enero</option>
							<option value="02">Febrero</option>
							<option value="03">Marzo</option>
							<option value="04">Abril</option>
							<option value="05">Mayo</option>
							<option value="06">Junio</option>
							<option value="07">Julio</option>
							<option value="08">Agosto</option>
							<option value="09">Septiembre</option>
							<option value="10">Octubre</option>
							<option value="11">Noviembre</option>
							<option value="12">Diciembre</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="colFormLabel">&nbsp; &nbsp; Final</label>
						</div>
						<select class="browser-default custom-select" id="mesFinal"
							required="required">
							<option value="01">Enero</option>
							<option value="02">Febrero</option>
							<option value="03">Marzo</option>
							<option value="04">Abril</option>
							<option value="05">Mayo</option>
							<option value="06">Junio</option>
							<option value="07">Julio</option>
							<option value="08">Agosto</option>
							<option value="09">Septiembre</option>
							<option value="10">Octubre</option>
							<option value="11">Noviembre</option>
							<option value="12">Diciembre</option>
						</select>
					</div>
					<div class="form-group col-md-2">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa2"
						required="required">
					</select>
				   </div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label>&nbsp;</label>
						</div>
						<button type="button" class="btn btn-primary"
							onclick="reporteGeneral()">Generar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="card border-secondary mb-3">
			<div class="card-header">Balance clasificado</div>
			<div class="card-body text-secondary">
				<div class="form-row">
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="año">&nbsp;&nbsp;Año</label>
						</div>
						<select class="browser-default custom-select" id="anioClasificado"
							required="required">
						</select>
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="colFormLabel">&nbsp; &nbsp; Inicial</label>
						</div>
						<select class="browser-default custom-select"
							id="mesInicioClasificado" required="required">
							<option value="01">Enero</option>
							<option value="02">Febrero</option>
							<option value="03">Marzo</option>
							<option value="04">Abril</option>
							<option value="05">Mayo</option>
							<option value="06">Junio</option>
							<option value="07">Julio</option>
							<option value="08">Agosto</option>
							<option value="09">Septiembre</option>
							<option value="10">Octubre</option>
							<option value="11">Noviembre</option>
							<option value="12">Diciembre</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label for="colFormLabel">&nbsp; &nbsp; Final</label>
						</div>
						<select class="browser-default custom-select"
							id="mesFinalClasificado" required="required">
							<option value="01">Enero</option>
							<option value="02">Febrero</option>
							<option value="03">Marzo</option>
							<option value="04">Abril</option>
							<option value="05">Mayo</option>
							<option value="06">Junio</option>
							<option value="07">Julio</option>
							<option value="08">Agosto</option>
							<option value="09">Septiembre</option>
							<option value="10">Octubre</option>
							<option value="11">Noviembre</option>
							<option value="12">Diciembre</option>
						</select>
					</div>
					<div class="form-group col-md-2">
					<label for="empresa">&nbsp;Empresa</label> <select
						class="browser-default custom-select" id="empresa3"
						required="required">
					</select>
				   </div>
					<div class="form-group col-md-2">
						<div class="form-row">
							<label>&nbsp;</label>
						</div>
						<button type="button" class="btn btn-primary"
							onclick="reporteClasificado()">Generar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#anio").select2();
		$("#mesInicio").select2();
		$("#mesFinal").select2();
		$("#anioClasificado").select2();
		$("#mesInicioClasificado").select2();
		$("#mesFinalClasificado").select2();
		$("#empresa").select2();
		$("#empresa1").select2();
		$("#empresa2").select2();
		$("#empresa3").select2();

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
						}, "json");
		
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
								document.getElementById("empresa1").innerHTML = str;
							}, "json");
		
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
								document.getElementById("empresa2").innerHTML = str;
							}, "json");
		
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
								document.getElementById("empresa3").innerHTML = str;
							}, "json");
		
		ComboAnio();
		ComboAnioClasificado();

	});

	function ComboAnio() {
		var str = "";
		var n = (new Date()).getFullYear();

		for (var i = n; i <= (n + 5); i++) {
			str += "<option value="+i+">" + i + "</option>";
		}
		document.getElementById("anio").innerHTML = str;
	};

	function ComboAnioClasificado() {
		var str = "";
		var n = (new Date()).getFullYear();

		for (var i = n; i <= (n + 5); i++) {
			str += "<option value="+i+">" + i + "</option>";
		}
		document.getElementById("anioClasificado").innerHTML = str;
	};

	function reporte() {
		if (document.getElementById('filtro1').value == ""
				|| document.getElementById('filtro2').value == "") {
			alert('No pueden haber campos vacios');
			return;
		}

		var fechaDesde = new Date(document.getElementById('filtro1').value);
		var fechaHasta = new Date(document.getElementById('filtro2').value);
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;
		}

		location.href = "/byeContabilidad/rest-services/private/reporte/getLibroDiario?fechaDesde="
				+ document.getElementById('filtro1').value
				+ "&fechaHasta="
				+ document.getElementById('filtro2').value
				+ "&idUsuario="
				+ document.getElementById('idUsuario').value
				+ "&idEmpresa="
				+ document.getElementById('empresa').value

	}

	function reporteMayor() {
		if (document.getElementById('filtro1Mayor').value == ""
				|| document.getElementById('filtro2Mayor').value == ""
				|| document.getElementById('inicialMayor').value == ""
				|| document.getElementById('finalMayor').value == "") {
			alert('No pueden haber campos vacios');
			return;
		}
		var fechaDesde = new Date(document.getElementById('filtro1Mayor').value);
		var fechaHasta = new Date(document.getElementById('filtro2Mayor').value);
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;
		}

		var inicialMayor = document.getElementById('inicialMayor').value;
		var finalMayor = document.getElementById('finalMayor').value;
		if (inicialMayor > finalMayor) {
			alert('La cuenta inicial no debe ser mayor a la cuenta final');
			return;
		}

		location.href = "/byeContabilidad/rest-services/private/reporte/getLibroMayor?fechaDesde="
				+ document.getElementById('filtro1Mayor').value
				+ "&fechaHasta="
				+ document.getElementById('filtro2Mayor').value
				+ "&inicialMayor="
				+ document.getElementById('inicialMayor').value
				+ "&finalMayor="
				+ document.getElementById('finalMayor').value
				+ "&idUsuario=" + document.getElementById('idUsuario').value
				+ "&idEmpresa="
				+ document.getElementById('empresa1').value
	}

	function reporteGeneral() {
		var fechaDesde = document.getElementById('mesInicio').value;
		var fechaHasta = document.getElementById('mesFinal').value;
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;
		}

		location.href = "/byeContabilidad/rest-services/private/reporte/getBalanceGeneral?fechaDesde="
				+ document.getElementById('mesInicio').value
				+ "&fechaHasta="
				+ document.getElementById('mesFinal').value
				+ "&anio="
				+ document.getElementById('anio').value
				+ "&idUsuario="
				+ document.getElementById('idUsuario').value
				+ "&idEmpresa="
				+ document.getElementById('empresa2').value
	}

	function reporteClasificado() {
		var fechaDesde = document.getElementById('mesInicioClasificado').value;
		var fechaHasta = document.getElementById('mesFinalClasificado').value;
		if (fechaDesde > fechaHasta) {
			alert('Fecha Inicial no debe ser mayor que fecha final');
			return;
		}

		location.href = "/byeContabilidad/rest-services/private/reporte/getBalanceClasificado?fechaDesde="
				+ document.getElementById('mesInicioClasificado').value
				+ "&fechaHasta="
				+ document.getElementById('mesFinalClasificado').value
				+ "&anio="
				+ document.getElementById('anioClasificado').value
				+ "&idUsuario=" + document.getElementById('idUsuario').value
				+ "&idEmpresa="
				+ document.getElementById('empresa3').value
	}

</script>
</html>