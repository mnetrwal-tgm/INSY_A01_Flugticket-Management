<?php

$con = mysqli_connect("localhost","table","password","database");


// Check connection by checking for errors
if (mysqli_connect_errno())
{
    die("Failed to connect to MySQL: " . mysqli_connect_error());
}

if (!$result = mysqli_query($con,"SELECT * FROM flights where flightnr="+input))
{
    die("Error: " . mysqli_error($con));
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
while($row = mysqli_fetch_array($result))
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
<td><a href="delete.php?airline=<?php echo $row['airline']; ?>&flightnr=<?php echo $row['flightnr']; ?>&departure_airport=<?php echo $row['departure_airport']; ?>&destination_airport=<?php echo $row['destination_airport']; ?>&planetype=<?php echo $row['planetype']; ?>">Delete</a></td>
</tr>
<?php
}
mysqli_close($con);
?>
</table>