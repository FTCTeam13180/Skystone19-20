package org.firstinspires.ftc.team13180s3;
//osmehting
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="LoadingZoneAuto", group="autonomusGroup1")
public class LoadingZoneAuto extends LinearOpMode {
    private RoboNavigator robotNavigator;
    private StoneColorSensor stoneSenor;
    private Intake intake;
    private Grabber grab;

    @Override
    public void runOpMode() {
        robotNavigator = new RoboNavigator(this);
        robotNavigator.init();

        stoneSenor = new StoneColorSensor(this);
        stoneSenor.init();

        intake = new Intake(this);
        intake.init();

        grab = new Grabber(this);
        grab.init();

        double NAVIGATOR_POWER = 0.5; // check
        waitForStart();
        while (opModeIsActive()) {
            //This is assuming that we are next to the blue depot
            telemetry.addLine("Front of Robot Should be forward");
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 44*2.54, 1000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 4*2.54, 1000);
            grab.grabIn();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, NAVIGATOR_POWER,24*2.54,1000);
            grab.release();

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