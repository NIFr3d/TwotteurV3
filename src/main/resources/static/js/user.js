function afficher_msg(id) {
    $('#'+id+', #overlay-back').fadeIn(500);
}
function fadeOut(){
    $('.answerForm, #overlay-back').fadeOut(500);
}
function showfollowers(){
    $('#followers, #overlay-back').fadeIn(500);
}
function showfollowed(){
    $('#followed, #overlay-back').fadeIn(500);
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
function follow(followid){
    xmlHttpReq.open("GET","../follow/"+followid,false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1)document.getElementById("doifollow"+followid).innerHTML="Ne plus suivre";
    if(xmlHttpReq.responseText==2)document.getElementById("doifollow"+followid).innerHTML="Suivre";
}