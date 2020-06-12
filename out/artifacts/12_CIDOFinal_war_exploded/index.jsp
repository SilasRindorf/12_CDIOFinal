<%--
  Created by IntelliJ IDEA.
  User: Silas
  Date: 08-06-2020
  Time: 15:14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="CSS/frontpage.css">
    <link rel="stylesheet" href="CSS/bar.css">
    <script type="text/javascript" src="javascript/JSON_actions.js"></script>
    <script type="text/javascript" src="javascript/transitions.js"></script>
    <title>Apotek</title>
  </head>
  <body>
  <ul>
    <li><a class="active" href="#logInForm" onclick="logInScreen()">Log ind</a></li>
    <li><a class="inactive" href="#productionLeaderForm" onclick="productionLeaderScreen()">Producktions leder</a></li>
    <li><a class="inactive" href="#weighingForm" onclick="weighingScreen()">Afvejning</a></li>
    <li><a class="inactive" href="#commoditiesForm" onclick="commoditiesScreen()">Råvare</a></li>
    <li><a class="inactive" href="#productBatchForm" onclick="productBatchScreen()">Producktbatch</a></li>

  </ul>
  <h1>Velkommen til apotekets hjemmeside</h1>
  <form method="post" action="" name="logInForm" id="logInForm" style="position: absolute;">
    <h2>Log ind</h2>
    <p>Brugernavn</p>
    <input type="text" id="uname" name="uname">
    <p>Password</p>
    <input type="text" id="password" name="password">
    <br>
    <button type="button" onclick="logIn();">Log ind</button>
  </form>
  </body>
  <script>
    var uname;
    var password;
    function logIn(){
      uname = document.getElementById("uname").value;
      password = document.getElementById("password").value;
      document.getElementById("uname").value = "";
      document.getElementById("password").value = "";
      action(uname,password,"rest/actions/confirm-log-in");
    }
  </script>
</html>
