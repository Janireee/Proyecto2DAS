<?php 

$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_user";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$id =$_POST['id'];
$nombre =$_POST['nombre'];
$correo =$_POST['correo'];
$direccion =$_POST['direccion'];


$query ="UPDATE user SET nombre ='$nombre' ,correo ='$correo', direccion ='$direccion' WHERE id ='$id'";

$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datos actualizados";
}else{
    echo "error en actualizacion";
}


mysqli_close($conexion);

?>