<?php 

$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_usuario";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$nombre =$_POST['nombre'];
$email =$_POST['email'];
$password =$_POST['password'];
$tokenUsuario = $_POST['token'];

$passwordHash = password_hash($password, PASSWORD_DEFAULT);


$query ="INSERT INTO usuario(nombre,email,password,tokenUsuario) values('$nombre' ,'$email', '$passwordHash', '$tokenUsuario') ";
$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datas insertados";
}else{
    echo "datas error";
}
mysqli_close($conexion);


/*
$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_usuario";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$nombre =$_POST['nombre'];
$email =$_POST['email'];
$password =$_POST['password'];
$tokenUsuario = $_POST['token'];

$passwordHash = password_hash($password, PASSWORD_DEFAULT);
// aqui escribimos codigo sql
$query ="INSERT INTO usu(nombre,email,password,tokenUsuario) values('$nombre' ,'$email', '$passwordHash', '$tokenUsuario') ";
//$query ="INSERT INTO usu(nombre,email,password,tokenUsuario) values('$nombre' ,'$email', '$passwordHash', '$tokenUsuario') ON DUPLICATE KEY UPDATE nombre='$nombre', password='$passwordHash', tokenUsuario='$tokenUsuario'";

$resultado =mysqli_query($conexion,$query);


if($resultado){
    echo "datas insertados";
}else{
    echo "datas error";
}
mysqli_close($conexion);
*/
?>