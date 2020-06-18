function GET(url) {
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

function POSTAndAlert(url, object) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.responseType = "text";
    let sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            alert(request.responseText);
        }
    };

    request.send(sendStr);
}

GETTable = function (url, tableName) {
    const obj = {table: tableName.toString(), limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();

    request.open("GET", url, true);
    request.send("x= " + param);
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            return this.responseText;
        }
    }
};


function POST(url, object, doFunction) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    let sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');

    request.send(sendStr);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            doFunction(request.responseText);
        }
    };
}