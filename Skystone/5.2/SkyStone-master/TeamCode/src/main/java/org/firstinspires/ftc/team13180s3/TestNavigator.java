package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;



@TeleOp(name="TestNavigator", group="manualmode")

public class TestNavigator extends LinearOpMode {

    private RoboNavigator robotnav;

    static final double SPEED = 0.8;

    @Override
    public void runOpMode() {

        robotnav = new RoboNavigator(this);
        robotnav.init();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up) {
                robotnav.moveForward(SPEED);
            }
            else if (gamepad1.dpad_down) {
                robotnav.moveBackward(SPEED);
            }
            else if (gamepad1.dpad_left) {
                robotnav.shiftLeft(SPEED);
            }
            else if (gamepad1.dpad_right) {
                robotnav.shiftRight(SPEED);
            }
            if (gamepad1.right_bumper) {
                robotnav.turnRight(SPEED);
            }
            if (gamepad1.left_bumper) {
                robotnav.turnLeft(SPEED);
            }
            if(Math.abs(gamepad1.left_stick_x)>0.1 || Math.abs(gamepad1.left_stick_y)>0.1){   //can go any direction 360 degrees based on controller input
                if(Math.abs(gamepad1.left_stick_x)> 0.1 || Math.abs(gamepad1.left_stick_y) > 0.1) {
                    // VERY IMPORTANT: Gamepad joystick y-axis positive points downward, x-axis points right
                    // Always remember to reverse joystick stick_y value.

                    // OmniImu still has a bug where left and right are reversed, until that is fixed we use AnyMecanum drive.
                    //roboNav.OmniImu(nav_omni_x, -nav_omni_y, multiplier);
                    robotnav.OmniImu(gamepad1.left_stick_x, -gamepad1.left_stick_y, SPEED);
                }
                // Allow tight turns while
            }

            // EncoderMode
            else if (gamepad1.y) {
                //robotnav.moveForward(96, 10000);
                // Testing encode with Rampup. Start slow in first two seconds and then full speed.
                robotnav.moveForwardWithRampup(96,10000, 2000);
            }
            else if (gamepad1.a) {
                robotnav.moveBackward(96, 10000);
            }
            else if (gamepad1.b) {
                robotnav.shiftRight(96, 10000);
            }
            else if (gamepad1.x) {
                robotnav.shiftLeft(96, 10000);
            }
            else if(gamepad1.dpad_up){
                robotnav.setNavigatorPower(0.4);
            }
            else if(gamepad1.dpad_down){
                robotnav.setNavigatorPower(1.0);
            }

            else if (gamepad1.left_trigger > 0.1) {
                robotnav.encoderDrive(RoboNavigator.DIRECTION.TURN_LEFT, SPEED, 90,5000);
            }
            else if (gamepad1.right_trigger > 0.1){
                robotnav.encoderDrive(RoboNavigator.DIRECTION.TURN_RIGHT,SPEED, 90,5000);
            }
            if(gamepad2.left_stick_x>0.1 ||gamepad2.left_stick_y>0.1){
                double ang=Math.atan2(gamepad2.left_stick_y,-gamepad2.left_stick_y);
                if(ang<0){
                    ang=Math.abs(ang)+Math.PI;
                }
                double turn=0;
                if(gamepad2.left_trigger>0){
                    turn=-1*gamepad2.left_trigger;
                }
                else{
                    turn=gamepad2.right_trigger;
                }
                robotnav.SwerveDrive(ang,turn,Math.sqrt(gamepad2.left_stick_x*gamepad2.left_stick_x+gamepad2.left_stick_y*gamepad2.left_stick_y));
            }
            else {
                robotnav.stopMotor();
            }


        }
    }
}