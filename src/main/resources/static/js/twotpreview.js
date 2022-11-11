let xmlHttpReq = new XMLHttpRequest();

async function getanswto(twotid){
    let response = await fetch('../original/'+twotid);
    if (response.ok) {
        let json = await response.json();
        document.getElementById("answto"+twotid).innerHTML="@"+json.user;
        document.getElementById("url"+twotid).href="../twot/"+json.original;
        if(window.location.pathname=="/twot/"+json.original) document.getElementById("answtodiv"+twotid).classList.add("hidden");
    }
}
function inittwot(twotid){
    xmlHttpReq.open("GET", "../countanswers/"+twotid, false);
    xmlHttpReq.send(null);
    document.getElementById("answercount"+twotid).innerHTML=xmlHttpReq.responseText;
    xmlHttpReq.open("GET", "../countlikes/"+twotid, false);
    xmlHttpReq.send(null);
    document.getElementById("likecount"+twotid).innerHTML=xmlHttpReq.responseText;
    xmlHttpReq.open("GET", "../didilike/"+twotid, false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1)document.getElementById("likeicon"+twotid).style.fill="red";
}
function deleteTwot(id){
    xmlHttpReq.open("DELETE", "../twot", false);
    var formdata =new FormData();
    formdata.append("id",id);
    xmlHttpReq.send(formdata);
    if(xmlHttpReq.responseText==1) {
        var div=document.getElementById("twot"+id);
        div.parentNode.removeChild(div);
    }
}
function like(id){
    xmlHttpReq.open("GET", "../like/"+id, false);
    xmlHttpReq.send(null);
    let compte=parseInt(document.getElementById("likecount"+id).innerHTML);
    if(xmlHttpReq.responseText==1) {
        document.getElementById("likecount"+id).innerHTML=compte+1;
        document.getElementById("likeicon"+id).style.fill="red";
    }
    else if(xmlHttpReq.responseText==2) {
        document.getElementById("likecount"+id).innerHTML=compte-1;
        document.getElementById("likeicon"+id).style.fill="black";
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
        if(jsdate.getUTCDate()<10) affichage=jsdate.getMonth()+"/0"+jsdate.getUTCDate();
        else affichage=jsdate.getMonth()+"/"+jsdate.getUTCDate();
    }else{
        if(timedif<3600000){
            affichage=Math.round(Math.abs(timedif/(1000*60))%60)+"min";
        }else{
            if(now.getDate()==jsdate.getDate()) affichage=jsdate.getHours()+":"+jsdate.getMinutes();
            else affichage="Hier";
        }
    }
    document.getElementById("date"+id).innerHTML=affichage;
}
