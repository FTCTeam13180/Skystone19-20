package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="BlueBuildingZoneBridge", group="autonomusGroup1")
public class BuildingZoneFullAuto extends LinearOpMode {
    private RoboNavigator robotNavigator;
    private Hook hook;
    private Grabber grab;
    private WinchOut winchOut;
    private WinchUp winchUp;

    @Override
    public void runOpMode() {
        robotNavigator = new RoboNavigator(this);
        robotNavigator.init();
        hook = new Hook(this);
        hook.init();
        grab = new Grabber(this);
        grab.init();
        winchOut = new WinchOut(this);
        winchOut.init();
        winchUp = new WinchUp(this);
        winchUp.init();

        double NAVIGATOR_POWER = 0.5; // check

        // to set position before start
        hook.detach();
        //wait for start
        waitForStart();


        while (opModeIsActive()) {




            //goes up to the foundation to move it into the building zone
            //winchUp.encoderDrive(NAVIGATOR_POWER,10*2.54,1000);

            //winch down
            hook.halfattach(0.5);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 29* 2.54, 5000);
            sleep(1000);

            //HOOK comes down heref

            hook.attach();

            sleep(1500);

            //pulls the foundation to the triangle build ZONE
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.TURN_RIGHT, NAVIGATOR_POWER, 210, 3000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD,NAVIGATOR_POWER,40*2.54,2000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT,NAVIGATOR_POWER,36*2.54,2000);
            sleep(1000);
            hook.detach();
            sleep(1000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,5*2.54,2000);
            hook.attach();
            winchOut.goOutTime(-0.6,2600);
            hook.detach();
            winchUp.encoderDrive(NAVIGATOR_POWER,5*2.54,1000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,36*2.54,2000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT,NAVIGATOR_POWER,24*2.54,2000);
            //robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD,NAVIGATOR_POWER,20*2.54,2000);

            // winchOut.goOutTime(0.6, 1000);
            //winchUp.goDownTime(0.6, 2000);
            //robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 24, 10000);
            //winchOut.goOutTime(0.6, 2000);
            /*robotNavigator.encoderDrive(RoboNav0igator.DIRECTION.BACKWARD, NAVIGATOR_POWER, 44*2.54, 10000);
            //going around the foundation to the other side of it
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 35*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 12*2.54, 10000);
            //pushes the foundation completely into the building zone
            //8 in to the wall of                                                      the build zone (Driver station side)
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 35*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 5*2.54, 10000);

            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 20*2.54, 10000);
            //ROBOT IS PARKED UNDER THE ALLIANCE SKYBRIDGE*/

            break;


        }
    }
}

