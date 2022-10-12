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
<body>
<div class="flex">
    <%@include file="leftsidebar.jsp" %>
    <div class="ml-64 mt-8">
        <%@include file="twotter.jsp"%>
    </div>

</div>
</body>
</html>