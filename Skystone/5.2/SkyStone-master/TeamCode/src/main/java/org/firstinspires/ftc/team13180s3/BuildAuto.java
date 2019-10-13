package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="BuildAuto", group="autonomusGroup1")
public class BuildAuto extends LinearOpMode {
    private RoboNavigator robotNavigator;

    @Override
    public void runOpMode() {
        robotNavigator = new RoboNavigator(this);
        robotNavigator.init();
        double NAVIGATOR_POWER = 0.5; // check
        waitForStart();
        while (opModeIsActive()) {
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 31.25*2.54, 10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 31.25*2.54, 10000);
            //pulls the foundation to the triangle build tape zone
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 20*2.54, 10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 12*2.54, 10000);
            //shifts left to align with bridge, haven't crossed yet
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 64*2.54, 10000);
            //goes under bridge
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 24*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.TURN_LEFT, NAVIGATOR_POWER, 90, 10000);
            //HERE IS WHERE YOU PUT IN THE BLOCK SCANNING CODE
            //HERE IS WHERE YOU PUT IN THE BLOCK SCANNING CODE
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 24*2.54, 10000);
            //scanning the blocks
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 36*2.54, 10000);
            //go to the other side of the block line
            //now we assume that the robot has detected a skystone
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 12*2.54, 10000);
            //GRipper takes block in
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 96*2.54, 10000);
            //goes back to building zone
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD, NAVIGATOR_POWER, 24*2.54, 10000);
            //MOVES NEXT TO the foundation
            //code to deposit the skystone in foundation




        }
    }
}
