package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.gamepad.GamepadManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechaninisms.RobotStateHandler;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;
import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;

@TeleOp(name = "Robot Centric TeleOP", group = "Linear OpMode")
public class Drive_Code extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RobotVars robotVars = new RobotVars();
        RobotStateHandler robotStateHandler = new RobotStateHandler();
        GamepadController gamepadController = new GamepadController(gamepad1, gamepad2);

        RobotVars.init_TeleOp(telemetry);

        Drivetrain drive = new Drivetrain(hardwareMap, robotVars);



        while (!opModeIsActive()) {

            robotStateHandler.RunMenu(robotVars, drive, gamepadController);

            telemetry.addData("TeleOp: ", (robotVars.allianceColor == AllianceColor.Red ? "Red " : "Blue ") + (robotVars.startsInCorner ? "Corner" : "Wall"));
            telemetry.update();

        }

        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();


        //-----------------------------------------------------------------
        // Start ↑ and Primary Loop ↓
        //-----------------------------------------------------------------


        // Primary loop
        while (opModeIsActive() && !isStopRequested()) {
            drive.runFieldCentricDrive(
                    gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );


            telemetry.addData("TeleOp: ", (robotVars.allianceColor == AllianceColor.Red ? "Red " : "Blue ") + (robotVars.startsInCorner ? "Corner" : "Wall"));
            telemetry.addData("Start Pose: ", robotVars.startPose);
            telemetry.addData("Left Front", drive.leftFront.getCurrentPosition());
            telemetry.addData("Left Rear: ", drive.leftBack.getCurrentPosition());
            telemetry.addData("Right Front", drive.rightFront.getCurrentPosition());
            telemetry.addData("Right Rear: ", drive.rightBack.getCurrentPosition());
            telemetry.addData("Follower: ", drive.follower.getPose().getX() + " " + drive.follower.getPose().getY() + ", " + Math.toDegrees(drive.follower.getPose().getHeading()));


            telemetry.update();

        }


        // Emergency shut down
        drive.setPowers(0, 0, 0, 0);


    }
}
