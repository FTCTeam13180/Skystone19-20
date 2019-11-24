package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class
Grabber {
    public LinearOpMode opMode;
    private Servo grab;

    final double attachpos=1;
    Grabber(LinearOpMode op){opMode=op;}
    public void init(){
        grab=opMode.hardwareMap.get(Servo.class,"Grabber");

    }
    public void grabIn(){
        grab.setPosition(0.1);

    }
    public void release(){
        grab.setPosition(0.6);

    }
}

