<?php

header("Content-Type: application/json");
header("Acess-Control-Allow-Origin: *");

$studentID = $_POST["studentID"];

require_once "dbconfig.php";

$query = "DELETE FROM Student WHERE studentID=?";

$stmt = $conn->prepare($query);
$stmt->bind_param("s", $studentID);
$res = $stmt->execute();

if ($res) {
    echo json_encode(array("message" => "Student Delete Successfully", "status" => true));
} else {
    echo json_encode(array("message" => "Student Delete Unsuccessfully", "status" => false));
}

?>