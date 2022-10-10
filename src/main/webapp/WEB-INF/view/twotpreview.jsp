<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="com.example.twotteur.models.Twot" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<p>
    <a href="../twot/${twot.getId()}">
        <span class="font-bold"><c:out value="${user.getDisplayname()}"/></span>
        <span class="font-light">@<c:out value="${user.getNickname()}"/></span>
        <%
            Date nowplus1d=new Date(new Date().getTime() - TimeUnit.DAYS.toMillis( 1 ));
            Twot twot=(Twot)pageContext.getAttribute("twot");
            int cpre=twot.getDate().compareTo(nowplus1d);
            String timedif="";
            if(cpre==1){
                long dif=twot.getDate().getTime()- new Date().getTime();
                if(((dif/(1000*60*60))%24)==0) {
                    timedif=String.valueOf(Math.abs(dif/(1000*60))%60)+"min";
                }else{
                    timedif=String.valueOf(Math.abs(dif/(1000*60*60))%24)+"h";
                }
            }
        %>
        <c:set var="cpre"><%= cpre %></c:set>
        <c:choose>
            <c:when test="${cpre==1}">
                <%= timedif %>
            </c:when>
            <c:otherwise>
                <c:set var="dateE" value="${twot.getDate().toString()}"/>
                <c:out value="${dateE.substring(8,10)}"/>/<c:out value="${dateE.substring(5,7)}"/>/<c:out value="${dateE.substring(0,4)}"/>
            </c:otherwise>
        </c:choose>
        <br>
        <c:out value="${twot.getText()}"/> <br>
    </a>
    <div>
        <button class="" onclick="like${twot.getId()}()">
            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                 viewBox="0 0 471.701 471.701" style="enable-background:new 0 0 471.701 471.701;" xml:space="preserve" width="20" height="20">
                <path id="likeicon${twot.getId()}" d="M433.601,67.001c-24.7-24.7-57.4-38.2-92.3-38.2s-67.7,13.6-92.4,38.3l-12.9,12.9l-13.1-13.1
                    c-24.7-24.7-57.6-38.4-92.5-38.4c-34.8,0-67.6,13.6-92.2,38.2c-24.7,24.7-38.3,57.5-38.2,92.4c0,34.9,13.7,67.6,38.4,92.3
                    l187.8,187.8c2.6,2.6,6.1,4,9.5,4c3.4,0,6.9-1.3,9.5-3.9l188.2-187.5c24.7-24.7,38.3-57.5,38.3-92.4
                    C471.801,124.501,458.301,91.701,433.601,67.001z M414.401,232.701l-178.7,178l-178.3-178.3c-19.6-19.6-30.4-45.6-30.4-73.3
                    s10.7-53.7,30.3-73.2c19.5-19.5,45.5-30.3,73.1-30.3c27.7,0,53.8,10.8,73.4,30.4l22.6,22.6c5.3,5.3,13.8,5.3,19.1,0l22.4-22.4
                    c19.6-19.6,45.7-30.4,73.3-30.4c27.6,0,53.6,10.8,73.2,30.3c19.6,19.6,30.3,45.6,30.3,73.3
                    C444.801,187.101,434.001,213.101,414.401,232.701z"/>

            </svg>
            <span id="likecount${twot.getId()}">0</span>
        </button>
        <button class="text-left" onclick="afficher_msg(<c:out value="${twot.getId()}"></c:out>)">
            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                 viewBox="0 0 230 230" style="enable-background:new 0 0 230 230;" xml:space="preserve" width="20" height="20">
            <path d="M230,222.087V7.913H0v179h174.813L230,222.087z M15,22.913h200v171.826l-35.814-22.826H15V22.913z M62.75,112.08h104.5v15
                H62.75V112.08z M62.75,67.747h104.5v15H62.75V67.747z"/>
            </svg>
            <span id="answercount${twot.getId()}">0</span>
        </button>
    </div>
</p>
<form action="/answer" method="post" style="
            position:fixed;
            top:10%;
            left:30%;
            width:40%;
            height:auto;
            background-color:white;
            z-index:100;
            display:none;
            " id="<c:out value="${twot.getId()}"></c:out>" class="answerForm">
    <input type="hidden" name="originalid" value="${twot.getId()}">
    <input type="hidden" name="userid" value="${user.getNickname()}">
    <textarea class="resize-none border-none" name="text" placeholder="Donnez votre avis" required></textarea><br>
    <button type="submit" class="rounded-lg bg-gray-200">Répondre</button>
</form>
<script>
        function like${twot.getId()}(){
            xmlHttpReq.open("GET", "../like/${twot.getId()}", false);
            xmlHttpReq.send(null);
            let compte=parseInt(document.getElementById("likecount${twot.getId()}").innerHTML);
            if(xmlHttpReq.responseText==1) {
                document.getElementById("likecount${twot.getId()}").innerHTML=compte+1;
                document.getElementById("likeicon${twot.getId()}").style.fill="red";
            }
            else if(xmlHttpReq.responseText==2) {
                document.getElementById("likecount${twot.getId()}").innerHTML=compte-1;
                document.getElementById("likeicon${twot.getId()}").style.fill="black";
            }
        }
        xmlHttpReq.open("GET", "../countanswers/${twot.getId()}", false);
        xmlHttpReq.send(null);
        document.getElementById("answercount${twot.getId()}").innerHTML=xmlHttpReq.responseText;
        xmlHttpReq.open("GET", "../countlikes/${twot.getId()}", false);
        xmlHttpReq.send(null);
        document.getElementById("likecount${twot.getId()}").innerHTML=xmlHttpReq.responseText;
        xmlHttpReq.open("GET", "../didilike/${twot.getId()}", false);
        xmlHttpReq.send(null);
        if(xmlHttpReq.responseText==1)document.getElementById("likeicon${twot.getId()}").style.fill="red";
</script>
