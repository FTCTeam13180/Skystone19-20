package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
public class WinchOut {
    public LinearOpMode opMode;
    private DcMotor InOut;
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
}
