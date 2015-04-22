<?php
session_start();
include_once 'connection.php';

if ($_POST['username'] == 'admin' && $_POST['password'] == 'admin') {
	$_SESSION['logged_in'] = 'admin';
}

$thisPage="stats";

$db = open_connection();

$handbook_sql = "SELECT handbook_year, count(*) as total
		FROM student 
		GROUP BY handbook_year 
		ORDER BY handbook_year;";
$handbook = $db->prepare($handbook_sql);
$handbook->execute();

?>
<!doctype html>
<html>
  <head>
    <title>Honor's Application Administration</title>
    <link type="text/css" rel="stylesheet" href="styles.css">
    <style>
    th, td{
    text-align:center;
    padding:5px;
    }
    </style>
  </head>
  <body>
    <div id="container">
    <?php include 'header.html'?>
      <h2>Display Statistics</h2>
      <center>
      <table border="1">
        <tr><th>Handbook Year</th><th>Students</th></tr>
       <?php 
       while($result = $handbook->fetch(PDO::FETCH_ASSOC)){
       		echo "<tr>";
       		echo "<td style='text-align:center'>" . $result['handbook_year'] . "</td><td style='text-align:center'>" . $result['total'] . "</td>";
       		echo "</tr>";
       }
       ?>
      </table>
      <br>
      <br>
      <table border="1">
        <tr><th>Type of Honors</th><th>Students</th></tr>
       <?php 
        $type_sql = "SELECT honors_type, count(student_following.person_id) as total
        			 FROM student, student_following
					 WHERE student.person_id = student_following.person_id
        			 GROUP BY honors_type;";
        $type = $db->prepare($type_sql);
        $type->execute();
       
       while($result = $type->fetch(PDO::FETCH_ASSOC)){
       		echo "<tr>";
       		echo "<td>" . $result['honors_type'] . "</td><td style='text-align:center'>" . $result['total'] . "</td>";
       		echo "</tr>";
       }
       ?>
      </table>
      <h3>By Major:</h3>
      <form action="stats.php" method="post">
      <select name="department">
        	<option>Choose a Department</option>
	      <?php 
	      $major_sql = "SELECT DISTINCT department
						FROM student_program;";
	      $major_sql = $db->prepare($major_sql);
	      $major_sql->execute();
	      
	      while($result = $major_sql->fetch(PDO::FETCH_ASSOC)){
	      	echo "<option>" . $result['department'] . "</option>";
	      }
	      ?>
	  </select>
	  <button>Update</button>
      </form>
      <br />
      <table border="1">
        <tr><th>Department</th><th>Students</th>
        <tr><td> 
        <?php 
        	if ($_POST['department'] == null) {
        		echo 'N/A';
        	} else {
        		echo $_POST['department'];
        	}
        ?> 
        </td>
        <?php 
          if ($_POST['department'] == null) {
          	echo "<td style='text-align:center'>N/A</td></tr>";
          } else {
		      $count_sql = "SELECT count(student.person_id) as total
							FROM student, student_program
							WHERE student.person_id = student_program.person_id
							AND department='". $_POST['department'] . "';";
		      $count_sql = $db->prepare($count_sql);
		      $count_sql->execute();
		      
		      while($result = $count_sql->fetch(PDO::FETCH_ASSOC)){
		      	echo "<td style='text-align:center'>". $result['total'] . "</td></tr>";
		      }
          }
	      ?>
      </table>
      <br />
      </center>
      <div id="footer"></div>
    </div>
  </body>
</html>
