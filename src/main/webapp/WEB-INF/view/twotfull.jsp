<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<div class="flex">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div>
        <%@include file="twotpreview.jsp"%>
        <span class="tilte">Réponses :</span>
        <c:choose>
            <c:when test="${fn:length(twots)>0}">
                <c:forEach begin="0" end="${fn:length(twots)-1}" var="index">
                    <c:set var="twot" value="${twots[index]}"></c:set>
                    <%@include file="twotpreview.jsp"%>
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
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>
                    Ce tweet n'as pas de réponses.
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
<script>
    function fadeOut(){
        $('.answerForm, #overlay-back').fadeOut(500);
    }
</script>
</html>