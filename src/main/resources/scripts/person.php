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
$min_date = "2010-02-27T01:00:00Z";
$offset = 0;
$aumento_offset = 1;

$contador_personas = 1;

$cont = 0;
$fecha_temp = null;

mysql_connect("10.20.130.31", "peoplefinder", "people4ca_7321");
mysql_select_db("peoplefinder");

//mysql_connect("localhost", "root", "");
//mysql_select_db("chileayuda");

while (true) {
	$url = "http://chileagils.appspot.com/feeds/person?max_results=".$max_results."&min_entry_date=".$min_date."&skip=".$offset;

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
		$entry_date = "";
		$autor = "";
		$url = "";
		$nombre = "";
		$apellido = "";
		$home_city = "";
		$home_neighborhood = "";
		$home_state = "";
		$home_street = "";
		$last_known_location = "";
		$descripcion = "";
		$status = "";
		$status_autor = "";
		$status_date = "";
		$status_found = "0";
		$info_location = "";

		for ($j = 0 ; $j < $entry->childNodes->item(0)->childNodes->length ; $j++) {
			$node = $entry->childNodes->item(0)->childNodes->item($j);

			if ($node->nodeName == "pfif:person_record_id") {
				$record_id = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:entry_date") {
				$entry_date = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:author_name") {
				$autor = utf8_decode($node->nodeValue);
			}
			else if ($node->nodeName == "pfif:source_url") {
				$url = $node->nodeValue;
			}
			else if ($node->nodeName == "pfif:first_name") {
				$nombre = utf8_decode(addslashes($node->nodeValue));
			}
			else if ($node->nodeName == "pfif:last_name") {
				$apellido = utf8_decode(addslashes($node->nodeValue));
			}
			else if ($node->nodeName == "pfif:home_city") {
				$home_city = utf8_decode(addslashes($node->nodeValue));
			}
			else if ($node->nodeName == "pfif:home_neighborhood") {
				$home_neighborhood = utf8_decode(addslashes($node->nodeValue));
			}
			else if ($node->nodeName == "pfif:home_state") {
				$home_state = utf8_decode(addslashes($node->nodeValue));
			}
			else if ($node->nodeName == "pfif:home_street") {
				$home_street = utf8_decode(addslashes($node->nodeValue));
			}
		}

		$info_location = $home_street.chr(9).$home_neighborhood.chr(9).$home_city.chr(9).$home_state;


		//Ajuste para no repetir personas cuando cambie la fecha de busqueda de registros
		if ($fecha_temp == $entry_date) {
			$aumento_offset++;
		}
		else {
			$aumento_offset = 1;
		}

		$fecha_temp = $entry_date;

		$nombre_completo = $nombre.' '.$apellido;

		$query = "INSERT INTO people (id, name, info_location, url, source_date, cont, home_street, home_neighborhood, home_city, home_state) VALUES ('$record_id', '$nombre_completo', '$info_location', '$url', '$entry_date', $contador_personas, '$home_street', '$home_neighborhood', '$home_city', '$home_state')";
		//echo $query;
		//echo "<br>";
		mysql_query($query);

		$contador_personas++;

	}

	//Ajuste para continuar la busqueda, dado que la consulta para una fecha retorna un max de 800 registros.
	//Si max_results es mayor que entreCnt => que ya proceso toda la info historica y esta agregando las actualizaciones del momento.
	//por lo que la fecha se actualiza con la del ultimo registro procesado.
	if ($entryCnt == 0) {
		//No hago nada esperando que aparezca un registro
	}
	else if ($offset >= 800 || ($max_results > $entryCnt)) {
		if ($max_results > $entryCnt && $entryCnt > 0) {
			//echo "Agregue ".$entryCnt." personas [".date("F j, Y, g:i a"); ;
		}
		$min_date = $fecha_temp;
		$offset = $aumento_offset;

	} else {
		$offset += $max_results;
		$cont++;
	}
}

?>