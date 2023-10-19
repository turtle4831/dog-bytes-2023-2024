package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@Autonomous(name="dogBytesAutoBlueEncoder")
public class dogBytesDriveByEncoderBlue extends LinearOpMode {
   static String direction;
    final double WHEEL_DIAMETER = 96.0;
    final double PULSES_PER_REV = 1120;
    final double GEAR_REDUCTION = 2.0;
    final double COUNTS_PER_INCH = (PULSES_PER_REV * GEAR_REDUCTION) / (WHEEL_DIAMETER * 3.1415); //in inches
    public DcMotor FL_Motor;
    public DcMotor FR_Motor;
    public DcMotor BR_Motor;
    public DcMotor BL_Motor;
    public DistanceSensor sensorLeft;
    public DistanceSensor sensorRight;
    public ElapsedTime runtime = new ElapsedTime();
    static boolean search = true;



    public void encoderDrive(double speed, double leftInches, double rightInches) {
        int newFrontLeft;
        int newBottomLeft;
        int newFrontRight;
        int newBottomRight;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeft = FL_Motor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newBottomLeft = BL_Motor.getCurrentPosition() + (int) (leftInches *COUNTS_PER_INCH);
            newBottomRight = BR_Motor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newFrontRight = FR_Motor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);


            FL_Motor.setTargetPosition(newFrontLeft);
            BL_Motor.setTargetPosition(newBottomLeft);
            FR_Motor.setTargetPosition(newFrontRight);
            BR_Motor.setTargetPosition(newBottomRight);

            // Turn On RUN_TO_POSITION
            FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();

            FL_Motor.setPower(Math.abs(speed));
            BL_Motor.setPower(Math.abs(speed));
            FR_Motor.setPower(Math.abs(speed));
            BR_Motor.setPower(Math.abs(speed));

