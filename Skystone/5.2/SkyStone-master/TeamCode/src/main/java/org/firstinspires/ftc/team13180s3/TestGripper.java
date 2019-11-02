package org.firstinspires.ftc.team13180s3;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="TestGripper", group="manualmode")
public class TestGripper extends LinearOpMode {

    public Grabber Gripper;
    @Override
    public void runOpMode() {
        Gripper=new Grabber(this);
        Gripper.init();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                Gripper.grabIn();
            }
            else if (gamepad1.b){
                Gripper.release();
            }
        }
    }
}