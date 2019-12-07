package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import static java.lang.Math.abs;

public class WinchUp {
    public LinearOpMode opMode;
    private DcMotor upDown;
    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 0.5 ;     // This is < 1.0 if geared UP
    private static final double     WHINCH_DIAMETER_CM   = 2.2 ;     // For figuring circumference
    static final double     COUNTS_PER_CM         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHINCH_DIAMETER_CM * 3.1415);
    WinchUp(LinearOpMode op){opMode=op;}
    public void init(){

        upDown=opMode.hardwareMap.get(DcMotor.class,"verticalWinch");
    }
    public void goUp(double power){
        upDown.setPower(-power);
    }
    public void goDown(double power){
        upDown.setPower(power);
    }
    public void stop(){
        upDown.setPower(0);
    }
    public void goDownTime(double power, long time) {
        goDown(power);
        opMode.sleep(time);
        stop();
    }
    public void goUpTime(double power, long time) {
        goUp(power);
        opMode.sleep(time);
        stop();
    }
    public void LevelUp(double levels){
        encoderDrive(0.8,2.54*-4*levels,10000);
    }
    public void LevelDown(double levels){
        encoderDrive(0.8,2.54*4*levels,10000);
    }
    //its reversed dont worry about it ^^
    public void encoderDrive(double speed,
                             double cms,
                             double timeoutMs) {
        int newWinchUpTarget;

        opMode.telemetry.addData("RoboLander:", "Resetting Encoders");    //
        opMode.telemetry.update();

        upDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        upDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        opMode.telemetry.addData("Path0", "Starting at %7d",
                upDown.getCurrentPosition());
        opMode.telemetry.update();


        // Ensure that the opmode is still active
        if (opMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newWinchUpTarget = upDown.getCurrentPosition() + (int) (cms * COUNTS_PER_CM);
            upDown.setTargetPosition(newWinchUpTarget);

            // Turn On RUN_TO_POSITION
            upDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            ElapsedTime runtime = new ElapsedTime();

            runtime.reset();

            goUp(speed*(Math.abs(cms)/cms));

            // keep looping while we are still active, and there is time left, and motor is running.
            while (opMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutMs) &&
                    (upDown.isBusy())) {

                // Display it for the driver.
                opMode.telemetry.addData("Path1", "Running to %7d", newWinchUpTarget);
                opMode.telemetry.addData("Path2", "Running at %7d",
                        upDown.getCurrentPosition());
                opMode.telemetry.update();
            }

            // Stop all motion;
            stop();

            // Turn off RUN_TO_POSITION
            upDown.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        }
    }
}