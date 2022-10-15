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
<body class="text-gray-300 bg-blue-900">
<div class="flex">
    <%@include file="leftsidebar.jsp" %>
    <div class="ml-64 mt-8 w-1/5">
        <%@include file="twotter.jsp"%>
    </div>

</div>
</body>
</html>