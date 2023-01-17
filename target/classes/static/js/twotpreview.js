async function getanswto(twotid){
    let response = await fetch('../original/'+twotid);
    if (response.ok) {
        let json = await response.json();
        Array.from(document.getElementsByClassName("answto"+twotid)).forEach(function(element) {
            element.innerHTML="@"+json.user;
        });
        Array.from(document.getElementsByClassName("url"+twotid)).forEach(function(element) {
            element.href="../twot/"+json.original;
        });
        if(window.location.pathname=="/twot/"+json.original){
            Array.from(document.getElementsByClassName("answtodiv"+twotid)).forEach(function(element) {
                element.classList.add("hidden");
            });
        }
    }
}
function inittwot(twotid){
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.open("GET", "../countanswers/"+twotid, false);
    xmlHttpReq.send(null);
    Array.from(document.getElementsByClassName("answercount"+twotid)).forEach(function(element) {
        element.innerHTML=xmlHttpReq.responseText;
    });
    xmlHttpReq.open("GET", "../countlikes/"+twotid, false);
    xmlHttpReq.send(null);
    Array.from(document.getElementsByClassName("likecount"+twotid)).forEach(function(element) {
        element.innerHTML=xmlHttpReq.responseText;
    });
    xmlHttpReq.open("GET", "../didilike/"+twotid, false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1){
        Array.from(document.getElementsByClassName("likeicon"+twotid)).forEach(function (element) {
            element.style.fill="red";
            element.style.stroke="red";
        });
    }
    xmlHttpReq.open("GET", "../didirt/"+twotid, false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1){
        Array.from(document.getElementsByClassName("retwotbutton"+twotid)).forEach(function (element) {
            element.style.stroke="green";
        });
    }
    xmlHttpReq.open("GET", "../countretwots/"+twotid, false);
    xmlHttpReq.send(null);
    Array.from(document.getElementsByClassName("retwotcount"+twotid)).forEach(function(element) {
        element.innerHTML=xmlHttpReq.responseText;
    });
}
function deleteTwot(id){
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.open("DELETE", "../twot", false);
    var formdata =new FormData();
    formdata.append("id",id);
    xmlHttpReq.send(formdata);
    if(xmlHttpReq.responseText==1) {
        Array.from(document.getElementsByClassName("twot"+id)).forEach(function(element) {
            element.remove();
        });
    }
}
function like(id){
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.open("GET", "../like/"+id, false);
    xmlHttpReq.send(null);
    let compte=parseInt(document.getElementsByClassName("likecount"+id)[0].innerHTML);
    if(xmlHttpReq.responseText==1) {
        Array.from(document.getElementsByClassName("likecount"+id)).forEach(function(element) {
            element.innerHTML=compte+1;
        });
        Array.from(document.getElementsByClassName("likeicon"+id)).forEach(function (element) {
            element.style.fill="red";
            element.style.stroke="red";
        });
    }
    else if(xmlHttpReq.responseText==2) {
        Array.from(document.getElementsByClassName("likecount"+id)).forEach(function(element) {
            element.innerHTML=compte-1;
        });
        Array.from(document.getElementsByClassName("likeicon"+id)).forEach(function (element) {
            element.style.fill="transparent";
            element.style.stroke="black";
        });
    }
}
function afficher_msg(id) {
    $('#'+id+', #overlay-back').fadeIn(500);
}
function fadeOut(){
    $('.answerForm, #overlay-back').fadeOut(500);
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
function initdate(id,javadate){
    var jsdate=sqlToJsDate(javadate);
    var now=new Date();
    var timedif=now.getTime()-jsdate.getTime();
    if(timedif>86400000){
        var month=parseInt(jsdate.getMonth())+1;
        if(jsdate.getUTCDate()<10){
            if(month<10) affichage="0"+jsdate.getUTCDate()+"/0"+month
            else affichage="0"+jsdate.getUTCDate()+"/"+month;
        }
        else {
            if(month<10) affichage=jsdate.getUTCDate()+"/0"+month
            else affichage=jsdate.getUTCDate()+"/"+month;
        }
    }else{
        if(timedif<3600000){
            affichage=Math.round(Math.abs(timedif/(1000*60))%60)+"min";
        }else{
            if(now.getDate()==jsdate.getDate()) affichage=jsdate.getHours()+":"+jsdate.getMinutes();
            else affichage="Hier";
        }
    }
    Array.from(document.getElementsByClassName("date"+id)).forEach(function(element) {
        element.innerHTML=affichage;
    });
}
function retwotmenu(id){
    if(document.getElementsByClassName("retwotbutton"+id)[0].style.stroke=="green"){
        simpleretwot(id);
    }else{
        Array.from(document.getElementsByClassName("retwotmenu"+id)).forEach(function(element) {
            element.classList.toggle("hidden");
        });
    }
}
function simpleretwot(id){
    var xmlHttpReq = new XMLHttpRequest();
    Array.from(document.getElementsByClassName("retwotmenu"+id)).forEach(function(element) {
        element.classList.add("hidden");
    });
    xmlHttpReq.open("GET", "../simpleretwot/"+id, false);
    xmlHttpReq.send(null);
    let compte=parseInt(document.getElementsByClassName("retwotcount"+id)[0].innerHTML);
    if(xmlHttpReq.responseText==1) {
        Array.from(document.getElementsByClassName("retwotcount"+id)).forEach(function(element) {
            element.innerHTML=compte+1;
        });
        Array.from(document.getElementsByClassName("retwotbutton"+id)).forEach(function (element) {
            element.style.stroke="green";
        });
    }
    else if(xmlHttpReq.responseText==2) {
        Array.from(document.getElementsByClassName("retwotcount"+id)).forEach(function(element) {
            element.innerHTML=compte-1;
        });
        Array.from(document.getElementsByClassName("retwotbutton"+id)).forEach(function (element) {
            element.style.stroke="black";
        });
    }
}
function retwotWithText(id){
    Array.from(document.getElementsByClassName("retwotmenu"+id)).forEach(function(element) {
        element.classList.add("hidden");
    });
    var div=document.createElement("div");
    div.classList.add("answerForm");
    div.classList.add("border-2");
    div.classList.add("p-2");
    div.classList.add("rounded-lg");
    div.classList.add("mt-2");
    div.classList.add("w-4/5");
    div.classList.add("md:w-1/3");
    div.classList.add("bg-blue-900");
    div.classList.add("top-1/4");
    div.classList.add("left-auto");
    div.classList.add("md:left-1/3");
    div.style.position="fixed";
    div.style.zIndex="100";
    div.innerHTML="<form id='retwotform"+id+"'>"+
    "<input type=\"hidden\" name=\"id\" value=\""+id+"\"/>"+
    "<textarea name=\"text\" placeholder=\"Ajouter un commentaire\" maxlength=\"140\" class='resize-none w-full bg-blue-900 border-2 pl-1 rounded-lg'></textarea><br>"+
    "<button type='button' onclick='submitRetwot("+id+")' class='rounded-lg border-2 bg-blue-900 w-fit px-2 py-1 my-auto mt-2 hover:bg-blue-700'>Retwoter</button>"+
    "</form>";
    document.body.appendChild(div);
    afficher_msg("retwotform"+id);
}
function submitRetwot(id){
    var xmlHttpReq = new XMLHttpRequest();
    var form=document.getElementById("retwotform"+id);
    var formdata =new FormData(form);
    xmlHttpReq.open("POST", "../retwot", false);
    xmlHttpReq.send(formdata);
    if(xmlHttpReq.responseText==1) {
        var div=document.getElementById("retwotform"+id);
        div.parentNode.remove();
        fadeOut();
        let compte=parseInt(document.getElementsByClassName("retwotcount"+id)[0].innerHTML);
        Array.from(document.getElementsByClassName("retwotcount"+id)).forEach(function(element) {
            element.innerHTML=compte+1;
        });
        Array.from(document.getElementsByClassName("retwotbutton"+id)).forEach(function (element) {
            element.style.stroke="green";
        });
    }
}
