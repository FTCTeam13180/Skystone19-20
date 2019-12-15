package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


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


        // Set hook in attach position
        hook.halfattach(0.5);

        // Go to Foundation and grab it
        robotNavigator.moveForward(29, 10000);
        opMode.sleep(200);
        hook.attach();
        opMode.sleep(200);


        /*
         * Move the foundation to the triangle build ZONE
         */
        // TURN horizontal
        double turn_degrees = 210;
        if (alliance == Alliance.BLUE)
            robotNavigator.turnLeft(turn_degrees, 10000);
        else
            robotNavigator.turnRight(turn_degrees, 10000);

        // Allign with field perimeter
        robotNavigator.moveForward(40,10000);

        // Drag to the corner
        double shift_inches = 36;
        if (alliance == Alliance.BLUE)
            robotNavigator.shiftLeft(shift_inches,10000);
        else
            robotNavigator.shiftRight(shift_inches,10000);

        /*
         * Detach from the foundation
         */
        opMode.sleep(200);
        hook.detach();
        opMode.sleep(200);
        robotNavigator.moveBackward(5,10000);

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
        if (parking == Parking.BRIDGE) {
            if (alliance == Alliance.BLUE)
                robotNavigator.shiftRight(24, 10000);
            else
                robotNavigator.shiftLeft(24, 10000);
        }
        robotNavigator.moveBackward(36,10000);

        // ROBOT IS PARKED UNDER THE ALLIANCE SKYBRIDGE*/
    }
}

