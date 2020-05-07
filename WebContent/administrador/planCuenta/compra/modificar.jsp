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
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Modificar boleta honorario</h1>
			</div>
			<div class="container">
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">N°
						Boleta</label> <input type="number" id="numBoleta" name="numBoleta"
						class="in" placeholder="Ingrese número" required="required"
						min="0" pattern="^[0-9]+" />
				</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Empresa</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="empresa">
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Rut</label>
					<input type="text" id="rut" name="rut" class="in"
						placeholder="Ingrese rut" required="required" />
				</div>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Nombre</label>
					<input type="text" id="nombre" name="nombre" class="in"
						placeholder="Ingrese nombre" required="required" />
				</div>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Fecha</label>
					<input type="date" id="fecha" name="fecha" class="in"
						required="required" />
				</div>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Monto
						bruto</label> <input type="number" id="montoBruto" name="montoBruto"
						placeholder="Ingrese monto bruto" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
				<div class="row">
					<div class="col-sm-2">&nbsp;&nbsp; Seleccione</div>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								id="retencionEstado"> <label class="form-check-label"
								for="gridCheck1"> Retención </label>
						</div>
					</div>
				</div>
				<div class="row collapse" id="collapse">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
					</label>
					<div class="col-3">
					     <label class="col-form-label">Porcentaje de retención
					</label>
						<input type="text" id="retencionValor" name="retencionValor" readonly />
					</div>
				   <div class="col-3">
				   <label  class="col-form-label">Monto de retención
					</label> 
						<input type="number" id="retencion" name="retencion" readonly />
					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Monto
						Liquido</label> <input type="number" id="montoLiquido" name="montoLiquido"
						class="in" required="required" min="0" pattern="^[0-9]+" readonly />
				</div>
				<div class="row">
					<div class="col-xs-6 col-md-2">
						<button class=" btt btn btn-primary btn-lg btn-block"
							type="button" onclick="guardar()">Guardar</button>
					</div>
					<div class="col-xs-6 col-md-2">
						<button class=" btt btn btn-primary btn-lg btn-block"
							type="button" id="back">Cancelar</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<input type="hidden" name="retencion" id="retencion" type=number />
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#empresa").select2({width:'200'});
						var submitjson = {id: "<%=request.getParameter("id")%>",};

						$.post('/byeContabilidad/rest-services/private/honorario/getById',
										JSON.stringify(submitjson)).done(
										function(data) {
											document.getElementById("retencion").value = data.retencion;
											document.getElementById("numBoleta").value = data.numBoleta;
											document.getElementById("rut").value = data.rut;
											document.getElementById("nombre").value = data.nombre;
											document.getElementById("retencionEstado").checked = data.retencionEstado;
											document.getElementById("fecha").value = data.fecha;
											document.getElementById("montoBruto").value = data.montoBruto;
											document.getElementById("montoLiquido").value = data.montoLiquido;

											cargaEmpresa(data.idEmpresa);
											
											if(document.getElementById("retencionEstado").checked){
												$("#montoBruto").prop("disabled", true);
											}
										})
								.fail(
										function(jqxhr, settings, ex) {
											alert('No se pudo modificar la boleta de honorario '
													+ ex);
										});

						$("#montoBruto").keyup(function() {
							var value = $(this).val();
							$("#montoLiquido").val(value);
						});
						$('#retencionEstado')
								.on(
										'change',
										function() {
											if (document
													.getElementById("retencionEstado").checked) {
												if (document
														.getElementById("montoBruto").value == "") {
													alert("Debe ingresar monto bruto");
													document
															.getElementById("retencionEstado").checked = 0;
													return;
												}
												$('#collapse').collapse('show');
												$("#retencionValor").val('10.750');
												$("#montoBruto").prop("disabled", true);

												var bruto = document.getElementById("montoBruto").value;
												var retencion = bruto * 10.750 / 100;
												var liquido = Math.round(bruto - (retencion));
												$("#montoLiquido").val(liquido);
												$("#retencion").val(retencion);
											} else {
												$('#collapse').collapse('hide');
												$("#montoLiquido").val(document.getElementById("montoBruto").value);
												$("#montoBruto").prop("disabled", false);
												$("#retencion").val(0);
											}
										})

					});

	document.getElementById('rut')
			.addEventListener(
					'input',
					function(evt) {
						let value = this.value.replace(/\./g, '').replace('-',
								'');

						if (value.match(/^(\d{2})(\d{3}){2}(\w{1})$/)) {
							value = value.replace(
									/^(\d{2})(\d{3})(\d{3})(\w{1})$/,
									'$1.$2.$3-$4');
						} else if (value.match(/^(\d)(\d{3}){2}(\w{0,1})$/)) {
							value = value.replace(
									/^(\d)(\d{3})(\d{3})(\w{0,1})$/,
									'$1.$2.$3-$4');
						} else if (value.match(/^(\d)(\d{3})(\d{0,2})$/)) {
							value = value.replace(/^(\d)(\d{3})(\d{0,2})$/,
									'$1.$2.$3');
						} else if (value.match(/^(\d)(\d{0,2})$/)) {
							value = value.replace(/^(\d)(\d{0,2})$/, '$1.$2');
						}
						this.value = value;
					});

	function guardar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if ($('#empresa option:selected').text() == '') {
			alert("Debe seleccionar una empresa valida");
			return;
		}

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		var retencion = Math.round(document.getElementById("retencion").value)
		
		var submitJson = {
			id :<%=request.getParameter("id")%>,
			numBoleta : document.getElementById("numBoleta").value,
			rut : document.getElementById("rut").value,
			nombre : document.getElementById("nombre").value,
			retencionEstado : document.getElementById("retencionEstado").checked,
			fecha : document.getElementById("fecha").value,
			retencion : retencion,
			montoBruto : document.getElementById("montoBruto").value,
			montoLiquido : document.getElementById("montoLiquido").value,
			idUsuario : document.getElementById("idUsuario").value,
			idEmpresa : document.getElementById("empresa").value
		}

		$.post('/byeContabilidad/rest-services/private/honorario/update',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Cambios guardados exitosamente');
				location.href = "index.jsp";
				limpia()
			} else {
				alert(data);
			}

		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo modificar la boleta de honorario ' + ex);
		});
	}

	function cargaEmpresa(idEmpresa) {
		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value
		}
		$.post('/byeContabilidad/rest-services/private/empresa/getLista', JSON
				.stringify(submitJson), function(res, code) {
			var str;
			for (var i = 0, len = res.length; i < len; i++) {
				if (idEmpresa == res[i].id) {
					str += "<option value="+res[i].id+" selected>"
							+ res[i].razonSocial + "</option>";
				} else {

					str += "<option value="+res[i].id+">" + res[i].razonSocial
							+ "</option>";
				}
			}

			document.getElementById("empresa").innerHTML = str;
		}, "json");
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);

	function limpia() {
		document.getElementById("numBoleta").value = "";
		document.getElementById("rut").value = "";
		document.getElementById("nombre").value = "";
		document.getElementById("retencionEstado").value = "";
		//		document.getElementById("fecha").value = "";
		document.getElementById("montoBruto").value = "";
		document.getElementById("montoLiquido").value = "";
	}
</script>
</html>