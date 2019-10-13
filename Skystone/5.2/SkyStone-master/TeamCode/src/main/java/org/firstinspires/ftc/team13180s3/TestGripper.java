package org.firstinspires.ftc.team13180s3;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="TestGripper", group="manualmode")
public class TestGripper extends LinearOpMode {

    private Servo test1;
    private Servo test2;
    @Override
    public void runOpMode() {
        test1 = this.hardwareMap.get(Servo.class , "Ser1");
        test2=this.hardwareMap.get(Servo.class,"Ser2");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                test1.setPosition(1);
                test2.setPosition(1);
            }
            else if (gamepad1.b){
                test1.setPosition(0);
                test2.setPosition(0);
            }
        }
    }
}