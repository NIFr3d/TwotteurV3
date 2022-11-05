<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/tailwind.css" />
    <title>Edition du profil</title>
</head>
<body class="text-gray-300 bg-blue-900 h-screen w-screen">
<div class="flex h-screen mx-auto w-4/5">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div class="w-3/5">
        <form method="post">
            <label>Pseudo</label><br>
            <input type="text" name="nickname" class="border-4 rounded-lg bg-blue-900" value="${user.getnickname()}" required/><br>
            <label>Biographie</label><br>
            <textarea class="resize-none border-4 rounded-lg bg-blue-900" name="biography">${user.getBiography()}</textarea><br>
            <button type="submit" class="text-white bg-blue-700 rounded-lg">Sauvegarder</button>
        </form>
    </div>
</div>
</body>
</html>