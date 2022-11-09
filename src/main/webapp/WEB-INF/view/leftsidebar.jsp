<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="text-center hidden md:block h-1/2 w-fit my-auto mr-4">
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
    <c:if test="${!empty sessionScope.isLogged }">
    <%
        if((boolean) session.getAttribute("isLogged")){
    %>
        <button class="rounded-lg bg-blue-900 text-2xl p-2" onclick="twotter()" type=button>Twoter</button>
    <% } %>
    </c:if>
</div>
<form class="border-2 p-2 rounded-lg mt-2 w-4/5 md:w-3/5 bg-blue-700 mx-auto top-1/4 left-auto md:left-1/4" style="
          position:fixed;
          height:auto;
          z-index:100;
          display:none;" method="post" id="twotterform">
    <textarea id="twottextarea" class="resize-none border-none bg-blue-900 w-full" name="text" placeholder="Quoi de neuf ?" required></textarea>
    <input type="hidden" name="id" value=<c:out value="${userid}"></c:out> />
    <button type="button" onclick="sendtwot()" class="rounded-lg bg-blue-900 w-full">Twoter</button>
</form>
<button onclick="hide()"><div id="overlay-back0" style="position   : fixed;
                top        : 0;
                left       : 0;
                width      : 100%;
                height     : 100%;
                background : #000;
                opacity    : 0.6;
                filter     : alpha(opacity=60);
                z-index    : 5;
                display    : none;"></div></button>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function twotter() {
        $('#twotterform, #overlay-back0').fadeIn(500);
    }
    function hide(){
        $('#twotterform, #overlay-back0').fadeOut(500);
    }
    var twotform=document.getElementById("twotterform");
    function sendtwot(){
        var response = fetch('../resttwot', {
            method: 'POST',
            body: new FormData(twotform)
        });
        document.getElementById("twottextarea").value="";
        hide();
    }
</script>