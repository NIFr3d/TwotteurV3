<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<?xml version="1.0" encoding="iso-8859-1"?>
<link rel="stylesheet" href="../styles/tailwind.css" />
<title>Profil de ${user.getnickname()}</title>
</head>
<script>
    let xmlHttpReq = new XMLHttpRequest();
</script>
<body>
<div class="inline-flex w-full">
    <div><%@include file="leftsidebar.jsp" %></div>
    <div style="width:50%">
        <div>
            <span class="text-xl font-bold">${user.getnickname()} </span>
            <span class="text-l font-sm">@${user.getusername()}</span><br>
            <a href="../editprofile">
                <button>
                    <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                         viewBox="0 0 490.584 490.584" style="enable-background:new 0 0 490.584 490.584;" xml:space="preserve" height="20" width="20">
                        <g>
                            <g>
                                <path d="M100.911,419.404l123.8-51c3.1-2.1,6.2-4.2,8.3-6.2l203.9-248.6c6.2-9.4,5.2-21.8-3.1-29.1l-96.8-80.1
                                    c-8-5.9-20.3-6.8-28.1,3.1l-204.9,248.5c-2.1,3.1-3.1,6.2-4.2,9.4l-26,132.1C72.511,420.104,90.611,424.004,100.911,419.404z
                                     M326.611,49.004l65.5,54.1l-177.7,217.1l-64.9-53.7L326.611,49.004z M133.411,306.904l44.4,36.8l-57.2,23.6L133.411,306.904z"/>
                                <path d="M469.111,448.504h-349.5c0,0-72.5,3.4-75.2-15.2c0-1-1.8-5.6,7.6-17c7.3-9.4,6.2-21.8-2.1-29.1
                                    c-9.4-7.3-21.8-6.2-29.1,2.1c-19.8,23.9-25,44.7-15.6,63.5c25.5,47.5,111.3,36.3,115.4,37.3h348.5c11.4,0,20.8-9.4,20.8-20.8
                                    C490.011,457.804,480.611,448.504,469.111,448.504z"/>
                            </g>
                        </g>
                    </svg>
                </button>
            </a>
            <span class="text-xl font-sm">Biographie :</span><br>
            <span class="text-l">${user.getBiography()}</span><br>

            <c:set var="date" value="${user.getCreatedat().toString()}"/>
            <span class="text-l font-sm italic text-right">A rejoint twotteur le
                <c:out value="${date.substring(8,10)}"/>/<c:out value="${date.substring(5,7)}"/>/<c:out value="${date.substring(0,4)}"/>
            </span>
        </div>
        <div class="mt-10">
            <c:choose>
            <c:when test="${fn:length(twots)>0}">
            <c:forEach begin="0" end="${fn:length(twots)-1}" var="index">
                <c:set var="twot" value="${twots[index]}"></c:set>
                <c:set var="answercount" value="${answerCounts[index]}"></c:set>
                <%@include file="twotpreview.jsp"%>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <p>
                    Cet utilisateur n'as pas encore twotté.
                </p>
            </c:otherwise>
            </c:choose>
            <button onclick="fadeOut()"><div id="overlay-back" style="position   : fixed;
                top        : 0;
                left       : 0;
                width      : 100%;
                height     : 100%;
                background : #000;
                opacity    : 0.6;
                filter     : alpha(opacity=60);
                z-index    : 5;
                display    : none;"></div></button>
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function afficher_msg(id) {
        $('#'+id+', #overlay-back').fadeIn(500);
    }
    function fadeOut(){
        $('.answerForm, #overlay-back').fadeOut(500);
    }
</script>
</html>