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
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,-3*2.54,200);
        grab.release();
        elevator.goOutByRotations(0.8,3.5);
        robotNavigator.moveForward(25,4000);
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
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,6*2.54,2200);
        opMode.sleep(400);
        grab.grabIn();
        opMode.sleep(400);
        robotNavigator.moveBackward(15,10000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(100,5000);
        else
            robotNavigator.turnRight(100,5000);
        robotNavigator.moveForward(72,10000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,-6*2.54,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(100,5000);
        else
            robotNavigator.turnLeft(100,5000);
        robotNavigator.moveForward(5,5000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,3*2.54,5000);
        grab.release();
        robotNavigator.moveBackward(5,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(100,5000);
        else
            robotNavigator.turnRight(100,5000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,3*2.54,5000);
        robotNavigator.moveBackward(72+7.75,10000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,-8.5*2.54,2200);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(100,5000);
        else
            robotNavigator.turnLeft(100,5000);


        opMode.sleep(500);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,-6*2.54,2200);
        robotNavigator.moveForward(2,5000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,3*2.54,2200);
        opMode.sleep(400);
        grab.grabIn();
        opMode.sleep(400);
        robotNavigator.moveBackward(5,5000);
        //
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(100,5000);
        else
            robotNavigator.turnRight(100,5000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,3*2.54,2200);
        robotNavigator.moveForward(72+7.75,10000);

        elevator.upDownEncoderDrive(NAVIGATOR_POWER,-7.5*2.54,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(100,5000);
        else
            robotNavigator.turnLeft(100,5000);
        robotNavigator.moveForward(7,5000);
        grab.release();
        robotNavigator.moveBackward(7,5000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(100,5000);
        else
            robotNavigator.turnRight(100,5000);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER,7*2.54,2200);
        robotNavigator.moveBackward(110,10000);






        if(parking==Parking.BRIDGE) {
            if (alliance == Alliance.BLUE)
                robotNavigator.shiftLeft(5, 10000);
            else
                robotNavigator.shiftRight(5,10000);
        }
        opMode.sleep(5000);
        robotNavigator.moveForward(54,10000);
    }
    public void HomePosition(){
        grab.rotateToDegrees_180();
        grab.grabIn();
        elevator.upDownEncoderDrive(NAVIGATOR_POWER, elevator.height-5,10000);
        elevator.goInByRotations(0.4,4);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER, elevator.height,10000);


    }
}