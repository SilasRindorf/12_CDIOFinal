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
            if (document.readyState === "complete") {
                alert(request.responseText);
            } else {
                window.addEventListener("load", () =>{
                    alert(request.responseText);
                });
            }
        }
    };

    request.send(sendStr);
}

PUTUser = function (user) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/userput/?ID=" + user.ID + "&username=" + user.username + "&ini=" + user.ini + "&CPR=" + user.CPR + "&nonHashedPassword=" + user.nonHashedPass + "&role=" + user.role + "&isActive=true", true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetUserTable("rest/actions/user-get", "UserTable");
        }
    };
    request.send();

};

PUTCommodity = function (commodity) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/commodityput/?commodityNr=" + commodity.commodityNr + "&name=" + commodity.name + "&isActive=true", true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetCommodityTable("rest/actions/commodity-get", "UserTable");
        }
    };
    request.send();
};

PUTCommodityBatch = function (commodityBatch) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/commoditybatchput/?commodityBatchNr=" + commodityBatch.commodityBatchNr + "&commodityNr=" + commodityBatch.commodityNr + "&amount=" + commodityBatch.amount + "&provider=" + commodityBatch.provider + "&isActive=true", true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetCommodityBatchTable("rest/actions/commoditybatch-get", "UserTable");
        }
    };
    request.send();
};

setIsActiveUser = function (id, isActive) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/setisactive-user/?ID=" + id + "&isActive=" + isActive, true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetUserTable("rest/actions/user-get", "UserTable");
        }
    };
    request.send();

};


GETTable = function (url, tableName) {
    const obj = {table: tableName.toString(), limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();

    request.open("GET", url, true);
    request.send("x= " + param);
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var temp = this.responseText;
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
                    "<td><button type=\"button\" onclick=\"setIsActiveUser(" + objects[i].id + "," + !objects[i].isActive + ")\">Ændre Status</button></td>" +
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

JSONGetCommodityTable = function (url, div) {
    const obj = {table: "RaavareTable", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var objects = JSON.parse(this.responseText);
            var txt = "<table border='1'>" +
                "<th>Råvare nummer</th>" +
                "<th>Råvare</th>" +
                "<th>Status</th>" +
                "<th>Inaktiver</th>";
            for (let i in objects) {
                txt += "<tr>" +
                    "<td>" + objects[i].commodityNr + "</td>" +
                    "<td>" + objects[i].name + "</td>";
                if (objects[i].isActive === true) {
                    txt += "<td>" + "Aktiv" + "</td>";
                } else {
                    txt += "<td>" + "Inaktiv" + "</td>";
                }
                txt += "<td><button type=\"button\" onclick=\"setIsActiveCommodity(" + objects[i].commodityNr + "," + !objects[i].isActive + ")\">Ændre Status</button></td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById("RaavareTable").innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};

setIsActiveCommodity = function (commodityNr, isActive) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/setisactive-commodity/?commodityNr=" + commodityNr + "&isActive=" + isActive, true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetCommodityTable("rest/actions/commodity-get", "RaavareTable");
        }
    };
    request.send();

};

setIsActiveCommodityBatch = function (commodityBatchNr, isActive) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/setisactive-commoditybatch/?commodityBatchNr=" + commodityBatchNr + "&isActive=" + isActive, true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetCommodityBatchTable("rest/actions/commoditybatch-get", "RaavareBatchTable");
        }
    };
    request.send();

};

JSONGetCommodityBatchTable = function (url, div) {
    const obj = {table: "RaavareBatchTable", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var objects = JSON.parse(this.responseText);
            var txt = "<table border='1'>" +
                "<th>Råvarebatch Nr</th>" +
                "<th>RåvareID</th>" +
                "<th>Mængde</th>" +
                "<th>Leverandør</th>" +
                "<th>Status</th>" +
                "<th>Inaktiver</th>";
            for (let i in objects) {

                txt += "<tr>" +
                    "<td>" + objects[i].commodityBatchNr + "</td>" +
                    "<td>" + objects[i].commodityNr + "</td>" +
                    "<td>" + objects[i].amount + "</td>" +
                    "<td>" + objects[i].provider + "</td>";
                if (objects[i].isActive === true) {
                    txt += "<td>" + "Aktiv" + "</td>";
                } else {
                    txt += "<td>" + "Inaktiv" + "</td>";
                }
                txt += "<td><button type=\"button\" onclick=\"setIsActiveCommodityBatch(" + objects[i].commodityBatchNr + "," + !objects[i].isActive + ")\">Ændre Status</button></td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById("RaavareBatchTable").innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};
/*
function POSTUser(url, ID, username, ini, CPR, nonHashedPass, role, isActive) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    isActive = isActive === "Aktiv";
    ID = parseInt(ID);
    const userDTO = {
        "ID": ID,
        "username": username,
        "ini": ini,
        "CPR": CPR,
        "nonHashedPass": nonHashedPass,
        "role": role,
        "isActive": isActive
    };


    let sendStr = JSON.stringify(userDTO);
    console.log(sendStr);
    request.
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    request.send(sendStr);

    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
            alert("Succes")
        }
    };
}

 */