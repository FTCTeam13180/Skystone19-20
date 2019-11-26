package org.firstinspires.ftc.team13180s3;
//osmehting
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="RedLoadingZoneWall", group="autonomusGroup1")
public class RedLoadingZoneWall extends LinearOpMode {
    private RoboNavigator robotNavigator;
    private StoneColorSensor stoneSenor;
    private Intake intake;
    private Grabber grab;
    private Hook hook;
    private WinchUp upWinch;
    private WinchOut outWinch;

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
        upWinch=new WinchUp(this);
        outWinch=new WinchOut(this);
        upWinch.init();
        outWinch.init();
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

            upWinch.encoderDrive(NAVIGATOR_POWER,-2.5*2.54,200);
            grab.release();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER, 25*2.54, 4000);
            //winch out 5 inch
            //grab.grabIn();
            //winch down
            outWinch.goOutTime(-0.6,1500);
            sleep(1000);
            upWinch.encoderDrive(NAVIGATOR_POWER,6*2.54,2200);
            sleep(500);
            grab.grabIn();
            sleep(500);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,15*2.54,10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.TURN_RIGHT,NAVIGATOR_POWER,100,5000);
//            upWinch.encoderDrive(NAVIGATOR_POWER,5*2.54,1200);

//            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, NAVIGATOR_POWER, 4*2.54, 1000);
//            grab.grabIn();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD, NAVIGATOR_POWER,44*2.54,10000);
            grab.release();
            //grab.release();
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.BACKWARD,NAVIGATOR_POWER,68*2.54,10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT,NAVIGATOR_POWER,5*2.54,10000);
            sleep(5000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.FORWARD,NAVIGATOR_POWER,54*2.54,10000);
            robotNavigator.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT,NAVIGATOR_POWER,7*2.54,10000);

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
            break;
        }
    }
}