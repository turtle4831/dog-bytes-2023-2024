package org.firstinspires.ftc.teamcode;
import android.os.PowerManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ultraBadlyMadTele")
public class UltraBadlyMadTele extends LinearOpMode {
    public DcMotor FL_Motor;
    public DcMotor FR_Motor;
    public DcMotor BL_Motor;
    public DcMotor BR_Motor;
    public DcMotor HangerLeft;
    public DcMotor HangerRight;
    @Override
    public void runOpMode(){
        FL_Motor = hardwareMap.get(DcMotor.class,"FL_Motor");
        FR_Motor = hardwareMap.get(DcMotor.class,"FR_Motor");
        BL_Motor = hardwareMap.get(DcMotor.class,"BL_Motor");
        BR_Motor = hardwareMap.get(DcMotor.class,"BR_Motor");
        HangerLeft = hardwareMap.get(DcMotor.class, "HangerLeft");
        HangerRight=hardwareMap.get(DcMotor.class,"HangerRight");
        /* FR_Motor.setDirection(DcMotor.Direction.REVERSE);
        BR_Motor.setDirection(DcMotor.Direction.REVERSE); */
        FL_Motor.setDirection(DcMotor.Direction.REVERSE);
        BL_Motor.setDirection(DcMotor.Direction.REVERSE);
        HangerLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        while(opModeIsActive()){
            //local variables for the motor power values
            double axial = -gamepad1.left_stick_y;
            double lateral = -gamepad1.left_stick_x;
            double yaw = -gamepad1.right_stick_x * 0.8;


            //turning the power values to the sum of all the stick inputs
            double FRPower = axial + lateral + yaw;
            double FLPower = axial - lateral - yaw;
            double BRPower = axial - lateral + yaw;
            double BLPower = axial + lateral - yaw;

            //setting power to the power values set by the stick inputs
            FL_Motor.setPower(FLPower);
            FR_Motor.setPower(FRPower);
            BL_Motor.setPower(BLPower);
            BR_Motor.setPower(BRPower);
            double trigger = gamepad1.left_trigger - gamepad1.right_trigger;
            HangerLeft.setPower(trigger);
            HangerRight.setPower(trigger);


            telemetry.addData("FL_Motor Current Power", "%.2f", FLPower);
            telemetry.addData("FR_Motor Current Power", "%.2f", FRPower);
            telemetry.addData("BL_Motor Current Power", "%.2f", BLPower);
            telemetry.addData("BR_Motor Current Power", "%.2f", BRPower);
            telemetry.update();



        }
    }
}
