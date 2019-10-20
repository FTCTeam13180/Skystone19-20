package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="BuildingZoneAuto", group="autonomusGroup1")
public class BuildingZoneAuto extends LinearOpMode {
    private RoboNavigator robotNavigator;
    private Servo servo_hook;
    @Override
    public void runOpMode() {
        robotNavigator = new RoboNavigator(this);
        robotNavigator.init();
        double NAVIGATOR_POWER = 0.5; // check
        servo_hook=hardwareMap.get(Servo.class,"servo_hook");
        waitForStart();
        while (opModeIsActive()) {
            //goes up to the foundation to move it into the building zone
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD, NAVIGATOR_POWER, 30.5*2.54, 10000);

            //HOOK comes down here
            servo_hook.setPosition(0.50);

            //pulls the foundation to the triangle build ZONE
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 28*2.54, 10000);

            servo_hook.setPosition(0.0);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 30*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD, NAVIGATOR_POWER, 44*2.54, 10000);
            //going around the foundation to the other side of it
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 35*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 12*2.54, 10000);
            //pushes the foundation completely into the building zone
            //8 in to the wall of the build zone (Driver station side)
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 35*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 5*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 20*2.54, 10000);
            //ROBOT IS PARKED UNDER THE ALLIANCE SKYBRIDGE


        }
    }
}
