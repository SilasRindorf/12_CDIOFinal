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

<form method="POST" action="" name="recept1" id="recept1" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <button type="button" onclick="goToRecept2">Opret ny recept</button>
    <button type="button" onclick="goToRecept3">Vis recepter</button>
    <button type="button" onclick="goToFarmaceut()">Gå tilbage</button>
</form>

<form method="POST" action="" name="recept2" id="recept2" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <label  for="receptNr">Indtast råvaren: </label>
    <input type="text" name="receptNr"><br><br>
    <label  for="receptNavn">Indtast råvarebatch nr: </label>
    <input type="text" id="receptNavn" name="receptNavn"><br><br>
    <button type="button" onclick="goToRecept1()">Gå tilbage</button>
    <button type="button" onclick="goToRecept3()">Opret recept</button>
</form>

<form method="POST" action="" name="recept3" id="recept3" style="position: absolute; left: 50px; top: 70px; visibility: hidden">
    <label  for="raavareRecept">Indtast råvaren: </label>
    <input type="text" id="raavareRecept" name="raavareRecept"><br><br>
    <label  for="maengdeRecept">Indtast mængden: </label>
    <input type="text" id="maengdeRecept" for="maengdeRecept" name="maengdeRecept"><br><br>
    <label  for="tolerance" >Indtast tolerancen: </label>
    <input type="text" id="tolerance" for="tolerance" name="receptNavn"><br><br>
    <table id="tableRecept" border="1">
        <thead id="tableRecept-head">
        <tr>
            <th>Raavare</th>
            <th>Mængde</th>
            <th>Tolerance</th>
        </tr>
        </thead>
        <tbody id="tableRecept-body">
        </tbody>
    </table>
    <label for="statusOprettet"><strong>Status:</strong> Ny recept kan påbegyndes</label><br><br>
    <label for="statusUnderProduktion"><strong>Status:</strong> Recept er under produktion</label><br><br>
    <button type="button" onclick="goToRecept2()">Gå tilbage</button>
    <input type="button" value="Tilføj recept komponent"  onClick="addReceptbatchComponent()" id="addRbComponent"><br /><br />
    <button type="button" onclick="finishRecept()">Færdigør X</button>
</form>

</body>
</html>
