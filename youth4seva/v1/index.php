<?php

//including the required files
require_once '../include/DbOperation.php';
require '.././libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

/* *
 * URL: http://localhost/StudentApp/v1/createstudent
 * Parameters: name, username, password
 * Method: POST
 * */
$app->post('/registerVolunteer', function () use ($app) {
    verifyRequiredParams(array('name','designation', 'phone','locality', 'password'));
    $response = array();
    $name = $app->request->post('name');
    $des = $app->request->post('designation');
    $phone = $app->request->post('phone');
    $locality = $app->request->post('locality');
    $password = $app->request->post('password');
    $db = new DbOperation();
    $res = $db->addUser($name,$des, $phone,$locality, $password);
    if ($res == 0) {
        $response["error"] = false;
        $response["message"] = "You are successfully registered";
        echoResponse(201, $response);
    } else if ($res == 1) {
        $response["error"] = true;
        $response["message"] = "Oops! An error occurred while registereing";
        echoResponse(200, $response);
    } else if ($res == 2) {
        $response["error"] = true;
        $response["message"] = "Sorry, this user  already existed";
        echoResponse(200, $response);
    }
});

$app->get('/getEvents', function() use ($app){
    $db = new DbOperation();
    $result = $db->getAllEvents();
    $response = array();
    $response['error'] = false;
    $response['students'] = array();

    while($row = $result->fetch_assoc()){
        $temp = array();
        $temp['id'] = $row['eid'];
        $temp['name'] = $row['eventname'];
        $temp['startdate'] = $row['startdate'];
        $temp['enddate'] = $row['enddate'];
        $temp['description'] = $row['description'];
        $temp['locality'] = $row['locality'];
        $temp['status'] = $row['status'];
        array_push($response['students'],$temp);
    }

    echoResponse(200,$response);
});

function echoResponse($status_code, $response)
{
    $app = \Slim\Slim::getInstance();
    $app->status($status_code);
    $app->contentType('application/json');
    echo json_encode($response);
}


function verifyRequiredParams($required_fields)
{
    $error = false;
    $error_fields = "";
    $request_params = $_REQUEST;

    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }

    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }

    if ($error) {
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoResponse(400, $response);
        $app->stop();
    }
}
$app->post('/createEvent', function () use ($app) {
    verifyRequiredParams(array('eventname', 'startdate', 'enddate','description','locality','trainee','status'));
    $response = array();
    $eventname = $app->request->post('eventname');
    $startdate = $app->request->post('startdate');
    $enddate = $app->request->post('enddate');
	  $description = $app->request->post('description');
    $locality = $app->request->post('locality');
	$trainee = $app->request->post('trainee');
	$status = $app->request->post('status');
    $db = new DbOperation();
    $res = $db->createEvent($eventname, $startdate, $enddate,$description,$locality,$trainee,$status);
    if ($res == 0) {
        $response["error"] = false;
        $response["message"] = "You are successfully registered";
        echoResponse(201, $response);
    } else if ($res == 1) {
        $response["error"] = true;
        $response["message"] = "Oops! An error occurred while registereing";
        echoResponse(200, $response);
    } else if ($res == 2) {
        $response["error"] = true;
        $response["message"] = "Sorry, this event already existed";
        echoResponse(200, $response);
    }
});

/* *
 * URL: http://localhost/StudentApp/v1/studentlogin
 * Parameters: username, password
 * Method: POST
 * */
$app->post('/addDayActivity', function () use ($app) {
    verifyRequiredParams(array('uid', 'eid', 'checkin','checkout','date'));
    $response = array();
    $uid = $app->request->post('uid');
    $eid = $app->request->post('eid');
    $checkin = $app->request->post('checkin');
	$checkout = $app->request->post('checkout');
    $date = $app->request->post('date');
    $db = new DbOperation();
    $res = $db->createEvent($uid, $eid, $checkin,$checkout,$date);
    if ($res == 0) {
        $response["error"] = false;
        $response["message"] = "You are successfully registered";
        echoResponse(201, $response);
    } else if ($res == 1) {
        $response["error"] = true;
        $response["message"] = "Oops! An error occurred while registereing";
        echoResponse(200, $response);
    } else if ($res == 2) {
        $response["error"] = true;
        $response["message"] = "Sorry, this event already existed";
        echoResponse(200, $response);
    }
});

$app->post('/adminlogin', function() use ($app){
    verifyRequiredParams(array('username','password'));
    $username = $app->request->post('username');
    $password = $app->request->post('password');

    $db = new DbOperation();
    $response = array();
    if($username=="admin" && $password=="admin"){

        $response['error'] = false;
        $response['message'] = "welcome admin!";
    }else{
        $response['error'] = true;
        $response['message'] = "Invalid username or password";
    }

    echoResponse(200,$response);
});
$app->post('/volunteerLogin', function() use ($app){
    verifyRequiredParams(array('username','password'));
    $username = $app->request->post('username');
    $password = $app->request->post('password');
    $db = new DbOperation();
    $response = array();
    if($db->isUserExists($username,$password)){
        $volunteer = $db->getVolunteer($username,$password);
        $response['error'] = false;
        $response['uid'] = $volunteer['uid'];
        $response['name'] = $volunteer['name'];
        $response['phone'] = $volunteer['phone'];
        $response['locality'] = $volunteer['locality'];
    }else{
        $response['error'] = true;
        $response['message'] = "Invalid username or password";
    }
    echoResponse(200,$response);
});


$app->run();
