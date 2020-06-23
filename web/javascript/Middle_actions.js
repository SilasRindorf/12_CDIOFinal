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
        "id": ID,
        "username": username,
        "ini": ini,
        "cpr": CPR,
        "nonHashedPassword": nonHashedPass,
        "role": role,
        "active": isActive
    };
    //PUTUser(userDTO)
    POSTF(url,userDTO,JSONGetUserTable("rest/actions/user-get", "UserTable"));
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
    POSTF(url, receipt)
}

function createReceiptComp(url, commodityNr, amount, tolerance) {
    const receiptComp = {
        "commodityNr": commodityNr,
        "amount": amount,
        "tolerance": tolerance
    };
    POSTF(url, receiptComp)
}
function createProductBatchComp(url, tara, weighted, commodityBatchNr, commodityNr, userID) {
    const productBatchComp = {
        "tara": tara,
        "weighted": weighted,
        "commodityBatchNr": commodityBatchNr,
        "commodityNr": commodityNr,
        "userID": userID
    };
    POSTF(url, productBatchComp)
}

function createCommodity(url, name, commodityNr) {
    const commodity = {
        "name": name,
        "commodityNr": commodityNr,
    };
    POSTF(url, commodity)
    //PUTCommodity(commodity)

}
function createCommodityBatch(url, commodityBatchNr, commodityNr, amount, provider) {
    const commodityBatch = {
        "commodityBatchNr": commodityBatchNr,
        "commodityNr": commodityNr,
        "amount": amount,
        "provider": provider,
        "active": true
    };
    //PUTCommodityBatch(commodityBatch);
    POSTF(url, commodityBatch);
}

function createProductBatch(url, receiptNr, created, status, productComps) {
    const productBatch = {
        "receiptNr": receiptNr,
        "created": created,
        "status": status,
        "productComps": productComps
    };
    POSTF(url, productBatch)
}