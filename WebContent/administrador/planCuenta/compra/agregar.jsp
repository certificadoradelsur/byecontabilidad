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
					</label> <label>&nbsp;&nbsp;&nbsp; Agregar otros impuestos</label> <input
						type="button" id="btnAdd" value="+" onclick="add()"/> <input
						type="button" id="btnDel" value="-" onclick="del()"/>
					<div class="col-sm-10" id="empresas">
						<div class="form-check"></div>
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
						$("#empresa").select2({width:'200'});
											
				        $("#montoNeto").keyup(function () {
				            var value = $(this).val();
				            $("#montoTotal").val(value);
				        });
						
				     	$('#ivaEstado').on('change', function(){
					         if(document.getElementById("ivaEstado").checked){
					        	 if(document.getElementById("montoNeto").value==""){
					        		 alert("Debe ingresar monto neto");
					        		 document.getElementById("ivaEstado").checked=0;
					        		 return;
					        	 }					        	 
					      $("#montoNeto").prop("disabled", true);      
					      var neto = document.getElementById("montoNeto").value;
					      var iva = (neto * 0.19);
					      var total = Math.round(Number(neto) + Number(iva));
					             $("#montoTotal").val(total);
					             $("#iva").val(iva);					      
					   	}else{
					             $('#collapse').collapse('hide');
					             $("#montoTotal").val(document.getElementById("montoNeto").value);
					             $("#montoNeto").prop("disabled", false);
					             $("#iva").val(0);
								}
					     })
					     
					     
					     $('#otrosImpuestos').on('change', function(){
					    	 if(document.getElementById("otrosImpuestos").checked){
					    $('#collapse').collapse('show');
					    	}else{
					    $('#collapse').collapse('hide');
					    	}
					     })
					     
					     
					     					        
				        
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
										}, "json");	
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
	
	function add() {
		  var btn = document.createElement("BUTTON");
		  empresas.innerHTML = "CLICK ME";
		  document.body.appendChild(btn);
		}
	
	
	
	function del() {
		
		
	}
	
	
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

		var submitJson = {
			folio :document.getElementById("folio").value,
			rut : document.getElementById("rut").value,
			nombre : document.getElementById("nombre").value,
			ivaEstado : document.getElementById("ivaEstado").checked,
			fecha : document.getElementById("fecha").value,
			iva: document.getElementById("iva").value,
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
//		document.getElementById("fecha").value = "";
		document.getElementById("montoNeto").value = "";
		document.getElementById("montoTotal").value = "";
	}
	
</script>
</html>