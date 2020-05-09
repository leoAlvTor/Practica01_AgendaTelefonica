<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Telephoniq</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700, 900|Vollkorn:400i" rel="stylesheet">
	<link rel="stylesheet" href="/AgendaTelefonica_Web_exploded/private/css/bootstrap.min.css">
	<link rel="stylesheet" href="/AgendaTelefonica_Web_exploded/private/css/jquery-ui.css">
	<link rel="stylesheet" href="/AgendaTelefonica_Web_exploded/private/css/jquery.fancybox.min.css">
	<link rel="stylesheet" href="/AgendaTelefonica_Web_exploded/private/css/aos.css">
	<link rel="stylesheet" href="/AgendaTelefonica_Web_exploded/private/css/style.css">
</head>

<body id="home-section" style="background-image: url('https://wallpaperaccess.com/full/7285.jpg');">

<form style="display: none;" id="form_cabecera" action="/AgendaTelefonica_Web_exploded/ServletCabacera" method="POST">
	<input id="bsc_numero" name="bsc_numero" type="text" style="display: none" value="" />
	<button id="btn_listar" name="btn" value="listar_numeros" type="submit" class="btn btn-secondary" style="display: none">Listar Mis Números</button>
</form>

<script>
	function setCodigo(param) {
		document.getElementById("tel_codigo").value = param;
		document.getElementById("imp_delete").value = param;
	}

	function listar(){
		document.getElementById('btn_listar').click();
	}

	function buscar() {
		let rs = prompt("Ingrese el numero a buscar:", "");
		rs = rs.replace(/\D/g, '');
		if (rs.length === 9 || rs.length === 10) {
			document.getElementById("bsc_numero").value = rs;
			document.getElementById("form_cabecera").submit();
		} else {
			alert('Debe ingresar un numero valido!');
		}
		return false;
	}

	function changeCSS(id) {
		document.getElementById(id).style.border = 'solid 6px';
		document.getElementById(id).style.borderRadius = '3%';
		setTimeout(function () {
			document.getElementById(id).style.border = '';
		}, 1000);
	}

	function llamar() {
		let numero = "";
		return false;
	}

	function enviarCorreo() {
		let correo = prompt("Ingrese el correo:");
		if (verificarEmail(correo)) {
			window.location.href = "mailto:" + correo;
		} else {
			alert('Direccion de correo invalida');
		}
		return false;
	}

	function verificarEmail(mail) {
		var re = /\S+@\S+\.\S+/;
		return re.test(mail);
	}

</script>

<c:set var="obj_tabla" value="${requestScope['lst_telefonos'] }"></c:set>
<c:set var="correo" scope="session" value="${usuario}"></c:set>


