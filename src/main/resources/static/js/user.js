function showfollowers(){
    $('#followers, #overlay-back').fadeIn(500);
}
function showfollowed(){
    $('#followed, #overlay-back').fadeIn(500);
}
function follow(followid,categorie){
    xmlHttpReq.open("GET","../follow/"+followid,false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1) document.getElementById("doifollow"+categorie+followid).innerHTML="Ne plus suivre";
    if(xmlHttpReq.responseText==2) document.getElementById("doifollow"+categorie+followid).innerHTML="Suivre";
}
function veriffollow(userid,categorie){
    xmlHttpReq.open("GET","../doifollow/"+userid,false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1){
        document.getElementById("doifollow"+categorie+userid).innerHTML="Ne plus suivre";
    }
}