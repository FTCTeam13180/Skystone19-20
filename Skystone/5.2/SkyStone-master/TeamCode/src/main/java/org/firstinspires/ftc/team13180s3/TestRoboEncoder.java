package org.firstinspires.ftc.team13180s3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TestRoboEncoder", group = "manualmode")
public class TestRoboEncoder extends LinearOpMode {
    private DcMotor dcMotor;

    public void runOpMode() throws InterruptedException {

        dcMotor = hardwareMap.get(DcMotor.class, "dcMotor");
        telemetry.addData("TestRoboEncoder", "Initialized");

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.dpad_up) {
                dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                dcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                telemetry.addData("Encoder Value = ", "%d", dcMotor.getCurrentPosition());
                dcMotor.setTargetPosition(1);
                dcMotor.setPower(1);
                telemetry.addData("Encoder Value = ", "%d", dcMotor.getCurrentPosition());

            }
            else if (gamepad1.a) {
                telemetry.update();
            }
        }
    }
}

