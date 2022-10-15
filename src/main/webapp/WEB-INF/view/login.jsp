<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../styles/tailwind.css" />
<title>Twotteur - Connexion</title>
</head>
<body class="text-gray-300 bg-blue-900">
<div class="flex">
	<%@include file="leftsidebar.jsp"%>
	<div class="ml-64 mt-8">
	<form method="post">
	<label>Email</label><br>
	<input class="border-2 rounded-lg bg-blue-900" type="email" name="email"/><br>
	<label>Mot de passe</label><br>
	<input class="border-2 rounded-lg mb-2 bg-blue-900" type="password" name="password"/><br>
	<% if(request.getParameter("e")!=null){
		switch(request.getParameter("e")){
			case("0"):
				out.println("Mot de passe incorrect. <br>");
				break;
			case("1"):
				out.println("Email inconnu ! <br>");
				break;
	}
	}
	%>
	<button class="rounded-lg bg-blue-900" type="submit">Connexion</button>

	</form>
	<p>Vous n'avez pas de compte ?</p>
	<a class="text-blue-700 underline" href="register">S'enregistrer</a>

	</div>
</div>
</body>
</html>