<div class="site-wrap">
	<div class="site-mobile-menu site-navbar-target">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close mt-3">
				<span class="icon-close2 js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>

	<header class="site-navbar js-sticky-header site-navbar-target" role="banner">

		<div class="container bg-light shadow fixed-top">
			<div class="row align-items-center">

				<div style="float: left; margin-right: auto;">
					<h1 class="mb-0 site-logo"><a class="h2 mb-0" style="margin-left: 5px;">TelePhoniq<span
							class="text-primary">.</span> </a></h1>
				</div>

				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
							aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav nav nav-tabs">
							<li class="nav-item">
								<a class="nav-link" onclick="buscar()">Buscar Un Telefono</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" onclick="listar()">Listar Mis Telefonos</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="/AgendaTelefonica_Web_exploded/ServletIMG?salir=true">Cerrar Sesion</a>
							</li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</header>

	<section class="site-section" style="margin-top: 55px;">
		<div class="container">

			<div class="form-submit">
				<form id="form_cabecera" action="/AgendaTelefonica_Web_exploded/ServletBusquedas" method="post">
					<h5 style="color: white" , class="bg-black"><strong>Listar Telefonos Por Cedula:</strong></h5>
					<div class="input-group input-group-lg">
						<input type="text" class="form-control" id="cedula" name="cedula" placeholder="Numero de cedula">
						<div class="input-group-prepend">
							<button class="input-group-text" name="btn" value="bscCedula" type="submit">Buscar</button>
						</div>
					</div>
				</form>
			</div>
			<br>
			<div class="form-submit">
				<form action="/AgendaTelefonica_Web_exploded/ServletBusquedas" method="post">
					<h5 style="color: white" , class="bg-black"><strong>Listar Telefonos Por Correo Electronico:</strong></h5>
					<div class="input-group input-group-lg">
						<input type="text" class="form-control" placeholder="Correo Eletronico" name="correo">
						<div class="input-group-prepend">
							<button class="input-group-text" name="btn" value="bscCorreo" type="submit">Buscar</button>
						</div>
					</div>
				</form>
			</div>

			<br>

			<hr style="height:1px;border:none;color:#333;background-color:#333;" />

			<br>

			<div class="row hover-1-wrap mb-5 mb-lg-0 overflow-auto">
				<div class="col-lg-2">
					<h4><strong>Lista de telefonos:</strong></h4><br>
				</div>
				<c:if test="${obj_tabla != null}">
				<div class="col-lg-12">
					<div class="hover-1">
						<table class="table table-dark">
							<thead>
							<tr>
								<th>Numero</th>
								<th>Tipo</th>
								<th>Operadora</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${obj_tabla[0] == false}">
								<c:forEach var="telefono" items="${obj_tabla[1]}">
									<tr>
										<td><a href="tel:${telefono.numero}">${telefono.numero}</a></td>
										<td>${telefono.tipo}</td>
										<td>${telefono.operadora}</td>

									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${obj_tabla[0] == true}">
								<c:forEach var="telefono" items="${obj_tabla[1]}">
									<tr>
										<td><a href="tel:${telefono.numero}">${telefono.numero}</a></td>
										<td>${telefono.tipo}</td>
										<td>${telefono.operadora}</td>
										<td>
											<button id="${telefono.codigo}" class="btn btn-secondary"
													onclick="setCodigo(this.id)">Modificar</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
						</table>
					</div>
				</div>
				</c:if>
			</div>

			<br>

			<hr style="height:1px;border:none;color:#333;background-color:#ffff;" />

			<br>

			<div class="form-submit">
				<form action="/AgendaTelefonica_Web_exploded/ServletCrtUpt" method="post">
					<h5><strong>Datos Telefonicos:</strong></h5><br>
					<label class="label">
						<h5>Mi Correo</h5>
					</label>
					<div class="input-group input-group-lg">
						<input id="tel_codigo" name="tel_codigo" type="text" style="display: none" value="" />
						<input type="text" class="form-control" placeholder="Mi correo" readonly>
					</div><br>
					<label class="label">
						<h5>Numero</h5>
					</label>
					<div class="input-group input-group-lg">
						<input type="text" class="form-control" placeholder="Numero de telefono" name="numero">
					</div><br>
					<label class="label">
						<h5>Tipo de Telefono</h5>
					</label>
					<div class="input-group input-group-lg">
						<select class="form-control" name="tipo">
							<option>Convencional</option>
							<option>Celular</option>
						</select>
					</div><br>
					<label class="label">
						<h5>Operadora</h5>
					</label>
					<div class="input-group input-group-lg">
						<select class="form-control" name="operadora">
							<option>Movistar</option>
							<option>Claro</option>
							<option>CNT</option>
							<option>Etapa EP</option>
						</select>
					</div><br>
					<div class="input-group input-group-lg">
						<button type="submit" class="form-control btn-block btn-secondary mb-2" value="crear" name="btn">Crear
							Registro</button>
						<button type="submit" class="form-control mb-2" value="actualizar" name="btn">Actualizar Registro</button>
					</div><br>
				</form>
			</div>

			<br>

			<hr style="height:1px;border:none;color:#333;background-color:#ffff;" />

			<br>

			<div class="form-submit">
				<form action="/AgendaTelefonica_Web_exploded/ServletDelete" method="post">
					<h5><strong>Eliminar Registro Telefonico:</strong></h5><br>
					<label class="label">
						<h5>Numero Telefonico</h5>
					</label>
					<div class="input-group input-group-lg">
						<input type="text" class="form-control" placeholder="Seleccione un numero de la tabla">
					</div><br>
					<div class="input-group input-group-lg">
						<button type="submit" class="form-control btn-block btn-danger mb-2">Eliminar Numero Telefonico</button>
					</div><br>
				</form>
			</div>
		</div>

	</section>

	<footer class="site-footer">
		<div class="container">
			<div class="border-top">
				<p class="copyright">
					Copyright &copy; 2020. Designed <i class="icon-heart text-danger" aria-hidden="true"></i> by <a
						href="https://github.com/leoAlvTor" target="_blank">Leo Alrvarado</a>
				<p><a href="https://github.com/leoAlvTor/Practica01_AgendaTelefonica">GitHub Repository</a></p>
				<p><a href="https://www.facebook.com/programmingCube/?ref=bookmarks">Our <small>(TMP)</small>Web Page</a>
				</p>
			</div>
		</div>
	</footer>
</div>
<script src="/AgendaTelefonica_Web_exploded/private/js/jquery-3.3.1.min.js"></script>
<script src="/AgendaTelefonica_Web_exploded/private/js/jquery-ui.js"></script>
<script src="/AgendaTelefonica_Web_exploded/private/js/bootstrap.min.js"></script>
<script src="/AgendaTelefonica_Web_exploded/private/js/aos.js"></script>


<script src="/AgendaTelefonica_Web_exploded/private/js/main.js"></script>


</body>
</html>