//Laborant functions
//Laborant afvejning functions

var productBatchGlobal;
function goToAfvejning1_Laborant() {
    hideall();
    document.getElementById("productbatchID_Laborant").value = "";
    document.getElementById("title").innerHTML = "Laborant";
    document.getElementById("afvejning1_Laborant").style.visibility = "visible";
}

function goToAfvejning2_Laborant() {
    hideall();
    productBatchGlobal = document.getElementById("productbatchID_Laborant").value
    document.getElementById("title").innerHTML = "Laborant";
    document.getElementById("productbatchID_Laborant").value = "";
    document.getElementById("afvejning2_Laborant").style.visibility = "visible";
    JSONGetAfvejningTable("rest/actions/get-afvejning/?productBatchNr=" + productBatchGlobal,"table_Laborant_Afvejning")
}

function addProductbatchComponent_Laborant() {
    if(!document.getElementById("tara_Laborant").value == "" &&
        !document.getElementById("weight_Laborant").value == "" &&
        !document.getElementById("raavarebatchID_Laborant").value == "" &&
        !document.getElementById("raavare_Laborant").value == "" &&
        !document.getElementById("initialer_Laborant").value == "") {

        const tara = document.getElementById("tara_Laborant").value;
        const weighted = document.getElementById("weight_Laborant").value;
        const commodityBatchNr = document.getElementById("raavarebatchID_Laborant").value;
        const commodityNr = document.getElementById("raavare_Laborant").value;
        const ini = document.getElementById("initialer_Laborant").value;
        createProductBatchComp("rest/actions/product-batch-comp-post/?productBatchNr=" + productBatchGlobal, tara, weighted, commodityBatchNr, commodityNr, ini)

    }
    else {
        alert("Please fill out all the fields!");
    }
    resetValuesProductbatchComponentLaborant()

}

function resetValuesProductbatchComponentLaborant() {
    document.getElementById("raavare_Laborant").value = "";
    document.getElementById("weight_Laborant").value = "";
    document.getElementById("tara_Laborant").value = "";
    document.getElementById("raavarebatchID_Laborant").value = "";
}

//LABORANT MANGLER
function finishProductbatch_Laborant() {
    hideall()
    PUTFinishProductBatch(productBatchGlobal);
    document.getElementById("title").innerHTML = "Laborant";
    document.getElementById("afvejning1_Laborant").style.visibility = "visible";
}
