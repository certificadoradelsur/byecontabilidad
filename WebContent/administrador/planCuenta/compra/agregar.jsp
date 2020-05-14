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
<style type="text/css">
.collapsing {
	-webkit-transition-delay: 0s;
	transition-delay: 0s;
	visibility: hidden;
}

.collapse.show {
	-webkit-transition-delay: 0s;
	transition-delay: 0s;
	visibility: visible;
}
</style>

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
						placeholder="Ingrese monto bruto" class="in" required="required"
						min="0" pattern="^[0-9]+" />
				</div>
				<div class="row">
					<div class="col-sm-2">&nbsp;&nbsp; Seleccione</div>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="ivaEstado">
							<label class="form-check-label" for="gridCheck1"> Iva </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="otrosEstado">
							<label class="form-check-label" for="gridCheck2"> Otros
								impuestos </label>
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
								<!-- 								<label class='col-form-label'>Código </label> <input type='number' style='width: 60px;' id='codigo1' class='in' required='required' /> -->
							</div>
							<div class='col-sm-2 col-form' id='monto'>
								<!-- 								<label class='col-form-label'>Monto</label> <input type='number' onkeyup='sumar()' style='width: 140px;' id='monto1' class='montoS' required='required' min='0' pattern='^[0-9]+'/> -->
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
	<input type="hidden" name="spTotal" id="spTotal" />
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
						borraName();
						$("#montoNeto").keyup(function() {
							var value = $(this).val();
							$("#montoTotal").val(value);
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
											cliente();
										}, "json");
					});

	var xx = 2;
	var list = [];
	var indice = 1;
	list[0] = [ 2 ];
	list[0][0] = 'codigo1';
	list[0][1] = 'monto1';

	$('#otrosEstado')
			.on(
					'change',
					function() {
						if (document.getElementById("otrosEstado").checked) {
							if (document.getElementById("montoNeto").value == "") {
								alert("Debe ingresar monto neto");
								document.getElementById("otrosEstado").checked = 0;
								return;
							}
							$("#montoNeto").prop("disabled", true);
							c = "<label class='col-form-label'>Código </label> <input type='number' style='width: 60px;' id='codigo1' class='in' required='required' />";
							document.getElementById("codigo").innerHTML = c;
							m = "<label class='col-form-label'>Monto</label> <input type='number' onkeyup='sumar()' style='width: 140px;' id='monto1' class='montoS' required='required' min='0' pattern='^[0-9]+'/>";
							document.getElementById("monto").innerHTML = m;
							$('#collapse').collapse('show');
							xx = 2;
							list = [];
							indice = 1;
							list[0] = [ 2 ];
							list[0][0] = 'codigo1';
							list[0][1] = 'monto1';

							if (document.getElementById("otrosEstado").checked
									&& document.getElementById("ivaEstado").checked) {
								var i = Math.round(document
										.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								var s = Math.round(document
										.getElementById("spTotal").value);
								var sumatoria = (m) + (i) + (s);
								$("#montoTotal").val(sumatoria);
							} else if (document.getElementById("otrosEstado").checked
									&& !document.getElementById("ivaEstado").checked) {
								//var i =Math.round(document.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								var s = Math.round(document
										.getElementById("spTotal").value)
								var sumatoria = (s) + (m);
								$("#montoTotal").val(sumatoria);
							}

						} else if (!document.getElementById("otrosEstado").checked) {
							if (!document.getElementById("otrosEstado").checked
									&& !document.getElementById("ivaEstado").checked) {
								$("#montoNeto").prop("disabled", false);
							}
							$('#collapse').collapse('hide');
							$(codigo).empty()
							$(monto).empty()

							if (!document.getElementById("otrosEstado").checked
									&& document.getElementById("ivaEstado").checked) {
								var i = Math.round(document
										.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								//var s = Math.round(document.getElementById("spTotal").value);
								$("#spTotal").val(0);
								var sumatoria = (m) + (i);
								$("#montoTotal").val(sumatoria);
							} else if (!document.getElementById("otrosEstado").checked
									&& !document.getElementById("ivaEstado").checked) {
								//var i =Math.round(document.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								// var s =Math.round(document.getElementById("spTotal").value);
								$("#spTotal").val(0);
								var sumatoria = (m);
								$("#montoTotal").val(sumatoria);
							}
						}
					})

	$('#ivaEstado')
			.on(
					'change',
					function() {
						if (document.getElementById("ivaEstado").checked) {
							if (document.getElementById("montoNeto").value == "") {
								alert("Debe ingresar monto neto");
								document.getElementById("ivaEstado").checked = 0;
								return;
							}
							$("#montoNeto").prop("disabled", true);
							var neto = document.getElementById("montoNeto").value;
							var iva = (neto * 0.19);
							var total = Math.round(Number(neto) + Number(iva));
							$("#iva").val(iva);

							if (document.getElementById("otrosEstado").checked
									&& document.getElementById("ivaEstado").checked) {
								var i = Math.round(document
										.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								var s = Math.round(document
										.getElementById("spTotal").value);
								var sumatoria = (i) + (m) + (s);
								$("#montoTotal").val(sumatoria);
							} else if (!document.getElementById("otrosEstado").checked
									&& document.getElementById("ivaEstado").checked) {
								var i = Math.round(document
										.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								//var s = Math.round(document.getElementById("spTotal").value);
								$("#spTotal").val(0);
								var sumatoria = (i) + (m);
								$("#montoTotal").val(sumatoria);
							}

						} else {

							if (!document.getElementById("otrosEstado").checked
									&& !document.getElementById("ivaEstado").checked) {
								$("#montoNeto").prop("disabled", false);
							}
							$("#montoTotal").val(
									document.getElementById("montoNeto").value);
							$("#iva").val(0);

							if (document.getElementById("otrosEstado").checked
									&& !document.getElementById("ivaEstado").checked) {
								//var i = Math.round(document.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								var s = Math.round(document
										.getElementById("spTotal").value);
								var sumatoria = (m) + (s);
								$("#montoTotal").val(sumatoria);
							} else if (!document.getElementById("otrosEstado").checked
									&& !document.getElementById("ivaEstado").checked) {
								//var i =Math.round(document.getElementById("iva").value);
								var m = Math.round(document
										.getElementById("montoNeto").value);
								//var s =Math.round(document.getElementById("spTotal").value);
								$("#spTotal").val(0);
								var sumatoria = (m);
								$("#montoTotal").val(sumatoria);
							}
						}
					})

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

	function add() {
		if (document.getElementById("codigo" + (xx - 1)).value == ''
				|| document.getElementById("monto" + (xx - 1)).value == '') {
			alert('Debe ingresar valores valido');
			return;
		}
		var p = document.getElementById("codigo");
		var inp = document.createElement("INPUT");
		inp.type = 'number';
		inp.id = 'codigo' + xx;
		inp.style = 'width:60px;';
		inp.className = 'in';
		p.appendChild(inp);
		var pd = document.getElementById("monto");
		var ipt = document.createElement("INPUT");
		ipt.type = 'number';
		ipt.id = 'monto' + xx;
		ipt.style = 'width:140px;';
		ipt.className = 'montoS';
		$(ipt).on('keyup', function() {
			sumar();
		});
		pd.appendChild(ipt);
		list[indice] = [ 2 ];
		list[indice][0] = 'codigo' + xx;
		list[indice][1] = 'monto' + xx;

		$("#codigo" + (xx - 1)).prop("disabled", true);
		$("#monto" + (xx - 1)).prop("disabled", true);
		indice++;
		xx++;
		console.log(list);

	}

	function delet() {
		if (xx >= 3) {
			xx--;
			var menos = document.getElementById("monto" + xx).value;
			console.log(menos);
			var total = document.getElementById("montoTotal").value;
			console.log(total);
			console.log(xx);

			indice--;
			$("#codigo" + xx).remove();
			$("#monto" + xx).remove();
			list.splice(indice, xx);
			$("#codigo" + (xx - 1)).prop("disabled", false);
			$("#monto" + (xx - 1)).prop("disabled", false);
			console.log(list);
			var resultado = (total) - (menos);
			$("#montoTotal").val(resultado);
		}else if(xx == 2){
			document.getElementById("otrosEstado").click();
		}
	}

	function sumar() {
		var total = 0;
		$(".montoS").each(function() {
			if (isNaN(parseFloat($(this).val()))) {
				total += 0;
			} else {
				total += parseFloat($(this).val());
			}
		});
		$("#spTotal").val(total);

		calculaTotal();
	}

	function calculaTotal() {

		if (document.getElementById("otrosEstado").checked
				&& document.getElementById("ivaEstado").checked) {
			var i = Math.round(document.getElementById("iva").value);
			var m = Math.round(document.getElementById("montoNeto").value);
			var s = Math.round(document.getElementById("spTotal").value);
			var sumatoria = (i) + (m) + (s);
			$("#montoTotal").val(sumatoria);
		} else if (!document.getElementById("otrosEstado").checked
				&& document.getElementById("ivaEstado").checked) {
			var i = Math.round(document.getElementById("iva").value);
			var m = Math.round(document.getElementById("montoNeto").value);
			//var s = Math.round(document.getElementById("spTotal").value);
			var sumatoria = (i) + (m);
			$("#montoTotal").val(sumatoria);
		} else if (document.getElementById("otrosEstado").checked
				&& !document.getElementById("ivaEstado").checked) {
			//var i = Math.round(document.getElementById("iva").value);
			var m = Math.round(document.getElementById("montoNeto").value);
			var s = Math.round(document.getElementById("spTotal").value);
			var sumatoria = (m) + (s);
			$("#montoTotal").val(sumatoria);
		} else if (!document.getElementById("otrosEstado").checked
				&& !document.getElementById("ivaEstado").checked) {
			//var i =Math.round(document.getElementById("iva").value);
			var m = Math.round(document.getElementById("montoNeto").value);
			// var s =Math.round(document.getElementById("spTotal").value)
			var sumatoria = +(m);
			$("#montoTotal").val(sumatoria);
		}
	}

	var listValores = [];
	var indiceValores = 0;

	function guardar() {
		var bool = $('.in').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		var mon = $('.montoS').toArray().some(function(el) {
			return $(el).val().length < 1
		});

		if (document.getElementById("folio").value == 0) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		if (document.getElementById("montoNeto").value == 0) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		if ($('#empresa option:selected').text() == '') {
			alert("Debe seleccionar una empresa valida");
			return;
		}

		if ($('#cliente option:selected').text() == 'Seleccione cliente') {
			alert("Debe seleccionar un cliente valido");
			return;
		}

		if (bool && mon) {
			alert("Todos los campos deben estar llenos");
			return;
		}

		var montoNeto = Math.round(document.getElementById("montoNeto").value)
		var montoTotal = Math
				.round(document.getElementById("montoTotal").value)
		var iva = Math.round(document.getElementById("iva").value)

		if (document.getElementById("otrosEstado").checked) {
			for (var i = 0; i < list.length; i++) {
				listValores[indiceValores] = [ 2 ];
				listValores[indiceValores][0] = document
						.getElementById(list[i][0]).value;
				listValores[indiceValores][1] = document
						.getElementById(list[i][1]).value;
				indiceValores++;
			}

			var submitJson = {
				folio : document.getElementById("folio").value,
				idCliente : varIdCliente,
				nombre : varNombre,
				ivaEstado : document.getElementById("ivaEstado").checked,
				otrosEstado : document.getElementById("otrosEstado").checked,
				fecha : document.getElementById("fecha").value,
				iva : iva,
				montoNeto : montoNeto,
				montoTotal : montoTotal,
				idUsuario : document.getElementById("idUsuario").value,
				idEmpresa : document.getElementById("empresa").value,
				otrosImpuestos : listValores.map(function(value) {
					return {
						codigo : value[0],
						monto : value[1]
					}
				}),
			}

			$.post('/byeContabilidad/rest-services/private/compra/add',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Se guardo exitosamente la compra');
					location.href = "index.jsp";
					limpia()
				} else {
					alert(data);
				}
			}).fail(function(jqxhr, settings, ex) {
				alert('No se pudo guardar la compra ' + ex);
			});

		} else if (!document.getElementById("otrosEstado").checked) {
			var submitJson = {
				folio : document.getElementById("folio").value,
				idCliente : varIdCliente,
				nombre : varNombre,
				ivaEstado : document.getElementById("ivaEstado").checked,
				otrosEstado : document.getElementById("otrosEstado").checked,
				fecha : document.getElementById("fecha").value,
				iva : iva,
				montoNeto : montoNeto,
				montoTotal : montoTotal,
				idUsuario : document.getElementById("idUsuario").value,
				idEmpresa : document.getElementById("empresa").value,
			}

			$.post('/byeContabilidad/rest-services/private/compra/add',
					JSON.stringify(submitJson)).done(function(data) {
				if (data == 'OK') {
					alert('Se guardo exitosamente la compra');
					location.href = "index.jsp";
					limpia()
				} else {
					alert(data);
				}

			}).fail(function(jqxhr, settings, ex) {
				alert('No se pudo guardar la compra ' + ex);
			});
		}
	}

	back.addEventListener("click", function() {
		window.history.back();
	}, false);

	function borraName() {
		document.getElementById("nombre").value = "";
	}

	function limpia() {
		document.getElementById("folio").value = "";
		document.getElementById("nombre").value = "";
		document.getElementById("ivaEstado").value = "";
		//document.getElementById("fecha").value = "";
		document.getElementById("montoNeto").value = "";
		document.getElementById("montoTotal").value = "";
	}
</script>
</html>