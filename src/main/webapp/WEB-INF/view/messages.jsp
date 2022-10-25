<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="taglibs.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/tailwind.css" />
    <title>Messages</title>
</head>
<body class="text-gray-300 bg-blue-900 h-screen">
<div class="flex h-screen">
    <%@include file="leftsidebar.jsp" %>
    <div class="ml-64 mt-8 w-1/5">
        <h2 class="text-3xl font-bold mb-8">Messages</h2>
        <c:choose>
        <c:when test="${fn:length(contacts)>0}">
            <c:forEach begin="0" end="${fn:length(contacts)-1}" var="index">
                <button class="p-3 w-full text-left" type="button" onclick=showConv("${contacts[index].getusername()}")>
                        ${contacts[index].getusername()}
                </button><br>
            </c:forEach>
        </c:when>
        <c:otherwise>
            Aucun contact
        </c:otherwise>
        </c:choose>
    </div>
    <div id="conv" class="mt-8 w-full h-full">
        <div class="mt-24 ml-6 text-xl">Cliquez sur un contact pour commencer une conversation !</div>
    </div>
</div>
</body>
</html>
<script>
    let conv=document.getElementById("conv");
    function showConv(contact){
        conv.innerHTML="" +
            "<div class='mt-4 w-full h-full'>" +
                "<div class='text-xl text-center'>"+contact+ "</div>" +
                "<div class='' style='height:80%' id='msghisto'></div>" +
                "<div class='text-center w-full'>"+
                    "<input  class='bg-blue-900 border-2 rounded-lg' type='text'/>"+
                    "<button>Envoyer</button>"+
                "</div>"+
            "</div>";
    }
</script>