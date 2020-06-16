function logInAction(username, password) {
    var logInDTO = {
        "username": username,
        "password": password
    };
    POST(logInDTO)
}


function POST(url, object) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    var sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            console.log(request.responseText);
            if (request.status === 200) {
                alert(request.responseText);
            }

        }
        request.send(sendStr);
    }
}