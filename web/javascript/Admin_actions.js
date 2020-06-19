function finishCreateUser() {
    let role;
    if (document.getElementById("Pharmaceut").checked === true){
        role = "Pharmaceut";
    }

    if (document.getElementById("AdminID").checked === true){
        role = "Adminstrator";
    }

    if (document.getElementById("ProduktionslederRadio").checked === true){
        role = "Produktionsleder";
    }

    if (document.getElementById("Laborant").checked === true){
        role = "Laborant";
    }
    if (!document.getElementById("id").value == "" &&
        !document.getElementById("username").value == "" &&
        !document.getElementById("ini").value == "" &&
        !document.getElementById("cpr").value == "" &&
        !document.getElementById("hashedPass").value == "" &&
        (document.getElementById("Pharmaceut").checked == true ||
            document.getElementById("AdminID").checked == true ||
            document.getElementById("ProduktionslederRadio").checked == true ||
            document.getElementById("Laborant").checked == true)) {
        var ID = document.getElementById("id").value;
        var username = document.getElementById("username").value;
        var ini = document.getElementById("ini").value;
        var CPR = document.getElementById("cpr").value;
        var hashedPass = document.getElementById("hashedPass").value;
        createUser("rest/actions/user-create", ID,username,ini,CPR,hashedPass,role,"Aktiv");
        resetValuesCreateUser();
    } else {
        alert("Please fill out all the fields!");
    }
    JSONGetUserTable("rest/actions/user-get","UserTable");
}


function resetValuesCreateUser() {
    document.getElementById("id").value = "";
    document.getElementById("username").value = "";
    document.getElementById("ini").value = "";
    document.getElementById("cpr").value = "";
    document.getElementById("hashedPass").value = "";
    document.getElementById("Pharmaceut").checked = false;
    document.getElementById("AdminID").checked = false;
    document.getElementById("ProduktionslederRadio").checked = false;
    document.getElementById("Laborant").checked = false;
}
