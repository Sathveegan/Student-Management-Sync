<?php

header("Content-Type: application/json");
header("Acess-Control-Allow-Origin: *");

require_once "dbconfig.php";

$query = "SELECT * FROM Student";

$result = mysqli_query($conn, $query);

$count = mysqli_num_rows($result);

if ($count > 0) {
    $row = mysqli_fetch_all($result, MYSQLI_ASSOC);

    echo json_encode($row);
} else {
    echo json_encode([]);
}

?>