package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/*

 */
public class Intake {
    public LinearOpMode opMode;

    private DcMotor leftIntake;
    private DcMotor rightIntake;

    boolean logging = true;

    Intake (LinearOpMode op)
    {
        opMode = op;
    }

    public void init() {
        leftIntake= opMode.hardwareMap.get(DcMotor.class, "Lintake");
        rightIntake = opMode.hardwareMap.get(DcMotor.class,"Rintake");
        if(logging) {
            opMode.telemetry.addData("Intake:", "Initialized");
        }
    }

    public void startIntake(double power){
        leftIntake.setPower(power);
        rightIntake.setPower(-power);
    }

    public void stopIntake(){
        leftIntake.setPower(0);
        rightIntake.setPower(0);
    }
}
