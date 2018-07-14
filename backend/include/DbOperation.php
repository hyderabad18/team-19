<?php

class DbOperation
{
    private $con;

    function __construct()
    {
        require_once dirname(__FILE__) . '/DbConnect.php';
        $db = new DbConnect();
        $this->con = $db->connect();
    }


    //Method to register a new student
    public function addUser($name,$des,$phone,$locality,$pass){
        if (!$this->isUserExists($name,$pass)) {


            //$des="volunteer";
            $stmt = $this->con->prepare("INSERT INTO user(name,designation,phone,locality,password) values(?, ?, ?, ?, ?)");
            $stmt->bind_param("sssss", $name, $des,$phone,$locality,$pass);

            $result = $stmt->execute();
            $stmt->close();

            if ($result) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }


    //Method to check the student username already exist or not
    private function isUserExists($name,$pass) {
        $stmt = $this->con->prepare("SELECT uid from user WHERE name = ? and password=?");
        $stmt->bind_param("ss", $name,$pass);
        $stmt->execute();
        $stmt->store_result();
        $num_rows = $stmt->num_rows;
        $stmt->close();
        return $num_rows > 0;
    }

    public function createEvent($eventname,$startdate,$enddate,$description,$locality,$trainee,$status){



            //$des="volunteer";
            $stmt = $this->con->prepare("INSERT INTO event(eventname,startdate,enddate,description,locality,trainees,status) values(?, ?, ?, ?, ?,?,?)");
            echo "DB insert start\n";

            $stmt->bind_param("sssssss", $eventname, $startdate,$enddate,$description,$locality,$trainee,$status);

            $result = $stmt->execute();
            print $stmt->error;

            $stmt->close();

            if ($result) {
                return 0;
            } else {
                return 1;
            }

    }

    //Method to generate a unique api key every time

}
