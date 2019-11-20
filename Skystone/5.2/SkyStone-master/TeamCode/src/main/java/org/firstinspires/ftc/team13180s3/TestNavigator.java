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
            else if (gamepad1.right_bumper) {
                robotnav.turnRight(SPEED);
            }
            else if (gamepad1.left_bumper) {
                robotnav.turnLeft(SPEED);
            }
            else if(Math.abs(gamepad1.left_stick_x)>0.1 || Math.abs(gamepad1.left_stick_y)>0.1){   //can go any direction 360 degrees based on controller input
                telemetry.addData("left_stick", "x=%f, y=%f", gamepad1.left_stick_x, gamepad1.left_stick_y);
                telemetry.update();
                // VERY IMPORTANT: Gamepad joystick y-axis positive points downward, x-axis points right
                // Always remember to reverse joystick stick_y value.
                robotnav.AnyMecanum(gamepad1.left_stick_x, -gamepad1.left_stick_y, SPEED);
            }

            // EncoderMode
            else if (gamepad1.y) {
                robotnav.encoderDrive(RoboNavigator.DIRECTION.FORWARD, SPEED, 48*2.54,5000);
            }
            else if (gamepad1.a) {
                robotnav.encoderDrive(RoboNavigator.DIRECTION.BACKWARD, SPEED, 48*2.54,5000);
            }
            else if (gamepad1.b) {
                robotnav.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, SPEED, 48*2.54,5000);
            }
            else if (gamepad1.x) {
                robotnav.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, SPEED, 48*2.54,5000 );
            }
            else if (gamepad1.left_trigger > 0.1) {
                robotnav.encoderDrive(RoboNavigator.DIRECTION.TURN_LEFT, SPEED, 90,5000);
            }
            else if (gamepad1.right_trigger > 0.1){
                robotnav.encoderDrive(RoboNavigator.DIRECTION.TURN_RIGHT,SPEED, 90,5000);
            }
            else {
                robotnav.stopMotor();
            }

        }
    }
}