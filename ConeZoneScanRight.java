package DogBytes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous  (name = "ConeandZoneScanRight")

public class ConeZoneScanRight extends LinearOpMode {
    private DcMotor motor_left;
    private DcMotor motor_right;
    private DcMotor motor_B_left;
    private DcMotor motor_B_right;
    private DcMotor slider;
    
    private Servo Chomp;
    
    private ColorSensor Eyes;

    // Reset Encoders
    public void ResetEncodersDT(){
        motor_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_B_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor_B_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
   }
    
    
     //set target position
    public void RunPosDT(){
        motor_B_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_B_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    
    
    //drivetrain target position for forward and backward movement and turns
        public void SetTgPosDT(int Position1, int Position2){
            motor_left.setTargetPosition(Position1);
            motor_right.setTargetPosition(Position2);
            motor_B_left.setTargetPosition(Position1);
            motor_B_right.setTargetPosition(Position2);
        }
        
        
        // set target position for strafes
        public void SetTgPosStrafeDT(int Position1, int Position2, int Position3,int Position4){
            motor_B_left.setTargetPosition(Position1);
            motor_B_right.setTargetPosition(Position2);
            motor_left.setTargetPosition(Position3);
            motor_right.setTargetPosition(Position4);
            
        }
        
        
        //sets drivetrain velocity
            public void SetVelocityDT(int Power){
                ((DcMotorEx) motor_B_left).setVelocity(Power);
                ((DcMotorEx) motor_B_right).setVelocity(Power);
                ((DcMotorEx) motor_left).setVelocity(Power);
                ((DcMotorEx) motor_right).setVelocity(Power);
            }
            
            
            //wait for motors to finish running
            public void telemetry(){
                while(motor_B_left.isBusy()){
                    telemetry.addData("Status","Waiting for motors to reach their targets");
                    telemetry.update();
                }
            }
            
            
             public void runOpMode(){
            // naming motors
            motor_B_left = hardwareMap.get(DcMotor.class,"motor_B_left");
            motor_B_right = hardwareMap.get(DcMotor.class,"motor_B_right");
            motor_left = hardwareMap.get(DcMotor.class,"motor_left");
            motor_right = hardwareMap.get(DcMotor.class,"motor_right");
            slider = hardwareMap.get(DcMotor.class,"slider");
            
            Chomp = hardwareMap.get(Servo.class,"Chomp");
            
            Eyes = hardwareMap.get(ColorSensor.class,"Eyes");
            
            int Red;
            int Blue;
            int Green;
            
            
            ResetEncodersDT();
        
          //Chomp.setPosition(0.9);
         
         waitForStart();
         //sets motors to reverse
         motor_B_left.setDirection(DcMotorSimple.Direction.REVERSE);
         motor_left.setDirection(DcMotorSimple.Direction.REVERSE);
         
         
         
         // Move robot Left
         /*ResetEncodersDT();
         SetTgPosStrafeDT(700 ,-700,-700, 700);
         RunPosDT();
         SetVelocityDT(1000);
         telemetry();
         SetVelocityDT(0);
         sleep(1000);*/
         
         
         // strafe Right
         /*ResetEncodersDT();
         SetTgPosStrafeDT(-770,770,770,-770);
         RunPosDT();
         SetVelocityDT(1000);
         telemetry();
         SetVelocityDT(0);
         sleep(1000);*/
         
         
         //Move Forward
         ResetEncodersDT();
         SetTgPosDT(790,790);
         RunPosDT();
         SetVelocityDT(700);
         telemetry();
         SetVelocityDT(0);
         sleep(500);
         
         
         //adds red, green, and blue data to telemtry
         telemetry.addData("Red", Eyes.red());
         telemetry.addData("Blue", Eyes.blue());
         telemetry.addData("Green", Eyes.green());
         Red = Eyes.red();
         Blue = Eyes.blue();
         Green = Eyes.green();
         telemetry.update();
         sleep(1000);
         
         
         // if senses Red Go To Zone 1
         if(Red > Blue && Red > Green){
            
            telemetry.addLine("Red");
            telemetry.update();
            sleep(500);
            
            //Move Forward
                ResetEncodersDT();
                SetTgPosDT(1400,1400);
                RunPosDT();
                SetVelocityDT(900);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                // Turn Right 
                ResetEncodersDT();
                SetTgPosDT(750,-750);
                RunPosDT();
                SetVelocityDT(600);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                // Move Foward
                ResetEncodersDT();
                SetTgPosDT(762,762);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //lift up to the cone
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(440);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(1000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                    
                }
                sleep(400);
                
                //move forward
                ResetEncodersDT();
                SetTgPosDT(90,90);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //grab
                Chomp.setPosition(0.9);
                sleep(900);
                
                sleep(350);
                
                
                //lifts arm
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(1000);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(4000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                
                //lifts arm
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(720);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(4000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                //back up
                ResetEncodersDT();
                SetTgPosDT(-960,-960);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //turn left
                ResetEncodersDT();
                SetTgPosDT(1266,-1266);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //move to mid junction
                ResetEncodersDT();
                SetTgPosDT(253,253);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(-430);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(900);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                //claw open 
                Chomp.setPosition(0.1);
                
                //back up to green
                ResetEncodersDT();
                SetTgPosDT(-260,-260);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(600);
                
                //turn
                ResetEncodersDT();
                SetTgPosDT(379,-379);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
          
         
            //Move Forward
            ResetEncodersDT();
            SetTgPosDT(830,830);
            RunPosDT();
            SetVelocityDT(1000);
            telemetry();
            SetVelocityDT(0);
            sleep(500);
            
            
            
           /* //Strafe To left Zone 1
            ResetEncodersDT();
            SetTgPosDT(600,600);
            RunPosDT();
            SetVelocityDT(3000);
            telemetry();
            SetVelocityDT(0);
            sleep(500);*/
         }
            
            ResetEncodersDT();
            
            //if detects green move green Zone 2
            if (Green > Red && Green > Blue){
                telemetry.addLine("Green");
                telemetry.update();
                sleep(500);
                
                
                //Move Forward
                ResetEncodersDT();
                SetTgPosDT(1400,1400);
                RunPosDT();
                SetVelocityDT(900);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                // Turn Right 
                ResetEncodersDT();
                SetTgPosDT(750,-750);
                RunPosDT();
                SetVelocityDT(600);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                // Move Foward
                ResetEncodersDT();
                SetTgPosDT(762,762);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //lift up to the cone
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(440);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(1000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                    
                }
                sleep(400);
                
                //move forward
                ResetEncodersDT();
                SetTgPosDT(88,88);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //grab
                Chomp.setPosition(0.9);
                sleep(900);
                
                //lifts arm
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(1000);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(4000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                
                //lifts arm
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(720);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(4000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                //back up
                ResetEncodersDT();
                SetTgPosDT(-960,-960);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //turn left
                ResetEncodersDT();
                SetTgPosDT(1266,-1266);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //move to mid junction
                ResetEncodersDT();
                SetTgPosDT(260,260);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(-450);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(900);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                //claw open 
                Chomp.setPosition(0.1);
                
                //back up to green
                ResetEncodersDT();
                SetTgPosDT(-260,-260);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(600);
                
                //turn
                ResetEncodersDT();
                SetTgPosDT(350,-350);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                
               
            }
                
                //If Detects Blue Then Go to Zone 3
                if (Blue > Red && Blue > Green){
                    telemetry.addLine("Blue");
                    telemetry.update();
                  sleep(500);
                
                //Move Forward
                ResetEncodersDT();
                SetTgPosDT(1400,1400);
                RunPosDT();
                SetVelocityDT(900);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                // Turn Right 
                ResetEncodersDT();
                SetTgPosDT(750,-750);
                RunPosDT();
                SetVelocityDT(600);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                // Move Foward
                ResetEncodersDT();
                SetTgPosDT(762,762);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //lift up to the cone
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(440);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(1000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                    
                }
                sleep(600);
                
                //move forward
                ResetEncodersDT();
                SetTgPosDT(88,88);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //grab
                Chomp.setPosition(0.9);
                sleep(900);
                
                //lifts arm
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(1000);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(4000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                
                //lifts arm
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(720);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(4000);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                //back up
                ResetEncodersDT();
                SetTgPosDT(-960,-960);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //turn left
                ResetEncodersDT();
                SetTgPosDT(1266,-1266);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //move to mid junction
                ResetEncodersDT();
                SetTgPosDT(260,260);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                //
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slider.setTargetPosition(-450);
                slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                
                ((DcMotorEx) slider).setVelocity(900);
                
                
                while (slider. isBusy()) {
                    telemetry.addData("Status","waiting for the motors to reach their targets");
                    telemetry.update();
                }
                sleep(100);
                
                //claw open 
                Chomp.setPosition(0.1);
                
                //back up to green
                ResetEncodersDT();
                SetTgPosDT(-260,-260);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(600);
                
                //turn
                ResetEncodersDT();
                SetTgPosDT(350,-350);
                RunPosDT();
                SetVelocityDT(700);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                
                slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                
                
                
                
                //Move Backward
                ResetEncodersDT();
                SetTgPosDT(-800,-860);
                RunPosDT();
                SetVelocityDT(1300);
                telemetry();
                SetVelocityDT(0);
                sleep(500);
                
                
            }
            
            
            
         
         
         
            
            
        
            
            
         }
         
}
