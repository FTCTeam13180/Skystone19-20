package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.hardware.HardwareMap;
import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class TestColorSensor {
    private ColorSensor colorSensor;

    boolean yellowColor = false;
    boolean blackColor = false;

    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }
    public ColorSensor getColorSensor() {
        return colorSensor;
    }
    public boolean isYellowColor() {
        /*redColor = false;
        blueColor = false;
        if(colorSensor.red() > 100 && colorSensor.green() < 100 &&  colorSensor.blue() < 100) {
            redColor = true;
            blueColor = false;
            return true;
        }*/
        if(colorSensor.red() > colorSensor.blue() & colorSensor.blue() > colorSensor.green()) {
            return true;
        }
        return false;
    }

    public boolean isBlackColor() {
        /*
        redColor = false;
        blueColor = false;
        if(colorSensor.red() < 100 && colorSensor.green() < 100 &&  colorSensor.blue() > 100) {
            redColor = false;
            blueColor = true;
            return true;
        }
        */

        if((colorSensor.blue() > 60) & (colorSensor.blue()) > 60){
            return true;
        }
        return false;
    }
}