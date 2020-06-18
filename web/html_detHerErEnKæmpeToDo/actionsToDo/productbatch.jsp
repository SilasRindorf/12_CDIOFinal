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

<form method="POST" action="" name="produktbatch1" id="produktbatch1" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
    <button type="button" onclick="goToProduktbatch2()">Opret ny produktbatch</button>
    <button type="button" onclick="goToProduktbatch3()">Vis produktbatches</button>
    <button type="button" onclick="goToProduktionsleder()">Gå tilbage</button>
</form>

<form method="POST" action="" name="produktbatch2" id="produktbatch2" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
    <b>Indtast værdier</b> <br>
    <label for="receptNr">Recept nummer:</label><br>
    <input type="text" id="receptNr" name="receptNr"><br><br>
    <label for="dato">Dato:</label><br>
    <input type="date" id="dato" name="dato"><br><br>
    <input type="button" onclick="goToRaavarebatch1()" value="Gå tilbage">
    <button type="button" onclick="createProduktBatch()">Opret batch</button>
</form>

<form method="POST" action="" name="produktbatch3" id="produktbatch3" style="position: absolute; left: 50px; top: 50px;visibility: hidden">
    <div id="produktbatchList"></div> <br>
    <button type="button" onclick="goToWelcomeFromDelete()"><bs>Tilbage til start</bs></button>
</form>

</body>
</html>
