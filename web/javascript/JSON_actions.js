function action(username, password, url){
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    var logInDTO = {
        "username":username,
        "password":password
    };
    var sendStr = JSON.stringify(logInDTO);
    request.setRequestHeader('Content-Type','application/json; charset=utf-8');
    request.onreadystatechange = function() {
        if(request.readyState === 4 && request.status === 200) {
            alert(request.responseText);
        }
    };
    request.send(sendStr);
}

