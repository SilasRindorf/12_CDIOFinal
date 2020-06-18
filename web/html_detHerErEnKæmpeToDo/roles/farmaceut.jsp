<%--
  Created by IntelliJ IDEA.
  User: Sejr
  Date: 15/06/2020
  Time: 10.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="POST" action="" name="farmaceut" id="farmaceut" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <button type="button" onclick="goToRaavarebatch1()">Ændre på råvarebatches</button>
    <button type="button" onclick="goToProduktbatch1()">Ændre på produktbatches</button>
    <button type="button" onclick="goToAfvejning1()">Gå til afvejning</button>
    <button type="button" onclick="goToRecept1()">Gå til recept X</button>
    <button type="button" onclick="goToRaavare()">Ændre på råvare X</button>
    <button type="button" onclick="goToWelcomeFromDelete()"><bs>Tilbage til start</bs></button>
</form>

</body>
</html>
