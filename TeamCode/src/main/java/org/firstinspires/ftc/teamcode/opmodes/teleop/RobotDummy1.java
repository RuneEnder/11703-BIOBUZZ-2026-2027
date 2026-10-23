package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

@Disabled
@TeleOp(name = "Robot Dummy 1", group = "Linear OpMode")
public class RobotDummy1 extends LinearOpMode {

    public static double intakeSpeed = 0.1;
    public static double outtakeSpeed = 0.1;

    @Override
    public void runOpMode() throws InterruptedException {

        RobotVars robotVars = new RobotVars();

        RobotVars.init_TeleOp(telemetry);

        Drivetrain drive = new Drivetrain(hardwareMap, robotVars);


        final DcMotorEx intakeMotor, outtakeMotor;

        intakeMotor = hardwareMap.get(DcMotorEx.class, "in");
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "out");

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        outtakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        boolean isIntakeActive = false;
        boolean isOuttakeActive = false;


        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();


        //-----------------------------------------------------------------
        // Start ↑ and Primary Loop ↓
        //-----------------------------------------------------------------


        // Primary loop
        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad1.a) {
                isIntakeActive = !isIntakeActive;
            }

            if (gamepad1.b) {
                isOuttakeActive = !isOuttakeActive;
            }


            if (isIntakeActive) {
                intakeMotor.setPower(intakeSpeed);
            } else {
                intakeMotor.setPower(0);
            }

            if (isOuttakeActive) {
                outtakeMotor.setPower(outtakeSpeed);
            } else {
                outtakeMotor.setPower(0);
            }



            drive.runDriveCode(
                    gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );



            telemetry.update();

        }


        // Emergency shut down
        drive.setPowers(0, 0, 0, 0);


    }
}
