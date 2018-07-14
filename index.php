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

$app->post('/registerVolunteer', function () use ($app) {
    verifyRequiredParams(array('name', 'phone','locality', 'password'));
    $response = array();
    $name = $app->request->post('name');
    $phone = $app->request->post('phone');
    $locality = $app->request->post('locality');
    $password = $app->request->post('password');
    $db = new DbOperation();
    $res = $db->addUser($name, $phone,$locality, $password);
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




$app->post('/Adminlogin', function() use ($app){
    verifyRequiredParams(array('username','password'));
    $username = $app->request->post('username');
    $password = $app->request->post('password');
  if($username=="admin" && $password=="admin"){
    $db = new DbOperation();

    $response = array();

    if($db->facultyLogin($username,$password)){
        $admin = $db->getAdmin($username);
        $response['error'] = false;
        $response['uid'] = $admin['uid'];
        $response['name'] = $admin['name'];
        $response['username'] = $admin['username'];
        $response['subject'] = $admin['subject'];
        $response['apikey'] = $admin['api_key'];
    }else{
        $response['error'] = true;
        $response['message'] = "Invalid username or password";
    }
  }
    echoResponse(200,$response);
});


/* *
 * URL: http://localhost/StudentApp/v1/createassignment
 * Parameters: name, details, facultyid, studentid
 * Method: POST
 * 
$app->post('/createassignment',function() use ($app){
    verifyRequiredParams(array('name','details','facultyid','studentid'));

    $name = $app->request->post('name');
    $details = $app->request->post('details');
    $facultyid = $app->request->post('facultyid');
    $studentid = $app->request->post('studentid');

    $db = new DbOperation();

    $response = array();

    if($db->createAssignment($name,$details,$facultyid,$studentid)){
        $response['error'] = false;
        $response['message'] = "Assignment created successfully";
    }else{
        $response['error'] = true;
        $response['message'] = "Could not create assignment";
    }

    echoResponse(200,$response);

});
*/
/* *
 * URL: http://localhost/StudentApp/v1/assignments/<student_id>
 * Parameters: none
 * Authorization: Put API Key in Request Header
 * Method: GET
 * 
$app->get('/assignments/:id', 'authenticateStudent', function($student_id) use ($app){
    $db = new DbOperation();
    $result = $db->getAssignments($student_id);
    $response = array();
    $response['error'] = false;
    $response['assignments'] = array();
    while($row = $result->fetch_assoc()){
        $temp = array();
        $temp['id']=$row['id'];
        $temp['name'] = $row['name'];
        $temp['details'] = $row['details'];
        $temp['completed'] = $row['completed'];
        $temp['faculty']= $db->getFacultyName($row['faculties_id']);
        array_push($response['assignments'],$temp);
    }
    echoResponse(200,$response);
});
*/

/* *
 * URL: http://localhost/StudentApp/v1/submitassignment/<assignment_id>
 * Parameters: none
 * Authorization: Put API Key in Request Header
 * Method: PUT
 * 

$app->put('/submitassignment/:id', 'authenticateFaculty', function($assignment_id) use ($app){
    $db = new DbOperation();
    $result = $db->updateAssignment($assignment_id);
    $response = array();
    if($result){
        $response['error'] = false;
        $response['message'] = "Assignment submitted successfully";
    }else{
        $response['error'] = true;
        $response['message'] = "Could not submit assignment";
    }
    echoResponse(200,$response);
});
*/

/* *
 * URL: http://localhost/StudentApp/v1/students
 * Parameters: none
 * Authorization: Put API Key in Request Header
 * Method: GET
 * */
$app->get('/getEnrolled', function() use ($app){
    $db = new DbOperation();
    $result = $db->getAllEnrolledEvents();
    $response = array();
    $response['error'] = false;
    $response['events'] = array();

    while($row = $result->fetch_assoc()){
        $temp = array();
        $temp['uid'] = $row['uid'];
        $temp['eid'] = $row['eid'];
        $temp['status'] = $row['status'];
		$temp['locality'] = $row['locality'];
        array_push($response['events'],$temp);
    }

    echoResponse(200,$response);
});


$app->get('/getUpcoming', function() use ($app){
    $db = new DbOperation();
    $result = $db->getUpcomingEvents();
    $response = array();
    $response['error'] = false;
    $response['upevents'] = array();

    while($row = $result->fetch_assoc()){
        $temp = array();
        $temp['eid'] = $row['eid'];
        $temp['eventname'] = $row['eventname'];
		$temp['description'] = $row['description'];
        $temp['startdate'] = $row['startdate'];
		$temp['enddate'] = $row['enddate'];
        array_push($response['events'],$temp);
    }

    echoResponse(200,$response);
});

$app->get('/getEventsDone', function() use ($app){
    $db = new DbOperation();
    $result = $db->getEventsDone();
    $response = array();
    $response['error'] = false;
    $response['eventsdone'] = array();

    while($row = $result->fetch_assoc()){
        $temp = array();
        $temp['eid'] = $row['eid'];
        $temp['eventname'] = $row['eventname'];
		$temp['description'] = $row['description'];
        $temp['startdate'] = $row['startdate'];
		$temp['enddate'] = $row['enddate'];
		$temp['hours'] = $row['hours'];
        array_push($response['events'],$temp);
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

$app->run();
