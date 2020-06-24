function GET(url) {
    const obj = {table: "Users", limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (request.responseText.substring(0, 5).toLowerCase() === "alert") {
                alert(request.responseText);
            }
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
}

function POSTF(url, object, caseNumber) {
    const request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.responseType = "text";
    var sendStr = JSON.stringify(object);
    request.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
    request.onreadystatechange = function () {

        if (request.readyState === 4 && request.status === 200) {
            if (request.responseText.substring(0, 5).toLowerCase() === "alert") {
                alert(request.responseText);
                return;
            }
            doFunction(caseNumber, request.responseText);
        }
    };
    request.send(sendStr);
}

function doFunction(caseNumber, text) {
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
            JSONGetReceiptCompTable("rest/actions/receiptcomp-get/?receiptNr=" + receiptNrMemory, "ReceptCompTable");
            break;
        case 9:
            JSONGetAfvejningTable("rest/actions/get-afvejning/?productBatchNr=" + productBatchGlobal, "table_Laborant_Afvejning")
            break;
        case 10:
            JSONGetProductBatchTable("rest/actions/product-batch-get", "tableBatchFarmaceut")
            break;
        case 11:
            preparePrint(text);
            break;
        case 12:
            JSONGetProductBatchTable("rest/actions/product-batch-get","tableBatch");
            break;
    }
}

function preparePrint(text) {
    let parsedText = JSON.parse(text);
    document.getElementById("printBodyDiv").style.visibility = "visible";
    document.getElementById("printHeader").innerHTML = "<strong>Udskrevet</strong> " + new Date() +
        "<br><strong>Produkt Batch nr.</strong> " + parsedText.productBatchNr +
        "<br><strong>Recept nr.</strong> " + parsedText.receiptNr + "</p>";
    let totalNetto = 0;
    let totalTara = 0;
    for (let i in parsedText.list) {
        document.getElementById("printHeader").innerHTML += "<div id='printCommodity" + i + "'></div>";
        if (parsedText.list[i].amount === -1)
            parsedText.list[i].amount = "";
        if (parsedText.list[i].tolerance === -1)
            parsedText.list[i].tolerance = "";
        if (parsedText.list[i].tara === -1)
            parsedText.list[i].tara = "";
        else {
            totalTara += parsedText.list[i].tara;
        }
        if (parsedText.list[i].netto === -1)
            parsedText.list[i].netto = "";
        else {
            totalNetto += parsedText.list[i].netto;
        }
        if (parsedText.list[i].commodityBatchNr === -1)
            parsedText.list[i].commodityBatchNr = "";

        let table = "<table style='width=100%;'>" +
            "<th>Mængde</th>" +
            "<th>Tolerance</th>" +
            "<th>Tara</th>" +
            "<th>Netto (kg)</th>" +
            "<th>Batch</th>" +
            "<th>Opr.</th>";
        table += "<tr>" +
            "<td>" + parsedText.list[i].amount + "</td>" +
            "<td>&#177;" + parsedText.list[i].tolerance + " %</td>" +
            "<td>" + parsedText.list[i].tara + "</td>" +
            "<td>" + parsedText.list[i].netto + "</td>" +
            "<td>" + parsedText.list[i].commodityBatchNr + "</td>" +
            "<td>" + parsedText.list[i].ini + "</td>" +
            "</tr>";
        table += "</table>";

        document.getElementById("printCommodity" + i).innerHTML = "<strong>Råvare nr.:</strong> " + parsedText.list[i].commodityNr +
            "<br><strong>Råvare navn:</strong> " + parsedText.list[i].commodityName + table;

    }
    let endOfDoc = "<br><strong>Sum Tara:</strong> " + totalTara +
        "<br><strong>Sum Netto:</strong> " + totalNetto;
    document.getElementById("printBodyDiv").innerHTML += endOfDoc;
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

PUTFinishProductBatch = function (productBatchNr) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/product-batch-done/?productBatchNr=" + productBatchNr);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
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
            JSONGetReceiptCompTable("rest/actions/receiptcomp-get/?receiptNr=" + receipt.receiptNr, "ReceptCompTable");
        }
    };
    request.send();
};

PUTReceiptComp = function (comp) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/receiptcompput/?receiptNr=" + comp.receiptNr + "&commodityNr=" + comp.commodityNr + "&amount=" + comp.amount + "&tolerance=" + comp.tolerance, true);
    request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    request.onload = function () {
        if (request.readyState === 4 && request.status === 204) {
            JSONGetReceiptCompTable("rest/actions/receiptcomp-get/?receiptNr=" + comp.receiptNr, "ReceptCompTable");
        }
    };
    request.send();
};

PUTReceipt = function (receiptNr) {
    const request = new XMLHttpRequest();
    request.open("PUT", "rest/actions/receiptput/?receiptNr=" + receiptNr, true);
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
            sleep(100).then(() => {
                JSONGetCommodityBatchTable("rest/actions/commoditybatch-get", "RaavareBatchTableProduktionsleder");
                JSONGetCommodityBatchTable("rest/actions/commoditybatch-get", "RaavareBatchTable");
            });
        }
    };
    request.send();

};

