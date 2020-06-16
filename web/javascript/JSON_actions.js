function logInAction(url, username, password) {
    var logInDTO = {
        "username": username,
        "password": password
    };
    POSTAndReceive(url, logInDTO)
}

function createBatchAction(url, receiptNr, created, status, productComps) {
    var ProductBatchDTO = {
        "receiptNr": receiptNr,
        "created": created,
        "status": status,
        "productComps": productComps
    };
    POST(url, ProductBatchDTO)
}

function userDTO(url, receiptNr, created, status, productComps) {
    var ProductBatchDTO = {
        "receiptNr": receiptNr,
        "created": created,
        "status": status,
        "productComps": productComps
    };
    POST(url, ProductBatchDTO)
}

function POSTAndReceive(url, object) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.responseType = "text";

    var sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            console.log(request.responseText);
        }
    };

    request.send(sendStr);
    request.onload = function () {
        console.log(url);
        console.log(request.responseText);
    };
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