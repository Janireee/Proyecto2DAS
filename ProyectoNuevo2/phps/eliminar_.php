<?php 

$DB_SERVER="localhost";
$DB_USER="Xjveganzones001";
$DB_PASS="tW806L5JB";
$DB_DATABASE="Xjveganzones001_user";

$conexion =mysqli_connect($DB_SERVER,$DB_USER,$DB_PASS,$DB_DATABASE);
if(!$conexion){
    echo "error en conexion";
}

$id =$_POST["id"];
$query ="DELETE  FROM user WHERE id='$id'";
$result=mysqli_query($conexion,$query);
if($result){
    echo "datos eliminados";
}else{
    echo "error";
}

mysqli_close($conexion);

?>


<?php
    $DB_SERVER="localhost"; #la dirección del servidor
    $DB_USER="Xgsalaberria004"; #el usuario para esa base de datos
    $DB_PASS="fgnNdpq2x"; #la clave para ese usuario
    $DB_DATABASE="Xgsalaberria004_gartxon"; #la base de datos a la que hay que conectarse
    # Se establece la conexión:
    $con = mysqli_connect($DB_SERVER, $DB_USER, $DB_PASS, $DB_DATABASE);
    #Comprobamos conexión
    if (mysqli_connect_errno()) {
        echo 'Error de conexion: ' . mysqli_connect_error();
        exit();
    }

    # Ejecutar la sentencia SQL
    $resultado = mysqli_query($con, "SELECT token FROM usuarios");
    # Comprobar si se ha ejecutado correctamente
    if (!$resultado) {
        echo 'Ha ocurrido algún error: ' . mysqli_error($con);
    }
    else {
        $arrayresultados = array();
        
        while ($fila = mysqli_fetch_row($resultado)) {
            $arrayresultados[] = $fila[0];
        }
    }

    $cabecera= array(
    'Authorization: key=AAAAXTaDSbg:APA91bGZMv3I94nw616ADOeMLeM0qaovb1eAO5c8T3twxHb2KQWgxB_Nyf88CngQ7KZI51iPTz90Bn7_nGaIZrjQncd9TlMSJ4WTWYWaj12snQIAv33_CDkJbP8A1rT1CV7AS1XgEzNH',
    'Content-Type: application/json'
    );
    $msg = array (
        'registration_ids' => $arrayresultados,
        'notification' => array (
            "body" => "¡Entra en Gartxon y enterate de todas las novedades!",
            "title" => "Nuevas ofertas",
            "icon" => "ic_stat_ic_notification"
        )
    );
    $msgJSON= json_encode ($msg);

    $ch = curl_init(); #inicializar el handler de curl
    #indicar el destino de la petición, el servicio FCM de google
    curl_setopt( $ch, CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send');
    #indicar que la conexión es de tipo POST
    curl_setopt( $ch, CURLOPT_POST, true );
    #agregar las cabeceras
    curl_setopt( $ch, CURLOPT_HTTPHEADER, $cabecera);
    #Indicar que se desea recibir la respuesta a la conexión en forma de string
    curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
    #agregar los datos de la petición en formato JSON
    curl_setopt( $ch, CURLOPT_POSTFIELDS, $msgJSON );
    #ejecutar la llamada
    $resultado= curl_exec( $ch );
    #cerrar el handler de curl
    curl_close( $ch );

    if (curl_errno($ch)) {
        print curl_error($ch);
    }
    echo $resultado;
?>




























