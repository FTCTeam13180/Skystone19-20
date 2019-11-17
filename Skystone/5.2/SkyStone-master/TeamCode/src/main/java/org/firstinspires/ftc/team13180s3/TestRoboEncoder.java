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
        dcMotor.setDirection(DcMotor.Direction.FORWARD);
        dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        dcMotor.setTargetPosition(1440);
        dcMotor.setPower(1);
        dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(opModeIsActive()){

            while(dcMotor.isBusy()){
                telemetry.addData("IsBusy() cur = ", "%7d", dcMotor.getCurrentPosition());
                telemetry.update();
            }
            dcMotor.setPower(0);


            }
            if (gamepad1.a) {
                telemetry.addData("Encoder ValueS = ", "%7d", dcMotor.getCurrentPosition());
                telemetry.update();
            }
        }
    }


