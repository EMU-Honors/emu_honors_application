<?php
session_start();

if ($_POST['username'] == 'Honors' && $_POST['password'] == 'password') {
  $_SESSION['logged_in'] = 'admin';
}
?>
<!doctype html>
<html>
  <head>
    <title>Honor's Application Administration</title>
    <link type="text/css" rel="stylesheet" href="styles.css">
  </head>
  <body>
    <div id="container" style="background-color: #E1E1E1;">
    <img alt="" src="header.png">
      <?php
        if ($_SESSION['logged_in'] == 'admin') {
      ?>
      <div>
        <p><a href="create_reqs.php">Create Requirements</a></p>
        <p><a href="update_reqs.php">Update Requirements</a></p>
        <p><a href="update_reqs.php">View Statistics</a></p>
        <p><a href="logout.php">Logout</a></p>
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
    </div>
  </body>
</html>
