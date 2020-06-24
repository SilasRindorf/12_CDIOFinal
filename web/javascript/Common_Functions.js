//Common functions


function logIn() {
    hideall();
    logInAction("rest/actions/log-in",document.getElementById("logInName").value, document.getElementById("logInPass").value);
    document.getElementById("welcome").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Vælg venligst din bruger";
}

function hideall() {
    document.getElementById("afvejning1_Produktionsleder").style.visibility = "hidden";
    document.getElementById("logInForm").style.visibility = "hidden";
    document.getElementById("welcome").style.visibility = "hidden";
    document.getElementById("Produktionsleder").style.visibility = "hidden";
    document.getElementById("raavarebatch").style.visibility = "hidden";
    document.getElementById("produktbatch").style.visibility = "hidden"
    document.getElementById("finish").style.visibility = "hidden";
    document.getElementById("admin").style.visibili1ty = "hidden";
    document.getElementById("afvejning1").style.visibility = "hidden";
    document.getElementById("afvejning1").style.visibility = "hidden";
    document.getElementById("afvejning2_Produktionsleder").style.visibility = "hidden";
    document.getElementById("statusOprettet").style.visibility = "hidden";
    document.getElementById("statusUnderProduktion").style.visibility = "hidden";
    document.getElementById("farmaceut").style.visibility = "hidden";
    document.getElementById("recept2").style.visibility = "hidden";
    document.getElementById("recept3").style.visibility = "hidden";
    document.getElementById("recept4").style.visibility = "hidden";
    document.getElementById("Produktionsleder").style.visibility = "hidden";
    document.getElementById("afvejning1_Laborant").style.visibility = "hidden";
    document.getElementById("afvejning2_Laborant").style.visibility = "hidden";
    document.getElementById("afvejning1_Farmaceut").style.visibility = "hidden";
    document.getElementById("afvejning2_Farmaceut").style.visibility = "hidden";
    document.getElementById("raavarebatchFarmaceut").style.visibility = "hidden";
    document.getElementById("raavareFarmaceut").style.visibility = "hidden";
    document.getElementById("produktbatchFarmaceut").style.visibility = "hidden";
    document.getElementById("printPlace").style.visibility = "hidden";
    document.getElementById("recept1").style.visibility = "hidden";
}

function goLogIn() {
    hideall();
    document.getElementById("logInForm").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Login";
    document.getElementById("logInName").value = "";
    document.getElementById("logInPass").value = "";
    document.getElementById("admin").style.visibility = "hidden";
}

function goToWelcomeFromFinish() {
    hideall();
    document.getElementById("welcome").style.visibility = "visible";
}

function goToWelcomeFromDelete() {
    hideall();
    document.getElementById("welcome").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Welcome";
}

function returnToStart() {
    hideall();
    document.getElementById("welcome").style.visibility = "visible";
    document.getElementById("admin").style.visibility = "hidden";
    resetValuesCreateUser();
}

function printPage(){
    window.print();
}

//Go to different roles
function goToAdmin() {
    hideall();
    document.getElementById("welcome").style.visibility = "hidden";
    document.getElementById("admin").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Adminstrator";
    JSONGetUserTable("rest/actions/user-get","UserTable");
}

function goToProduktionsleder() {
    hideall();
    document.getElementById("Produktionsleder").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Produktionsleder";
}

function goToLaborant() {
    hideall();
    goToAfvejning1_Laborant();
    document.getElementById("title").innerHTML = "Laborant";
}

function goToFarmaceut() {
    hideall();
    document.getElementById("farmaceut").style.visibility = "visible";
    document.getElementById("title").innerHTML = "Farmaceut";
}
function printHideAll() {
    hideall();
    document.getElementById("printPlace").style.visibility = "true";

}

