package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.gamepad.GamepadManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechaninisms.Limelight;
import org.firstinspires.ftc.teamcode.mechaninisms.RobotStateHandler;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;
import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;

@TeleOp(name = "Dummy 1", group = "Linear OpMode")
public class RobotDummy1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RobotVars robotVars = new RobotVars();
//        RobotStateHandler robotStateHandler = new RobotStateHandler(hardwareMap, robotVars);
        GamepadController gamepadController = new GamepadController(gamepad1, gamepad2);

        RobotVars.init_TeleOp(telemetry);

        Limelight limelight = new Limelight(hardwareMap);

        DcMotorEx turretMotor;

        DcMotorEx leftFront, leftBack, rightBack, rightFront;


        leftFront = hardwareMap.get(DcMotorEx.class, "lf");
        rightFront = hardwareMap.get(DcMotorEx.class, "rf");
        leftBack = hardwareMap.get(DcMotorEx.class, "lr");
        rightBack = hardwareMap.get(DcMotorEx.class, "rr");


        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        turretMotor = hardwareMap.get(DcMotorEx.class, "turret");
        turretMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);









        double speed = 0.3;



        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();



        limelight.startScript();
        limelight.switchPipeline(0);

        //-----------------------------------------------------------------
        // Start ↑ and Primary Loop ↓
        //-----------------------------------------------------------------


        // Primary loop
        while (opModeIsActive() && !isStopRequested()) {



            turretMotor.setPower(speed * limelight.directionToHive());

            if (gamepad1.dpadDownWasPressed()) speed -= 0.05;
            if (gamepad1.dpadUpWasPressed()) speed += 0.05;

            telemetry.addData("tx: ", limelight.getTX());
            telemetry.addData("Direction to Hive: ", limelight.directionToHive());
            telemetry.addData("Speed: ", speed);
            telemetry.addData("", "");

            telemetry.addData("Left Front", leftFront.getCurrentPosition());
            telemetry.addData("Left Rear: ", leftBack.getCurrentPosition());
            telemetry.addData("Right Front", rightFront.getCurrentPosition());
            telemetry.addData("Right Rear: ", rightBack.getCurrentPosition());

            telemetry.update();

        }




    }
}
