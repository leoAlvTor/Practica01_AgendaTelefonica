<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registrate</title>
	<link rel="stylesheet" href="/AgendaTelefonica_Web_exploded/public/CSS/style.css">
</head>
<body style="display: flex; align-items: center; justify-content: center;">
	<section class="signup" style="position: absolute; top: 50%; -ms-transform: translateY(-50%); transform: translateY(-50%);">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Registrate</h2>
					<form method="POST" class="register-form" id="register-form" action="/AgendaTelefonica_Web_exploded/ServletRegister">
						<div class="form-group">
							<label for="cedula"><i class="zmdi zmdi-account material-icons-name"></i></label>
							<input type="text" name="cedula" id="cedula" placeholder="Cedula"/>
						</div>
						<div class="form-group">
							<label for="nombre"><i class="zmdi zmdi-email"></i></label>
							<input type="text" name="nombre" id="nombre" placeholder="Nombres"/>
						</div>
						<div class="form-group">
							<label for="apellido"><i class="zmdi zmdi-email"></i></label>
							<input type="text" name="apellido" id="apellido" placeholder="Apellidos"/>
						</div>
						<div class="form-group">
							<label for="correo"><i class="zmdi zmdi-lock"></i></label>
							<input type="text" name="correo" id="correo" placeholder="Correo"/>
						</div>
						<div class="form-group">
							<label for="password"><i class="zmdi zmdi-lock-outline"></i></label>
							<input type="password" name="password" id="password" placeholder="Password"/>
						</div>
						<div class="form-group form-button">
							<button class="form-submit" type="submit" value="registrarme">Registrate</button>
						</div>
					</form>
				</div>
				<div class="signup-image">
					<figure><img src="/AgendaTelefonica_Web_exploded/public/Images/register.jpg" alt="sing up image"/></figure>
					<a href="/AgendaTelefonica_Web_exploded/public/Index.html" class="signup-image-link">Ya tienes cuenta? Inicia Sesion</a>
				</div>
			</div>
		</div>
	</section>
<script src="vendor/jquery/jquery.min.js"></script>
</body>
</html>

		