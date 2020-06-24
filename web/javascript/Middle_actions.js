//This function functions as a test class currently
function logInAction(url, username, password) {
    const logInDTO = {
        "username": username,
        "password": password
    };

    POSTF(url, logInDTO,3);

}

// Usage!
//sleep(500).then(() => {
//    // Do something after the sleep!
//});
//}

// sleep time expects milliseconds
function sleep (time) {
    return new Promise((resolve) => setTimeout(resolve, time));
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
    POSTF(url,userDTO,4);
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
    POSTF(url, commodityBatch,5);
}

function createCommodity(url, name, commodityNr) {
    const commodity = {
        "name": name,
        "commodityNr": commodityNr,
        "active" : true
    };
    POSTF(url, commodity,6)
    //PUTCommodity(commodity)

}
function createReceiptAction(url, receiptNr, name) {
    const receipt = {
        "receiptNr": receiptNr,
        "name": name,
    };
    //PUTReceiptDTO(receipt)
    POSTF(url, receipt,8)
}

function createReceiptComp(url,receiptNr, commodityNr, amount, tolerance) {
    const receiptComp = {
        "receiptNr" : receiptNr,
        "commodityNr": commodityNr,
        "amount": amount,
        "tolerance": tolerance
    };
    //PUTReceiptComp(receiptComp)
    POSTF(url, receiptComp,8)
}
function createProductBatchComp(url, tara, weighted, commodityBatchNr, commodityNr, userID) {
    const productBatchComp = {
        "tara": tara,
        "weighted": weighted,
        "commodityBatchNr": commodityBatchNr,
        "commodityNr": commodityNr,
        "userID": userID
    };
    POSTF(url, productBatchComp,9)
}


function createProductBatch(url, productBatchNr, receiptNr, created, status, productComps) {
    const productBatch = {
        "productBatchNr": productBatchNr,
        "receiptNr": receiptNr,
        "created": created
    };
    POSTF(url, productBatch,10)
}