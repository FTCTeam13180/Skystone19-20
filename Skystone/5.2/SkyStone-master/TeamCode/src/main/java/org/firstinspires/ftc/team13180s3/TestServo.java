package org.firstinspires.ftc.team13180s3;
//Rohan Gulati -10/5/2019
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="TestServo", group="manualmode")
public class TestServo extends LinearOpMode{
    public LinearOpMode opMode;
    private CRServo TestServo;
    private Servo Test1;

    public void runOpMode()  {
        TestServo=opMode.hardwareMap.get(CRServo.class,"Ser");
        while (opModeIsActive()){

            double rx=gamepad1.left_stick_x;
            if(rx>0) {
                TestServo.setPower(0.6);
            }
            else if(rx<0) {
                TestServo.setPower(-0.6);
            }
            else{
                TestServo.setPower(0);
            }


        }
    }
}
