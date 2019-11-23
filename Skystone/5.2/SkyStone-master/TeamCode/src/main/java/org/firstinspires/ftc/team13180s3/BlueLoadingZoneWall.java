package org.firstinspires.ftc.team13180s3;
//osmehting
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="BlueLoadingZoneWall", group="autonomusGroup1")
public class BlueLoadingZoneWall extends LinearOpMode {
    private RoboNavigator robotNavigator;
    private StoneColorSensor stoneSenor;
    private Intake intake;
    private Grabber grab;
    private Hook hook;

    @Override
    public void runOpMode() {
        robotNavigator = new RoboNavigator(this);
        robotNavigator.init();

        //TODO
        // stoneSenor = new StoneColorSensor(this);
        //stoneSenor.init();

        //intake = new Intake(this);
        //intake.init();

        grab = new Grabber(this);
        grab.init();
        hook= new Hook(this);
        hook.init();
        double NAVIGATOR_POWER = 0.5; // check
        hook.detach();
        waitForStart();
        while (opModeIsActive()) {
            //This is assuming that we are next to the blue depot
            telemetry.addLine("Front of Robot Should be forward");
            telemetry.update();
            //winch up(TODO)
            // winch.up()
            // go forward 25 inches
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 100000);
            //winch out 5 inch
            grab.grabIn();
            //winch down
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,15*2.54,100000);
            //spin left 90 degrees
//            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 4*2.54, 1000);
//            grab.grabIn();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER,36*2.54,100000);
            grab.release();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,84*2.54,100000);
            //spin right 90 degrees
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT,NAVIGATOR_POWER,24*2.54,100000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD,NAVIGATOR_POWER,72*2.54,100000);

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
}