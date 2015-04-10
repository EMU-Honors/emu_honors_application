<?php
session_start();

include_once 'connection.php';

$thisPage="create";
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
    	<h3>Year:&nbsp;<input type="text">&nbsp;
    	Type of Honors:&nbsp;
    			<select name="type">
    				<option value="university">University</option>
    				<option value="departmental">Departmental</option>
    				<option value="highest">Highest</option>
    			</select></h3>
    	<button>Add Requirement</button>
    	<div style="margin:auto; padding:10px; text-align:left">
    	 <table>
	    	<tr><td><button>Remove</button></td><td>Requirement Name:</td><td><input type="text"></td></tr>
	    	<tr><td><button>Remove</button></td><td>Component:</td><td><input type="text"></td>
	    		<td>Quantity</td><td><input type="number"></td><td>Display Order</td><td><input type="number"></td></tr>
	    	<tr><td></td><td>Description:</td><td colspan="5"><input type="text" style="width:500px"></td></tr>
	    	<tr><td><button>Add Component</button></td></tr>
	    </table>
    	</div>
	<div id="footer"></div>
    </div>
  </body>
</html>