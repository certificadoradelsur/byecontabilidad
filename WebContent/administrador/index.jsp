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
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/dashboard.css" rel="stylesheet">
<link href="../css/micss.css" rel="stylesheet">
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

<nav class="navbar navbar-expand-lg navbar-light bg-dark">
	<a class="nav-link" hidden="true">Certificadora del sur</a>
	<button class="navbar-toggler bg-light" type="button"
		data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>


       	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

				<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" style="color: white;"
				id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> <img
					src="../images/empresa.ico" alt="Icono" /> Empresa
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="../administrador/planCuenta/empresa/index.jsp"><img
						src="../images/empresa.ico" alt="Icono" /> Empresa</a>
					<div class="dropdown-divider"></div>	
					<a class="dropdown-item" href="../administrador/planCuenta/sucursal/index.jsp"><img
						src="../images/sucur.ico" alt="Icono" /> Sucursal</a>
				</div>
			
			</li>
			<li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/cuenta/index.jsp" style="color: white;"><img
					src="../images/banco.ico" alt="Icono" /> Cuenta Banco</a></li>
			 <li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/cliente/index.jsp" style="color: white;"><img
					src="../images/cliente.ico" alt="Icono" /> Cliente</a></li>		
			<li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/clasificacion/index.jsp" style="color: white;"><img
					src="../images/clasi.ico" alt="Icono" /> Clasificación</a></li>
			<li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/cuentaContable/index.jsp" style="color: white;"><img
					src="../images/conta.ico" alt="Icono" /> Cuenta Contable</a></li>
			<li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/comprobanteContable/index.jsp" style="color: white;"><img
					src="../images/reporte.ico" alt="Icono" /> Comprobante Contable</a></li>
					
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" style="color: white;"
				id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> <img
					src="../images/libro.ico" alt="Icono" /> Libros
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="../administrador/planCuenta/honorario/index.jsp"><img
						src="../images/honorario.ico" alt="Icono" /> Honorario</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="../administrador/planCuenta/compra/index.jsp"><img
						src="../images/compra.ico" alt="Icono" /> Compra</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="../administrador/planCuenta/venta/index.jsp"><img
						src="../images/venta.ico" alt="Icono" /> Venta</a>	
				</div></li>
					
			<li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/reporte/index.jsp" style="color: white;"><img
					src="../images/informe.ico" alt="Icono" /> Reportes</a></li>		
			<li class="nav-item"><a class="color nav-link"
				href="../administrador/planCuenta/usuario/index.jsp" style="color: white;"><img
					src="../images/user.ico" alt="Icono" /> Usuarios</a></li>				
		</ul>




		<a style="padding-left: 0; color: white;"
				href="/byeContabilidad/logout.jsp"><img
					src="../images/exit.ico" alt="Icono" /> Salir</a>

	</div>
</nav>
	<div class="container-lg">
		<form name="formulario" id="formulario">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h2 class="h3">Bienvenido al sistema de contabilidad
					de certificador del sur</h2>
			</div>

			<img src="../images/LOGO.jpg" alt="Logo" style="width: 900px" />
		</form>
	</div>

	<input type="hidden" name="idUsuario" id="idUsuario"
		value=<%=request.getUserPrincipal().getName()%> />
</body>
<script type="text/javascript">
	
</script>
</html>