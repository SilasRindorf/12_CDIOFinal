<%--
  Created by IntelliJ IDEA.
  User: Sejr
  Date: 15/06/2020
  Time: 10.28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="POST" action="" name="createUser" id="createUser" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <button type="button" onclick="returnToStart();">Tilbage til start</button>
    <p>Brugernavn</p>
    <input type="text" id="uname" name="uname">
    <p>Initialer</p>
    <input type="text" id="ini" name="ini">
    <p>Cpr-nummer</p>
    <input type="text" id="cpr" name="cpr">
    <p>Password</p>
    <input type="text" id="password" name="password">
    <p>Rolle</p>
    <input type="radio" id="Pharmaceut" name="Roles" value="Pharmaceut">
    <label for="Pharmaceut"> Pharmaceut</label><br>
    <input type="radio" id="Admin" name="Roles" value="Admin">
    <label for="Admin"> Admin</label><br>
    <input type="radio" id="ProduktionslederRadio" name="Roles" value="Produktionsleder">
    <label for="ProduktionslederRadio"> Produktionsleder</label><br>
    <input type="radio" id="Laborant" name="Roles" value="Laborant">
    <label for="Laborant"> Laborant</label><br>
    <button type="button" onclick="finishCreateUser();">Opret bruger</button>
</form>

<form method="POST" action="" id="deleteUser" style="position: absolute; visibility: hidden; left: 50px; top: 50px; z-index: 200;">
    <p>Who do you want to delete?</p>
    <div id="userList"></div>
    <button type="button" onclick="goToWelcomeFromDelete()"><bs>Tilbage til start</bs></button>
</form>

</body>
</html>
