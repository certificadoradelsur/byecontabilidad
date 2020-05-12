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
				<h1 class="h2">Agregar compra</h1>
			</div>
			<div class="container">
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Folio</label>
					<input type="number" id="folio" name="folio" class="in"
						placeholder="Ingrese folio" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Empresa</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="empresa">
						</select>
					</div>
				</div>
				<div class="row">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
						&nbsp; &nbsp;Cliente</label>
					<div class="col-3">
						<select class="browser-default custom-select" id="cliente">
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Nombre</label>
					<input type="text" id="nombre" name="nombre" class="in"
						placeholder="Ingrese nombre" required="required" readonly />
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
						neto</label> <input type="number" id="montoNeto" name="montoNeto"
						placeholder="Ingrese monto bruto" required="required" min="0"
						pattern="^[0-9]+" />
				</div>
				<div class="row">
					<div class="col-sm-2">&nbsp;&nbsp; Seleccione</div>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="ivaEstado">
							<label class="form-check-label" for="gridCheck1"> Iva </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								id="otrosImpuestos"> <label class="form-check-label"
								for="gridCheck2"> Otros impuestos </label>
						</div>
					</div>
				</div>
				<div class="row collapse" id="collapse">
					<label for="colFormLabel" class="col-sm-2 col-form-label">
					</label> <label>&nbsp;&nbsp;&nbsp; Agregar otros impuestos</label> &nbsp;<input
						type="button" id="btnAdd" value="+" onclick="add()" /> &nbsp;<input
						type="button" id="btnDel" value="-" onclick="delet()" />
					<div class="col-12" id="otros">
						<div class='row'>
							<div class='col-sm-2'></div>
							<div class='col-sm-1 col-form' id='codigo'>
								<label class='col-form-label'>Código </label> <input type='text'
									style='width: 60px;' id='codigo1' />

							</div>
							<div class='col-sm-2 col-form' id='monto'>
								<label class='col-form-label'>Monto</label> <input type='text'
									style='width: 140px;' id='monto1' />
							</div>
						</div>
					</div>

				</div>

				<br>

				<div class="form-group">
					<div class="col-1"></div>
					<label for="colFormLabel" class="col-sm-2 col-form-label">Monto
						total</label> <input type="number" id="montoTotal" name="montoTotal"
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
	<input type="hidden" name="iva" id="iva" />
	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#empresa").select2({
							width : '200'
						});
						$("#cliente").select2({
							width : '200'
						});

						$("#montoNeto").keyup(function() {
							var value = $(this).val();
							$("#montoTotal").val(value);
						});

						$('#ivaEstado')
								.on(
										'change',
										function() {
											if (document
													.getElementById("ivaEstado").checked) {
												if (document
														.getElementById("montoNeto").value == "") {
													alert("Debe ingresar monto neto");
													document
															.getElementById("ivaEstado").checked = 0;
													return;
												}
												$("#montoNeto").prop(
														"disabled", true);
												var neto = document
														.getElementById("montoNeto").value;
												var iva = (neto * 0.19);
												var total = Math
														.round(Number(neto)
																+ Number(iva));
												$("#montoTotal").val(total);
												$("#iva").val(iva);
											} else {

												$("#montoTotal")
														.val(
																document
																		.getElementById("montoNeto").value);
												$("#montoNeto").prop(
														"disabled", false);
												$("#iva").val(0);
											}
										})

						$('#otrosImpuestos')
								.on(
										'change',
										function() {
											if (document
													.getElementById("otrosImpuestos").checked) {
												$('#collapse').collapse('show');
											} else {
												$('#collapse').collapse('hide');
												var xx = 2;
												var list = [];
												var indice = 0;
											}
										})

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
											cliente();
										}, "json");
					});

	function cliente() {
		var submitJson = {
			idUsuario : document.getElementById("idUsuario").value,
			idEmpresa : document.getElementById("empresa").value
		}
		$.post('/byeContabilidad/rest-services/private/cliente/getLista', JSON
				.stringify(submitJson), function(res, code) {
			var str = "<option>Seleccione cliente</option>";
			for (var i = 0, len = res.length; i < len; i++) {
				str += "<option value="+res[i].id+"/"+res[i].nombre+">"
						+ res[i].rut + "</option>";
			}
			document.getElementById("cliente").innerHTML = str;
		}, "json");
	}

	$('#empresa')
			.on(
					'change',
					function() {

						var submitJson = {
							idUsuario : document.getElementById("idUsuario").value,
							idEmpresa : document.getElementById("empresa").value
						}
						$
								.post(
										'/byeContabilidad/rest-services/private/cliente/getLista',
										JSON.stringify(submitJson),
										function(res, code) {
											var str = "<option>Seleccione cliente</option>";
											for (var i = 0, len = res.length; i < len; i++) {
												str += "<option value="+res[i].id+"/"+res[i].nombre+">"
														+ res[i].rut
														+ "</option>";
											}
											document.getElementById("cliente").innerHTML = str;
										}, "json");
						$("#nombre").val("");
					});

	$('#cliente').on('change', function() {
		var idCliente = document.getElementById("cliente").value.split("/");
		varIdCliente = idCliente[0];
		var nombre = document.getElementById("cliente").value.split("/");
		varNombre = nombre[1]
		$("#nombre").val(varNombre);
	});

	var xx = 2;
	//var n = "";
	var list = [];
	var indice = 0;

	function add() {
		var p = document.getElementById("codigo");
		var inp = document.createElement("INPUT");
		inp.type = 'text';
		inp.id = 'codigo' + xx;
		inp.style = 'width:60px;'
		p.appendChild(inp);
		var pd = document.getElementById("monto");
		var ipt = document.createElement("INPUT");
		ipt.type = 'text';
		ipt.id = 'monto' + xx;
		ipt.style = 'width:140px;';
		pd.appendChild(ipt);
		xx++;

		// 		var pad = document.getElementById("delete");
		// 		var inpt = document.createElement("BUTTON");
		// 		inpt.type='button';
		// 		inpt.innerHTML='-';
		// 		inpt.onclick=function(xx){
		// 			alert();
		// 			$("#codigo"+ xx).remove();
		// 			$("#monto" + xx).remove();
		// 			$("#delete" + xx).remove();
		// 		}; 
		//		pad.appendChild(inpt);

		// 		n += "<div class='row' id='div"+ x +"'><div class='col-sm-2'></div><div class='col-sm-1 col-form' ><label class='col-form-label'>Código </label><input type='text'style='width:60px;'  id='codigo"+x+"'/></div><div class='col-sm-2 col-form'><label class='col-form-label'>Monto</label><input type='text'  style='width:140px;'  id='monto"+x+"'/></div> <div><input type='button' id='btnDel' value='-' onclick='delet("+ x +")' style='margin-top: 38px;' /></div></div>";
		// 		document.getElementById("otros").innerHTML += otros+n;
		// 		list[indice] = "monto" + x;
		// 		indice++;
		// 		x++;
		// 		n="";
	}

	function delet() {
		xx--;
		$("#codigo" + xx).remove();
		$("#monto" + xx).remove();
		$("#delete" + xx).remove();
	}

	function guardar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});
		if ($('#empresa option:selected').text() == '') {
			alert("Debe seleccionar una empresa valida");
			return;
		}

		if ($('#cliente option:selected').text() == 'Seleccione cliente') {
			alert("Debe seleccionar un cliente valido");
			return;
		}

		if (bool) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		var submitJson = {
			folio : document.getElementById("folio").value,
			idCliente : varIdCliente,
			nombre : document.getElementById("nombre").value,
			ivaEstado : document.getElementById("ivaEstado").checked,
			fecha : document.getElementById("fecha").value,
			iva : document.getElementById("iva").value,
			montoNeto : document.getElementById("montoNeto").value,
			montoTotal : document.getElementById("montoTotal").value,
			idUsuario : document.getElementById("idUsuario").value,
			idEmpresa : document.getElementById("empresa").value
		}

		$.post('/byeContabilidad/rest-services/private/compra/add',
				JSON.stringify(submitJson)).done(function(data) {
			if (data == 'OK') {
				alert('Se guardo exitosamente la compra');
				location.href = "agregar.jsp";
				limpia()
			} else {
				alert(data);
			}

		}).fail(function(jqxhr, settings, ex) {
			alert('No se pudo guardar la compra ' + ex);
		});
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);

	function limpia() {
		document.getElementById("folio").value = "";
		document.getElementById("rut").value = "";
		document.getElementById("nombre").value = "";
		document.getElementById("ivaEstado").value = "";
		//document.getElementById("fecha").value = "";
		document.getElementById("montoNeto").value = "";
		document.getElementById("montoTotal").value = "";
	}
</script>
</html>