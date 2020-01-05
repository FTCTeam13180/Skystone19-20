package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class BuildingZoneFullAuto {
    private LinearOpMode opMode;
    private RoboNavigator robotNavigator;
    private Hook hook;
    private Grabber grab;
    private Elevator elevator;
    private Alliance alliance;
    private Parking parking;
    private double NAVIGATOR_POWER = 0.5;

    public enum Alliance {
        BLUE,
        RED
    }

    public enum Parking {
        BRIDGE,
        WALL
    }

    BuildingZoneFullAuto(LinearOpMode op, Alliance al, Parking pa) {
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

        opMode.sleep(2000);
        // Set hook in attach position
        hook.halfattach(0.5);

        // Go to Foundation and grab it
        robotNavigator.moveBackward(29, 10000);
        opMode.sleep(200);
        hook.attach();
        opMode.sleep(200);


        /*
         * Move the foundation to the triangle build ZONE
         */
        // Move foundation away from opposing alliance foundation
        robotNavigator.moveForward(12, 10000);
        //pull foundation towards the bridges, away from north wall
        // then turning the foundation
        double turn_degrees = 190;
        if (alliance == Alliance.BLUE) {
            robotNavigator.shiftLeft(12, 10000);
            robotNavigator.turnLeft(turn_degrees, 10000);
        } else {
            robotNavigator.shiftRight(12, 10000);
            robotNavigator.turnRight(turn_degrees, 10000);
        }

        // Pushing foundation into depot
        robotNavigator.moveBackward(30, 10000);

        /*
         * Detach from the foundation
         */
        opMode.sleep(200);
        hook.detach();
        opMode.sleep(200);
        /*if (alliance == Alliance.BLUE) {
            robotNavigator.turnRight(50, 10000);
        }

        else {
            robotNavigator.turnLeft(50, 10000);
        }*/
        //opMode.sleep(2000);
        //robotNavigator.moveForward(6,10000);
        robotNavigator.moveForward(68, 10000);
        if (alliance == Alliance.BLUE) {
            robotNavigator.turnLeft(90, 1000);
        } else {
            robotNavigator.turnRight(90, 10000);
        }
        robotNavigator.moveForward(24, 10000);
        elevator.goOutByRotations(0.8, 3);
        elevator.upDownEncoderDrive(0.8, -5 * 2.54, 10000);
        grab.grabIn();
        robotNavigator.moveBackward(24, 10000);
        if (alliance == Alliance.BLUE) {
            robotNavigator.turnRight(90, 1000);
            robotNavigator.moveForward(72, 1000);

        } else {
            robotNavigator.turnLeft(90, 10000);
            robotNavigator.moveForward(72, 10000);
        }
        //robotNavigator.moveForward(6, 1000);
        grab.release();
        robotNavigator.moveBackward(24,1000);
        if(alliance == Alliance.BLUE){
            if (parking == Parking.WALL) {
                robotNavigator.shiftLeft(18, 10000);
            } else {
                robotNavigator.shiftRight(18, 1000);
            }
        }
        else {
            if (parking == Parking.WALL) {
                    robotNavigator.shiftRight(18, 10000);
                } else {
                    robotNavigator.shiftLeft(18, 1000);
                }
            }
    }






        // ROBOT IS PARKED UNDER THE ALLIANCE SKYBRIDGE*/


    public void stage2(){

    }
    public void HomePosition(){
        grab.rotateToDegrees_180();
        grab.grabIn();
        elevator.upDownEncoderDrive(NAVIGATOR_POWER, elevator.height-5,10000);
        elevator.goInByRotations(0.4,4);
        elevator.upDownEncoderDrive(NAVIGATOR_POWER, elevator.height,10000);


    }
}

