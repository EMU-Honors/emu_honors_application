<?php
session_start();

if ($_POST['username'] == 'admin' && $_POST['password'] == 'admin') {
  $_SESSION['logged_in'] = 'admin';
}
?>
<!doctype html>
<html>
  <head>
    <title>Honor's Application Administration</title>
  </head>
  <body>
    <?php
      if ($_SESSION['logged_in'] == 'admin') {
    ?>
    <div>
      <p><a href="create_reqs.php">Create Requirements</a></p>
      <p><a href="update_reqs.php">Update Requirements</a></p>
      <p><a href="logout.php">Logout</a></p>
    </div>
    <?php
      } else {
    ?>
    <div id="login">
      <form action="<?=$_SERVER['PHP_SELF']?>" method="post">
        <p>Username: <input type="text" name="username" /></p>
        <p>Password: <input type="password" name="password" /></p>
        <button>Login</button>
      </form>
    </div>
    <?php
      }
    ?>
  </body>
</html>
