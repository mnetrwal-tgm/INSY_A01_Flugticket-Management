<form action="table.php" method="GET">
	Flugnummer: <input type="text" name="input"/>
</form>
<?php
$db_host = '';
$db_user = '';
$db_pass = '';
$db_name = '';

if (!isset($_GET['airline']))
{
    echo 'No flight was given...';
    exit;
}

$con = new mysqli($db_host, $db_user, $db_pass, $db_name);
if ($con->connect_error)
{
    die('Connect Error (' . $con->connect_errno . ') ' . $con->connect_error);
}

$sql = "DELETE FROM flights WHERE "+$_GET['airline']+" and "+$_GET['flightnr']+" and "+$_GET['departure_airport']+" and "+$_GET['destination_airport']+" and "+$_GET['planetype'];
if (!$result = $con->prepare($sql))
{
    die('Query failed: (' . $con->errno . ') ' . $con->error);
}

if (!$result->execute())
{
    die('Execute failed: (' . $result->errno . ') ' . $result->error);
}

if ($result->affected_rows > 0)
{
    echo "The flight was deleted with success.";
}
else
{
    echo "Couldn't delete the flight.";
}
$result->close();
$con->close();
?>
<a href="table.php?input=<?php echo &_GET['flightnr']; ?>"> Zur&uuml;ck</a>