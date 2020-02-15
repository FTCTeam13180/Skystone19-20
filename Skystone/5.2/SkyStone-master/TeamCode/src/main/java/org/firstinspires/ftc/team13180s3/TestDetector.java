package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import java.util.List;

@TeleOp(name="TestDetector", group="manualmode")
public class TestDetector extends LinearOpMode {
    public Detector detect;
    @Override
    public void runOpMode(){
        detect = new Detector(this);
        detect.init();
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.b){
                List<Recognition> blocks = detect.scan();
                int i=0;
                for(Recognition recog: blocks){
                    if(i==0){
                        telemetry.addData("Image Width: ", recog.getImageWidth());
                        telemetry.addData("Image Height",recog.getImageHeight());
                    }
                    telemetry.addData("Block Number: ",i);
                    telemetry.addData("Width",recog.getWidth());
                    telemetry.addData("Label",recog.getLabel());
                    telemetry.addLine();
                }
                telemetry.update();
            }
        }

        detect.shutdown();
    }
}
