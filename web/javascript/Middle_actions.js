//This function functions as a test class currently
function logInAction(url, username, password) {
    const logInDTO = {
        "username": username,
        "password": password
    };
    POSTAndAlert(url, logInDTO);
}
function createUser(url, ID, username, ini, CPR, nonHashedPass, role, isActive) {
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
    console.log(userDTO)
    PUTUser(userDTO)
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