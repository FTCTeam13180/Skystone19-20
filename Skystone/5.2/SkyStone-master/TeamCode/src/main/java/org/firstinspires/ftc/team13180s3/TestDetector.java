package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import java.util.List;

@TeleOp(name="TestDetector", group="manualmode")
public class TestDetector extends LinearOpMode {
    public Detector detect;
    private RoboNavigator robo;
    @Override
    public void runOpMode(){
        detect = new Detector(this);
        detect.init();
        robo = new RoboNavigator(this);
        robo.init();
        waitForStart();
        boolean foundSkytone = false;
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
            else if(gamepad1.a){
                List<Recognition> blocks = detect.scan();
                for(Recognition recog: blocks){

                        if(recog.getLabel().equalsIgnoreCase(Detector.LABEL_SECOND_ELEMENT)){
                            telemetry.addData("Image Width: ", recog.getImageWidth());
                            telemetry.addData("Image Height",recog.getImageHeight());
                            telemetry.addData("Label", recog.getLabel());
                            telemetry.addData("Left", recog.getLeft());
                            telemetry.addData("Right", recog.getRight());
                            float x  = Math.abs((recog.getRight()- recog.getLeft()) / 2);
                            if(x < 180){
                                telemetry.addLine("Position 2");
                                //robo.shiftRight(3,10000);
                                robo.shiftRight(4.25,1000);
                                foundSkytone = true;
                                break;
                            }
                            else if (x > 400){
                                telemetry.addLine("Position 3");
                                //robo.shiftRight(12,1000);
                                robo.shiftRight(11.75,1000);
                                foundSkytone = true;
                                break;
                            }
                        }
                        else{
                            telemetry.addLine("Position 1");
                            robo.shiftLeft(4.5,1000);
                            break;
                        }
                        telemetry.update();
                    }
                if(foundSkytone) {
                    robo.moveForward(24,10000);
                }
            }
        }

        detect.shutdown();
        //6.5 in
    }
}

