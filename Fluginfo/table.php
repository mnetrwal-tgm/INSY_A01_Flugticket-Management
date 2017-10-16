<form action="table.php" method="GET">
	Flugnummer: <input type="text" name="input"/>
</form>
<?php

$database = include('config.php');

// Check connection by checking for errors
try{
    $con=new PDO('mysql:host='.$database['host'].';dbname='.$database['name'].';charset=utf8mb',$database['user'],$database['pass']);
    $input=$_GET['input'];
    $temp1= $con->prepare("SELECT * FROM flights natural join passengers where flightnr=(:input)");
	$temp1->bindParam(":input",$input);
	$result=$temp1->execute();
?>


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
    $temp1 = $con->prepare("SELECT maxseats FROM flights inner join planes on flights.planetype=planes.id where flightnr=(:input)");
    $temp1->bindParam(":input",$input);
    $result=$temp1->execute();
    $seats_left = $result['maxseats']-$count;
    echo "Fluggäste: ".$count. "</br>Sitze frei: ".$seats_left;
    $con=null;
}catch(PDOException $ex){
    echo "ERROR: ".$ex;
}
?>