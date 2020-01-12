package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class LoadingZoneFullAuto {
    private LinearOpMode opMode;
    private RoboNavigator robotNavigator;
    private Hook hook;
    private Grabber grab;
    private Elevator elevator;
    private Alliance alliance;
    private Parking parking;
    private double NAVIGATOR_POWER=0.5;
    public enum Alliance {
        BLUE,
        RED
    }

    public enum Parking {
        BRIDGE,
        WALL
    }

    LoadingZoneFullAuto (LinearOpMode op, Alliance al, Parking pa) {
        this.opMode = op;
        this.alliance = al;
        this.parking = pa;
    }

    public void run() {
        robotNavigator = new RoboNavigator(opMode);
        robotNavigator.init();
        robotNavigator.setNavigatorPower(1.0);
        hook = new Hook(opMode);
        hook.init();
        hook.detach();

        grab = new Grabber(opMode);
        grab.init();

        elevator = new Elevator(opMode);
        elevator.init();


//        hook.detach();


        opMode.telemetry.addLine("Front of Robot Should be forward");
        opMode.telemetry.update();

        elevator.playposition();
        grab.rotateToDegrees_0();
 //       grab.release();
        robotNavigator.moveForward(24,4000);
/*  *** SKYSTONE DETECTION- ADD LATER ***
            detect.activate();
            if(detect.scan()){
                robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
                grab.grabIn();
            }
            else{
                robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
            }
*/

        grab.grabIn();
        opMode.sleep(200);

        robotNavigator.moveBackward(8,10000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        robotNavigator.moveForward(65,10000);
        elevator.goUpByRotations(1,1.3);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(90,5000);
        else
            robotNavigator.turnLeft(90,5000);
        robotNavigator.moveForward(6.5,5000);
        grab.release();
        opMode.sleep(100);

        robotNavigator.moveBackward(6.5,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(180,5000);
        else
            robotNavigator.turnRight(180,5000);
        robotNavigator.moveBackward(6.5,5000);
       //SHIVAMS ROTATION PORTION
        hook.attach();
        opMode.sleep(200);
        robotNavigator.moveForward(12,5000);
        double turn_degrees = 190;
        if (alliance == Alliance.BLUE) {
            robotNavigator.shiftLeft(12, 10000);
            robotNavigator.turnLeft(turn_degrees, 10000);
            robotNavigator.shiftRight(24,1000);
        } else {
            robotNavigator.shiftRight(12, 10000);
            robotNavigator.turnRight(turn_degrees, 10000);
            robotNavigator.shiftLeft(24,1000);
        }

        // Pushing foundation into depot

        robotNavigator.moveBackward(34, 10000);
        /*
         * Detach from the foundation
         */
        opMode.sleep(200);
        hook.detach();
        opMode.sleep(200);
        robotNavigator.moveForward(20,5000);

        //SHIVAMS ROTATION --------------------------------------------
/*---------------------------------------------
        elevator.goDownByRotations(1,0.8);
        robotNavigator.moveBackward(65+7.75,10000);

        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(90,5000);
        else
            robotNavigator.turnLeft(90,5000);


        robotNavigator.moveForward(3,5000);
        grab.grabIn();
        opMode.sleep(200);

        robotNavigator.moveBackward(5,5000);
        //
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        robotNavigator.moveForward(65+7.75,10000);

        elevator.goUpByRotations(1,1.8);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(90,5000);
        else
            robotNavigator.turnLeft(90,5000);
        robotNavigator.moveForward(12,5000);
        grab.release();
        opMode.sleep(200);

        robotNavigator.moveBackward(12,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        elevator.goDownByRotations(1,1.4);
        robotNavigator.moveBackward(110,10000);
*/
        //-----------------------------
        if(parking==Parking.BRIDGE) {
            if (alliance == Alliance.BLUE)
                robotNavigator.shiftLeft(5, 10000);
            else
                robotNavigator.shiftRight(5,10000);
        }
//        elevator.homeposition();
//        opMode.sleep(200);
        robotNavigator.moveForward(54,10000);
    }

}