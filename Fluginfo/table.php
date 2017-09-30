<form action="table.php" method="GET">
	Flugnummer: <input type="text" name="input"/>
</form>
<?php

$db_host = '';
$db_user = '';
$db_pass = '';
$db_name = '';

$con=new PDO('mysql:host='+$db_host+';dbname='+$db_name+';charset=utf8mb',$db_user,$db_pass)


// Check connection by checking for errors
try{
	$result= $con->query("SELECT * FROM flights natural join passengers where flightnr="+$_GET['input']+";");
}catch(PDOException $ex){
	echo "ERROR:" $ex;
}
?>


<table border='1'>
<tr>
<th>Airline</th>
<th>Flug Nummer</th>
<th>Abflugzeit</th>
<th>Abflugort</th>
<th>Ankunftszeit</th>
<th>Ankunftsort</th>
<th>Flugzeugtyp</th>
</tr>
<?php
foreach($result as $row)
{
?>
<tr>
<td><?php echo $row['airline']; ?></td>
<td><?php echo $row['flightnr']; ?></td>
<td><?php echo $row['departure_time']; ?></td>
<td><?php echo $row['departure_airport']; ?></td>
<td><?php echo $row['destination_time']; ?></td>
<td><?php echo $row['destination_airport']; ?></td>
<td><?php echo $row['planetype']; ?></td>
</tr>
</table>
<table border='1'>
<tr>
<th>Vorname</th>
<th>Nachname</th>
<th>Reihe</th>
<th>Sitz</th>
<th>Löschen</th>
</tr>
<?php
foreach($result as $row)
{
?>
<tr>
<td><?php echo $row['firstname']; ?></td>
<td><?php echo $row['lastname']; ?></td>
<td><?php echo $row['rownr']; ?></td>
<td><?php echo $row['seatposition']; ?></td>
<td><form><input type="button" value="-" onclick="window.location.href='delete.php?id=<?php echo $row['id']; ?>&flightnr=<?php echo $row['flightnr']; ?>'" /></form></td>
</tr>
</table>
<?php
}
$count = $result->rowCount();
$temp = $con->query($con,"SELECT maxseats FROM flights inner join planes on flights.planetype=planes.id where flightnr="+$_GET['input']+";");
$temp = $temp->fetch(PDO::FETCH_ASSOC:);
$seats_left = $temp['maxseats']-$count;
echo "Fluggäste: "+$count+ "</br>Sitze frei: "+$seats_left;
$con=null;
?>
