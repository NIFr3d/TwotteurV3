<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil de <c:out value="${user.getDisplayname()}"/></title>
</head>
</head>
<script>
    let xmlHttpReq = new XMLHttpRequest();
</script>
<body>
<div class="inline-flex w-full">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div style="width:50%">
        <div>
            <span class="text-xl font-bold"><c:out value="${user.getDisplayname()}"/> </span>
            <span class="text-l font-sm"><c:out value="@${user.getNickname()}"/> </span><br>
            <span class="text-xl font-sm">Biographie :</span><br>
            <span class="text-l"><c:out value="${user.getBiography()}"/></span><br>

            <c:set var="date" value="${user.getCreatedat().toString()}"/>
            <span class="text-l font-sm italic text-right">A rejoint twotteur le
                <c:out value="${date.substring(8,10)}"/>/<c:out value="${date.substring(5,7)}"/>/<c:out value="${date.substring(0,4)}"/>
            </span>
        </div>
        <div class="mt-10">
            <c:choose>
            <c:when test="${fn:length(twots)>0}">
            <c:forEach begin="0" end="${fn:length(twots)-1}" var="index">
                <c:set var="twot" value="${twots[index]}"></c:set>
                <c:set var="answercount" value="${answerCounts[index]}"></c:set>
                <%@include file="twotpreview.jsp"%>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <p>
                    Cet utilisateur n'as pas encore twotté.
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
</script>
</html>