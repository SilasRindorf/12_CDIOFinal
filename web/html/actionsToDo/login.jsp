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

<form method="post" action="" name="logInForm" id="logInForm" style="position: absolute;">
    <p>Brugernavn</p>
    <input type="text" for="uname" name="uname">
    <p>Password</p>
    <input type="text" for="password" name="password">
    <br>
    <button type="button" onclick="logIn();">Log ind</button>
</form>

<form method="POST" action="" name="welcome" id="welcome" style="position: absolute; left: 50px; top: 50px; visibility: hidden;" >
    <p><strong>WELCOME TO THIS FANTASTIC SOFTWARE</strong></p>
    <button type="button" onclick="goToCreateUser()">Opret en bruger</button>
    <button type="button" onclick="goToDeleteUser()">Se brugere & slet brugere</button>
    <button type="button" onclick="goToAdmin()">Administrator</button>
    <button type="button" onclick="goToProduktionsleder()">Produktionsleder</button>
    <button type="button" onclick="goToAfvejning1()">Laborant</button>
    <button type="button" onclick="goToFarmaceut()">Farmaceut</button>
</form>

</body>
</html>
