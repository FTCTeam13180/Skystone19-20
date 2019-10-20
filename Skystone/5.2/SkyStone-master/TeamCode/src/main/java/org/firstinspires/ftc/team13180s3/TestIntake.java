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
    private Intake intake;

    double power=0.6;
    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(this);
        intake.init();

        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.left_bumper){
               intake.startIntake(power);
            }
            else if(gamepad1.right_bumper){
                intake.startIntake(-power);
            }
            else{
                intake.stopIntake();
            }
        }
    }
}
