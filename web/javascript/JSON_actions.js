function GET(url){
    const obj = {table: "Users", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(request.responseText);
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
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
        console.log(request.responseText);
    };
}

function POST(url, object) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    var sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');

    request.send(sendStr);
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            console.log(request.responseText);
        }
    };
}