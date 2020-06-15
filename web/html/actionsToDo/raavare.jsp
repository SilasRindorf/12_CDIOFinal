<%--
  Created by IntelliJ IDEA.
  User: Sejr
  Date: 15/06/2020
  Time: 10.27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="POST" action="" name="raavarebatch1" id="raavarebatch1" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
    <button type="button" onclick="goToRaavarebatch2()">Opret ny råvarebatch</button>
    <button type="button" onclick="goToRaavarebatch3()">Vis råvarebatch</button>
    <button type="button" onclick="goToProduktionsleder()">Gå tilbage</button>
</form>

<form method="POST" action="" name="raavarebatch2" id="raavarebatch2" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
    <b>Indtast værider</b> <br>
    <label for="raavarebatchNr">Råvarebatch ID:</label><br>
    <input type="text" id="raavarebatchNr" name="raavarebatchNr"><br><br>
    <label for="RaavareID">RåvareID:</label><br>
    <form method="POST" action="" name="Raavarebatch2" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
        <b>Indtast værdier</b>
        <label for="raavarebatchNr">Råvarebatch ID:</label>
        <input type="text" name="raavarebatchNr"><br><br>
        <label for="RaavareID">RåvareID:</label>
        <input type="text" id="RaavareID" name="RaavareID"><br><br>
        <label for="maengde">Mængde</label> <br>
        <input type="text" id="maengde" name="maengde"><br><br>
        <input type="button" onclick="goToRaavarebatch1()" value="Gå tilbage">
        <input type="submit" value="Submit">
    </form>
</form>

<form method="POST" action="" name="raavarebatch3" id="raavarebatch3" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
    <div id="raavareBatchList"></div>
    <button type="button" onclick="goToWelcomeFromDelete()"><bs>Tilbage til start</bs></button>
</form>

</body>
</html>
