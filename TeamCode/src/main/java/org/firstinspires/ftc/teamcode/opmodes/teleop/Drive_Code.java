package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

@TeleOp(name = "Robot Centric TeleOP", group = "Linear OpMode")
public class Drive_Code extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Globals.init_TeleOp(telemetry);

        Drivetrain drive = new Drivetrain(hardwareMap, Globals.constants);



        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();

        while (!opModeIsActive()) {
            telemetry.update();

            if (gamepad2.xWasPressed()) {
                Globals.allianceColor = AllianceColor.Blue;
                Globals.constants.setStartPose(AllianceColor.Blue, Globals.startsInCorner);

                drive.follower.setStartingPose(Globals.constants.startPose);
            } else if (gamepad2.bWasPressed()) {
                Globals.allianceColor = AllianceColor.Red;
                Globals.constants.setStartPose(AllianceColor.Red, Globals.startsInCorner);

                drive.follower.setStartingPose(Globals.constants.startPose);
            }

            if (gamepad2.dpadDownWasPressed()) {
                Globals.startsInCorner = false;
                Globals.constants.setStartPose(Globals.allianceColor, false);

                drive.follower.setStartingPose(Globals.constants.startPose);
            } else if (gamepad2.dpadUpWasPressed()) {
                Globals.startsInCorner = true;
                Globals.constants.setStartPose(Globals.allianceColor, true);

                drive.follower.setStartingPose(Globals.constants.startPose);
            }

            telemetry.addData("TeleOp: ", (Globals.allianceColor == AllianceColor.Red ? "Red " : "Blue ") + (Globals.startsInCorner ? "Corner" : "Wall"));
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
