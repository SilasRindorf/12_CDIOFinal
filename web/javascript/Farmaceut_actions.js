//Farmaceut Råvarebatch

var receiptNrMemory;
function goToRaavarebatchFarmaceut() {
    hideall();
    document.getElementById("raavarebatchFarmaceut").style.visibility = "visible";
    JSONGetCommodityBatchTable("rest/actions/commoditybatch-get","RaavareBatchTable")
}

function createRaavarebatchComponentFarmaceut() {
    if(!document.getElementById("raavarebatchNrFarmaceut").value == "" &&
        !document.getElementById("raavareIDFarmaceut").value == "" &&
    !document.getElementById("maengdeFarmaceut").value == "" &&
    !document.getElementById("leverandoer").value == ""){
        var commodityBatchNr = document.getElementById("raavarebatchNrFarmaceut").value;
        var commodityNr = document.getElementById("raavareIDFarmaceut").value;
        var amount = document.getElementById("maengdeFarmaceut").value;
        var provider = document.getElementById("leverandoer").value;
        createCommodityBatch("rest/actions/commodity-batch-post",commodityBatchNr,commodityNr,amount,provider);
    }
    else {
        alert("Please fill out all the fields!");
    }
    resetValuesCreateRaavarebatchFarmaceut();
}

function resetValuesCreateRaavarebatchFarmaceut() {
    document.getElementById("raavarebatchNrFarmaceut").value = "";
    document.getElementById("raavareIDFarmaceut").value = "";
    document.getElementById("maengdeFarmaceut").value = "";
    document.getElementById("leverandoer").value = "";
}

//Farmaceut Produktbatch
function goToProduktbatchFarmaceut() {
    hideall();
    document.getElementById("produktbatchFarmaceut").style.visibility = "visible";
}

function finishCreateBatchFarmaceut() {
    var tableBody = document.getElementById("tableBatchFarmaceut");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var row = document.createElement("tr");
    var status = "Oprettet"
    td1.innerHTML = document.getElementById("receptNrFarmaceut").value;
    td2.innerHTML = document.getElementById("datoFarmaceut").value;
    td3.innerHTML = status;
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    tableBody.appendChild(row);
    resetValuesCreateBatchFarmaceut();
}

function resetValuesCreateBatchFarmaceut() {
    document.getElementById("receptNrFarmaceut").value = "";
    document.getElementById("datoFarmaceut").value = "";
}

//Farmaceut afvejning functions
function goToAfvejning1_Farmaceut() {
    hideall();
    document.getElementById("title").innerHTML = "Farmaceut";
    document.getElementById("productbatchID_Farmaceut").value = "";
    document.getElementById("afvejning1_Farmaceut").style.visibility = "visible";

}

function goToAfvejning2_Farmaceut() {
    hideall();
    document.getElementById("title").innerHTML = "Farmaceut";
    document.getElementById("afvejning2_Farmaceut").style.visibility = "visible";
}
function addProductbatchComponent_Farmaceut() {
    var tableBody = document.getElementById("table-body_Farmaceut");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var row = document.createElement("tr");
    td1.innerHTML = document.getElementById("raavare_Farmaceut").value;
    td2.innerHTML = document.getElementById("weight_Farmaceut").value - document.getElementById("tara_Farmaceut").value;
    td3.innerHTML = document.getElementById("raavarebatchID_Farmaceut").value;
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    tableBody.appendChild(row);
    resetValuesProductbatchComponentFarmaceut();
}

function resetValuesProductbatchComponentFarmaceut() {
    document.getElementById("raavare_Farmaceut").value = "";
    document.getElementById("weight_Farmaceut").value = "";
    document.getElementById("tara_Farmaceut").value = "";
    document.getElementById("raavarebatchID_Farmaceut").value = "";
}

//Farmaceut råvare functions

function goToRaavare() {
    hideall();
    document.getElementById("raavareFarmaceut").style.visibility = "visible";
    JSONGetCommodityTable("rest/actions/commodity-get", "RaavareTable");
}

function opretRaavare() {
    if(!document.getElementById("raavareNummer").value == "" &&
    !document.getElementById("raavareNavn").value == ""){
        var commodityNr = document.getElementById("raavareNummer").value;
        var name = document.getElementById("raavareNavn").value;
        const commodity = {
            "name": name,
            "commodityNr": commodityNr
        };
        resetValuesRaavareFarmaceut();
        createCommodity();
    }
    else {
        alert("Please fill out all the fields!");
    }
}

function createReceipt(){
    if(!document.getElementById("recept2Nr").value =="" &&
    !document.getElementById("receptNavn").value == ""){
        var receiptNr = document.getElementById("recept2Nr").value;
        var name = document.getElementById("receptNavn").value;

        const receipt = {
            "receiptNr": receiptNr,
            "name": name
        };
        receiptNrMemory = receiptNr;
        resetValuesRecept2()
        PUTReceiptDTO(receipt)
    }
    else
    {
        alert("Please fill out all the fields!");
    }
}

function resetValuesRaavareFarmaceut() {
    document.getElementById("raavareNummer").value = "";
    document.getElementById("raavareNavn").value = "";
}

//Farmaceut recept functions
function goToRecept1() {
    hideall();
    document.getElementById("recept1").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Farmaceut";
}

function goToRecept2() {
    hideall();
    document.getElementById("recept2").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Farmaceut";
}

function goToRecept3() {
    hideall();
    document.getElementById("recept3").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Farmaceut";
    createReceipt();
    resetValuesRecept2();
}

function goToRecept4() {
    hideall();
    JSONGetReceiptTable("rest/actions/receipt-get");
    document.getElementById("recept4").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Farmaceut";

}

function addReceptbatchComponent() {
    if(!document.getElementById("raavareRecept").value =="" &&
        !document.getElementById("maengdeRecept").value == "" &&
        !document.getElementById("tolerance").value == "" ){
        var receiptNr = receiptNrMemory
        var commodityNr = document.getElementById("raavareRecept").value;
        var amount = document.getElementById("maengdeRecept").value;
        var tolerance = document.getElementById("tolerance").value;

        const receiptComp = {
            "receiptNr": receiptNr,
            "commodityNr": commodityNr,
            "amount": amount,
            "tolerance": tolerance,
        };
        resetValuesReceptComponentFarmaceut()
        PUTReceiptComp(receiptComp);
    }
}

function resetValuesReceptComponentFarmaceut() {
    document.getElementById("maengdeRecept").value = "";
    document.getElementById("raavareRecept").value = "";
    document.getElementById("tolerance").value = "";
}

function resetValuesRecept2() {
    document.getElementById("recept2Nr").value = "";
    document.getElementById("receptNavn").value = "";
}

//FARMACEUT MANGLER
function finishRecept() {
    hideall();
    document.getElementById("recept2").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Farmaceut";
    PUTReceipt(receiptNrMemory)
}

function finishProductbatch_Farmaceut() {
}
