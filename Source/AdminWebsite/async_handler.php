<?php
require_once 'connection.php';

$to_return = '';

if (isset($_POST['request'])) {

  $content = json_decode($_POST['content']);
  $db = open_connection();

  switch ($_POST['request']) {
    case 'login':

      break;
    case 'new_user':
      break;
    case 'handbook':
      $sql = "SELECT *
              FROM requirement
              WHERE handbook_year=:year
                  AND honors_type=:type;";
      $stmt = $db->prepare($sql);
      $stmt->execute($content);
      $array_of_requirements = $stmt->fetchAll(PDO::FETCH_ASSOC);

      $return_obj = (object) [
        'requirements' => $array_of_requirements
      ];
      $to_return = json_encode($return_obj);
      break;
  }
}

echo $to_return;
/*
include_once 'connect.php';

$toReturn = '';

if (isset($_POST['get_handbook'])) {
  $something = json_decode($_POST['get_handbook'], true);
  $db = open_connection();
  //echo 'eh';
  $sql = "SELECT display_number,requirement_name,name,total,description
			FROM requirement
			WHERE handbook_year=:handbook_year
				AND honors_type=:honors_type;";
  $stmt = $db->prepare($sql);
  $stmt->execute($something);


  $results=$stmt->fetchAll(PDO::FETCH_ASSOC);
  $toReturn .= json_encode($results);
  //print_r($something);
  /*
  foreach ($something as $key=>$value) {
  $toReturn .= "key: $key; value: $value";

  }
  //*/
  //echo $something['handbook_year'];
  //$toReturn .= "connected and the post was 'test', with 'some data' sent";
  /*
} else {
$toReturn .= 'at least connected';
}

*/
?>