<%--
  Created by IntelliJ IDEA.
  User: Sejr
  Date: 15/06/2020
  Time: 10.38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    function resetValues(){
        document.getElementById("uname").value = "";
        document.getElementById("ini").value = "";
        document.getElementById("cpr").value = "";
        document.getElementById("password").value = "";
        document.getElementById("Pharmaceut").checked = false;
        document.getElementById("Admin").checked = false;
        document.getElementById("Produktionsleder").checked = false;
        document.getElementById("Laborant").checked = false;
    }
    function goToCreateUser() {
        document.getElementById("administrator").style.visibility = "hidden";
        document.getElementById("welcome").style.visibility = "hidden";
        document.getElementById("createUser").style.visibility = "visible";
    }
    function returnToStart() {
        document.getElementById("welcome").style.visibility = "visible";
        document.getElementById("createUser").style.visibility = "hidden";
        resetValues();
    }
    function goToProduktbatch1() {
        hideall();
        document.getElementById("produktbatch1").style.visibility = "visible";
    }
    function goToProduktbatch2() {
        hideall();
        document.getElementById("produktbatch2").style.visibility = "visible";
    }
    function goToProduktbatch3() {
        hideall();
        document.getElementById("produktbatch3").style.visibility = "visible";
        getAllProduktBatches("produktbatchList");
    }
    function createProduktBatch() {
        if(!document.getElementById("receptNr").value =="" &&
            !document.getElementById("dato") =="")
        {
            postAnProduktBatch();
            resetProduktBatch2();
            goToProduktionsleder();
        }else{
            alert("Please fill out all the fields!")
        }
    }
    function resetProduktBatch2() {
        document.getElementById("receptNr").value = "";
    }
    function finishCreateUser() {
        if (!document.getElementById("uname").value == "" &&
            !document.getElementById("ini").value == "" &&
            !document.getElementById("cpr").value == "" &&
            !document.getElementById("password").value == "" &&
            (document.getElementById("Pharmaceut").checked == true ||
                document.getElementById("Admin").checked == true ||
                document.getElementById("Produktionsleder").checked == true ||
                document.getElementById("Laborant").checked == true))
        {
            postAnUser();

            resetValues();

            document.getElementById("createUser").style.visibility = "hidden";
            document.getElementById("finish").style.visibility = "visible";
        }
        else {
            alert("Please fill out all the fields!");
        }
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
    function goToDeleteUser() {
        hideall();
        document.getElementById("deleteUser").style.visibility = "visible";
        document.getElementById("title").innerHTML = "User list";
        getAllUsers("userList");
    }
    function goToAdmin() {
        hideall();
        document.getElementById("administrator").style.visibility = "visible";
        document.getElementById("title").innerHTML = "User list";
    }
    function goToProduktionsleder() {
        hideall();
        document.getElementById("produktionsleder").style.visibility = "visible";
        document.getElementById("title").innerHTML = "Produktionsleder";
    }
    function goToRaavarebatch1() {
        hideall();
        document.getElementById("raavarebatch1").style.visibility = "visible";
    }
    function goToRaavarebatch2() {
        hideall();
        document.getElementById("raavarebatch2").style.visibility = "visible";

    }
    function goToRaavarebatch3() {
        hideall();
        document.getElementById("raavarebatch3").style.visibility = "visible";
        document.getElementById("title").innerHTML = "Råvarebatch list";
        getAllRaavarebatches("raavarebatchlist");
    }
    function goToAfvejning1() {
        hideall();
        document.getElementById("afvejning1").style.visibility = "visible";
        document.getElementById("title").innerHTML = "Afvejning1";
    }
    function goToAfvejning2() {
        hideall();
        document.getElementById("afvejning2").style.visibility = "visible";
        document.getElementById("title").innerHTML = "Afvejning2";
        document.getElementById("statusOprettet").style.visibility = "visible"
        resetTable();
    }
    function resetTable(){
        var tableBody = document.getElementById("table-body");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var row = document.createElement("tr");
        td1.innerHTML = document.getElementById("raavare").value;
        td2.innerHTML = document.getElementById("weight").value - document.getElementById("tara").value;
        td3.innerHTML = document.getElementById("raavarebatchID").value;
        row.removeChild(td1);
        row.removeChild(td2);
        row.removeChild(td3);
        tableBody.removeChild(row);
        document.getElementById("statusOprettet").style.visibility = "visible";
        document.getElementById("statusUnderProduktion").style.visibility = "hidden";
    }
    function hideall() {
        document.getElementById("logInForm").style.visibility = "hidden";
        document.getElementById("welcome").style.visibility = "hidden";
        document.getElementById("administrator").style.visibility = "hidden";
        document.getElementById("produktionsleder").style.visibility = "hidden";
        document.getElementById("raavarebatch1").style.visibility = "hidden";
        document.getElementById("raavarebatch2").style.visibility = "hidden";
        document.getElementById("raavarebatch3").style.visibility = "hidden"
        document.getElementById("produktbatch1").style.visibility = "hidden"
        document.getElementById("produktbatch2").style.visibility = "hidden"
        // document.getElementById("produktbatch3").style.visibility = "hidden"
        document.getElementById("deleteUser").style.visibility = "hidden";
        document.getElementById("finish").style.visibility = "hidden";
        document.getElementById("createUser").style.visibility = "hidden";
        document.getElementById("afvejning1").style.visibility = "hidden";
        document.getElementById("afvejning2").style.visibility = "hidden";
        document.getElementById("statusOprettet").style.visibility = "hidden";
        document.getElementById("statusUnderProduktion").style.visibility = "hidden";
        document.getElementById("farmaceut").style.visibility = "hidden";
        document.getElementById("recept1").style.visibility = "hidden";
        document.getElementById("recept2").style.visibility = "hidden";
        document.getElementById("recept3").style.visibility = "hidden";
    }
    function addProductbatchComponent() {
        var tableBody = document.getElementById("table-body");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var row = document.createElement("tr");
        td1.innerHTML = document.getElementById("raavare").value;
        td2.innerHTML = document.getElementById("weight").value - document.getElementById("tara").value;
        td3.innerHTML = document.getElementById("raavarebatchID").value;
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        tableBody.appendChild(row);
        document.getElementById("statusOprettet").style.visibility = "hidden";
        document.getElementById("statusUnderProduktion").style.visibility = "visible";
    }
    function goToFarmaceut(){
        hideall();
        document.getElementById("farmaceut").style.visibility = "visible";
    }
    function goToRecept1(){
        hideall();
        document.getElementById("recept1").style.visibility = "visible";
    }
    function goToRecept2() {
        hideall();
        document.getElementById("recept2").style.visibility = "visible";
    }
    function goToRecept3() {
        hideall();
        document.getElementById("recept3").style.visibility = "visible";
    }
    function addReceptbatchComponent() {
        var tableBody = document.getElementById("table-body");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var row = document.createElement("tr");
        td1.innerHTML = document.getElementById("raavareRecept").value;
        td2.innerHTML = document.getElementById("maengdeRecept").value ;
        td3.innerHTML = document.getElementById("tolerance").value;
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        tableBody.appendChild(row);
        document.getElementById("statusOprettet").style.visibility = "hidden";
        document.getElementById("statusUnderProduktion").style.visibility = "visible";
    }
    function finishRecept() {
        hideall();
        //MANGLER
    }
    function goLogIn() {
        hideall();
        logIn();
    }
    var uname;
    var password;
    function logIn(){
        hideall();
        /*uname = document.getElementById("uname").value;
        password = document.getElementById("password").value;
        action(uname,password,"rest/actions/confirm-log-in");*/
        document.getElementById("logInForm").style.visibility = "visible";
    }
</script>

</body>
</html>
