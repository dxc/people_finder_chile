<?php
// Parametros de config
$host     = '10.20.130.31';
$user     = 'peoplefinder';
$pass     = 'people4ca_7321';
$dbName   = 'peoplefinder';
$filename = 'listado-people';
$tabla    = 'people';
// Campos de la tabla que deben ser pasados al Excel
$campos = array('name', 'info_location', 'last_status', 'status_date');

// Hacemos todo lo respectivo a la BD
$conx = mysql_connect($host, $user, $pass);
mysql_select_db($dbName);
// Construyo la consulta
$sql = "SELECT * FROM $tabla WHERE status_found = 0";
// Ejecuto
$result = mysql_query($sql);

// Headers para el Excel
header("Content-Type: application/vnd.ms-excel");
header("Expires: 0");
header("Cache-Control: must-revalidate, post-check=0, pre-check=0");
header("content-disposition: attachment;filename=$filename.xls");
?>
<table border="1">
<tr>
<?php
foreach ($campos as $campo) {
    echo "<th>$campo</th>\n";
}
?>
</tr>
<?php
while ($row = mysql_fetch_array($result)) {
    echo "<tr>";

        foreach ($campos as $campo) {
            echo "<td>";
            echo $row[$campo];
            echo "</td>\n";
        }

    echo "</tr>\n   ";
}
?>
</table>