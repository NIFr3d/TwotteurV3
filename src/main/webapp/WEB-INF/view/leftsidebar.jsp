<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="text-center my-auto mr-4" style="width:fit-content; height:50%">
    <a href="../home"><button class="rounded-lg bg-blue-900 text-xl p-2" type=button>Accueil</button></a><br>
    <a href="../messages"><button class="rounded-lg bg-blue-900 text-xl p-2" type=button>Messages</button></a><br>
    <c:choose>
    <c:when test="${!empty sessionScope.isLogged }">
    <%
    if((boolean) session.getAttribute("isLogged")){
    %>
    <a href="../profile"><button class="rounded-lg bg-blue-900 text-xl p-2" type=button>Profil</button></a><br>
    <a href="../logout"><button class="rounded-lg bg-blue-900 text-xl p-2" type=button>Se déconnecter</button></a><br>
    <% } %>
    </c:when>
    <c:otherwise>
    <a href="../login"><button class="rounded-lg bg-blue-900 text-xl p-2" type=button>Se connecter</button></a><br>
    </c:otherwise>
    </c:choose>
    <button class="rounded-lg bg-blue-900 text-2xl p-2" type=button>Twoter</button>
</div>