package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestElevator", group="manualmode")
public class TestElevator extends LinearOpMode {
    public Elevator elevator;
    @Override
    public void runOpMode() throws InterruptedException {
        elevator =new Elevator (this);
        elevator.init();
        waitForStart();
        while (opModeIsActive()){
            double updir=gamepad1.left_stick_y;
            double sidedir=gamepad1.right_stick_x;
            if (Math.abs(updir)>.1){
                elevator.goUp(updir);
                //in/out winch controls
            } else if(Math.abs(sidedir)>.1) {
                elevator.goOut(sidedir);
            }
              else if(gamepad2.y){
                elevator.goOutByRotations(1.0,5);
            }
              else if(gamepad2.x){
                elevator.goInByRotations(1.0, 5);
            }
              else if(gamepad2.right_bumper){
                  elevator.goOut(1.0);
            }
              else if(gamepad2.left_bumper){
                  elevator.goIn(1.0);
            }

              //up and down winch controls

              else if(gamepad1.dpad_up){
                  elevator.goUp(1.0);
            }
              else if(gamepad1.dpad_down){
                  elevator.goDown(1.0);
            }
             else if(gamepad1.a){
                elevator.upDownEncoderDrive(0.8,2*2.54,5000);
            }
             else{
                elevator.stopInOut();
                elevator.stopUpDown();
            }

        }
    }
}
