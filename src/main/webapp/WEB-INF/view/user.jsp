<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil de <%out.println((String)request.getAttribute("nickname")); %></title>
</head>
<body>

<c:forEach begin="0" end="${fn:length(texts)-1}" var="index">
	<tr>
      <td><c:out value="${texts[index]}"/></td>
      <td><c:out value="${timestamps[index]}"/></td>
   </tr>
</c:forEach>
</body>
</html>