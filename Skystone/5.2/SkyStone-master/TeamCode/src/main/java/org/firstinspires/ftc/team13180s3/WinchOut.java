package org.firstinspires.ftc.team13180s3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.abs;

public class WinchOut {
    public LinearOpMode opMode;
    private CRServo winchServo;


    private final double MSECS_PER_ROTATION = 840;

    WinchOut(LinearOpMode op) {
        opMode = op;
    }

    public void init() {
        winchServo = opMode.hardwareMap.get(CRServo.class, "horizontalWinch");
    }

    public void goOut(double power) {
        winchServo.setPower(abs(power));
    }

    public void goIn(double power) {
        winchServo.setPower(-abs(power));
    }

    public void stop() {
        winchServo.setPower(0);
    }

    public void goOutByRotations(double power, double rotations) {
        goOut(power);
        opMode.sleep((long)(rotations * MSECS_PER_ROTATION));
        stop();
    }

    public void goInByRotations(double power, double rotations) {
        goIn(power);
        opMode.sleep((long)(rotations * MSECS_PER_ROTATION));
        stop();
    }

}