<?php
session_start();

if ($_POST['username'] == 'admin' && $_POST['password'] == 'admin') {
  $_SESSION['logged_in'] = 'admin';
}

$thisPage="stats";
?>
<!doctype html>
<html>
  <head>
    <title>Honor's Application Administration</title>
    <link type="text/css" rel="stylesheet" href="styles.css">
    <style>
    td {
    text-align:right;
    }
    </style>
  </head>
  <body>
    <div id="container">
    <?php include 'header.html'?>
      <h2>Display Statistics</h2>
      <center>
      <table border="1">
        <tr><th>Year Following</th><th>Num Students</th></tr>
        <tr><th>2015</th><td>5983</td></tr>
        <tr><th>2016</th><td>23</td></tr>
        <tr><th>2017</th><td>512</td></tr>
        <tr><th>2018</th><td>466</td></tr>
      </table>
      <br>
      <br>
      <table border="1">
        <tr><th>Type of Honors</th><th>Num Students</th></tr>
        <tr><th>University</th><td>116</td></tr>
        <tr><th>Departmental</th><td>2</td></tr>
        <tr><th>Highest</th><td>34</td></tr>
        <tr><th>Full</th><td>2103</td></tr>
      </table>
      <h3>By Major:</h3>
      <select>
        <option>Computer Science - Applied</option>
      </select><br>
      <table border="1">
        <tr><th>Num Students:</th><td>3054</td></tr>
      </table>
      </center>
      <div id="footer"></div>
    </div>
  </body>
</html>
