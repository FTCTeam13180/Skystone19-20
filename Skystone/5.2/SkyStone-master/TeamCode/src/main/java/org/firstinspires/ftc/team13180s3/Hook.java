package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class Hook {
    public LinearOpMode opMode;
    private Servo hookl;
    private Servo hookr;

    Hook(LinearOpMode op){opMode=op;}
    public void init(){
        hookl=opMode.hardwareMap.get(Servo.class,"hookL");
        hookr=opMode.hardwareMap.get(Servo.class,"hookR");
    }
    public void attach(){

        hookl.setPosition(1);
        hookr.setPosition(0);
    }
    public void detach(){
        hookl.setPosition(0);
        hookr.setPosition(1);
    }
}

