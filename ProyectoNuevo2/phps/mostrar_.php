<?php 

$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_user";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$result= array();
$result['datos'] =array();
$query ="SELECT *FROM user WHER  ";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce))
{
    $index['id'] =$row['0'];
    $index['nombre'] =$row['1'];
    $index['correo'] =$row['2'];
    $index['direccion'] =$row['3'];

    array_push($result['datos'], $index);

}
$result["exito"]="1";
echo json_encode($result);

?>