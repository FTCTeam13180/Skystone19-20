package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

public class Elevator {
    public LinearOpMode opMode;
    private CRServo inOutWinch;
    private DcMotor upDownWinch;


    private final double MSECS_PER_ROTATION = 840;

    Elevator(LinearOpMode op) {
        opMode = op;
    }

    public void init() {
        inOutWinch = opMode.hardwareMap.get(CRServo.class, "horizontalWinch");
        upDownWinch=opMode.hardwareMap.get(DcMotor.class,"verticalWinch");
    }

    //
    // InOutWinch Controls used by telescopic arm
    //
    public void goOut(double power) {
        inOutWinch.setPower(abs(power));
    }

    public void goIn(double power) {
        inOutWinch.setPower(-abs(power));
    }

    public void stopInOut() {
        inOutWinch.setPower(0);
    }

    public void goOutByRotations(double power, double rotations) {
        goOut(power);
        opMode.sleep((long)(rotations * MSECS_PER_ROTATION));
        stopInOut();
    }

    public void goInByRotations(double power, double rotations) {
        goIn(power);
        opMode.sleep((long)(rotations * MSECS_PER_ROTATION));
        stopInOut();
    }

    //
    // UpDownWinch Controls used by elevator arm
    //
    public void goUp(double power){
        upDownWinch.setPower(abs(power));
    }

    public void goDown(double power){
        upDownWinch.setPower(-abs(power));
    }

    public void stopUpDown(){ upDownWinch.setPower(0); }


    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 0.5 ;     // This is < 1.0 if geared UP
    private static final double     WHINCH_DIAMETER_CM   = 2.2 ;     // For figuring circumference
    static final double     COUNTS_PER_CM         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHINCH_DIAMETER_CM * 3.1415);

    public void upDownEncoderDrive(double speed,
                                   double cms,
                                   double timeoutMs) {
        int newWinchUpTarget;

        opMode.telemetry.addData("Elevator:", "Resetting Encoders");    //
        opMode.telemetry.update();

        upDownWinch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upDownWinch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        opMode.telemetry.addData("Path0", "Starting at %7d",
                upDownWinch.getCurrentPosition());
        opMode.telemetry.update();


        // Ensure that the opmode is still active
        if (opMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newWinchUpTarget = upDownWinch.getCurrentPosition() + (int) (cms * COUNTS_PER_CM);
            upDownWinch.setTargetPosition(newWinchUpTarget);

            // Turn On RUN_TO_POSITION
            upDownWinch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            ElapsedTime runtime = new ElapsedTime();

            runtime.reset();

            goUp(speed*(Math.abs(cms)/cms));

            // keep looping while we are still active, and there is time left, and motor is running.
            while (opMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutMs) &&
                    (upDownWinch.isBusy())) {

                // Display it for the driver.
                opMode.telemetry.addData("Path1", "Running to %7d", newWinchUpTarget);
                opMode.telemetry.addData("Path2", "Running at %7d",
                        upDownWinch.getCurrentPosition());
                opMode.telemetry.update();
            }

            // Stop all motion;
            stopUpDown();

            // Turn off RUN_TO_POSITION
            upDownWinch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }
    }
}