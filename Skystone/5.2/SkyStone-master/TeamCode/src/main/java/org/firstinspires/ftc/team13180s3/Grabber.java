package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
public class Grabber {
    public LinearOpMode opMode;
    private Servo leftServo;
    private Servo rightServo;
    final double attachpos=1;
    Grabber(LinearOpMode op){opMode=op;}
    public void init(){
        leftServo=opMode.hardwareMap.get(Servo.class,"leftGrabber");
        rightServo=opMode.hardwareMap.get(Servo.class,"rightGrabber");
    }
    public void grabIn(){
        leftServo.setPosition(attachpos);
        rightServo.setPosition(attachpos);
    }
    public void release(){
        leftServo.setPosition(0);
        rightServo.setPosition(0);
    }
}
