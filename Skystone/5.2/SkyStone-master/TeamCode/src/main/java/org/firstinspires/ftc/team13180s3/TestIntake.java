package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name="TestIntake", group="manualmode")
public class TestIntake extends LinearOpMode {
    public LinearOpMode opMode;
    public DcMotor LeftIntake;
    public DcMotor RightIntake;
    double power=0.6;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        LeftIntake=hardwareMap.get(DcMotor.class,"Lintake");
        RightIntake=hardwareMap.get(DcMotor.class,"Rintake");
        while (opModeIsActive()){
            if(gamepad1.left_bumper){
                LeftIntake.setPower(power);
                RightIntake.setPower(-1*power);
            }
            else if(gamepad1.right_bumper){
                LeftIntake.setPower(-1*power);
                RightIntake.setPower(power);
            }
            else{
                LeftIntake.setPower(0);
                RightIntake.setPower(0);
            }

        }




    }
}
