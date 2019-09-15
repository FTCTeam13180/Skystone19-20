package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="TestServo", group="manualmode")
public class TestServo extends LinearOpMode{
    public LinearOpMode opMode;
    private CRServo TestServo;

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
