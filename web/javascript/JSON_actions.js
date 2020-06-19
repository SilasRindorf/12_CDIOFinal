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
    console.log(sendStr);
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
            var temp = this.responseText;
            console.log("I am here");
            console.log(JSON.parse(temp));
            return temp;
        }
    }
};

JSONGetUserTable = function (url, div) {
    const obj = {table: "UserTable", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var objects = JSON.parse(this.responseText);
            var txt = "<table border='1'>" +
                "<th>BrugerID</th>" +
                "<th>Brugernavn</th>" +
                "<th>Initialer</th>" +
                "<th>Cpr-nummer</th>" +
                "<th>Status</th>" +
                "<th>Password</th>" +
                "<th>Rolle</th>" +
                "<th>Inaktiver</th>";
            for (let i in objects) {
                txt += "<tr>" +
                    "<td>" + objects[i].id + "</td>" +
                    "<td>" + objects[i].username + "</td>" +
                    "<td>" + objects[i].ini + "</td>" +
                    "<td>" + objects[i].cpr + "</td>";
                if (objects[i].isActive === true) {
                    txt += "<td>" + "Aktiv" + "</td>";
                } else {
                    txt += "<td>" + "Inaktiv" + "</td>";
                }
                txt += "<td>" + objects[i].hashedPass + "</td>" +
                    "<td>" + objects[i].role + "</td>" +
                    "<td><button type=\"button\" onclick=\"JSONDelete(" + div + "," + objects[i].userID + ")\">Inaktiver bruger</button></td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById("UserTable").innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};


function POST(url, object) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    let sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');

    request.send(sendStr);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
        }
    };
}