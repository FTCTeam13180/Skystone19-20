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
    double vWinchPowerUp=1.0;
    double vWinchPowerDown=0.6;
    double multiplier=0.5;
    boolean hookPosition = false; //False=Up, True=Down
    boolean gripperPos=false;
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
//GAMEPAD 1 (START A) CONTROLS
            double rx=gamepad1.left_stick_x;
            double ry=gamepad1.left_stick_y;
            boolean ybutton=gamepad1.y;
            boolean leftbumper1=gamepad1.left_bumper;
            boolean rightbumper1=gamepad1.right_bumper;
            boolean abutton=gamepad1.a;
            boolean bbutton=gamepad1.b;
            boolean dpadleft=gamepad1.dpad_left;
//GAMEPAD 2 (START B) CONTROLS
            boolean l2bumper=gamepad2.left_bumper;
            boolean r2bumper=gamepad2.right_bumper;
            boolean dpadUp=gamepad2.dpad_up;
            boolean dpadDown=gamepad2.dpad_down;
            boolean x2=gamepad2.x;
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
                if(hookPosition=false) {
                    hook.attach();
                    hookPosition=true;
                }
                else{
                    hook.detach();
                    hookPosition=false;
                }
            }
            if(dpadleft){
                roboNav.initIMU();
            }
            if(Math.abs(rx)> 0.1 || Math.abs(ry) > 0.1){
                roboNav.OmniImu(rx*multiplier,ry*multiplier);
            }
            else{
                roboNav.stopMotor();
            }


            if(r2bumper){
                hWinch.goOut(1);
            }
            else if(l2bumper){
                hWinch.goIn(1);
            }
            else if(dpadUp){
                vWinch.goUp(vWinchPowerUp);
            }
            else if(dpadDown){
                vWinch.goDown(vWinchPowerDown);
            }
            else if(leftbumper1){
                roboNav.turnLeft(0.75);
            }
            else if(rightbumper1){
                roboNav.turnRight(0.75);
            }
            else if(ybutton){
                hook.attach();
            }
            else if(bbutton){
                hook.detach();
            }
            else if (x2){
                if(gripperPos==false){
                    Gripper.grabIn();
                    gripperPos=true;
                }
                else{
                    Gripper.release();
                    gripperPos=false;
                }
            }

            else{
                hWinch.stop();
                vWinch.stop();
            }

        }



    }
}

