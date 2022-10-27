<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de test</title>
</head>
<body>
<button type="button" onclick="envoi()">Test</button>
</body>
</html>
<script>
    var authToken = '${wstoken}';
    document.cookie = 'WEBSOCKET-IDENT=' + authToken + '; path=/';
    let socket = new WebSocket("ws://localhost:8080/chat");
    socket.onopen=function(){
        console.log("Socket ouvert");
    }
    socket.onmessage=function (msg){
        console.log(msg.data);
    }
    function envoi(){
        socket.send(JSON.stringify({
            message:"test"
        }));
    }
</script>