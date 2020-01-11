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
        robotNavigator.setNavigatorPower(0.8);
        hook = new Hook(opMode);
        hook.init();
        hook.detach();

        grab = new Grabber(opMode);
        grab.init();

        elevator = new Elevator(opMode);
        elevator.init();


        hook.detach();


        opMode.telemetry.addLine("Front of Robot Should be forward");
        opMode.telemetry.update();
        opMode.sleep(100);
        elevator.playposition();
        grab.rotateToDegrees_180();
        grab.release();
        elevator.goUpByRotations(1,1.3);
        robotNavigator.moveForward(20,4000);
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

        opMode.sleep(500);
        elevator.goDownByRotations(1,1.3);
        opMode.sleep(400);
        grab.grabIn();
        opMode.sleep(400);
        robotNavigator.moveBackward(5,10000);
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
        robotNavigator.moveForward(15,5000);
        elevator.goDownByRotations(1,1.3);
        grab.release();
        robotNavigator.moveBackward(15,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        robotNavigator.moveBackward(65+7.75,10000);
        elevator.goUpByRotations(1,1.3);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(90,5000);
        else
            robotNavigator.turnLeft(90,5000);


        opMode.sleep(500);
        robotNavigator.moveForward(2,5000);
        elevator.goDownByRotations(1,1.3);
        opMode.sleep(400);
        grab.grabIn();
        opMode.sleep(400);
        robotNavigator.moveBackward(5,5000);
        //
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        robotNavigator.moveForward(65+7.75,10000);

        elevator.goUpByRotations(1,2);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(90,5000);
        else
            robotNavigator.turnLeft(90,5000);
        robotNavigator.moveForward(17,5000);
        grab.release();
        robotNavigator.moveBackward(17,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        elevator.goDownByRotations(1,2);
        robotNavigator.moveBackward(110,10000);
        opMode.sleep(4000);






        if(parking==Parking.BRIDGE) {
            if (alliance == Alliance.BLUE)
                robotNavigator.shiftLeft(5, 10000);
            else
                robotNavigator.shiftRight(5,10000);
        }
        elevator.homeposition();
        opMode.sleep(5000);
        robotNavigator.moveForward(54,10000);
    }

}