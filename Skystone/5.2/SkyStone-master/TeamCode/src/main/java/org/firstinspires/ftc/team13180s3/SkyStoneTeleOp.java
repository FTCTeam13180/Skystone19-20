package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SkyStoneTeleOp", group = "manualmode")
public class SkyStoneTeleOp extends LinearOpMode {
    private RoboNavigator roboNav;
    public void runOpMode (){
        roboNav = new RoboNavigator(this);
        roboNav.init();
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.left_stick_x > 0)
                roboNav.moveForward(0.5);
            else if(gamepad1.left_stick_x < 0){
                roboNav.moveBackward(0.5);
            }


        }
    }

}
