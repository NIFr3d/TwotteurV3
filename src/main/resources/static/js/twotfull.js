function deleteFullTwot(id){
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.open("DELETE", "../twot", false);
    var formdata =new FormData();
    formdata.append("id",id);
    xmlHttpReq.send(formdata);
    if(xmlHttpReq.responseText==1) {
        window.location.replace("../")
    }
}