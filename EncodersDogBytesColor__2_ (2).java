package DogBytes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "ZonescanR")

public class EncodersDogBytesColor__2_ extends LinearOpMode{
    
    
    private DcMotor motor_left;
    private DcMotor motor_right;
    private DcMotor motor_B_left;
    private DcMotor motor_B_right;
    private DcMotor slider;
    
    private Servo Chomp;
    
    private ColorSensor Eyes;

    //Reset DT Encoders
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
            
            //Chomp = hardwareMap.get(Servo.class,"Chomp");
            
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
         

            
         // Move robot forward
         ResetEncodersDT();
         SetTgPosDT(795 , 795);
         RunPosDT();
         SetVelocityDT(700);
         telemetry();
         SetVelocityDT(0);
         sleep(1000);
         
         
         // strafe right //
         /*ResetEncodersDT();
         SetTgPosStrafeDT(-260,260,260,-260);
         RunPosDT();
         SetVelocityDT(700);
         telemetry();
         SetVelocityDT(0);
         sleep(1000);*/
         

         
            
         //adds red, green, and blue data to telemtry
         telemetry.addData("Red", Eyes.red());
         telemetry.addData("Blue", Eyes.blue());
         telemetry.addData("Green", Eyes.green());
         Red = Eyes.red();
         Blue = Eyes.blue();
         Green = Eyes.green();
         telemetry.update();
         sleep(1000);
         

          
        //
        if(Red > Blue && Red > Green){
            
            telemetry.addLine("Red");
            telemetry.update();
            sleep(500);
            
            //Move Forward
            ResetEncodersDT();
            SetTgPosDT(600,600);
            RunPosDT();
            SetVelocityDT(1000);
            telemetry();
            SetVelocityDT(0);
            sleep(500);
        
          //Move Robot Backward
          ResetEncodersDT();
          SetTgPosDT(-250,-250);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(500);


          //Strafe Left
          ResetEncodersDT();
          SetTgPosStrafeDT(1225,-1225,-1225, 1225);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(1000);
          
          
          //Move Forward To Zone 1
          /*ResetEncodersDT();
          SetTgPosDT(1000,1000);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(1000);*/
        }


         //if Senses Green, then go to Green Station
            
            if(Green > Red && Green > Blue){
                
            telemetry.addLine("Green");
            telemetry.update();
            sleep(500);
            
            
            //Move Robot Forward
          ResetEncodersDT();
          SetTgPosDT(600,600);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(500);

          
          
          //go backwards
          ResetEncodersDT();
          SetTgPosDT(-200,-200);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(500);
          
          
          //Strafe Left
            /*ResetEncodersDT();
            SetTgPosStrafeDT(900,-900,-900,900);
            RunPosDT();
            SetVelocityDT(1000);
            telemetry();
            SetVelocityDT(0);
            sleep(500);*/
            }
          
          
          if (Blue > Red && Blue > Green){
              telemetry.addLine("Blue");
            telemetry.update();
            sleep(500);
            
            
            // Move Forward
            ResetEncodersDT();
            SetTgPosDT(500,500);
            RunPosDT();
            SetVelocityDT(1000);
            telemetry();
            SetVelocityDT(0);
            sleep(500);
            
            
            //Move Back
            ResetEncodersDT();
            SetTgPosDT(-260,-260);
            RunPosDT();
            SetVelocityDT(1000);
            telemetry();
            SetVelocityDT(0);
            sleep(500);
            
            
            //Strafe Right
            ResetEncodersDT();
          SetTgPosStrafeDT(-1400, 1400, 1400, -1400);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(500);
          
          
          //Move Forward
          /*ResetEncodersDT();
          SetTgPosDT(600,600);
          RunPosDT();
          SetVelocityDT(1000);
          telemetry();
          SetVelocityDT(0);
          sleep(600);*/
          }
          
          
          
          
        }
            }
            
