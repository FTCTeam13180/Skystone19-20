package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestEncoderDrive", group = "manualmode")
public class TestEncoderDrive extends LinearOpMode {
    public LinearOpMode opMode;
    private RoboNavigator robonav;
    public void runOpMode() throws InterruptedException {
        robonav=new RoboNavigator(this);
        robonav.init();
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.dpad_up){
                robonav.encoderDrive(RoboNavigator.DIRECTION.FORWARD,0.6,24*2.54,10000);
            }
            else if(gamepad1.dpad_down){
                robonav.encoderDrive(RoboNavigator.DIRECTION.FORWARD,0.6,24*2.54,10000);
            }
        }
    }
}
