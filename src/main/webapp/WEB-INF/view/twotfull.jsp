<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/tailwind.css" />
    <title>Twot de ${user.getusername()}</title>
</head>
<script>
    let xmlHttpReq = new XMLHttpRequest();
</script>
<body>
<div class="inline-flex">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div>
        <div>
            <span class="font-bold"><c:out value="${user.getnickname()}"/></span>
            <span class="font-light">@<c:out value="${user.getusername()}"/></span><br>
            <c:out value="${twot.getText()}"/> <br>
            <c:set var="date" value="${twot.getDate().toString()}"/>
            <span class="text-l font-sm italic text-right">
                <c:out value="${date.substring(11,13)}"/>:<c:out value="${date.substring(14,16)}"/>:<c:out value="${date.substring(17,19)}"/>
                <c:out value="${date.substring(8,10)}"/>/<c:out value="${date.substring(5,7)}"/>/<c:out value="${date.substring(0,4)}"/>
            </span>
        </div>
        <span class="text-xl mt-2">Répondre :</span>
        <form action="/answer" method="post">
            <input type="hidden" name="originalid" value="${twot.getId()}">
            <input type="hidden" name="userid" value="${user.getusername()}">
            <textarea class="resize-none border-none" name="text" placeholder="Donnez votre avis" required></textarea><br>
            <button type="submit" class="rounded-lg bg-gray-200">Envoyer</button>
        </form>
        <span class="fond-bold text-xl">Réponses :</span>
        <c:choose>
            <c:when test="${fn:length(twots)>0}">
                <c:forEach begin="0" end="${fn:length(twots)-1}" var="index">
                    <c:set var="twot" value="${twots[index]}"></c:set>
                    <%@include file="twotpreview.jsp"%>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>
                    Ce tweet n'as pas de réponses.
                </p>
            </c:otherwise>
        </c:choose>
        <button onclick="fadeOut()"><div id="overlay-back" style="position   : fixed;
                top        : 0;
                left       : 0;
                width      : 100%;
                height     : 100%;
                background : #000;
                opacity    : 0.6;
                filter     : alpha(opacity=60);
                z-index    : 5;
                display    : none;"></div></button>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function afficher_msg(id) {
        $('#'+id+', #overlay-back').fadeIn(500);
    }
    function fadeOut(){
        $('.answerForm, #overlay-back').fadeOut(500);
    }
    function likeButton(id){

    }
</script>
</html>