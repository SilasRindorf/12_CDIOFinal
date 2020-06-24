function goToRaavarebatch() {
    hideall();
    document.getElementById("raavarebatch").style.visibility = "visible";
}

function createRaavarebatchComponent() {
    var tableBody = document.getElementById("table_raavarebatch");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var row = document.createElement("tr");
    td1.innerHTML = document.getElementById("raavarebatchNr").value;
    td2.innerHTML = document.getElementById("raavareID").value;
    td3.innerHTML = document.getElementById("maengde").value;
    td4.innerHTML = document.getElementById("leverandoer_produktionsleder").value;
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    tableBody.appendChild(row);
    resetValuesCreateRaavarebatch();
}

function resetValuesCreateRaavarebatch() {
    document.getElementById("raavarebatchNr").value = "";
    document.getElementById("raavareID").value = "";
    document.getElementById("maengde").value = "";
    document.getElementById("leverandoer_produktionsleder").value = "";
}

//Produktionsleder produktbatch functions
function goToProduktbatch() {
    hideall();
    document.getElementById("produktbatch").style.visibility = "visible";
}


function resetValuesCreateBatch() {
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