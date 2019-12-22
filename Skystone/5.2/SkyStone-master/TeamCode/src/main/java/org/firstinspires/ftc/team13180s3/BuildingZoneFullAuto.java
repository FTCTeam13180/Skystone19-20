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

    public enum Alliance {
        BLUE,
        RED
    }

    public enum Parking {
        BRIDGE,
        WALL
    }

    BuildingZoneFullAuto (LinearOpMode op, Alliance al, Parking pa) {
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

        opMode.sleep(20000);
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
        double turn_degrees = 210;
        if (alliance == Alliance.BLUE) {
            robotNavigator.shiftLeft(12, 10000);
            robotNavigator.turnLeft(turn_degrees, 10000);
        }

        else {
            robotNavigator.shiftRight(12, 10000);
            robotNavigator.turnRight(turn_degrees, 10000);
        }

        // Pushing foundation into depot
        robotNavigator.moveBackward(24,10000);

        /*
         * Detach from the foundation
         */
        opMode.sleep(200);
        hook.detach();
        opMode.sleep(200);
        robotNavigator.moveForward(6,10000);

        /*
         * Lower the elevator to fit under bridge, move the hook out of way first.
         */
        hook.attach();
        elevator.goOutByRotations(0.8,4);
        hook.detach();
        elevator.upDownEncoderDrive(0.5,5*2.54,10000);

        /*
         * Move the robot under the bridge
         */
        if (parking == Parking.WALL) {
            if (alliance == Alliance.BLUE)
                robotNavigator.shiftRight(18, 10000);
            else
                robotNavigator.shiftLeft(18, 10000);
        }
        robotNavigator.moveForward(36,10000);

        // ROBOT IS PARKED UNDER THE ALLIANCE SKYBRIDGE*/
    }
}

