<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Registrate</title>
	<link rel="stylesheet" href="/AgendaTelefonica/public/CSS/Index.css"/>
</head>
<body>
	<form class="/AgendaTelefonica/ServletRegister" action="/AgendaTelefonica/ServletRegister" method="post">
		<div class="imgcontainer">
              <img src="/AgendaTelefonica/public/Images/user.png" alt="Avatar" class="avatar"/>
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
            <div class="container">
            	<label for="cedula"><b>Cedula: </b></label>
				<input type="text" placeholder="Cedula" name="cedula" required></input>
				
				<label for="nombre"><b>Nombre: </b></label>
				<input type="text" placeholder="Nombre" name="nombre" required></input>
				
				<label for="apellido"><b>Apellido: </b></label>
				<input type="text" placeholder="Apellido" name="apellido" required></input>
				
				<label for="correo"><b>Correo: </b></label>
				<input type="text" placeholder="Correo" name="correo" required></input>
				
				<label for="password"><b>Password: </b></label>
				<input type="password" placeholder="Password" name="password" required></input>
            	
            	<button type="submit" value = "registrarme">Registrate</button>
            	<a href="/AgendaTelefonica/public/Index.html">Regresar al Log In</a>
            </div>
	</form>
</body>
</html>

		