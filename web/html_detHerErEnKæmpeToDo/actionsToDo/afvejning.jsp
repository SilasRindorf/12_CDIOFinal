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

<form method="POST" action="" name="afvejning1" id="afvejning1" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <label  for="productbatchID">Produktbatch ID:</label>
    <input type="text" id="productbatchID" name="productbatchID"><br><br>
    <button type="button" onclick="goToAfvejning2()">Send ind</button>
</form>

<form method="POST" action="" name="afvejning2" id="afvejning2" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <label  for="raavare">Indtast råvaren: </label>
    <input type="text" id="raavare" name="raavare"><br><br>
    <label  for="raavarebatchNr">Indtast råvarebatch nr: </label>
    <input type="text" id="raavarebatchID" name="raavarebatchNr"><br><br>
    <label  for="weight">Indtast vægten: </label>
    <input type="text" id="weight" name="weight"><br><br>
    <label  for="tara">Indtast tara: </label>
    <input type="text" id="tara" name="tara"><br><br>
    <input type="button" value="Tilføj produckbatch komponent"  onClick="addProductbatchComponent()" id="addPbComponent"><br /><br />
    <table id="table" border="1">
        <thead id="table-head">
        <tr>
            <th>raavare</th>
            <th>weight - tara</th>
            <th>raavarebatchNr</th>
        </tr>
        </thead>
        <tbody id="table-body">
        </tbody>
    </table>
    <label id="statusOprettet" for="statusOprettet"><strong>Status:</strong> Nyt produktbatch kan påbegyndes</label><br><br>
    <label id="statusUnderProduktion" for="statusUnderProduktion"><strong>Status:</strong> Produktbatch er under produktion</label><br><br>
    <button type="button" onclick="goToAfvejning2()">Send ind</button>
    <button type="button" onclick="goToWelcomeFromFinish()">Send ind og afslut</button>
    <button type="button" onclick="resetTable()">Reset tabel</button>
</form>

</body>
</html>
