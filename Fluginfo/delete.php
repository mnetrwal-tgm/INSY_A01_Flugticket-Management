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
	$result= $con->query("DELETE FROM passengers WHERE id="+$_GET['id']+";");
	echo "Passagier erfolgreich gelöscht."
}catch(PDOException $ex){
	echo "ERROR:" $ex;
}


$con=null;
?>
<a href="table.php?input=<?php echo &_GET['flightnr']; ?>"> Zur&uuml;ck</a>