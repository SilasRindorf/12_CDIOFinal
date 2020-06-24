function GET(url) {
    const obj = {table: "Users", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if(request.responseText.substring(0,5).toLowerCase() ==="alert") {
                console.log("Kørt makker"+request.responseText);
                alert(request.responseText);
                return;
            }
            console.log(request.responseText);
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
}
function POSTF(url, object, caseNumber) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.responseType = "text";
    let sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    request.onreadystatechange = function () {

        if (request.readyState === 4 && request.status === 200) {
            if(request.responseText.substring(0,5).toLowerCase() ==="alert") {
                console.log("Kørt makker"+request.responseText);
                alert(request.responseText);

                return;
            }
            doFunction(caseNumber,request.responseText);
        }
    };
    request.send(sendStr);
}

function doFunction(caseNumber, text){
    switch (caseNumber) {
        case 1:
            break;
        case 2:
            console.log(text);
            break;
        case 3:
            alert(text);
            break;
        case 4:
            JSONGetUserTable("rest/actions/user-get");
            break;
        case 5:
            JSONGetCommodityBatchTable("rest/actions/commoditybatch-get");
            break;
        case 6:
            JSONGetCommodityTable("rest/actions/commodity-get");
            break;
        case 7:
            JSONGetReceiptTable("rest/actions/receipt-get");
            break;
        case 8:
            JSONGetReceiptCompTable("rest/actions/receiptcomp-get/?receiptNr=" +  receiptNrMemory);
            break;
        case 9:
            //product Batch comp
            console.log(text);
            break;
        case 10:
            //product Batch
            console.log(text);
            break;
    }

}

PUTUser = function (user) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/userput/?ID=" + user.ID + "&username=" + user.username + "&ini=" + user.ini +
        "&CPR=" + user.CPR + "&nonHashedPassword=" + user.nonHashedPass + "&role=" + user.role + "&isActive=true");

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

PUTReceiptDTO = function (receipt) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/receiptdtoput/?receiptNr=" + receipt.receiptNr + "&name=" + receipt.name, true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetReceiptCompTable("rest/actions/receiptcomp-get/?receiptNr=" + receipt.receiptNr);
        }
    };
    request.send();
};

PUTReceiptComp = function (comp) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/receiptcompput/?receiptNr=" + comp.receiptNr + "&commodityNr=" + comp.commodityNr + "&amount=" + comp.amount + "&tolerance=" + comp.tolerance ,true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetReceiptCompTable("rest/actions/receiptcomp-get/?receiptNr=" + comp.receiptNr);
        }
    };
    request.send();
};

PUTReceipt = function (receiptNr) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/receiptput/?receiptNr=" + receiptNr,true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
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
setIsActiveReceipt = function (receiptNr, isActive) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/setisactive-receipt/?receiptNr=" + receiptNr + "&isActive=" + isActive, true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetReceiptTable("rest/actions/receipt-get");
        }
    };
    request.send();

};

JSONGetReceiptTable = function (url, div) {
    const obj = {table: "ReceptTable", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var objects = JSON.parse(this.responseText);
            var txt = "<table border='1'>" +
                "<th>Recept nummer</th>" +
                "<th>Navn</th>" +
                "<th>Antal receptkomponenter</th>" +
                "<th>Status</th>" +
                "<th>Inaktiver</th>";
            for (let i in objects) {
                var length = objects[i].receiptComps.length
                txt += "<tr>" +
                    "<td>" + objects[i].id + "</td>" +
                    "<td>" + objects[i].name + "</td>" +
                    "<td>" + length + "</td>";
                if (objects[i].isActive === true) {
                    txt += "<td>" + "Aktiv" + "</td>";
                } else {
                    txt += "<td>" + "Inaktiv" + "</td>";
                }
                txt += "<td><button type=\"button\" onclick=\"setIsActiveReceipt("  + objects[i].id + "," + !objects[i].isActive + ")\">Ændre Status</button></td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById("ReceptTable").innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};

JSONGetReceiptCompTable = function (url, div) {
    const obj = {table: "ReceptCompTable", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText)
            var objects = JSON.parse(this.responseText);
            var txt = "<table border='1'>" +
                "<th>Råvare</th>" +
                "<th>Mængde</th>" +
                "<th>Tolerance</th>";
            for (let i in objects) {
                txt += "<tr>" +
                    "<td>" + objects[i].commodityNr + "</td>" +
                    "<td>" + objects[i].amount + "</td>" +
                    "<td>" + objects[i].tolerance + "</td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById("ReceptCompTable").innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};