<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <% String nickname=(String)request.getAttribute("nickname"); %>
<title>Profil de <c:out value="${nickname}"/></title>
</head>
<body>
<div class="flex">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div>
        <c:choose>
        <c:when test="${fn:length(twots)>0}">
        <c:forEach begin="0" end="${fn:length(twots)-1}" var="index">
            <p>
                <span class="font-bold"><c:out value="${nickname}"/></span>
                <span class="font-light">@<c:out value="${nickname}"/></span>
                <span><c:out value="${twots[index].getDate()}"/></span><br>
                <c:out value="${twots[index].getText()}"/>

            </p>

        </c:forEach>
        </c:when>
        <c:otherwise>
            <p>
                Cet utilisateur n'as pas encore twotté.
            </p>
        </c:otherwise>
        </c:choose>
    </div>
</body>
</html>