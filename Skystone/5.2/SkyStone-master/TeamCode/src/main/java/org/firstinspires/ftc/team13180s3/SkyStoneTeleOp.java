package org.firstinspires.ftc.team13180s3;

import  com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SkyStoneTeleOp", group = "manualmode")
public class SkyStoneTeleOp extends LinearOpMode {
    private RoboNavigator roboNav;
    private Elevator elevator;
    private Grabber Gripper;
    private Hook hook;
    double vWinchPowerUp=1.0;
    double vWinchPowerDown=0.6;
    double multiplier=0.5;
    boolean hookPosition = false; //False=Up, True=Down
    boolean gripperPos=false;
    public void runOpMode (){
        roboNav = new RoboNavigator(this);
        elevator =new Elevator(this);
        Gripper=new Grabber(this);
        hook=new Hook(this);
        roboNav.init();
        roboNav.initIMU();
        elevator.init();
        Gripper.init();
        hook.init();
        waitForStart();

        //GAMEPAD 1 (START A) NAVIGATOR CONTROLS
        double nav_omni_x;
        double nav_omni_y;
        boolean nav_init_IMU;
        boolean nav_left_turn;
        boolean nav_right_turn;
        boolean nav_speed_up;
        boolean nav_speed_down;
        boolean hook_attach;
        boolean hook_detach;

        //GAMEPAD 2 (START B) ARM CONTROLS
        boolean arm_in;
        boolean arm_out;
        boolean arm_up;
        boolean arm_down;
        boolean gripper_release;
        boolean gripper_grab;
        boolean shift_left;
        boolean shift_right;

        while (opModeIsActive()){
            //GAMEPAD 1 (START A) NAVIGATOR CONTROLS
            nav_omni_x=gamepad1.left_stick_x;
            nav_omni_y=gamepad1.left_stick_y;
            hook_attach=gamepad1.y;
            hook_detach=gamepad1.x;
            nav_left_turn=gamepad1.left_bumper;
            nav_right_turn=gamepad1.right_bumper;
            nav_speed_up=gamepad1.a;
            nav_speed_down=gamepad1.b;
            nav_init_IMU=gamepad1.dpad_left;

            //GAMEPAD 2 (START B) ARM CONTROLS
            arm_in=gamepad2.left_bumper;
            arm_out=gamepad2.right_bumper;
            arm_up=gamepad2.dpad_up;
            arm_down=gamepad2.dpad_down;
            gripper_release=gamepad2.x;
            gripper_grab=gamepad2.y;
            shift_left = gamepad2.a;
            shift_right = gamepad2.b;

            // Navigation Controls
            if(nav_speed_up){
                if(multiplier<1) {
                    multiplier += 0.1;
                }
            }
            else if(nav_speed_down){
                if(multiplier>0.5) {
                    multiplier -= 0.1;
                }
            }

            if(nav_init_IMU){
                roboNav.initIMU();
            }

            if(Math.abs(nav_omni_x)> 0.1 || Math.abs(nav_omni_y) > 0.1 || nav_left_turn || nav_right_turn){
                if(Math.abs(nav_omni_x)> 0.1 || Math.abs(nav_omni_y) > 0.1) {
                    // VERY IMPORTANT: Gamepad joystick y-axis positive points downward, x-axis points right
                    // Always remember to reverse joystick stick_y value.

                    // OmniImu still has a bug where left and right are reversed, until that is fixed we use AnyMecanum drive.
                    roboNav.OmniImu(nav_omni_x, -nav_omni_y, multiplier);
                    //roboNav.AnyMecanum(nav_omni_x, -nav_omni_y, multiplier);
                }
                // Allow tight turns while
                if(nav_left_turn){
                    roboNav.turnLeft(multiplier);
                }
                else if(nav_right_turn){
                    roboNav.turnRight(multiplier);
                }
            }
            else{
                roboNav.stopMotor();
            }

            // Arm Controls
            if(arm_out){
                elevator.goOut(1);
            }
            else if(arm_in){
                elevator.goIn(1);
            }
            else {
                elevator.stopInOut();
            }

            if(arm_up){
                elevator.goUp(vWinchPowerUp);
                //vWinch.encoderDrive(1,6,1000);
            }
            else if(arm_down){
                elevator.goDown(vWinchPowerDown);
                //vWinch.encoderDrive(1,-6,1000);
            }
            //
            else {
                elevator.stopUpDown();
            }

            if(gripper_release){
                Gripper.release();
            }
            else if(gripper_grab){
                Gripper.grabIn();
            }

            if (shift_left) {
                roboNav.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, 0.6, 5, 5000);
            }
            else if (shift_right) {
                roboNav.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, 0.6, 5, 5000);
            }

            // Foundation Hook Controls
            if(hook_attach){
                hook.attach();
            }
            else if(hook_detach){
                hook.detach();
            }
            else if(gamepad1.dpad_right){
                hook.halfattach(0.5);
            }
        }

    }
}

