<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../styles/tailwind.css" />
<title>Twotteur - Enregistrement</title>
</head>
	<body>
	<div class="flex">
	<%@include file="leftsidebar.jsp"%>
	<div class="ml-64 mt-8">
	<form method="post">
		<label>Email</label><br>
		<input class="border-2 rounded-lg" type="email" name="email" required/><br>
		<label>Pseudo</label><br>
		<input class="border-2 rounded-lg" type="text" name="username" required/><br>
		<label>Mot de passe</label><br>
		<input class="border-2 rounded-lg mb-2" type="password" name="password" required/><br>
		<label>Confirmer le mot de passe</label><br>
		<input class="border-2 rounded-lg mb-2" type="password" name="password2" required/><br>
	<% if(request.getParameter("e")!=null){
		switch(request.getParameter("e")){
			case("0"):
				out.println("Veuillez entrer 2 fois le même mot de passe ! <br>");
				break;
			case("1"):
				out.println("Un compte est déjà associé à cet email ! <br>");
				break;
			case("2"):
				out.println("Pseudo déjà utilisé ! <br>");
				break;
			}
	}
	%>
		<button class="rounded-lg bg-gray-200" type="submit">S'enregistrer</button>
	</form>
	<p>Vous avez déjà un compte ?</p>
	<a class="text-blue-700 underline" href="./login">Se connecter</a>
	</div>
	</div>
	</body>
</html>