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
<body class="text-gray-300 bg-sky-900 h-screen w-screen">
<div class="flex h-screen mx-auto w-3/5">
	<%@include file="leftsidebar.jsp"%>
	<div class="w-full md:w-2/5 my-auto h-4/5 ml-8">
		<form method="post">
		<label class="text-2xl">Email</label><br>
		<input class="border-2 text-2xl rounded-lg bg-blue-900" type="email" name="email"/><br>
		<label class="text-2xl">Mot de passe</label><br>
		<input class="border-2 text-2xl rounded-lg mb-2 bg-blue-900" type="password" name="password"/><br>
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
		<button class="rounded-lg bg-blue-900 text-2xl mb-24" type="submit">Connexion</button>

		</form>
		<p class="text-xl">Vous n'avez pas de compte ?</p>
		<a class="text-blue-700 underline text-xl" href="register">S'enregistrer</a>
	</div>
</div>
</body>
</html>