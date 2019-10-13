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


        }
    }

}
