function showfollowers(){
    $('#followers, #overlay-back').fadeIn(500);
}
function showfollowed(){
    $('#followed, #overlay-back').fadeIn(500);
}
function follow(followid){
    xmlHttpReq.open("GET","../follow/"+followid,false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1)document.getElementById("doifollow"+followid).innerHTML="Ne plus suivre";
    if(xmlHttpReq.responseText==2)document.getElementById("doifollow"+followid).innerHTML="Suivre";
}
function veriffollow(userid,categorie){
    xmlHttpReq.open("GET","../doifollow/"+userid,false);
    xmlHttpReq.send(null);
    if(xmlHttpReq.responseText==1){
        switch(categorie){
            case 0:
                document.getElementById("doifollow"+userid).innerHTML="Ne plus suivre";
                break;
            case 1:
                document.getElementById("doifollow1"+userid).innerHTML="Ne plus suivre";
                break;
            case 2:
                document.getElementById("doifollow2"+userid).innerHTML="Ne plus suivre";
                break;
        }
    }
}