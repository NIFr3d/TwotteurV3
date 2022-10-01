<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Twotteur - Connexion</title>
</head>
<body>
<div class="flex">
<%@include file="leftsidebar.jsp"%>
<div class="block">
<form method="post">
<label for="email">Email</label><br>
<input class="border-2 rounded-lg" type="email" name="email"/><br>
<label for="password">Mot de passe</label><br>
<input class="border-2 rounded-lg mb-2" type="password" name="password"/><br>
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
<button class="rounded-lg bg-gray-200" type="submit">Connexion</button>

</form>
<p>Vous n'avez pas de compte ?</p>
<a class="text-blue-700 underline" href="./register">S'enregistrer</a>

</div>
</div>
</body>
</html>