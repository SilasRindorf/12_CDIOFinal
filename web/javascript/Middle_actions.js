//This function functions as a test class currently
function logInAction(url, username, password) {
    const logInDTO = {
        "username": username,
        "password": password
    };
    POSTAndAlert(url, logInDTO);
}
function createUser(url, ID, username, ini, CPR, hashedPass, role, isActive) {
    const user = {
        "ID": ID,
        "username": username,
        "ini": ini,
        "CPR": CPR,
        "hashedPass": hashedPass,
        "Role": role,
        "isActive": isActive,
    };

    POST(url, user)
}

function userTable(tableID,objects){
    var JSONParsed = JSON.parse(objects);
    var txt = "<table border='1'>" +
        "<th>BrugerID</th>" +
        "<th>Brugernavn</th>" +
        "<th>Initialer</th>" +
        "<th>Cpr-nummer</th>" +
        "<th>Password</th>" +
        "<th>Rolle</th>" +
        "<th>Status</th>" +
        "<th>Inaktiver</th>";
    console.log("I am here");
    console.log(JSONParsed);
    for (let i in JSONParsed) {
        console.log(JSONParsed[i]);
        txt += "<tr>" +
            "<td>" + JSONParsed[i].userID + "</td>" +
            "<td>" + JSONParsed[i].uname + "</td>" +
            "<td>" + JSONParsed[i].ini + "</td>" +
            "<td>" + JSONParsed[i].cpr + "</td>" +
            "<td>" + JSONParsed[i].password + "</td>" +
            "<td>" + JSONParsed[i].Roles + "</td>" +
            "<td><button type=\"button\" onclick=\"JSONDelete(" + div + "," + JSONParsed[i].userID + ")\">Inaktiver user</button></td>" +
            "</tr>";
    }
    txt += "</table>";
    document.getElementById("UserTable").innerHTML = txt;
}

JSONDelete = function (div,id) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/person/delete-user/?id=" + id, true);
    request.setRequestHeader('Content-type','application/json; charset=utf-8');
    request.onload = function () {
        //alert("readyState=" + request.readyState+ "\nstatus=" +  request.status);
        if (request.readyState === 4 && request.status === 204){
            getAllUsers();
        }
    };
    request.send();
};

function createReceipt(url, name, receiptComps) {
    const receipt = {
        "name": name,
        "receiptComps": receiptComps,
    };
    POST(url, receipt)
}

function createReceiptComp(url, commodityNr, amount, tolerance) {
    const receiptComp = {
        "commodityNr": commodityNr,
        "amount": amount,
        "tolerance": tolerance
    };
    POST(url, receiptComp)
}
function createProductBatchComp(url, tara, weighted, commodityBatchNr, commodityNr, userID) {
    const productBatchComp = {
        "tara": tara,
        "weighted": weighted,
        "commodityBatchNr": commodityBatchNr,
        "commodityNr": commodityNr,
        "userID": userID
    };
    POST(url, productBatchComp)
}

function createCommodity(url, name, commodityNr) {
    const commodity = {
        "name": name,
        "commodityNr": commodityNr,
    };
    POST(url, commodity)
}

function createProductBatch(url, receiptNr, created, status, productComps) {
    const productBatch = {
        "receiptNr": receiptNr,
        "created": created,
        "status": status,
        "productComps": productComps
    };
    POST(url, productBatch)
}