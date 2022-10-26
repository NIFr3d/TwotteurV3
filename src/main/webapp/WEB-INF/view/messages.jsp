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
    <div class="ml-64 w-1/5">
        <h2 class="text-3xl font-bold mt-8 mb-8">Messages</h2>
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
    <div id="conv" class="w-2/5 h-full">
        <div class="mt-32 ml-6 text-xl">Cliquez sur un contact pour commencer une conversation !</div>
    </div>
</div>
</body>
</html>
<script>
    let conv=document.getElementById("conv");
    let xmlHttpReq = new XMLHttpRequest();
    function showConv(contact){
        conv.innerHTML="" +
            "<div class='w-full h-full border-l-2 border-r-2 overflow-hidden'>" +
                "<div class='text-xl text-center border-t-2 border-b-2' style='height:4%'>@"+contact+ "</div>" +
                "<div class='' style='height:90%' id='msghisto'></div>" +
                "<div class='text-center w-full border-t-2 border-b-2' style='height:6%'>"+
                    "<input  class='bg-blue-900 border-2 rounded-lg w-3/5' type='text'/>"+
                    "<button class='w-1/5 border-2 m-2'>Envoyer</button>"+
                "</div>"+
            "</div>";
        xmlHttpReq.open("GET","../conv/"+contact,false);
        xmlHttpReq.send(null);
        var msgs=JSON.parse(xmlHttpReq.responseText);
        msgs.sort(comparedates);
        msgs.forEach(function(msg){
            var convdiv=document.getElementById("msghisto");
            var align="";
            if(msg.user==0) align="text-right bg-blue-700 mr-2 ml-auto rounded-l-full rounded-tr-full";
            if(msg.user==1) align="text-left bg-gray-600 ml-2 rounded-r-full rounded-tl-full";
            convdiv.innerHTML+="<div class='"+align+" border-2 p-2 mb-2' style='max-width: min-content'>"+msg.text+"</div>"
        });
        function comparedates(value1,value2){
            var date1=sqlToJsDate(value1.date).getTime();
            var date2=sqlToJsDate(value2.date).getTime();
            if(date1<date2) return -1;
            else if(date1>date2) return 1;
            else return 0;
        }
        function sqlToJsDate(sqlDate){
            //sqlDate in SQL DATETIME format ("yyyy-mm-dd hh:mm:ss.ms")
            var sqlDateArr1 = sqlDate.split("-");
            //format of sqlDateArr1[] = ['yyyy','mm','dd hh:mm:ms']
            var sYear = sqlDateArr1[0];
            var sMonth = (Number(sqlDateArr1[1]) - 1).toString();
            var sqlDateArr2 = sqlDateArr1[2].split(" ");
            //format of sqlDateArr2[] = ['dd', 'hh:mm:ss.ms']
            var sDay = sqlDateArr2[0];
            var sqlDateArr3 = sqlDateArr2[1].split(":");
            //format of sqlDateArr3[] = ['hh','mm','ss.ms']
            var sHour = sqlDateArr3[0];
            var sMinute = sqlDateArr3[1];
            var sqlDateArr4 = sqlDateArr3[2].split(".");
            //format of sqlDateArr4[] = ['ss','ms']
            var sSecond = sqlDateArr4[0];
            var sMillisecond = sqlDateArr4[1];

            return new Date(sYear,parseInt(sMonth),sDay,sHour,sMinute,sSecond,sMillisecond);
        }
    }
</script>