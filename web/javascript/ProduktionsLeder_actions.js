function goToRaavarebatch() {
    hideall();
    document.getElementById("raavarebatch").style.visibility = "visible";
    JSONGetCommodityBatchTable("rest/actions/commoditybatch-get","RaavareBatchTableProduktionsleder")


}

function createRaavarebatchComponent() {
    if(!document.getElementById("raavarebatchNrProd").value == "" &&
        !document.getElementById("raavareIDProd").value == "" &&
        !document.getElementById("maengdeProd").value == "" &&
        !document.getElementById("leverandoerProd").value == ""){

        var commodityBatchNr = document.getElementById("raavarebatchNrProd").value;
        var commodityNr = document.getElementById("raavareIDProd").value;
        var amount = document.getElementById("maengdeProd").value;
        var provider = document.getElementById("leverandoerProd").value;
        createCommodityBatch("rest/actions/commodity-batch-post",commodityBatchNr,commodityNr,amount,provider);
    }
    else {
        alert("Please fill out all the fields!");
    }
    resetValuesCreateRaavarebatch();

    sleep(100).then(() => {
        JSONGetCommodityBatchTable("rest/actions/commoditybatch-get","RaavareBatchTableProduktionsleder")
    });
}

function resetValuesCreateRaavarebatch() {
    document.getElementById("raavarebatchNrProd").value = "";
    document.getElementById("raavareIDProd").value = "";
    document.getElementById("maengdeProd").value = "";
    document.getElementById("leverandoerProd").value = "";
}

//Produktionsleder produktbatch functions
function goToProduktbatch() {
    hideall();
    document.getElementById("produktbatch").style.visibility = "visible";
    JSONGetProductBatchTable("rest/actions/product-batch-get","tableBatch")
}
function finishCreateBatch() {
    if(!document.getElementById("productbatchID_Produktionsleder_produktbatchside").value == "" &&
        !document.getElementById("dato").value == "" &&
        !document.getElementById("receptNr").value == ""){
        var productBatchNr = document.getElementById("productbatchID_Produktionsleder_produktbatchside").value;
        var receiptNr = document.getElementById("receptNr").value;
        var created = document.getElementById("dato").value;

        createProductBatchProduktionsLeader("rest/actions/product-batch-post",productBatchNr,receiptNr,created);
    }
    else {
        alert("Please fill out all the fields!");
    }

    resetValuesCreateBatch();
}

function resetValuesCreateBatch() {
    document.getElementById("productbatchID_Produktionsleder_produktbatchside").value = "";
    document.getElementById("receptNr").value = "";
    document.getElementById("dato").value = "";
}

//Produktionsleder afvejning functions
function goToAfvejning1_Produktionsleder() {
    hideall();
    document.getElementById("afvejning1_Produktionsleder").style.visibility = "visible";
}

function goToAfvejning2_Produktionsleder() {
    hideall();
    document.getElementById("productbatchID_Produktionsleder").value = "";
    document.getElementById("afvejning2_Produktionsleder").style.visibility = "visible";
    document.getElementById("statusOprettet_Produktionsleder").style.visibility = "visible";
}

function addProductbatchComponent_Produktionsleder() {
    var tableBody = document.getElementById("table-body_Produktionsleder");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var row = document.createElement("tr");
    td1.innerHTML = document.getElementById("raavare_Produktionsleder").value;
    td2.innerHTML = document.getElementById("weight_Produktionsleder").value - document.getElementById("tara_Produktionsleder").value;
    td3.innerHTML = document.getElementById("raavarebatchID_Produktionsleder").value;
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    tableBody.appendChild(row);
    resetValuesProductbatchComponentProduktionsLeder();
}

function resetValuesProductbatchComponentProduktionsLeder() {
    document.getElementById("raavare_Produktionsleder").value = "";
    document.getElementById("weight_Produktionsleder").value = "";
    document.getElementById("tara_Produktionsleder").value = "";
    document.getElementById("raavarebatchID_Produktionsleder").value = "";
}

//PRODUKTIONSLEDER MANGLER
function finishProductbatch_Produktionsleder() {

}