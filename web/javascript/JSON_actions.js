function action(username, password, url){
    const request = new XMLHttpRequest();
    let running = true;
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
        else if (request.status === 400 && running){
            alert("Wrong username or password.\nTry again")
            running = false;
        }
    };
    request.send(sendStr);
}