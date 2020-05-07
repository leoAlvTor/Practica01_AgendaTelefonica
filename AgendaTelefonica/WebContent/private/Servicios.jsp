<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Servicios</title>
<link href="/AgendaTelefonica_Web_exploded/private/css/editor.css" rel="stylesheet" />
<link href="/AgendaTelefonica_Web_exploded/private/css/errores.css" rel="stylesheet"/>
</head>
<body>
	<script>
		function setCodigo(param) {
			document.getElementById("tel_codigo").value = param;
			document.getElementById("imp_delete").value = param;
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
			setTimeout(function() {
				document.getElementById(id).style.border = '';
			}, 1000);
		}
		
		function llamar(){
			let numero = "";
			
			return false;
		}
		
		function enviarCorreo(){
			let correo = prompt("Ingrese el correo:");
			if(verificarEmail(correo)){
				window.location.href = "mailto:"+correo;
			}else{
				alert('Direccion de correo invalida');
			}
			return false;
		}
		
		function verificarEmail(mail){
			var re = /\S+@\S+\.\S+/;
	        return re.test(mail);
		}
		
	</script>

	<c:set var="obj_tabla" value="${requestScope['lst_telefonos'] }"></c:set>
	<c:set var="correo" scope="session" value="${usuario}"></c:set>

	<div class="container-fluid Default"
		style="margin-top: 2%; margin-bottom: 2%; min-height: auto; display: inline-block; position: static; float: none; text-align: center; width: auto; min-width: 100%;">
		<form id="form_cabecera" action="/AgendaTelefonica_Web_exploded/ServletCabecera"
			method="post">
			<input id="bsc_numero" name="bsc_numero" type="text"
				style="display: none" value="" />
			<div class="btn-group" role="group" aria-label="Basic example">
				<a href="#form1" onclick="changeCSS('form1')">
					<button type="button" class="btn btn-secondary"
						style="margin-right: 2%;">Registrar Teléfono</button>
				</a> <a href="#form1" onclick="changeCSS('form1')">
					<button type="button" class="btn btn-secondary"
						style="margin-right: 3%;">Modificar Registro</button>
				</a> <a href="#form1" onclick="changeCSS('form2')">
					<button type="button" class="btn btn-secondary"
						style="margin-right: 3%;">Eliminar Teléfono</button>
				</a>
				<button type="button" class="btn btn-secondary"
					style="margin-right: 1%;" onclick="buscar()">Buscar
					Registro</button>
				<button name="btn" value="listar_numeros" type="submit"
					class="btn btn-secondary">Listar Mis Números</button>
			</div>
		</form>
	</div>
	
	<c:set var="p1" value="${requestScope['error']}"></c:set>
        <c:if test="${p1 != null }">
        	<div class="isa_error">
   				<i class="fa fa-times-circle"></i>
  				<p class="error"><strong>Error:</strong> ${p1.error} </p><br/>
			</div>
       		<div class="isa_info">
   				<i class="fa fa-info-circle"></i>
   				<p class="error"><strong>Causa del error:</strong> ${p1.causa} </p><br/>
			</div>
    	</c:if>

	<div class="container-fluid">
		<div class="row" style="margin-bottom: 2%;">
			<div class="col-sm-2" style="float: none;">
				<img src="http://www.vvveb.com/vvvebjs/libs/builder/icons/image.svg"
					width="128" height="128" />
			</div>
			<div class=" col-5   col-sm-5">
				<div class="form-group">
					<form action="/AgendaTelefonica_Web_exploded/ServletBusquedas" method="post">
						<label>Listar Por Cédula:</label> <input type="text"
							class="form-control" name="cedula" placeholder="# de Cedula" />
						<button class="btn btn-success" style="margin-top: 1%; width: 30%" name="btn"
							value="bscCedula" type="submit">Buscar</button>
					</form>
					<br />
					<div class="form-group">
						<form action="/AgendaTelefonica_Web_exploded/ServletBusquedas" method="post">
							<label>Listar Por Correo:</label> <input type="text"
								class="form-control" name="correo" placeholder="Correo" '/>
							<button class="btn btn-success" style="margin-top: 1%; width: 30%" name="btn"
								value="bscCorreo" type="submit">Buscar</button>
						</form>
					</div>
				</div>
			</div>
			<div class=" col-sm-5">
				<div class="row">
					<a
						style="margin-right: 1%; padding: 5px; border: solid black 2px; border-radius: 2%; margin-left: auto;"
						href="/AgendaTelefonica_Web_exploded/ServletIMG?salir=true"><div class="col-sm-4">
							<img src="https://image.flaticon.com/icons/svg/58/58452.svg"
								width="100" height="100" />
						</div></a>
				</div>
			</div>
		</div>

		<div class="row marketing">
			<div class="col-lg-6" style="text-align: left;" id="form1">
				<h4>
					Datos Telefónicos <br />
				</h4>
				<ul class="list-group" style="">
					<form action="/AgendaTelefonica_Web_exploded/ServletCrtUpt" method="post">
						<li class="list-group-item"><br />
							<div class="form-group">
								<label>Mi correo:</label> <input id="tel_codigo"
									name="tel_codigo" type="text" style="display: none" value="" />
								<input name="correo" type="text" class="form-control"
									value="${correo}" readonly />
							</div></li>
						<li class="list-group-item"><br />
							<div class="form-group">
								<label>Numero:</label> <input name="numero" type="text"
									class="form-control" />
							</div></li>
						<li class="list-group-item"><label>Tipo:</label><br />
							<div class="form-group">
								<select class="form-control" name="tipo">
									<option value="convencional">Convencional</option>
									<option value="celular">Celular</option>
								</select>
							</div></li>
						<li class="list-group-item">
							<div class="form-group">
								<label>Operadora:</label> <select class="form-control"
									name="operadora">
									<option value="movistar">Movistar</option>
									<option value="claro">Claro</option>
									<option value="cnt">CNT</option>
								</select>
							</div>
						</li>
						<div class="btn-group" role="group" aria-label="Basic example"
							style="margin-bottom: 5%; margin-top: 2%">
							<button value="crear" name="btn" type="submit"
								class="btn btn-secondary" style="margin-right: 2em;">Crear
								Nuevo Registro</button>
							<button value="actualizar" name="btn" type="submit"
								class="btn btn-secondary">Actualizar Datos Telefónicos</button>
						</div>
					</form>
				</ul>
			</div>
			<div class="col-lg-6" id="form2">
				<h4
					style="text-decoration-color: rgb(255, 28, 0); border-color: rgb(252, 6, 6);">
					Eliminar Registro<br />
				</h4>
				<ul class="list-group">
					<li class="list-group-item"><br />
						<form action="/AgendaTelefonica_Web_exploded/ServletDelete" method="post">
							<div class="form-group">
								<label>Código:</label> 
								<input id="imp_delete" type="text" name="imp_delete" class="form-control" readonly/>
							</div>
							<button style="background-color: rgb(255, 255, 255); color: rgb(255, 0, 0);" type="submit">Eliminar
								Numero Telefónico</button>
						</form></li>
				</ul>

				<ul class="list-group">
					<li class="list-group-item"><br />
						<div class="form-group">
							<label>Lista de Teléfonos:</label>
						</div> <c:if test="${obj_tabla != null}">
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
						</c:if></li>
				</ul>
			</div>
		</div>

	</div>

</body>
</html>