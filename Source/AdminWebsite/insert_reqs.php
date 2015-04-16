<?php 

foreach($_POST['component'] as $req_name_index=>$value) {
	foreach($value as $key=>$component) {
		$req = $_POST['requirement'][substr($req_name_index,3)];
		$keys = implode(',', array_keys($component));
		$vals = '"' . implode('","', $component) . '"';
		echo "\nINSERT INTO requirement (year, type, $keys, requirement_name)
				VALUES (\"" . $_POST['year'] . "\", \"" . $_POST['type'] . "\", $vals, \"" . $req . "\");";
	}
}
?>