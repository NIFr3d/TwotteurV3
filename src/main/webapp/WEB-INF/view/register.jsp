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
<body class="text-gray-300 bg-blue-900">
<div class="flex">
	<%@include file="leftsidebar.jsp"%>
	<div class="ml-64 mt-24">
	<div class="ml-24">
		<form method="post">
			<label class="text-2xl">Email</label><br>
			<input class="border-2 text-2xl rounded-lg bg-blue-900" type="email" name="email" required/><br>
			<label class="text-2xl">Pseudo</label><br>
			<input class="border-2 text-2xl rounded-lg bg-blue-900" type="text" name="username" required/><br>
			<label class="text-2xl">Mot de passe</label><br>
			<input class="border-2 text-2xl rounded-lg mb-2 bg-blue-900" type="password" name="password" required/><br>
			<label class="text-2xl">Confirmer le mot de passe</label><br>
			<input class="border-2 text-2xl rounded-lg mb-2 bg-blue-900" type="password" name="password2" required/><br>
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
			<button class="rounded-lg p-1 text-2xl" type="submit">S'enregistrer</button>
		</form>
		<p class="mt-24 text-xl">Vous avez déjà un compte ?</p>
		<a class="text-blue-700 underline text-xl" href="./login">Se connecter</a>
	</div>
	</div>
</div>
</body>
</html>