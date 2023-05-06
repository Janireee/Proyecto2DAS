<?php 

$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_usuario";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$email =$_POST['email'];
$password =$_POST['pass'];

$query1= mysqli_query($conexion, "SELECT password FROM usuario WHERE email= '$email'");
if (!$query1) {
    echo 'Ha ocurrido algún error: ' . mysqli_error($conexion);
}

$fila = mysqli_fetch_row($query1);

$passwordHash = $fila[0];

if(password_verify($password, $passwordHash)){
    echo "Ingreso correctamente";
}
else{
    echo 'La contraseña no es válida.';
}
/*
// aqui escribimos codigo sql
$query ="SELECT * FROM usuario WHERE email= '$email' AND password='$password'";
$resultado =mysqli_query($conexion,$query);*/
?>