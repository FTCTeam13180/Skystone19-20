package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestTensorFlow", group="manualmode")
public class TestTensorFlow extends LinearOpMode {
    public SkyStoneTensorFlow detect;
    @Override
    public void runOpMode() throws InterruptedException {
        detect = new SkyStoneTensorFlow();
        detect.initvuforia(this);
        detect.initTfod(this);
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.x){
                detect.activate();
            }
            if(gamepad1.b){
                boolean skystone = detect.scan();
                if(skystone){
                    telemetry.addData("Skystone Found",1);
                    telemetry.update();

                }
            }

            }

        }
    }
