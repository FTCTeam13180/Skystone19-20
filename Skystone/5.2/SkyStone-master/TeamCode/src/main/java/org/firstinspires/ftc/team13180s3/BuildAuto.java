package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;


@Autonomous(name="BuildAuto", group="autonomusGroup1")
public class BuildAuto extends LinearOpMode {
    private RoboNavigator robotNavigator;

    @Override
    public void runOpMode () {
        robotNavigator = new RoboNavigator(this) ;
        robotNavigator.init() ;
        double NAVIGATOR_POWER = 0.5; // check

        //waitforstart() ;

        try {
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, 0.5, 36, 10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 15, 10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 40, 10000);
        } catch(Exception e) {
        }
    }
}