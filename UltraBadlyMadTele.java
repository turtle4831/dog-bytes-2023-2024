package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ultraBadlyMadTele")
public class UltraBadlyMadTele extends LinearOpMode {
    public DcMotor FL_Motor;
    public DcMotor FR_Motor;
    public DcMotor BL_Motor;
    public DcMotor BR_Motor;
    @Override
    public void runOpMode(){
        FL_Motor = hardwareMap.get(DcMotor.class,"FL_Motor");
        FR_Motor = hardwareMap.get(DcMotor.class,"FR_Motor");
        BL_Motor = hardwareMap.get(DcMotor.class,"BL_Motor");
        BR_Motor = hardwareMap.get(DcMotor.class,"BR_Motor");

        /* FR_Motor.setDirection(DcMotor.Direction.REVERSE);
        BR_Motor.setDirection(DcMotor.Direction.REVERSE); */
        FL_Motor.setDirection(DcMotor.Direction.REVERSE);
        BL_Motor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){
            //local variables for the motor power values
            double axial = -gamepad1.left_stick_y;
            double lateral = -gamepad1.right_stick_x;
            double yaw = -gamepad1.left_stick_x;
            double turn = gamepad1.left_stick_x;

            //turning the power values to the sum of all the stick inputs
            double FRPower = axial + lateral + yaw - turn;
            double FLPower = axial - lateral - yaw + turn;
            double BRPower = axial - lateral - yaw - turn;
            double BLPower = axial + lateral + yaw + turn;



            //setting power to the power values set by the stick inputs
            FL_Motor.setPower(FLPower);
            FR_Motor.setPower(FRPower);
            BL_Motor.setPower(BLPower);
            BR_Motor.setPower(BRPower);


        }
    }
}
