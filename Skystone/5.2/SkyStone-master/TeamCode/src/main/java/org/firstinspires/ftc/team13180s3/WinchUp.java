package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
public class WinchUp {
    public LinearOpMode opMode;
    private DcMotor UpDown;
    WinchUp(LinearOpMode op){opMode=op;}
    public void init(){
        UpDown=opMode.hardwareMap.get(DcMotor.class,"verticalWinch");
    }
    public void goUp(double power){
        UpDown.setPower(power);
    }
    public void goDown(double power){
        UpDown.setPower(-power);
    }
    public void stop(){
        UpDown.setPower(0);
    }
}
