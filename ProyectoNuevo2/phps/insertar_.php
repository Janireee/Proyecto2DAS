<?php 

$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_user";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$nombre =$_POST['nombre'];
$correo =$_POST['correo'];
$direccion =$_POST['direccion'];

// aqui escribimos codigo sql
$query ="INSERT INTO user(nombre,correo,direccion) values('$nombre' ,'$correo', '$direccion') ";
$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datas insertados";
}else{
    echo "datas error";
}
mysqli_close($conexion);

?>