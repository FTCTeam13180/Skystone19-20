package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Detector{
    public LinearOpMode opMode;
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private static final String VUFORIA_KEY =
            "AVdwfAr/////AAABmdM3Kk8IxkDQjQG71A+rk8NU2OUbDsWM9YiWVWAkOBcldvIm6Cw/4Iu6f7wudYpOXealww8jyuj9cBAIic0AJfjnD/DqTPKQhKx+UIpZ0wjBWJJxFeNlenuMS2ZDNjsf3OwqQIyykOGtHL3UqX3fyTiCeTjBCm7BsyXQ1tAQATkOyg4MyW3XPl2LHif449qOuHg4RjAByXEZpRQJAlsxwea3CW9Dl66DQgPEjeub5HCHW+NiM1WKEZ5rQyBcS9+ZgkSdzYmiEVtMNJApGv1P8kew2WZ39FWKu+LCLo0cbuTRWtq98ANlCUtFjPUwFzbuwbUUhePfHhtLGGA9lvJkw4y4XP/r3P7iV3ouLORBDYMJ";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    Detector(LinearOpMode op) {
        opMode = op;
    }
    public void initVuforia(){
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        //int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
        //        "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters();
        tfodParameters.minimumConfidence = 0.75;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
    public void init() {
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        }
        tfod.activate();
    }

    public float scan(){
        List <Recognition> recognitions= tfod.getUpdatedRecognitions();
        while(recognitions==null){
            recognitions=tfod.getUpdatedRecognitions();
        }
        float left_bound=0;
        float right_bound=0;
        for(Recognition recog: recognitions){
            if(recog.getLabel().equals("Skystone")){
                left_bound=recog.getLeft();
                right_bound=recog.getRight();
                break;
            }
        }
        return (right_bound+left_bound)/2;
    }

}

