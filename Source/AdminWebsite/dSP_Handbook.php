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
    <script>
    function $(id) {
    	  return document.getElementById(id);
    }
    var numberOfRequirements = 1;

    function addReq() {
        if (!$('req0')) {
			$('reqs').innerHTML = 
				'<div class="req-holder" id="req0"> \
		    		<div class="row"><button type="button" onclick="this.parentNode.parentNode.remove()">Remove</button> \
		    		<label>Requirement Name:</label><input type="text" class="long-field" name="requirement[' + numberOfRequirements + ']"></div> \
    				<button type="button" onclick="addComp(this.parentNode);">Add Component</button> \
    			</div>';
        } else {
			var reqId = 'req' + numberOfRequirements;
	
			var newReq = $('req0').cloneNode();
			newReq.id = reqId;
			newReq.innerHTML = 
				'<div class="row"><button type="button" onclick="this.parentNode.parentNode.remove()">Remove</button> \
				<label>Requirement Name:</label><input type="text" class="long-field" name="requirement[' + numberOfRequirements + ']"></div> \
				<button type="button" onclick="addComp(this.parentNode);">Add Component</button>';
	
			$('reqs').insertBefore(newReq, $('reqs').childNodes[0]);
	
			numberOfRequirements++;
        }
    }

    var numberOfComps = 0;

    function addComp(parent) {
		var newComp = document.createElement('div');
		
		newComp.id = 'comp' + numberOfComps;
		newComp.innerHTML = 
			'<div class="comp-holder"> \
			<div class="row"><button type="button" onclick="this.parentNode.parentNode.remove()">Remove</button> \
			<label>Component: </label><input type="text" class="long-field" name="component[' + parent.id + '][' + numberOfComps + '][name]"></div> \
		    <div class="row"><label class="inner-comp">Quantity:</label><input type="number" name="component[' + parent.id + '][' + numberOfComps + '][total]"> \
		    	<label>Display Order:</label><input type="number" name="component[' + parent.id + '][' + numberOfComps + '][order]"></div> \
		    <div class="row"><label class="inner-comp">Description:</label> \
		    <input type="text" class="long-field" name="component[' + parent.id + '][' + numberOfComps + '][decription]"></div> \
		    </div>';

	    parent.insertBefore(newComp, parent.lastChild.previousSibling);

	    numberOfComps++;
    }
    </script>
    <style>
    	#reqs {
    		overflow:scroll;
    		height:500px;
    	}
    	.req-holder{
    		margin:auto;
    		padding:10px;
    		text-align:left;
    	}
    	.comp-holder {
    		padding-left:5%;
    	}
    	.row {
    		clear:both;
    		width:100%;
    		padding-bottom:1%;
    	}
    	button, label {
    		margin-right:1%;
    	}
    	label {
    		margin-left:1%;
    		text-align:right;
    	}
    	.inner-comp {
    		margin-left: 9%;
    	}
    	.long-field {
    		width:500px;
    	}
    </style>
   </head>
  <body>
    <div id="container">
    <?php include 'header.html'?>
    	<form action="insert_reqs.php" method="post">
    	<h3>Year:&nbsp;<input type="text" name="handbook_year">&nbsp;
    	Type of Honors:&nbsp;
    			<select name="honors_type">
    				<option value="university">University</option>
    				<option value="departmental">Departmental</option>
    				<option value="highest">Highest</option>
    			</select></h3>
    	<button type="button" onclick="addReq();">Add Requirement</button>
    	<button>Create</button>
    	<hr />
    	<div id="reqs">
    	<div class="req-holder" id="req0">
    		<div class="row"><button type="button" onclick="this.parentNode.parentNode.remove()">Remove</button><label>Requirement Name:</label>
    		<input type="text" class="long-field" name="requirement[0]"></div>
    		<button type="button" onclick="addComp(this.parentNode);">Add Component</button>
    	</div>
    	</div>
    	</form>
	<div id="footer"></div>
    </div>
  </body>
</html>