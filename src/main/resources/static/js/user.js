let xmlHttpReq = new XMLHttpRequest();
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
async function getanswto(twotid){
    let response = await fetch('../original/'+twotid);
    if (response.ok) {
        let json = await response.json();
        document.getElementById("answto"+twotid).innerHTML="@"+json.user;
        document.getElementById("url"+twotid).href="../twot/"+json.original;
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