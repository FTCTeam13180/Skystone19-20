package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;


public class LoadingZoneFullAuto {
    private LinearOpMode opMode;
    private RoboNavigator robotNavigator;
    private Hook hook;
    private Grabber grab;
    private Detector detect;
    private Elevator elevator;
    private Alliance alliance;
    private Parking parking;
    private double NAVIGATOR_POWER=0.5;
    boolean foundSkytone = false;
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

    private int skystone() {
        List<Recognition> blocks = detect.scan();
        int numblocks = blocks.size();
        int i = 0;
        if (numblocks < 2) {
            if(alliance == Alliance.RED){
                robotNavigator.shiftRight(3, 1000);
            }
            else{
                robotNavigator.shiftLeft(3, 1000);
            }

            blocks = detect.scan();
            numblocks = blocks.size();
        }
        if (numblocks == 2) {
            for (Recognition recog : blocks) {
                if (recog.getLabel().equalsIgnoreCase(Detector.LABEL_SECOND_ELEMENT)) {


                    //second closest to bridge if i == 1;
                    if (i == 1) {
                        opMode.telemetry.addLine("Position 2");
                        if(alliance == Alliance.RED){
                            robotNavigator.shiftRight(5, 1000);
                        }
                        else{
                            robotNavigator.shiftLeft(5, 1000);
                        }
                        foundSkytone = true;
                        opMode.telemetry.addData("Found skytone. i=", i);
                        break;
                    }
                    //Closer to bridge if i == 0
                    else if (i == 0) {
                        opMode.telemetry.addLine("Position 3");
                        if(alliance ==Alliance.RED){
                            robotNavigator.shiftRight(20, 1000);
                        }
                        else{
                            robotNavigator.shiftLeft(20,1000);
                        }
                        foundSkytone = true;
                        opMode.telemetry.addData("found skytone i=", i);
                        break;
                    }
                }
                i++;
            }
            if (foundSkytone) {
                opMode.telemetry.update();
                robotNavigator.moveForward(15, 10000);
            } else {
                if(alliance== Alliance.RED){
                robotNavigator.shiftLeft(6, 1000);
                }
                else{
                    robotNavigator.shiftRight(6,1000);
                }
                robotNavigator.moveForward(18, 10000);
            }
        }
        return i;
    }
    public void init(){
        robotNavigator = new RoboNavigator(opMode);
        robotNavigator.init();
        robotNavigator.setNavigatorPower(1);
        hook = new Hook(opMode);
        hook.init();
        hook.detach();

        grab = new Grabber(opMode);
        grab.init();
        grab.release();

        elevator = new Elevator(opMode);
        elevator.init();

        detect = new Detector(opMode);
        detect.init();



        opMode.telemetry.addLine("Front of Robot Should be forward");
        opMode.telemetry.update();
    }
    public void run() {
        elevator.playposition();
        grab.rotateToDegrees_0();
        robotNavigator.moveForward(6,1000);
        int position=skystone();
        grab.grabIn();
        robotNavigator.moveBackward(3,1000);
        if(alliance==Alliance.BLUE){
            robotNavigator.turnLeft(90,5000);
        }
        else{
            robotNavigator.turnRight(90,5000);
        }
        //moving to foundation after gettign first skystone based on position
        if(position==0){
            robotNavigator.moveForward(95,50000);
        }
        else if(position==1){
            robotNavigator.moveForward(84,50000);
        }
        else{
            robotNavigator.moveForward(69,50000);
        }


        if(alliance==Alliance.BLUE)
            robotNavigator.turnRight(90,5000);
        else
            robotNavigator.turnLeft(90,5000);

        elevator.goUpByRotations(1,1.6);
        hook.halfattach(.5);
        robotNavigator.moveForward(24,10000);
        hook.attach();
        robotNavigator.setNavigatorPower(0.4);
        robotNavigator.moveForward(1.5,5000);
        robotNavigator.setNavigatorPower(1.0);
        grab.release();
        int turnAngle=90;
        //have to test turn angle
        if(alliance == Alliance.RED){
            robotNavigator.shiftLeft(12,1000);
            robotNavigator.turnRight(turnAngle,1000);
        }
        else{
            robotNavigator.shiftRight(12,1000);
            robotNavigator.turnLeft(turnAngle,1000);
        }
        hook.detach();
        robotNavigator.moveForward(48,1000);
        robotNavigator.moveBackward(5,1000);
        elevator.goDownByRotations(1,1.6);
        if(position==0){
            robotNavigator.moveBackward(104,100);
        }
        else{
            robotNavigator.moveBackward(112,1000);
        }

        if(alliance==Alliance.BLUE){
            robotNavigator.turnRight(90,5000);
        }
        else{
            robotNavigator.turnLeft(90,5000);
        }
        robotNavigator.moveForward(4,1000);
        grab.grabIn();
        if(alliance==Alliance.BLUE){
            robotNavigator.turnLeft(90,5000);
        }
        else{
            robotNavigator.turnRight(90,5000);
        }
        if(position==0){
            robotNavigator.moveForward(69,50000);

        }
        if(position==1){
            robotNavigator.moveForward(84,50000);
        }
        if(position==2){
            robotNavigator.moveForward(95,50000);
        }
        elevator.goUpByRotations(1,1.6);
        robotNavigator.moveForward(65,1000);
        grab.release();
        robotNavigator.moveBackward(5,1000);
        elevator.goDownByRotations(1,1.6);
        robotNavigator.moveBackward(50,1000);


 //       grab.release();
        //robotNavigator.moveForward(24,4000);
/*  *** SKYSTONE DETECTION- ADD LATER ***
            detect.activate();
            if(detect.scan()){
                robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
                grab.grabIn();
            }
            else{
                robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
            }


        grab.grabIn();
        opMode.sleep(100);
        robotNavigator.moveBackward(3,10000);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(90,5000);
        else
            robotNavigator.turnRight(90,5000);
        robotNavigator.moveForward(69,10000);
        elevator.goUpByRotations(1,1.6);
        if(alliance==Alliance.BLUE){
            robotNavigator.turnRight(90,5000);
        }
        else{
            robotNavigator.turnLeft(90,5000);
        }
        robotNavigator.moveForward(8.5,5000);
        //elevator.goDownByRotations(1,0.8);
        grab.release();
        opMode.sleep(100);

        robotNavigator.moveBackward(4,5000);
        grab.rotateToDegrees_180();
        elevator.goInByRotations(1.0,1);
        if(alliance==Alliance.BLUE)
            robotNavigator.turnLeft(180,5000);
        else
            robotNavigator.turnRight(180,5000);
        hook.halfattach(0.5);
        robotNavigator.moveBackward(9,5000);
        hook.attach();
        opMode.sleep(200);
        robotNavigator.setNavigatorPower(0.4);
        robotNavigator.moveBackward(1.5,5000);
        robotNavigator.setNavigatorPower(1.0);
       /* robotNavigator.turnLeft(5,1000);
        robotNavigator.turnRight(10,1000);
        robotNavigator.turnLeft(5,1000);
        robotNavigator.moveForward(24,5000);
        double turn_degrees = 210;
        if (alliance == Alliance.BLUE) {
            robotNavigator.shiftLeft(12, 10000);
            robotNavigator.turnLeft(turn_degrees, 10000);
            robotNavigator.shiftRight(28,1000);
        } else {
            robotNavigator.shiftRight(12, 10000);
            robotNavigator.turnRight(turn_degrees, 10000);
            robotNavigator.shiftLeft(28,1000);
        }

        // Pushing foundation into depot

        robotNavigator.moveBackward(15, 10000);
        /*
         * Detach from the foundation


        opMode.sleep(200);
        hook.detach();
        opMode.sleep(200);
        */

        /* robotNavigator.moveBackward(15, 10000);
        robotNavigator.moveForward(2,10000);


        if(alliance == Alliance.BLUE){
            if (parking == parking.WALL) {
                robotNavigator.shiftRight(25, 10000);
                robotNavigator.shiftLeft(2,1000);
            }
            /*else
                robotNavigator.shiftLeft(4, 10000);
        }
        else {
            if (parking == parking.WALL) {
                robotNavigator.shiftLeft(25, 10000);
                robotNavigator.shiftRight(2,1000);
            }
            /*else
                robotNavigator.shiftRight(4, 10000);
                //

        }
        elevator.goDownByRotations(1,1);
        robotNavigator.moveForward(36,5000);

        elevator.goOutByRotations(1.0,1);
        grab.rotateToDegrees_0();
        */
//        elevator.homeposition();
//        opMode.sleep(200);


/*--------------------------------------------
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

    }

}