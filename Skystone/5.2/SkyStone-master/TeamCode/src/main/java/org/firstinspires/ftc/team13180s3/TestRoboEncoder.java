package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TestRoboEncoder", group = "manualmode")
public class TestRoboEncoder extends LinearOpMode {
    private DcMotor dcMotor;

    public void runOpMode() throws InterruptedException {

        dcMotor = hardwareMap.get(DcMotor.class, "dcMotor");


        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                telemetry.addData("TestRoboEncoder", "Initialized");

                telemetry.addData("curr:", "%d", dcMotor.getCurrentPosition());

                dcMotor.setTargetPosition(dcMotor.getCurrentPosition() + 1440);

                telemetry.addData("target:", "%d", dcMotor.getTargetPosition());

                dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                dcMotor.setPower(1.0);

                telemetry.update();
            }

            if (gamepad1.a) {
                telemetry.addData("Encoder ValueS = ", "%7d", dcMotor.getCurrentPosition());
                telemetry.update();
            }
        }
    }
}

