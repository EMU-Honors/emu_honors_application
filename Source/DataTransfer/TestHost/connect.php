<?php
function open_connection() {
  $db = new PDO('mysql:host=localhost;dbname=honors_application;charset=utf8', 'honors_app_admin', 'passwd');
  $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  $db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
  return $db;
}

?>