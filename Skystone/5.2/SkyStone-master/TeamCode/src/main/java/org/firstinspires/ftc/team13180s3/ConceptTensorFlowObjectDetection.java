/* Copyright (c) 2018 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.team13180s3;

import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@TeleOp(name = "Concept: TensorFlow Object Detection", group = "Concept")
@Disabled
public class ConceptTensorFlowObjectDetection extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY  = "AVdwfAr/////AAABmdM3Kk8IxkDQjQG71A+rk8NU2OUbDsWM9YiWVWAkOBcldvIm6Cw/4Iu6f7wudYpOXealww8jyuj9cBAIic0AJfjnD/DqTPKQhKx+UIpZ0wjBWJJxFeNlenuMS2ZDNjsf3OwqQIyykOGtHL3UqX3fyTiCeTjBCm7BsyXQ1tAQATkOyg4MyW3XPl2LHif449qOuHg4RjAByXEZpRQJAlsxwea3CW9Dl66DQgPEjeub5HCHW+NiM1WKEZ5rQyBcS9+ZgkSdzYmiEVtMNJApGv1P8kew2WZ39FWKu+LCLo0cbuTRWtq98ANlCUtFjPUwFzbuwbUUhePfHhtLGGA9lvJkw4y4XP/r3P7iV3ouLORBDYMJ";;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      telemetry.addData("# Object Detected", updatedRecognitions.size());
                      if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                          if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                          } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                          } else {
                            silverMineral2X = (int) recognition.getLeft();
                          }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                          if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left");
                          } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                          } else {
                            telemetry.addData("Gold Mineral Position", "Center");
                          }
                        }
                      }
                      telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    /**
     * {@link SensorBNO055IMUCalibration} calibrates the IMU accelerometer per
     * "Section 3.11 Calibration" of the BNO055 specification.
     *
     * <p>Manual calibration of the IMU is definitely NOT necessary: except for the magnetometer
     * (which is not used by the default {@link BNO055IMU.SensorMode#IMU
     * SensorMode#IMU}), the BNO055 is internally self-calibrating and thus can be very successfully
     * used without manual intervention. That said, performing a one-time calibration, saving the
     * results persistently, then loading them again at each run can help reduce the time that automatic
     * calibration requires.</p>
     *
     * <p>This summary of the calibration process, from <a href="http://iotdk.intel.com/docs/master/upm/classupm_1_1_b_n_o055.html">
     * Intel</a>, is informative:</p>
     *
     * <p>"This device requires calibration in order to operate accurately. [...] Calibration data is
     * lost on a power cycle. See one of the examples for a description of how to calibrate the device,
     * but in essence:</p>
     *
     * <p>There is a calibration status register available [...] that returns the calibration status
     * of the accelerometer (ACC), magnetometer (MAG), gyroscope (GYR), and overall system (SYS).
     * Each of these values range from 0 (uncalibrated) to 3 (fully calibrated). Calibration [ideally]
     * involves certain motions to get all 4 values at 3. The motions are as follows (though see the
     * datasheet for more information):</p>
     *
     * <li>
     *     <ol>GYR: Simply let the sensor sit flat for a few seconds.</ol>
     *     <ol>ACC: Move the sensor in various positions. Start flat, then rotate slowly by 45
     *              degrees, hold for a few seconds, then continue rotating another 45 degrees and
     *              hold, etc. 6 or more movements of this type may be required. You can move through
     *              any axis you desire, but make sure that the device is lying at least once
     *              perpendicular to the x, y, and z axis.</ol>
     *     <ol>MAG: Move slowly in a figure 8 pattern in the air, until the calibration values reaches 3.</ol>
     *     <ol>SYS: This will usually reach 3 when the other items have also reached 3. If not, continue
     *              slowly moving the device though various axes until it does."</ol>
     * </li>
     *
     * <p>To calibrate the IMU, run this sample opmode with a gamepad attached to the driver station.
     * Once the IMU has reached sufficient calibration as reported on telemetry, press the 'A'
     * button on the gamepad to write the calibration to a file. That file can then be indicated
     * later when running an opmode which uses the IMU.</p>
     *
     * <p>Note: if your intended uses of the IMU do not include use of all its sensors (for exmaple,
     * you might not use the magnetometer), then it makes little sense for you to wait for full
     * calibration of the sensors you are not using before saving the calibration data. Indeed,
     * it appears that in a SensorMode that doesn't use the magnetometer (for example), the
     * magnetometer cannot actually be calibrated.</p>
     *
     * @see AdafruitBNO055IMU
     * @see BNO055IMU.Parameters#calibrationDataFile
     * @see <a href="https://www.bosch-sensortec.com/bst/products/all_products/bno055">BNO055 product page</a>
     * @see <a href="https://ae-bst.resource.bosch.com/media/_tech/media/datasheets/BST_BNO055_DS000_14.pdf">BNO055 specification</a>
     */
    @TeleOp(name = "Sensor: BNO055 IMU Calibration", group = "Sensor")
    @Disabled                            // Uncomment this to add to the opmode list
    public static class SensorBNO055IMUCalibration extends LinearOpMode
        {
        //----------------------------------------------------------------------------------------------
        // State
        //----------------------------------------------------------------------------------------------

        // Our sensors, motors, and other devices go here, along with other long term state
        BNO055IMU imu;

        // State used for updating telemetry
        Orientation angles;

        //----------------------------------------------------------------------------------------------
        // Main logic
        //----------------------------------------------------------------------------------------------

        @Override public void runOpMode() {

            telemetry.log().setCapacity(12);
            telemetry.log().add("");
            telemetry.log().add("Please refer to the calibration instructions");
            telemetry.log().add("contained in the Adafruit IMU calibration");
            telemetry.log().add("sample opmode.");
            telemetry.log().add("");
            telemetry.log().add("When sufficient calibration has been reached,");
            telemetry.log().add("press the 'A' button to write the current");
            telemetry.log().add("calibration data to a file.");
            telemetry.log().add("");

            // We are expecting the IMU to be attached to an I2C port on a Core Device Interface Module and named "imu".
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.loggingEnabled = true;
            parameters.loggingTag     = "IMU";
            imu = hardwareMap.get(BNO055IMU.class, "imu");
            imu.initialize(parameters);

            composeTelemetry();
            telemetry.log().add("Waiting for start...");

            // Wait until we're told to go
            while (!isStarted()) {
                telemetry.update();
                idle();
            }

            telemetry.log().add("...started...");

            while (opModeIsActive()) {

                if (gamepad1.a) {

                    // Get the calibration data
                    BNO055IMU.CalibrationData calibrationData = imu.readCalibrationData();

                    // Save the calibration data to a file. You can choose whatever file
                    // name you wish here, but you'll want to indicate the same file name
                    // when you initialize the IMU in an opmode in which it is used. If you
                    // have more than one IMU on your robot, you'll of course want to use
                    // different configuration file names for each.
                    String filename = "AdafruitIMUCalibration.json";
                    File file = AppUtil.getInstance().getSettingsFile(filename);
                    ReadWriteFile.writeFile(file, calibrationData.serialize());
                    telemetry.log().add("saved to '%s'", filename);

                    // Wait for the button to be released
                    while (gamepad1.a) {
                        telemetry.update();
                        idle();
                    }
                }

                telemetry.update();
            }
        }

        void composeTelemetry() {

            // At the beginning of each telemetry update, grab a bunch of data
            // from the IMU that we will then display in separate lines.
            telemetry.addAction(new Runnable() { @Override public void run()
                    {
                    // Acquiring the angles is relatively expensive; we don't want
                    // to do that in each of the three items that need that info, as that's
                    // three times the necessary expense.
                    angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                    }
                });

            telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                        }
                    })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                        }
                    });

            telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                        }
                    })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                        }
                    })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                        }
                    });
        }

        //----------------------------------------------------------------------------------------------
        // Formatting
        //----------------------------------------------------------------------------------------------

        String formatAngle(AngleUnit angleUnit, double angle) {
            return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
        }

        String formatDegrees(double degrees){
            return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
        }
    }
}
