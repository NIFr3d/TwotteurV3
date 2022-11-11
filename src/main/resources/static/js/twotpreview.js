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