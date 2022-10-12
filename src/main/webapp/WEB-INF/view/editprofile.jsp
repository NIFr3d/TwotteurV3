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
<body style="background: #0f2f79" class="text-gray-300">
<div class="inline-flex w-full">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div class="ml-64 mt-8">
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