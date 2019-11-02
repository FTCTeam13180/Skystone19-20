package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestWinch", group="manualmode")
public class TestWinch extends LinearOpMode {
    public WinchUp vert;
    public WinchOut horiz;
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
            }
            else if(Math.abs(sidedir)>.1){
                horiz.goOut(sidedir);
            }
            else{
                horiz.stop();
                vert.stop();
            }
        }
    }
}
