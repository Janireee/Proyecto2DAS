<?php 

$message = "Bienvenido a tu JaniGaming. Gracias por confiar en nosotros.";
$title = "Te has registrado correctamente :)";
$path_to_fcm = 'https://fcm.googleapis.com/fcm/send';
$server_key = "AAAApDcvgq0:APA91bFmhwpjVJ3UR1z1XyTsUO1-38F7mdxRlMjrBOmki3OBAD_Xs3l76QE-L_1jSMTG1IFa6MSeiSqHGP6thtXzui8oyggLE5es_4Mx_vcXGWXEDYdIhxb8tV7YI1IFSIX10mOxAphx";
$token=$_POST["tokenID"];
$deviceToken = "dilayW7KTs-SiqWq1hXASi:APA91bEeaLi6kGQer3CLwUOIVwU79L0xBJ2Vv5r6yPmeI86CU4iwHQ_c9dv1v480HjxaJCf9iUJTw_dCcCo_0MP8hopwJ-gc-azzx-O5R8xMsnuIAMzqUJ-9ndS3KXBScTZUzT4fPUjK";

if($token ==null ){
    $token = $deviceToken;
}

$headers = array(
    'Authorization:key=' .$server_key,
    'Content-Type:application/json'
);

$fields = array('to'=>$deviceToken,
    'notification'=>array('title'=>$title,'body'=>$message));

$payload = json_encode($fields);

echo $payload;

$curl_session = curl_init();
curl_setopt($curl_session, CURLOPT_URL, $path_to_fcm);
curl_setopt($curl_session, CURLOPT_POST, true);
curl_setopt($curl_session, CURLOPT_HTTPHEADER, $headers);
curl_setopt($curl_session, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($curl_session, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4 );
curl_setopt($curl_session, CURLOPT_POSTFIELDS, $payload);
$result = curl_exec($curl_session);
echo $result;

?>