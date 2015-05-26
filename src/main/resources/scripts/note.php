<?php
$opts = array(
    'http' => array(
        'user_agent' => 'PHP libxml agent',
)
);

ini_set("max_execution_time","10000000000");

$context = stream_context_create($opts);
libxml_set_streams_context($context);

$dom = new DOMDocument('1.0', 'UTF-8');
$dom->preserveWhiteSpace = false;

$max_results = 200;
$min_date = "2010-02-27T01:01:01Z";
$offset = 0;
$aumento_offset = 1;

$cont = 0;
$fecha_temp = null;
$sqlStatements = "";

//$batchconnection = new mysqli("10.20.130.31", "peoplefinder", "people4ca_7321", "peoplefinder");

if ($batchconnection->connect_error) {
          echo "Error Occurred While Connection To DataBase";
      }

mysql_connect("10.20.130.31", "peoplefinder", "people4ca_7321");
mysql_select_db("peoplefinder");

//mysql_connect("localhost", "root", "");
//mysql_select_db("chileayuda");

$contador_ingreso = 1;

while (true) {
	$url = "http://chileagils.appspot.com/feeds/note?max_results=".$max_results."&min_entry_date=".$min_date."&skip=".$offset;

	$dom->load($url);

	$entries = $dom->getElementsByTagName('entry');

	$entryCnt  = $entries->length;

	$idx = 0;

	//Ajuste cuando el offset este desfasado para no repetir registros en la base de datos.
	if ($offset > 800) {
		$idx = $offset - 800;
	}

	for (; $idx < $entryCnt; $idx++) {
		$entry = $entries->item($idx);

		$record_id = "";
		$source_date = "";
		$status = "";
		$author_name = "";
		$found = "";
		$last_known_location = "";

		for ($j = 0 ; $j < $entry->childNodes->item(0)->childNodes->length ; $j++) {
			$node = $entry->childNodes->item(0)->childNodes->item($j);

			if ($node->nodeName == "pfif:person_record_id") {
				$record_id = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:entry_date") {
				$source_date = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:status") {
				$status = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:author_name") {
				$author_name = utf8_decode(addslashes($node->nodeValue));
			}
			else if ($node->nodeName == "pfif:found") {
				$found = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:last_known_location") {
				$last_known_location = utf8_decode(addslashes($node->nodeValue));
			}

		}


		//Ajuste para no repetir personas cuando cambie la fecha de busqueda de registros
		if ($fecha_temp == $source_date) {
			$aumento_offset++;
		}
		else {
			$aumento_offset = 1;
		}

		$fecha_temp = $source_date;

		if ($found) {
					$found = 1;
				}
				else {
					$found = 0;
		}



		$query = "UPDATE people SET last_status = '$status', status_author = '$author_name', status_date = '$source_date', status_found = $found, last_known_location = '$last_known_location', cont_note = '$contador_ingreso' where id = '$record_id'";
		mysql_query($query);
		//$sqlStatements = $sqlStatements."UPDATE people SET last_status = '$status', status_author = '$author_name', status_date = '$source_date', status_found = $found, last_known_location = '$last_known_location', cont_note = '$contador_ingreso' where id = '$record_id';";


		$contador_ingreso++;

	}


	/*$sqlResult = $batchconnection->multi_query($sqlStatements);

   	if($sqlResult == true) {
		echo "Successfully Inserted Records<br>";
   	} else {
		echo "Some Error Occured While Inserting Records<br>";
		mysqli_errno($batchconnection);
   	}
*/

   	$sqlStatements = "";




	//Ajuste para continuar la busqueda, dado que la consulta para una fecha retorna un max de 800 registros.
	//Si max_results es mayor que entreCnt => que ya proceso toda la info historica y esta agregando las actualizaciones del momento.
	//por lo que la fecha se actualiza con la del ultimo registro procesado.
	if ($entryCnt == 0) {
		//No hago nada esperando que aparezca un registro
		//sleep(120);
	}
	else if ($offset >= 800 || ($max_results > $entryCnt)) {
		$min_date = $fecha_temp;
		$offset = $aumento_offset;

	} else {
		$offset += $max_results;

	}
	$cont++;

}

?>
