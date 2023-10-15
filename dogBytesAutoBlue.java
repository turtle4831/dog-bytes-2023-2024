package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import java.util.List;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name="dogBytesAutoBlue")
public class dogBytesAutoBlue extends LinearOpMode {
    OpenCvCamera camera1;
    public DcMotor FL_Motor;
    public DcMotor FR_Motor;
    public DcMotor BR_Motor;
    public DcMotor BL_Motor;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    private static final String TFOD_MODEL_ASSET = "PowerPlay.tflite";
    // private static final String TFOD_MODEL_FILE  = "/sdcard/FIRST/tflitemodels/CustomTeamModel.tflite";
    private static final String VUFORIA_KEY = "AVwox1f/////AAABmYYBHxOVZkeXru6fgshIaWSINQ4TZRe1c8gc8RaqmYtNZ3q2V2lyxPOugWbwcftoT571ZD4ub4zfV1wmvYb/XeXV3c1fXUZupdXvsitnVLgA6alId1eeYDifBPQj8qQ3c3EIcjiDPKwkfjRSqo9HXyHUjST7HMDmryWwm7LxSGTp0MO7YjPawZyg1nE/Aj8A0/3s/KBrRkjhZCocGYPv9i2FP0vf9xe2xFxPQFcl8y8lsQaWDHPc9HXILexHucfGkYmre40IdM+fCs2dqzbUiFCnpz7k+k/IXu4P+ndFhziBED5BlJZOD84qRv5xVdKJV/uRqvXKFX4osdi3egdyA/yLCLa2v3L24WeZ0KYKEdYI";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    AprilTagDetection tagOfInterest = null;
    private static final String[] LABELS = {
            "1 TeamOBJ Left",
            "2 TeamOBJ Middle",
            "3 TeamObj Right"
    };
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    // UNITS ARE METERS
    double tagsize = 0.166;
    static final double FEET_PER_METER = 3.28084;
    int left = 1;
    int middle = 2;
    int right = 3;
    final double WHEEL_DIAMETER = 96.0;
    final double	PULSES_PER_REV = 1120;
    final double	GEAR_REDUCTION = 2.0;
    final double	COUNTS_PER_INCH = (PULSES_PER_REV  * GEAR_REDUCTION)  / (WHEEL_DIAMETER * 3.1415) ; //in inches
    private void Forward(double distance, double speed){
        FL_Motor.setTargetPosition(FL_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH)); //makes the motors go to
        FR_Motor.setTargetPosition(FR_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));// the target position given
        BL_Motor.setTargetPosition(BL_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));// by adding the current
        BR_Motor.setTargetPosition(BR_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));//position with the new wanted distance
        FL_Motor.setPower(speed);
        FR_Motor.setPower(speed);
        BL_Motor.setPower(speed);
        BR_Motor.setPower(speed);
        FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(20);
        FL_Motor.setPower(0);
        FR_Motor.setPower(0);
        BL_Motor.setPower(0);
        BR_Motor.setPower(0);

    }
    private void Backwards(double distance, double speed){
        distance = distance * -1;
        FL_Motor.setTargetPosition(FL_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));//does the stuff for forward
        FR_Motor.setTargetPosition(FR_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));// but makes the distance
        BL_Motor.setTargetPosition(BL_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));//value negative so it
        BR_Motor.setTargetPosition(BR_Motor.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH));// goes backwards
        FL_Motor.setPower(-speed);
        FR_Motor.setPower(-speed);
        BL_Motor.setPower(-speed);
        BR_Motor.setPower(-speed);
        FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(20);
        FL_Motor.setPower(0);
        FR_Motor.setPower(0);
        BR_Motor.setPower(0);
        BL_Motor.setPower(0);
    }

    private void strafe(String direction, double distance, double speed){
        if(direction.equals("Left")) {
            FL_Motor.setTargetPosition(FL_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH)); //makes the motors go to
            FR_Motor.setTargetPosition(FR_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH));// the target position given
            BL_Motor.setTargetPosition(BL_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH));// by adding the current
            BR_Motor.setTargetPosition(BR_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH));//position with the new wanted distance
            FL_Motor.setPower(-speed);
            FR_Motor.setPower(speed);
            BL_Motor.setPower(speed);
            BR_Motor.setPower(-speed);
            FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(20);
            FL_Motor.setPower(0);
            FR_Motor.setPower(0);
            BL_Motor.setPower(0);
            BR_Motor.setPower(0);
        }else if (direction.equals("Right")){
            FL_Motor.setTargetPosition(FL_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH)); //makes the motors go to
            FR_Motor.setTargetPosition(FR_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH));// the target position given
            BL_Motor.setTargetPosition(BL_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH));// by adding the current
            BR_Motor.setTargetPosition(BR_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH));//position with the new wanted distance
            FL_Motor.setPower(speed);
            FR_Motor.setPower(-speed);
            BL_Motor.setPower(-speed);
            BR_Motor.setPower(speed);
            FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(20);
            FL_Motor.setPower(0);
            FR_Motor.setPower(0);
            BL_Motor.setPower(0);
            BR_Motor.setPower(0);
        }
    }
    private void turn(String direction, double distance, double speed){
        int length = (int) (distance * COUNTS_PER_INCH) +
                FL_Motor.getCurrentPosition() +
                FR_Motor.getCurrentPosition() +
                BL_Motor.getCurrentPosition() +
                BR_Motor.getCurrentPosition();
        if (direction.equals("Left")){
            //code to turn left
            while(
                    length >= FL_Motor.getCurrentPosition() +
                            FR_Motor.getCurrentPosition() +
                            BL_Motor.getCurrentPosition() +
                            BR_Motor.getCurrentPosition()
            ) {
                FR_Motor.setPower(speed);
                BR_Motor.setPower(speed);
                FL_Motor.setPower(-speed);
                BL_Motor.setPower(-speed);

            }
        }else{
            //code to turn right
            while(
                    length >= FL_Motor.getCurrentPosition() +
                            FR_Motor.getCurrentPosition() +
                            BL_Motor.getCurrentPosition() +
                            BR_Motor.getCurrentPosition()
            ) {
                FL_Motor.setPower(speed);
                BL_Motor.setPower(speed);
                FR_Motor.setPower(-speed);
                BR_Motor.setPower(-speed);
            }
        }
    }


    private void initVuforia () {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "camera1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    private void initTfod () {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        // Use loadModelFromAsset() if the TF Model is built in as an asset by Android Studio
        // Use loadModelFromFile() if you have downloaded a custom team model to the Robot Controller's FLASH.
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        // tfod.loadModelFromFile(TFOD_MODEL_FILE, LABELS);
    }
    void tagToTelemetry (AprilTagDetection detection)
    {
        Orientation rot = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);

        telemetry.addData("\nDetected tag ID=%d", detection.id);
        telemetry.addData("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER);
        telemetry.addData("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER);
        telemetry.addData("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER);
        telemetry.addData("Rotation Yaw: %.2f degrees", rot.firstAngle);
        telemetry.addData("Rotation Pitch: %.2f degrees", rot.secondAngle);
        telemetry.addData("Rotation Roll: %.2f degrees", rot.thirdAngle);
        telemetry.update();
    }
    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can increase the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0 / 9.0);
        }
        //make the motors motor
        FL_Motor = hardwareMap.get(DcMotor.class, "FL_Motor");
        FR_Motor = hardwareMap.get(DcMotor.class, "FR_Motor");
        BL_Motor = hardwareMap.get(DcMotor.class, "BL_Motor");
        BR_Motor = hardwareMap.get(DcMotor.class, "BR_Motor");
        //reverse the motor direction
        FL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        BL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        //resets the encoders and starts them again cause i can
        FL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //runs the encoder
        FR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // adds the motor position to the telemetry
        telemetry.addData("Starting at", "%7d :%7d",
                FL_Motor.getCurrentPosition(),
                BL_Motor.getCurrentPosition(),
                FR_Motor.getCurrentPosition(),
                BR_Motor.getCurrentPosition());
        telemetry.update();
        // make the cameras camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera1 = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera1.setPipeline(aprilTagDetectionPipeline);
        //instance for the camera
        camera1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

            //if camera gets opened does this
            @Override
            public void onOpened() {
                camera1.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            // if it dosent it takes and error code and does something with it
            @Override
            public void onError(int errorCode) {

            }
        });
         telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        waitForStart();
        //on start

        while (opModeIsActive()) {
            Forward(15, 1.0);
            strafe("Left", 3, 0.5);
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    // step through the list of recognitions and display boundary info.
                    int i = 0;
                    for (Recognition recognition : updatedRecognitions) {
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                        i++;

                        // check label to decide the  next step
                        if (recognition.getLabel().equals("1 TeamOBJ Left")) {
                            //code if the object is on the left
                            //tells the driver hub the object used
                            telemetry.addData("Object Detected", "1 TeamOBJ Left");
                        } else if(recognition.getLabel().equals("2 TeamOBJ Middle")) {
                            //code if the object is on the Middle
                            //tells the driver hub the object used
                            telemetry.addData("Object Detected", "2 TeamOBJ Middle");
                        }else if(recognition.getLabel().equals("3 TeamOBJ Right")){
                            //code if the object is on the right
                            //tells the driver hub the object used
                            telemetry.addData("Object Detected", "3 TeamOBJ Right");
                        }else{
                            telemetry.addData("No object detected default auto", "GOING MIDDLE");

                        }


                    }
                    telemetry.update();
                }
            }
        }

        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
        //checks for detection
        if (currentDetections.size() != 0) {
            boolean tagFound = false;

            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == left || tag.id == middle || tag.id == right) {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }
            if (tagFound) {
                telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                tagToTelemetry(tagOfInterest);
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }



            telemetry.addData("FL_Motor Current encoder position", "%.2f", FL_Motor.getCurrentPosition());
            telemetry.addData("FL_Motor Current Power", "%.2f", FL_Motor.getPower());
            telemetry.addData("FR_Motor current encoder position", "%.2", FR_Motor.getCurrentPosition());
            telemetry.addData("FR_Motor Current Power", "%.2f", FR_Motor.getPower());
            telemetry.addData("BL_Motor Current encoder position", "%.2f", BL_Motor.getCurrentPosition());
            telemetry.addData("BL_Motor Current Power", "%.2f", BL_Motor.getPower());
            telemetry.addData("BR_Motor current encoder position", "%.2", BR_Motor.getCurrentPosition());
            telemetry.addData("BR_Motor Current Power", "%.2f", BR_Motor.getPower());
            telemetry.update();
            sleep(20);

        }
    }
}