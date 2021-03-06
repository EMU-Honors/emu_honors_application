<?php
session_start();

if ($_SESSION['logged_in'] != 'admin') {
  header('Location: ' . 'index.php');
  die();
}
$thisPage="create";
?>
<!doctype html>
<html>
  <head>
    <title>Honor's Application Administration</title>
    <link type="text/css" rel="stylesheet" href="styles.css">
    <script>
    var numberOfRequirements = 0;

    function addNewRequirement() {
        var requirementId = 'requirement' + numberOfRequirements;

    	  var requirementDiv = document.createElement('div');
    	  requirementDiv.id = requirementId;
    	  requirementDiv.className = 'requirement';

    	  var requirementHeading = document.createTextNode('Requirement Number ' + (numberOfRequirements + 1) + ':');
    	  requirementDiv.appendChild(requirementHeading);
    	  requirementDiv.appendChild(document.createElement('br'));

    	  var requirementNameLabel = document.createElement('label');
    	  var requirementNameLabelText = document.createTextNode('Name:');
    	  requirementNameLabel.appendChild(requirementNameLabelText);

    	  var requirementName = document.createElement('input');
    	  requirementName.name = requirementId + '#name';
    	  requirementName.type = 'text';

    	  requirementNameLabel.appendChild(requirementName);

    	  var requirementNumberLabel = document.createElement('label');
    	  var requirementNumberLabelText = document.createTextNode('Number Needed:');
    	  requirementNumberLabel.appendChild(requirementNumberLabelText);

    	  var requirementNumber = document.createElement('input');
    	  requirementNumber.name = requirementId + '#number';
    	  requirementNumber.type = 'number';

    	  requirementNumberLabel.appendChild(requirementNumber);

    	  requirementDiv.appendChild(requirementNameLabel);
    	  requirementDiv.appendChild(requirementNumberLabel);

    	  document.getElementById('requirements').appendChild(requirementDiv);
    	  numberOfRequirements++;
    }
    </script>
    <style type="text/css">
    div.requirement {
      margin-bottom: 10px;
      border: 1px solid black;
      padding: 5px;
      width: 60%;
      margin:auto;
    }
    label {
      padding: 20px;
    }
    </style>
  </head>
  <body>
    <div id="container">
    <?php include 'header.html'?>
      <form action="" method="post">
        <p>Year: <input type="number" min="2007" value="2007" /></p>
        <div id="requirements"></div>
        <p>
          <button type="button" onclick="addNewRequirement();">Add new requirement</button>
          <button type="button" >Load Previous Year</button>
          <button>Submit new Requirement Catalogue</button>
        </p>
      </form>
      <div id="footer"></div>
    </div>
  </body>
</html>
