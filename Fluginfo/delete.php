<form action="table.php" method="GET">
	Flugnummer: <input type="text" name="input"/>
</form>
<?php

$database = include('config.php');





// Check connection by checking for errors
try{
    $con=new PDO('mysql:host='.$database['host'].';dbname='.$database['name'].';charset=utf8mb',$database['user'],$database['pass']);

	$temp1= $con->prepare("DELETE FROM (:passengers) WHERE id=(:id)");
	$temp1->bindParam(':passengers',$table);
	$temp1->bindParam(':id',$rowid);

	$table="passengers";
	$rowid=$_GET['id'];
	$temp1->execute();

	echo "Passagier erfolgreich gelöscht.";
}catch(PDOException $ex){
	echo "ERROR:".$ex;
}


$con=null;
?>
<a href="table.php?input=<?php echo $_GET['flightnr']; ?>"> Zur&uuml;ck</a>