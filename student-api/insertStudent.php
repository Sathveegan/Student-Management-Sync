<?php

header("Content-Type: application/json");
header("Acess-Control-Allow-Origin: *");

$firstName = $_POST["firstName"];
$lastName = $_POST["lastName"];
$DOB = $_POST["DOB"];
$nic = $_POST["nic"];
$address = $_POST["address"];
$email = $_POST["email"];
$contactNumber = $_POST["contactNumber"];

require_once "dbconfig.php";

$query = "INSERT INTO Student(studentID, firstName, lastName, DOB, nic, address, email, contactNumber)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

$studentID = 'S' . round(microtime(true) * 1000);

$stmt = $conn->prepare($query);
$stmt->bind_param("ssssssss", $studentID, $firstName, $lastName, $DOB, $nic, $address, $email, $contactNumber);
$res = $stmt->execute();

if ($res) {
    echo json_encode(array("message" => "Student Inserted Successfully", "status" => true));
} else {
    echo json_encode(array("message" => "Student Inserted Unsuccessfully", "status" => false));
}

?>