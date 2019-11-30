package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorDistance {
    public LinearOpMode opMode;
    private DistanceSensor sensorDistance;

    SensorDistance(LinearOpMode op) {
        opMode = op;
    }

    public void init() {
        sensorDistance = opMode.hardwareMap.get(DistanceSensor.class, "sensor_color_distance");
    }
    public double getDistance(){
         return sensorDistance.getDistance(DistanceUnit.CM);
    }

}

