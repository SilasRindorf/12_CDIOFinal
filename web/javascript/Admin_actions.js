function finishCreateUser() {
    if (!document.getElementById("uname").value == "" &&
        !document.getElementById("ini").value == "" &&
        !document.getElementById("cpr").value == "" &&
        !document.getElementById("password").value == "" &&
        (document.getElementById("Pharmaceut").checked == true ||
            document.getElementById("AdminID").checked == true ||
            document.getElementById("ProduktionslederRadio").checked == true ||
            document.getElementById("Laborant").checked == true)) {
        postAnUser();
        resetValuesCreateUser();
    } else {
        alert("Please fill out all the fields!");
    }
}

function postAnUser() {
    var tableBody = document.getElementById("table-body_user");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");
    var td6 = document.createElement("td");
    var td7 = document.createElement("td");
    var row = document.createElement("tr");
    td1.innerHTML = document.getElementById("userID").value;
    td2.innerHTML = document.getElementById("uname").value;
    td3.innerHTML = document.getElementById("ini").value;
    td4.innerHTML = document.getElementById("cpr").value;
    td5.innerHTML = document.getElementById("password").value;
    var userRole = document.getElementsByName('Roles');
    if (document.getElementById("Pharmaceut").checked) {
        userRole = "Pharmaceut";
        td6.innerHTML = userRole;
    }
    if (document.getElementById("AdminID").checked) {
        userRole = "Admin";
        td6.innerHTML = userRole;
    }
    if (document.getElementById("ProduktionslederRadio").checked) {
        userRole = "Produktionsleder";
        td6.innerHTML = userRole;
    }
    if (document.getElementById("Laborant").checked) {
        userRole = "Laborant";
        td6.innerHTML = userRole;
    }
    var statusUser = "Aktiv";
    td7.innerHTML = statusUser;
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    row.appendChild(td5);
    row.appendChild(td6);
    row.appendChild(td7);
    tableBody.appendChild(row);
}

function resetValuesCreateUser() {
    document.getElementById("userID").value = "";
    document.getElementById("uname").value = "";
    document.getElementById("ini").value = "";
    document.getElementById("cpr").value = "";
    document.getElementById("password").value = "";
    document.getElementById("Pharmaceut").checked = false;
    document.getElementById("AdminID").checked = false;
    document.getElementById("ProduktionslederRadio").checked = false;
    document.getElementById("Laborant").checked = false;
}
