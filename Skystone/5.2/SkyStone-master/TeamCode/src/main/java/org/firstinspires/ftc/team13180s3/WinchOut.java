package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WinchOut {
    public LinearOpMode opMode;
    private DcMotor InOut;
    private static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 0.333 ;     // This is < 1.0 if geared UP
    private static final double     WINCH_DIAMETER_CM   = 0.866 ;     // For figuring circumference
    static final double     COUNTS_PER_CM         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WINCH_DIAMETER_CM * 3.1415);

    WinchOut(LinearOpMode op){opMode=op;}
    public void init(){
        InOut=opMode.hardwareMap.get(DcMotor.class,"horizontalWinch");
    }
    public void goOut(double power){
        InOut.setPower(power);
    }
    public void goIn(double power){
        InOut.setPower(-power);
    }
    public void stop(){
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

    }

