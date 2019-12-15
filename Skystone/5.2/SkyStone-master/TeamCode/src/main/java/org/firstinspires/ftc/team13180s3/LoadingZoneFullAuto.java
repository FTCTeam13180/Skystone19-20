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
    private SkyStoneTensorFlow detect;

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

        detect = new SkyStoneTensorFlow();
        detect.initvuforia(opMode);
        detect.initTfod(opMode);

        double NAVIGATOR_POWER = 0.5; // check
        hook.detach();


            //This is assuming that we are next to the blue depot
            opMode.telemetry.addLine("Front of Robot Should be forward");
            opMode.telemetry.update();
            //winch up(TODO)
            // winch.up()
            // go forward 25 inches

            elevator.upDownEncoderDrive(NAVIGATOR_POWER,-2.5*2.54,200);
            grab.release();
            
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
            //winch out 5 inch
            //grab.grabIn();
            //winch down
            detect.activate();
            if(detect.scan()){
                robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
                grab.grabIn();
            }
            else{
                robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
            }
            elevator.goOutByRotations(0.8,4);
            opMode.sleep(1000);
            elevator.upDownEncoderDrive(NAVIGATOR_POWER,6*2.54,2200);
            opMode.sleep(500);
            grab.grabIn();
            opMode.sleep(500);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,15*2.54,10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.TURN_LEFT,NAVIGATOR_POWER,100,5000);
//            upWinch.encoderDrive(NAVIGATOR_POWER,5*2.54,1200);

//            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 4*2.54, 1000);
//            grab.grabIn();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER,44*2.54,10000);
            grab.release();
            //grab.release();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,68*2.54,10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT,NAVIGATOR_POWER,5*2.54,10000);
            opMode.sleep(5000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD,NAVIGATOR_POWER,54*2.54,10000);

            /*robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 42*2.54, 10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD, NAVIGATOR_POWER, 24*2.54, 10000);
            while(stoneSenor.isBlackColor() == false){
                stoneSenor.getColorSensor();
                if (stoneSenor.isYellowColor() == true){
                    robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER,6*2.54, 10000);
                }
                else if(stoneSenor.isBlackColor()){
                    robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD, NAVIGATOR_POWER, 6*2.54, 10000);
                    robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER, 6*2.54, 10000);
                    robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 6*2.54, 10000);
                    intake.startIntake(0.6);
                    robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 12*2.54, 10000);
                    break;



                }

            }
*/
    }
}