<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="w-60">
<a href="../home"><button class="rounded-lg bg-gray-200" type=button>Accueil</button></a><br>
<a href="../messages"><button class="rounded-lg bg-gray-200" type=button>Messages</button></a><br>
<c:choose>
<c:when test="${!empty sessionScope.isLogged }">
<%
if((boolean) session.getAttribute("isLogged")){
%>
<a href="../profile"><button class="rounded-lg bg-gray-200" type=button>Profil</button></a><br>
<a href="../auth/singout"><button class="rounded-lg bg-gray-200" type=button>Se déconnecter</button></a><br>
<% } %>
</c:when>
<c:otherwise>
<a href="../auth/signin"><button class="rounded-lg bg-gray-200" type=button>Se connecter</button></a><br>
</c:otherwise>
</c:choose>
<button class="rounded-lg bg-gray-200" type=button>Twoter</button>
</div>