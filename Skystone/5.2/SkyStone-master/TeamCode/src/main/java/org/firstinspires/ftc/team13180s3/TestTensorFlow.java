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
                boolean skytone = detect.scan();
                /*if(skytone == true) {
                    telemetry.addData("Yes", "Skystone Detected");
                } else {
                    telemetry.addData("No", "NO Skystone Detected");
                }
                telemetry.update();
                */
            }

            }

        }
    }
