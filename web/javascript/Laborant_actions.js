//Laborant functions
//Laborant afvejning functions
function goToAfvejning1_Laborant() {
    hideall();
    document.getElementById("productbatchID_Laborant").value = "";
    document.getElementById("title").innerHTML = "Laborant";
    document.getElementById("afvejning1_Laborant").style.visibility = "visible";
}

function goToAfvejning2_Laborant() {
    hideall();
    document.getElementById("title").innerHTML = "Laborant";
    document.getElementById("afvejning2_Laborant").style.visibility = "visible";
}

function addProductbatchComponent_Laborant() {
    var tableBody = document.getElementById("table-body_Laborant");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var row = document.createElement("tr");
    td1.innerHTML = document.getElementById("raavare_Laborant").value;
    td2.innerHTML = document.getElementById("weight_Laborant").value - document.getElementById("tara_Laborant").value;
    td3.innerHTML = document.getElementById("raavarebatchID_Laborant").value;
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    tableBody.appendChild(row);
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

}
