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
    <script type="text/javascript" src="javascript/JSON_actions.js"></script>
    <title>Apotek</title>
  </head>
  <body>
  <h1>Velkommen til apotekets hjemmeside</h1>
  <h2>Log ind</h2>
  <form method="post" action="" name="logInForm" id="logInForm" style="position: absolute;">

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
      logInAction(uname,password,"rest/actions/log-in");
    }
  </script>
</html>
