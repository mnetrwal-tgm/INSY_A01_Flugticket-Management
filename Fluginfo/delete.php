<form action="table.php" method="GET">
	Flugnummer: <input type="text" name="input"/>
</form>
<?php

$database = include('config.php');


$con=new PDO('mysql:host='.$database['host'].';dbname='.$database['name'].';charset=utf8mb',$database['user'],$database['pass']);


// Check connection by checking for errors
try{
	$result= $con->query("DELETE FROM passengers WHERE id=".$_GET['id'].";");
	echo "Passagier erfolgreich gelöscht.";
}catch(PDOException $ex){
	echo "ERROR:".$ex;
}


$con=null;
?>
<a href="table.php?input=<?php echo $_GET['flightnr']; ?>"> Zur&uuml;ck</a>