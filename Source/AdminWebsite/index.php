<?php
session_start();

if ($_POST['username'] == 'Honors' && $_POST['password'] == 'password') {
  $_SESSION['logged_in'] = 'admin';
}

$thisPage="home";
?>
<!doctype html>
<html>
  <head>
    <title>Honor's Application Administration</title>
    <link type="text/css" rel="stylesheet" href="styles.css">
  </head>
  <body>
    <div id="container">
    <?php include 'header.html'?>
      <?php
        if ($_SESSION['logged_in'] == 'admin') {
      ?>
      <div style="margin-top:15%;">
	      <h1> Welcome! </h1>
	      <p> Please choose an option from above. </p>
      </div>
      <?php
        } else {
      ?>
      <div id="login">
        <h2>Admin Login</h2>
        <form action="<?=$_SERVER['PHP_SELF']?>" method="post">
          <p>Username: <input type="text" name="username" /></p>
          <p>Password: <input type="password" name="password" /></p>
          <button>Log In</button>
        </form>
      </div>
      <?php
        }
      ?>
      <div id="footer"></div>
    </div>
  </body>
</html>
