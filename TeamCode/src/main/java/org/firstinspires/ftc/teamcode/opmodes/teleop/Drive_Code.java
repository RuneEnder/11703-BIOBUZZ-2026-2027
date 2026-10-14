package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

@TeleOp(name = "Robot Centric TeleOP", group = "Linear OpMode")
public class Drive_Code extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RobotVars robotVars = new RobotVars();

        robotVars.init_TeleOp(telemetry);

        Drivetrain drive = new Drivetrain(hardwareMap, robotVars);



        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();

        while (!opModeIsActive()) {

            if (gamepad2.xWasPressed()) {
                robotVars.allianceColor = AllianceColor.Blue;
                robotVars.setStartPose();

                drive.follower.setStartingPose(robotVars.startPose);
            } else if (gamepad2.bWasPressed()) {
                robotVars.allianceColor = AllianceColor.Red;
                robotVars.setStartPose();

                drive.follower.setStartingPose(robotVars.startPose);
            }

            if (gamepad2.dpadDownWasPressed()) {
                robotVars.startsInCorner = false;
                robotVars.setStartPose();

                drive.follower.setStartingPose(robotVars.startPose);
            } else if (gamepad2.dpadUpWasPressed()) {
                robotVars.startsInCorner = true;
                robotVars.setStartPose();

                drive.follower.setStartingPose(robotVars.startPose);
            }

            telemetry.addData("TeleOp: ", (RobotVars.allianceColor == AllianceColor.Red ? "Red " : "Blue ") + (RobotVars.startsInCorner ? "Corner" : "Wall"));
            telemetry.update();

        }


        //-----------------------------------------------------------------
        // Start ↑ and Primary Loop ↓
        //-----------------------------------------------------------------


        // Primary loop
        while (opModeIsActive() && !isStopRequested()) {
            drive.runDriveCode(
                    gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );


            telemetry.addData("Front Left (L): ", drive.leftFront.getCurrentPosition());
            telemetry.addData("Front Right (R): ", drive.rightFront.getCurrentPosition());
            telemetry.addData("Left Back: ", drive.leftBack.getCurrentPosition());

            telemetry.update();

        }


        // Emergency shut down
        drive.setPowers(0, 0, 0, 0);


    }
}