            // Turn off RUN_TO_POSITION
            FL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
            FL_Motor.setPower(0);
            FR_Motor.setPower(0);
            BL_Motor.setPower(0);
            BR_Motor.setPower(0);
        }
    }



        public void Strafe(Double speed, String direction, Double distance){

        int newFrontLeft;
        int newBottomLeft;
        int newFrontRight;
        int newBottomRight;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

                if(direction.equals("Left")){
            newFrontLeft = FL_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH);
            newBottomLeft = BL_Motor.getCurrentPosition() + (int) (-distance *COUNTS_PER_INCH);
            newBottomRight = BR_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH);
            newFrontRight = FR_Motor.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);


            FL_Motor.setTargetPosition(newFrontLeft);
            BL_Motor.setTargetPosition(newBottomLeft);
            FR_Motor.setTargetPosition(newFrontRight);
            BR_Motor.setTargetPosition(newBottomRight);

            // Turn On RUN_TO_POSITION
            FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();

            FL_Motor.setPower(Math.abs(speed));
            BL_Motor.setPower(Math.abs(speed));
            FR_Motor.setPower(Math.abs(speed));
            BR_Motor.setPower(Math.abs(speed));


            // Turn off RUN_TO_POSITION
            FL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);
                    FL_Motor.setPower(0);
                    FR_Motor.setPower(0);
                    BL_Motor.setPower(0);
                    BR_Motor.setPower(0);

                }else if(direction.equals("Right")){
            // Determine new target position, and pass to motor controller
            newFrontLeft = FL_Motor.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
            newBottomLeft = BL_Motor.getCurrentPosition() + (int) (distance *COUNTS_PER_INCH);
            newBottomRight = BR_Motor.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
            newFrontRight = FR_Motor.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH);


            FL_Motor.setTargetPosition(newFrontLeft);
            BL_Motor.setTargetPosition( newBottomLeft);
            FR_Motor.setTargetPosition(newFrontRight);
            BR_Motor.setTargetPosition(newBottomRight);

            // Turn On RUN_TO_POSITION
            FL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BL_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            FR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            BR_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();

            FL_Motor.setPower(Math.abs(speed));
            BL_Motor.setPower(Math.abs(speed));
            FR_Motor.setPower(Math.abs(speed));
            BR_Motor.setPower(Math.abs(speed));

            // Stop all motion;
            FL_Motor.setPower(0);
            FR_Motor.setPower(0);
            BL_Motor.setPower(0);
            BR_Motor.setPower(0);

            // Turn off RUN_TO_POSITION
            FL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }
}

        @Override
        public void runOpMode() {
            FL_Motor = hardwareMap.get(DcMotor.class, "FL_Motor");
            FR_Motor = hardwareMap.get(DcMotor.class, "FR_Motor");
            BL_Motor = hardwareMap.get(DcMotor.class, "BL_Motor");
            BR_Motor = hardwareMap.get(DcMotor.class, "BR_Motor");
            sensorLeft = hardwareMap.get(DistanceSensor.class, "sensorLeft");
            sensorRight = hardwareMap.get(DistanceSensor.class, "sensorRight");

            //set the motor direction
            FL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
            BL_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
            FR_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
            BR_Motor.setDirection(DcMotorSimple.Direction.FORWARD);
            //resets the encoders and starts them again cause i can
            FL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BL_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            FR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            BR_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            waitForStart();
            if(sensorLeft.getDistance(DistanceUnit.INCH) < 25 && search){
                //code for left
                telemetry.addData("Going left", sensorLeft.getDistance(DistanceUnit.INCH));
                encoderDrive(1.0,4.0,4.0); //forward 4 inches
                Strafe(1.0, "Right", 6.0);  //strafe right 6 inches
                encoderDrive(1.0,12.0,12.0); //forward 12 inches
                encoderDrive(1.0,-12.0,12.0);   //turn 90 degrees counter clockwise
                encoderDrive(1.0,6.0,6.0); //forward 6 inches
                encoderDrive(-1.0,-5.0,-5.0); //back 5 inches
                Strafe(1.0,"Right",15.0); //strafe right 15 inches
                encoderDrive(1.0,69.0, 69.0); //forward 69 inches
                Strafe(1.0,"Left",46.0); //strafe left 46 inches
                encoderDrive(1.0,23.0,23.0); // forward 23

                telemetry.addData("Parked", search);
                telemetry.update();
                direction = "Left";
                search = false;
            }else if(sensorRight.getDistance(DistanceUnit.INCH) < 25  && search){
                //code for right
                telemetry.addData("Going right", sensorRight.getDistance(DistanceUnit.INCH));
                Strafe(1.0,"Left", 11.5); //strafe 11.5 inches left
                encoderDrive(1.0,42.5,42.5); //forward 42.5 inches
                encoderDrive(-1.0,-4.0,-4.0);//back 4 inches
                Strafe(1.0,"Left", 12.0); //strafe left 12 inches
                encoderDrive(1.0,2.0,2.0); //forward 2 inches
                Strafe(1.0,"Left",69.0);//strafe left 69 inches
                encoderDrive(1.0,23.0,-23.0);//turn 90 clockwise
                Strafe(1.0,"Right",23.0);
                encoderDrive(-1.0,-35.0,-35.0); //back 35 inches

                telemetry.addData("Parked", search);

                telemetry.update();
                direction = "Right";
                search = false;
            }else if(sensorLeft.getDistance(DistanceUnit.INCH) > 25 && sensorRight.getDistance(DistanceUnit.INCH) > 25 && search){
                //code for middle
                telemetry.addData("Going middle", sensorLeft.getDistance(DistanceUnit.INCH));
                Strafe(1.0,"Right",8.5);//strafe 8.5 right
                encoderDrive(1.0,30.0,30.0);//forward 30 inches
                encoderDrive(-1.0,-4.0,-4.0);//back 4 inches
                Strafe(1.0,"Left",77.5);//strafe 77.5 left
                encoderDrive(1.0,23.0,-23.0);//turn 90 clockwise
                Strafe(1.0,"Right",23.0);//strafe 23 inches right
                encoderDrive(-1.0,-35.0,-35.0);//back 35

                telemetry.addData("Parked", search);
                telemetry.update();
                search = false;
                direction = "Middle";

            }else{
                telemetry.addData("No objects found",search);
                //will do the code for middle anyways
                Strafe(1.0,"Right",8.5);
                encoderDrive(1.0,30.0,30.0);
                encoderDrive(-1.0,-4.0,-4.0);
                Strafe(1.0,"Left",77.5);
                encoderDrive(1.0,23.0,-23.0); //turn 90 clockwise
                Strafe(1.0,"Right",23.0);
                encoderDrive(-1.0,-35.0,-35.0);

                telemetry.addData("Parked", search);
                telemetry.update();
            }

            if (opModeIsActive()) {
                while (opModeIsActive()) {
                    // adds the motor position to the telemetry
                    telemetry.addData("Front Left position", FL_Motor.getCurrentPosition());
                    telemetry.addData("Front Right position", FR_Motor.getCurrentPosition());
                    telemetry.addData("Back Left position", BL_Motor.getCurrentPosition());
                    telemetry.addData("Back Right position", BR_Motor.getCurrentPosition());
                    telemetry.addData("asdasdad", FL_Motor.getPower());
                    telemetry.addData("asdasdad", FR_Motor.getPower());
                    telemetry.addData("Which way the robot is going", direction);
                    telemetry.update();
                }
            }
        }
    }
