<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/tailwind.css" />
    <title>Twot de ${user.getNickname()}</title>
</head>
<script>
    let xmlHttpReq = new XMLHttpRequest();
</script>
<body>
<div class="inline-flex">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div>
        <%@include file="twotpreview.jsp"%>
        <span class="text-xl mt-2">Répondre :</span>
        <form action="/answer" method="post">
            <input type="hidden" name="originalid" value="${twot.getId()}">
            <input type="hidden" name="userid" value="${user.getNickname()}">
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