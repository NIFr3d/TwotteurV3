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
<body class="text-gray-300 bg-sky-900 h-screen w-screen">
<div class="flex h-screen mx-auto w-4/5">
    <%@include file="leftsidebar.jsp" %>
    <div class="md:border-x w-full md:w-3/5 mx-auto">
        <div class="ml-2 mb-2">
            <span class="font-bold"><c:out value="${user.getnickname()}"/></span>
            <span class="font-light">@<c:out value="${user.getusername()}"/></span>
            <c:if test="${user.getid()==sessionScope.userid}">
                <button onclick="deleteFullTwot(${twot.getId()})" class="">
                    <svg width="24px" height="24px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                        <path fill="white" d="M20 2h-4v-.85C16 .52 15.48 0 14.85 0h-5.7C8.52 0 8 .52 8 1.15V2H4c-1.1 0-2 .9-2 2 0 .74.4 1.38 1 1.73v14.02C3 22.09 4.91 24 7.25 24h9.5c2.34 0 4.25-1.91 4.25-4.25V5.73c.6-.35 1-.99 1-1.73 0-1.1-.9-2-2-2zm-1 17.75c0 1.24-1.01 2.25-2.25 2.25h-9.5C6.01 22 5 20.99 5 19.75V6h14v13.75z"/>
                        <path fill="white" d="M8 20.022c-.553 0-1-.447-1-1v-10c0-.553.447-1 1-1s1 .447 1 1v10c0 .553-.447 1-1 1zm8 0c-.553 0-1-.447-1-1v-10c0-.553.447-1 1-1s1 .447 1 1v10c0 .553-.447 1-1 1zm-4 0c-.553 0-1-.447-1-1v-10c0-.553.447-1 1-1s1 .447 1 1v10c0 .553-.447 1-1 1z"/>
                    </svg>
                </button>
            </c:if><br>
            <c:out value="${twot.getText()}"/> <br>
            <c:set var="date" value="${twot.getDate().toString()}"/>
            <span class="text-l font-sm italic text-right">
                <c:out value="${date.substring(11,13)}"/>:<c:out value="${date.substring(14,16)}"/>:<c:out value="${date.substring(17,19)}"/>
                <c:out value="${date.substring(8,10)}"/>/<c:out value="${date.substring(5,7)}"/>/<c:out value="${date.substring(0,4)}"/>
            </span>
        </div>
        <div class="border-b-2 w-full"></div>
        <div class="mx-2 mb-2">
            <span class="text-xl mt-2">Répondre :</span>
            <form action="/answer" method="post">
                <input type="hidden" name="originalid" value="${twot.getId()}">
                <input type="hidden" name="userid" value="${user.getusername()}">
                <textarea class="w-full rounded-lg resize-none border-none bg-blue-700" name="text" placeholder="Donnez votre avis" required></textarea><br>
                <button type="submit" class="rounded-lg bg-blue-700 p-2">Envoyer</button>
            </form>
        </div>
        <div class="border-b-2 w-full"></div>
        <div class="mt-2">
            <span class="ml-2 fond-bold text-xl">Réponses :</span>
            <c:choose>
                <c:when test="${fn:length(twots)>0}">
                    <c:forEach begin="0" end="${fn:length(twots)-1}" var="index">
                        <c:set var="twot" value="${twots[index]}"></c:set>
                        <%@include file="twotpreview.jsp"%>
                        <div class="border-b w-full"></div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>
                        Ce tweet n'as pas de réponses.
                    </p>
                </c:otherwise>
            </c:choose>
        </div>
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
    function like(id){
        xmlHttpReq.open("GET", "../like/"+id, false);
        xmlHttpReq.send(null);
        let compte=parseInt(document.getElementById("likecount"+id).innerHTML);
        if(xmlHttpReq.responseText==1) {
            document.getElementById("likecount"+id).innerHTML=compte+1;
            document.getElementById("likeicon"+id).style.fill="red";
        }
        else if(xmlHttpReq.responseText==2) {
            document.getElementById("likecount"+id).innerHTML=compte-1;
            document.getElementById("likeicon"+id).style.fill="black";
        }
    }
    function deleteTwot(id){
        xmlHttpReq.open("DELETE", "../twot", false);
        var formdata =new FormData();
        formdata.append("id",id);
        xmlHttpReq.send(formdata);
        if(xmlHttpReq.responseText==1) {
            var div=document.getElementById("twot"+id);
            div.parentNode.removeChild(div);
        }
    }
    function deleteFullTwot(id){
        xmlHttpReq.open("DELETE", "../twot", false);
        var formdata =new FormData();
        formdata.append("id",id);
        xmlHttpReq.send(formdata);
        if(xmlHttpReq.responseText==1) {
            window.location.replace("../")
        }
    }
</script>
</html>