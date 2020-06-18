//This function functions as a test class currently
function logInAction(url, username, password) {
    const logInDTO = {
        "username": username,
        "password": password
    };
    POSTAndReceive(url, logInDTO);
}

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