package org.firstinspires.ftc.team13180s3;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class StoneColorSensor {
    public LinearOpMode opMode;
    private ColorSensor colorSensor;
    // hsvValues is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;

    StoneColorSensor (LinearOpMode op)
    {
        opMode = op;
    }

    public void init() {
        colorSensor = opMode.hardwareMap.get(ColorSensor.class, "sensor_color_distance");
    }

    public ColorSensor getColorSensor() {

        return colorSensor;
    }

    public boolean yello(){
        if(colorSensor.red() >= colorSensor.blue() && colorSensor.green()>= colorSensor.blue()){
           return true;
        }
        return false;
    }
    }
