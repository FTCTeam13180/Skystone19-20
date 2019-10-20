package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.hardware.HardwareMap;
import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class StoneColorSensor {
    private ColorSensor colorSensor;

    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
    }

    public ColorSensor getColorSensor() {
        return colorSensor;
    }

    public boolean isYellowColor() {
        if ((colorSensor.red() >= 60 && colorSensor.red() <= 65) &&
                (colorSensor.green() >= 50 && colorSensor.green() <= 55) &&
                (colorSensor.blue() >= 45 && colorSensor.blue() <= 50)) {
            return true;
        }
        return false;
    }

    public boolean isBlackColor() {

        if((colorSensor.red() >= 60 && colorSensor.red() <= 65) &&
                (colorSensor.green() >= 60 && colorSensor.green() <= 65) &&
                (colorSensor.blue() >= 35 && colorSensor.blue() <= 45)) {
            return true;
        }
        return false;
    }

}