JSONGetCommodityBatchTable = function (url, tabelNavn) {
    const obj = {table: tabelNavn, limit: 20};
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
                var commodity = objects[i].commodityBatchNr;
                var isActive = !objects[i].isActive;
                var args = commodity + "," + isActive;
                txt += "<td><button type=\"button\" onclick=\"setIsActiveCommodityBatch(" + args + ")" + "\">Ændre Status</button></td>" + "</tr>";
            }
            txt += "</table>";
            document.getElementById(tabelNavn).innerHTML = txt;
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
                txt += "<td><button type=\"button\" onclick=\"setIsActiveReceipt(" + objects[i].id + "," + !objects[i].isActive + ")\">Ændre Status</button></td>" +
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
    const obj = {table: div, limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
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
            document.getElementById(div).innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};

JSONGetProductBatchTable = function (url, div) {
    const obj = {table: div, limit: 20};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var objects = JSON.parse(this.responseText);
            var txt = "<table border='1'>" +
                "<th>Produktbatch ID</th>" +
                "<th>Recept nummer</th>" +
                "<th>Dato</th>" +
                "<th>Status</th>" +
                "<th>Print</th>";
            for (let i in objects) {
                txt += "<tr>" +
                    "<td>" + objects[i].id + "</td>" +
                    "<td>" + objects[i].receiptNr + "</td>" +
                    "<td>" + new Date(objects[i].created).toUTCString() + "</td>" +
                    "<td>" + objects[i].status + "</td>";
                txt += "<td><button type=\"button\" onclick=\"printProductionBatch(" + objects[i].id + ")\">Print</button></td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById(div).innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};

JSONGetAfvejningTable = function (url, div) {
    const obj = {table: div, limit: 50};
    const param = JSON.stringify(obj);
    const request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText)
            const objects = JSON.parse(this.responseText);
            console.log(objects)
            let txt = "<table border='1'>" +
                "<th>Råvare Nr</th>" +
                "<th>Vægt minus tara</th>" +
                "<th>Råvarebatch Nr</th>" +
                "<th>Initialer</th>";
            for (let i in objects) {
                txt += "<tr>" +
                    "<td>" + objects[i].commodityNr + "</td>" +
                    "<td>" + (objects[i].weighted - objects[i].tara) + "</td>" +
                    "<td>" + objects[i].commodityBatchNr + "</td>" +
                    "<td>" + objects[i].ini + "</td>" +
                    "</tr>";
            }
            txt += "</table>";
            document.getElementById(div).innerHTML = txt;
        }
    };
    request.open("GET", url, true);
    request.send("x= " + param);
};

printProductionBatch = function (productBatchNr) {
    hideallProductBatch();
    document.getElementById("printBodyDiv").style.visibility = "visible";
    document.getElementById("printPlace").style.visibility = "visible";

    POSTF("rest/actions/print-product-batch/?productBatchid=" + productBatchNr, productBatchNr, 11);
    document.getElementById("printBodyDiv").style.visibility = "hidden";
    document.getElementById("printBodyDiv").innerHTML = "<h1><i>Produktbatch</i> under produktion</h1><p id=\"printHeader\"></p>";
};

// DENNE HIDEALL RØRES IKKE, JAVASCRIPT DEN ER NØDVENDIG SELVOM DEN ER DUPLICATE
function hideallProductBatch() {
    document.getElementById("afvejning1_Produktionsleder").style.visibility = "hidden";
    document.getElementById("logInForm").style.visibility = "hidden";
    document.getElementById("welcome").style.visibility = "hidden";
    document.getElementById("Produktionsleder").style.visibility = "hidden";
    document.getElementById("raavarebatch").style.visibility = "hidden";
    document.getElementById("produktbatch").style.visibility = "hidden"
    document.getElementById("finish").style.visibility = "hidden";
    document.getElementById("admin").style.visibili1ty = "hidden";
    document.getElementById("afvejning1").style.visibility = "hidden";
    document.getElementById("afvejning1").style.visibility = "hidden";
    document.getElementById("afvejning2_Produktionsleder").style.visibility = "hidden";
    document.getElementById("statusOprettet").style.visibility = "hidden";
    document.getElementById("statusUnderProduktion").style.visibility = "hidden";
    document.getElementById("farmaceut").style.visibility = "hidden";
    document.getElementById("recept2").style.visibility = "hidden";
    document.getElementById("recept3").style.visibility = "hidden";
    document.getElementById("recept4").style.visibility = "hidden";
    document.getElementById("Produktionsleder").style.visibility = "hidden";
    document.getElementById("afvejning1_Laborant").style.visibility = "hidden";
    document.getElementById("afvejning2_Laborant").style.visibility = "hidden";
    document.getElementById("afvejning1_Farmaceut").style.visibility = "hidden";
    document.getElementById("afvejning2_Farmaceut").style.visibility = "hidden";
    document.getElementById("raavarebatchFarmaceut").style.visibility = "hidden";
    document.getElementById("raavareFarmaceut").style.visibility = "hidden";
    document.getElementById("produktbatchFarmaceut").style.visibility = "hidden";
    document.getElementById("printPlace").style.visibility = "hidden";
    document.getElementById("recept1").style.visibility = "hidden";
}
