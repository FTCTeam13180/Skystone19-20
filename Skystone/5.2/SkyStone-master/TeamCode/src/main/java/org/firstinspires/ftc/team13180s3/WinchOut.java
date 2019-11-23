package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

public class WinchOut {
    public LinearOpMode opMode;
    private DcMotor InOut;
    private static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 0.333;     // This is < 1.0 if geared UP
    private static final double WINCH_DIAMETER_CM = 0.866;     // For figuring circumference
    static final double COUNTS_PER_CM = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WINCH_DIAMETER_CM * 3.1415);

    WinchOut(LinearOpMode op) {
        opMode = op;
    }

    public void init() {
        InOut = opMode.hardwareMap.get(DcMotor.class, "horizontalWinch");
    }

    public void goOut(double power) {
        InOut.setPower(power);
    }

    public void goIn(double power) {
        InOut.setPower(-power);
    }

    public void stop() {
        InOut.setPower(0);
    }

    public void goOutTime(double power, long time) {
        goOut(power);
        opMode.sleep(time);
        stop();
    }

    public void goInTime(double power, long time) {
        goIn(power);
        opMode.sleep(time);
        stop();
    }

    public void encoderDrive(double speed,
                             double cms,
                             double timeoutMs) {
        int newWinchHorizTarget;

        opMode.telemetry.addData("RoboLander:", "Resetting Encoders");    //
        opMode.telemetry.update();

        InOut.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        InOut.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        opMode.telemetry.addData("Path0", "Starting at %7d",
                InOut.getCurrentPosition());
        opMode.telemetry.update();


        // Ensure that the opmode is still active
        if (opMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newWinchHorizTarget = InOut.getCurrentPosition() + (int) (cms * COUNTS_PER_CM);
            InOut.setTargetPosition(newWinchHorizTarget);

            // Turn On RUN_TO_POSITION
            InOut.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            ElapsedTime runtime = new ElapsedTime();

            runtime.reset();

            InOut.setPower(abs(speed));

            // keep looping while we are still active, and there is time left, and motor is running.
            while (opMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutMs) &&
                    (InOut.isBusy())) {

                // Display it for the driver.
                opMode.telemetry.addData("Path1", "Running to %7d", newWinchHorizTarget);
                opMode.telemetry.addData("Path2", "Running at %7d",
                        InOut.getCurrentPosition());
                opMode.telemetry.update();
            }

            // Stop all motion;
            InOut.setPower(0);

            // Turn off RUN_TO_POSITION
            InOut.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        }

    }

}