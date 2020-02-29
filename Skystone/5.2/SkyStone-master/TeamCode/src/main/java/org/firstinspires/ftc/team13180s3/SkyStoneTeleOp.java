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
    int currotations=0;
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
        double arm_vert;
        boolean gripper_release;
        boolean gripper_grab;
        boolean grabber_spin_left;
        boolean grabber_spin_right;
        double shift;
        boolean home;
        boolean play;

        /*
        Anton:
        left bumper--> arm in
        right bumper  arm out
        dpad left/right--> spin grabber
        right stick--> shift left/right rlly slowly
        a is close
        b is open
        left stick elevator up
        */

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
            arm_vert=gamepad2.left_stick_y;
            gripper_release=gamepad2.b;
            gripper_grab=gamepad2.a;
            shift = gamepad2.right_stick_x;
            grabber_spin_left=gamepad2.dpad_left;
            grabber_spin_right=gamepad2.dpad_right;
            home = gamepad2.x;
            play = gamepad2.y;

            // Navigation Controls
            if(nav_speed_up){
                multiplier= 1.0;
            }
            else if(nav_speed_down){
                multiplier = 0.5;
            }

//            if(nav_init_IMU){
//                roboNav.ResetImu();
//            }

            if(Math.abs(nav_omni_x)> 0.1 || Math.abs(nav_omni_y) > 0.1 || nav_left_turn || nav_right_turn){
                if(Math.abs(nav_omni_x)> 0.1 || Math.abs(nav_omni_y) > 0.1) {
                    // VERY IMPORTANT: Gamepad joystick y-axis positive points downward, x-axis points right
                    // Always remember to reverse joystick stick_y value.

                    // OmniImu still has a bug where left and right are reversed, until that is fixed we use AnyMecanum drive.
                    telemetry.addData("", "x=%f, y=%f", nav_omni_x, nav_omni_y);

//                    roboNav.OmniImu(nav_omni_x, -nav_omni_y, multiplier);
//                    roboNav.AnyMecanum(nav_omni_x, -nav_omni_y, multiplier);
                    roboNav.AnyMecanumSwerve(nav_omni_x, -nav_omni_y, multiplier);
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
                elevator.goOutbyOne();
            }
            else if(arm_in){
                elevator.goInbyOne();
            }

            if(arm_vert>0){
                elevator.goUp(Math.abs(arm_vert));
            }
            else if(arm_vert<0){
                elevator.goDown(Math.abs(arm_vert));
            }
            else {
                elevator.stopUpDown();
            }

            if(gripper_release){
                Gripper.release();
            }
            else if(gripper_grab){
                Gripper.grabIn();
            }

            if(grabber_spin_left){
                Gripper.rotateIncrementalAntiClockwise();
            }
            else if(grabber_spin_right){
                Gripper.rotateIncrementalClockwise();
            }

            if (shift<0) {
                roboNav.encoderDrive(RoboNavigator.DIRECTION.SHIFT_LEFT, shift*-1, 5, 5000);
                //roboNav.shiftLeft(multiplier);
            }
            else if (shift>0) {
                roboNav.encoderDrive(RoboNavigator.DIRECTION.SHIFT_RIGHT, shift, 5, 5000);
                //roboNav.shiftRight(multiplier);
            }

            if (home) {
                Gripper.rotateToDegrees_180();
                Gripper.release();
                elevator.homeposition();
            }

            if (play) {
                elevator.playposition();
                Gripper.release();
                Gripper.rotateToDegrees_0();
            }

            // Foundation Hook Controls
            if(hook_attach){
                hook.attach();
            }
            else if(hook_detach){
                hook.detach();
            }
            else if(gamepad1.dpad_right){
                hook.halfattach(0.4);
            }

            if(gamepad1.right_stick_x < 0){
                roboNav.shiftLeft(multiplier);
            }
            else if(gamepad1.right_stick_x >0){
                roboNav.shiftRight(multiplier);
            }
        }

    }

}

