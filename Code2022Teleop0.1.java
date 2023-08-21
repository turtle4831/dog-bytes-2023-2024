package CHSShortCircuits;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "PowerPlay")

public class Code2022Teleop extends LinearOpMode 
{
    private DcMotor motor_left;
    private DcMotor motor_right;
    private DcMotor motor_B_left;
    private DcMotor motor_B_right;
    private DcMotor Slider;
    private Servo Chomp;
    private DcMotor Cork;
    
    public final static double CHOMP_HOME=0.2; //START POS FOR CLAW
    public final static double CHOMP_MIN_RANGE=0.25; // SMALL# VALUE ALLOWED FOR CHOMP POS
    public final static double CHOMP_MAX_RANGE=0.8; //LARG# VALUE ALLOWED FOR CHOMP POS
    
    double ChompPosition= CHOMP_HOME; //SET SAFE POS
    final double CHOMP_SPEED=0.04; //SET RATE TO MOVE SERVO
    // todo: write your code here//
    @Override
    public void runOpMode() {
        motor_left = hardwareMap.get(DcMotor.class,"motor_left");
        motor_right = hardwareMap.get(DcMotor.class,"motor_right");
        motor_B_left = hardwareMap.get(DcMotor.class,"motor_B_left");
        motor_B_right = hardwareMap.get(DcMotor.class,"motor_B_right");
        //Cork = hardwareMap.get(DcMotor.class,"Cork");
        Chomp = hardwareMap.get(Servo.class,"Chomp");
        Slider = hardwareMap.get(DcMotor.class,"Slider");
        waitForStart();
        
        motor_right.setDirection(DcMotor.Direction.REVERSE);
        motor_B_right.setDirection(DcMotor.Direction.REVERSE);
        
        
        while(opModeIsActive()) {
            //Motors move forward and Backward//
           motor_left.setPower(1 * gamepad1.left_stick_y);
           motor_right.setPower(1 * gamepad1.left_stick_y);
           motor_B_left.setPower(1 * gamepad1.left_stick_y);
           motor_B_right.setPower(1 * gamepad1.left_stick_y);
           
           //Motor Crabbing Left//
           motor_left.setPower(-1 * gamepad1.left_stick_x);
           motor_right.setPower(1 * gamepad1.left_stick_x);
           motor_B_left.setPower(1 * gamepad1.left_stick_x);
           motor_B_right.setPower(-1 * gamepad1.left_stick_x);
           
           //Turning Left And Right//
           
           motor_left.setPower(-1 * gamepad1.right_stick_x);
           motor_right.setPower(1 * gamepad1.right_stick_x);
           motor_B_left.setPower(-1 * gamepad1.right_stick_x);
           motor_B_right.setPower(1 * gamepad1.right_stick_x);
           
           //Cork.setPower(1 * gamepad2.right_trigger);
                      // linear slider //
            Slider.setPower(-1.2 * gamepad2.left_stick_y);
            
            
            //      CHOMPER    //
            
            if (gamepad2.x) {
                ChompPosition += CHOMP_SPEED;
                
            }
            else if (gamepad2.b) {
               ChompPosition -= CHOMP_SPEED;
            }
            ChompPosition = Range.clip(ChompPosition, CHOMP_MIN_RANGE, CHOMP_MAX_RANGE);
            Chomp.setPosition(ChompPosition);
            

            
        }
    
    }   
           
    }
