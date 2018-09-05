<?php
include('dbconnection.php');

$mobile = $_POST["mobile"];


$statement = mysqli_query($con,"SELECT count(*),CONCAT(Student_First_Name, ' ', Student_Middle_Name, ' ',Student_Last_Name) AS FIRSTNAME, Admitted_Scholar_ID,Class,ClassSC FROM ScholarRegister,mstCLASS WHERE SMS_Mob_Number = '$mobile' ANd  ScholarRegister.Class= mstCLASS.ClassID GROUP BY ClassSC  ASC
HAVING COUNT(*) > 1 ");

$response = array();
$looparry = array();

$count =0;
$loop=0;
if($statement)
{
         
       while($row = mysqli_fetch_array($statement)) 
   {
       array_push($response,array(
           
           's_name'=>$row['FIRSTNAME'],
           'scholar_id'=>$row['Admitted_Scholar_ID'],
           'class_id'=>$row['ClassSC'],
           'class_no'=>$row['Class']
           ));
       
    }
     /*$looparry["loop"] = $loop;
     echo json_encode($response);*/
      echo json_encode(array('result'=>$response));
     mysqli_close($con);
}
else
{
    $response["success"] = false;
    $response["count"] = $count;
    echo json_encode($response);
    mysqli_close($con);
}
?>