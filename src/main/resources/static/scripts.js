function  generate_link(pageNumber) {
    var query=document.getElementById('q').value;
    Req="/search?q="+query+"&page="+pageNumber;
    window.location.href =Req;
}

function onClickHandler(){
    var chk=document.getElementById("ckBirthday").checked ;
    if(chk){
        document.getElementById("q").disabled=true;
        document.getElementById("birthDate").disabled=false;
    }else{
        document.getElementById("q").disabled=false;
        document.getElementById("birthDate").disabled=true;
    }

}