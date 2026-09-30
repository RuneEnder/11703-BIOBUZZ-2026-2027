package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Globals;

@TeleOp(name = "Field Centric TeleOP", group = "Linear OpMode")
public class fieldCentricTeleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drive = new Drivetrain(hardwareMap);

        Globals.init(telemetry);

        while (!opModeIsActive()) {
            telemetry.update();
        }

        while (opModeIsActive() && !isStopRequested()) {
            drive.runDriveCode(
                    gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.right_stick_x
            );
        }
        drive.setPowers(0, 0, 0, 0);
    }
}
