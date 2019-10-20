package org.firstinspires.ftc.team13180s3;
//osmehting
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="TestStoneColorSensor", group="manualMode")
public class TestStoneColorSensor extends LinearOpMode {
    private StoneColorSensor stoneSensor;

    @Override
    public void runOpMode() {
        stoneSensor= new StoneColorSensor();
        waitForStart();
        while (opModeIsActive()) {
            if(stoneSensor.isBlackColor()){
                telemetry.addLine("Black");
            }
            else if(stoneSensor.isYellowColor()){
                telemetry.addLine("Yellow");

            }
            telemetry.update();
        }
    }
}