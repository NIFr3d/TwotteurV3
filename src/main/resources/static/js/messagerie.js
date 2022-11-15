let socket = new WebSocket("ws://localhost:8080/chat");
var currentcontact="";
let xmlHttpReq = new XMLHttpRequest();
function showConv(contact){
    currentcontact=contact;
    document.getElementById("contactlist").classList.add("hidden");
    conv.classList.add("w-full");
    conv.classList.remove("hidden");
    var notifdiv=document.getElementById("notif"+contact);
    if(!notifdiv.classList.contains("hidden")) notifdiv.classList.add("hidden");
    conv.innerHTML="" +
        "<div class='w-full h-full flex flex-col md:mt-8 mt-2'>" +
        "<div class='bold text-2xl relative h-fit inline-flex'>" +
        "<button class='float-left' onclick='hideConv()'><svg class='h-8 md:h-6 w-8 md:h-6' fill='white' xmlns='http://www.w3.org/2000/svg'" +
        "viewBox='0 0 52.502 52.502' style='enable-background:new 0 0 52.502 52.502;' xml:space='preserve'>" +
        "<path d='M51.718,50.857l-1.341-2.252C40.075,31.295,25.975,32.357,22.524,32.917v13.642L0,23.995L22.524,1.644v13.43" +
        "c0.115,0,0.229-0.001,0.344-0.001c12.517,0,18.294,5.264,18.542,5.496c13.781,11.465,10.839,27.554,10.808,27.715L51.718,50.857z" +
        " M25.505,30.735c5.799,0,16.479,1.923,24.993,14.345c0.128-4.872-0.896-15.095-10.41-23.012c-0.099-0.088-5.935-5.364-18.533-4.975" +
        "l-1.03,0.03V6.447L2.832,24.001l17.692,17.724V31.311l0.76-0.188C21.338,31.109,22.947,30.735,25.505,30.735z'/></svg></button>"+
        "<span class='w-full text-center'>@"+contact+ "</span></div>" +
        "<div class='mt-2 overflow-y-scroll relative md:h-5/6 h-3/4' id='msghisto'></div>" +
        "<div class='text-center w-full h-fit'>"+
        "<input id='input' class='bg-blue-900 border-2 rounded-lg w-3/5' type='text'/>"+
        "<button class='w-1/5 border-2 m-2' onclick='envoyermsg(\""+contact+"\")'>Envoyer</button>"+
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
        convdiv.innerHTML+="<div class='"+align+" border-2 p-2 mb-2' style='width: max-content; max-width:50%;'>"+msg.text+"</div>";
        convdiv.scrollTop=convdiv.scrollHeight;
    });
    var input=document.getElementById("input");
    input.addEventListener('keypress',function(e){
        if(e.key=='Enter') envoyermsg(contact);
    });


}
socket.onopen=function(){
    console.log("Socket ouvert");
}
socket.onmessage=function (msg){
    msg=JSON.parse(msg.data);
    if(currentcontact==msg.sender){
        var convdiv=document.getElementById("msghisto");
        convdiv.innerHTML+="<div class='text-left bg-gray-600 ml-2 rounded-r-full rounded-tl-full border-2 p-2 mb-2' style='width: max-content; max-width:50%;'>"+msg.message+"</div>";
        convdiv.scrollTop=convdiv.scrollHeight;
    }else{
        var div=document.getElementById("notif"+msg.sender);
        count=parseInt(div.innerHTML);
        div.innerHTML=count+1;
        if(div.classList.contains("hidden")) div.classList.remove("hidden");
    }
}
function envoiws(message,receiver){
    socket.send(JSON.stringify({
        message:message,
        receiver:receiver
    }));
}
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
function envoyermsg(contact){
    var text=document.getElementById("input").value;
    xmlHttpReq.open("POST","../conv/"+contact,false);
    var formdata =new FormData();
    formdata.append("text",text);
    xmlHttpReq.send(formdata);
    if(xmlHttpReq.responseText==1){
        envoiws(text,contact);
        var convdiv=document.getElementById("msghisto");
        convdiv.innerHTML+="<div class='text-right bg-blue-700 mr-2 ml-auto rounded-l-full rounded-tr-full border-2 p-2 mb-2' style='width: max-content; max-width:50%;'>"+text+"</div>";
        document.getElementById("input").value="";
        convdiv.scrollTop=convdiv.scrollHeight;
    }
}
function hideConv(){
    currentcontact="";
    conv.innerHTML="<div class=\"mt-32 ml-6 text-xl\">Cliquez sur un contact pour commencer une conversation !</div>"
    document.getElementById("contactlist").classList.remove("hidden");
    conv.classList.remove("w-full");
    conv.classList.add("hidden");
}