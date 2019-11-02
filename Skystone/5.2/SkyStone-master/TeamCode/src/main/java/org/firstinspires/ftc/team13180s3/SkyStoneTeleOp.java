package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SkyStoneTeleOp", group = "manualmode")
public class SkyStoneTeleOp extends LinearOpMode {
    private RoboNavigator roboNav;
    private WinchUp vWinch;
    private WinchOut hWinch;
    private Grabber Gripper;
    final double vWinchPower=0.6;
    public void runOpMode (){
        roboNav = new RoboNavigator(this);
        vWinch=new WinchUp(this);
        hWinch=new WinchOut(this);
        Gripper=new Grabber(this);
        roboNav.init();
        roboNav.initIMU();
        vWinch.init();
        hWinch.init();
        Gripper.init();
        waitForStart();
        while (opModeIsActive()){
            double rx=gamepad1.left_stick_x;
            double ry=gamepad1.left_stick_y;
            double lTrigger=gamepad2.left_trigger;
            double rTrigger=gamepad2.right_trigger;
            boolean dpadUp=gamepad2.dpad_up;
            boolean dpadDown=gamepad2.dpad_down;
            boolean rbutton=gamepad2.right_bumper;
            boolean lbutton=gamepad2.left_bumper;
            if(rx>0.1 || ry>0.1){
                roboNav.OmniImu(rx,ry);
            }
            else if(lTrigger>0){
                hWinch.goOut(lTrigger);
            }
            else if(rTrigger>0){
                hWinch.goIn(rTrigger);
            }
            else if(dpadUp){
                vWinch.goUp(vWinchPower);
            }
            else if(dpadDown){
                vWinch.goDown(vWinchPower);
            }
            else if (rbutton){
                Gripper.grabIn();
            }
            else if (lbutton){
                Gripper.release();
            }
            else{
                hWinch.stop();
                vWinch.stop();
            }

        }



    }
}

