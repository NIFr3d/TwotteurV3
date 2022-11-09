<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="taglibs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../styles/tailwind.css" />
<title>Twotteur</title>
</head>
<body class="text-gray-300 bg-blue-900 h-screen w-screen">
<div class="flex h-screen mx-auto w-4/5">
    <%@include file="leftsidebar.jsp" %>
    <div class="ml-8 mt-8 w-full md:w-2/5">
        <c:if test="${!empty sessionScope.isLogged }">
            <form class="border-2 mt-2 w-full" action="../twot" method="post">
                <textarea class="resize-none border-none bg-blue-900 w-full" name="text" placeholder="Quoi de neuf ?" required></textarea>
                <input type="hidden" name="id" value=<c:out value="${userid}"></c:out> />
                <button type="submit" class="rounded-lg bg-blue-900 w-full">Twoter</button>
            </form>
        </c:if>
    </div>

</div>
</body>
</html>