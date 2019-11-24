package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestWinch", group="manualmode")
public class TestWinch extends LinearOpMode {
    public WinchUp vert;
    public WinchOut horiz;
    double landerpower = 0.6;
    @Override
    public void runOpMode() throws InterruptedException {
        vert=new WinchUp(this);
        horiz= new WinchOut(this);
        vert.init();
        horiz.init();
        waitForStart();
        while (opModeIsActive()){
            double updir=gamepad1.left_stick_y;
            double sidedir=gamepad1.right_stick_x;
            if (Math.abs(updir)>.1){
                vert.goUp(updir);
            } else if(Math.abs(sidedir)>.1) {
                horiz.goOut(sidedir);
            }
              else if(gamepad1.y){
                horiz.goOutTime(0.8,1000);
            }
              else if(gamepad1.b){
                vert.goUpTime(0.8,2000);
            }
             else if(gamepad2.a){
                vert.encoderDrive(landerpower,2*2.54,5000);
            } else if(gamepad2.x){
                horiz.encoderDrive(landerpower,2*2.54,5000);
            }
            else{
                horiz.stop();
                vert.stop();
            }

        }
    }
}
