package org.firstinspires.ftc.team13180s3;

import  com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SkyStoneTeleOp", group = "manualmode")
public class SkyStoneTeleOp extends LinearOpMode {
    private RoboNavigator roboNav;
    private WinchUp vWinch;
    private WinchOut hWinch;
    private Grabber Gripper;
    private Hook hook;
    final double vWinchPowerUp=1.0;
    final double vWinchPowerDown=0.6;
    final double multiplier=0.5;
    boolean HookPosition=False; //False=Up, True=Down
    public void runOpMode (){
        roboNav = new RoboNavigator(this);
        vWinch=new WinchUp(this);
        hWinch=new WinchOut(this);
        Gripper=new Grabber(this);
        hook=new Hook(this);
        roboNav.init();
        roboNav.initIMU();
        vWinch.init();
        hWinch.init();
        Gripper.init();
        hook.init();
        waitForStart();
        while (opModeIsActive()){
            double rx=gamepad1.left_stick_x;
            double ry=gamepad1.left_stick_y;
            boolean ybutton=gamepad1.y;
          //  boolean r1trigger=gamepad1.right_bumper;
            boolean abutton=gamepad1.a;
           // boolean dpaddown1=gamepad1.dpad_down;
            boolean bbutton=gamepad1.b;

            double l2Trigger=gamepad2.left_trigger;
            double r2Trigger=gamepad2.right_trigger;
            boolean dpadUp=gamepad2.dpad_up;
            boolean dpadDown=gamepad2.dpad_down;
            boolean rbutton=gamepad2.right_bumper;
            boolean lbutton=gamepad2.left_bumper;

            if(abutton){
                if(multiplier<1) {
                    multiplier += 0.1;
                }
            }
            else if(bbutton){
                if(multiplier>0.5) {
                    multiplier -= 0.1;
                }
            }
            if(ybutton){
                if(ybutton=true) {
                    hook.attach();
                }
                else{
                    hook.detach();
                }
            }
            if(Math.abs(rx)> 0.1 || Math.abs(ry) > 0.1){
                roboNav.OmniImu(rx*multiplier,ry*multiplier);
            }
            else{
                roboNav.stopMotor();
            }


            if(l2Trigger>0){
                hWinch.goOut(l2Trigger);
            }
            else if(r2Trigger>0){
                hWinch.goIn(r2Trigger);
            }
            else if(dpadUp){
                vWinch.goUp(vWinchPowerUp);
            }
            else if(dpadDown){
                vWinch.goDown(vWinchPowerDown);
            }
            else if(l1trigger){
                roboNav.turnLeft(0.75);
            }
            else if(r1trigger){
                roboNav.turnRight(0.75);
            }
            else if (rbutton){
                Gripper.grabIn();
            }
            else if(ybutton){
                hook.attach();
            }
            else if(bbutton){
                hook.detach();
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

