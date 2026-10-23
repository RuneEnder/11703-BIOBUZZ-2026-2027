package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.mechaninisms.RobotStateHandler;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;
import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;

@TeleOp(name = "Zero Pollen Auto", group = "Linear OpMode")
public class ZeroPollenAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        RobotVars robotVars = new RobotVars();
        RobotStateHandler robotStateHandler = new RobotStateHandler(hardwareMap, robotVars);
        GamepadController gamepadController = new GamepadController(gamepad1, gamepad2);

        RobotVars.init_TeleOp(telemetry);


        boolean hasReset = false;
        long initTime = 0;
        double duration = 0.1;



        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------


        waitForStart();


        //-----------------------------------------------------------------
        // Start ↑ and Primary Loop ↓
        //-----------------------------------------------------------------


        // Primary loop
        while (opModeIsActive() && !isStopRequested()) {
            if (!hasReset) {
                hasReset = true;
                initTime = System.nanoTime();
            }

            if (((System.nanoTime() - initTime) * Math.pow(10,-9)) < duration) {
                RobotStateHandler.drive.runDriveCode(
                        0,
                        -0.9f,
                        0
                );
            } else {
                RobotStateHandler.drive.setPowers(0, 0, 0, 0);

                RobotStateHandler.drive.runDriveCode(
                        0,
                        0,
                        0
                );

            }



            telemetry.addData("time:", (System.nanoTime() - initTime) * Math.pow(10,-9) );
            telemetry.addData("should be off:", (System.nanoTime() - initTime) * Math.pow(10,-9) < 0.05);
            telemetry.update();

        }


        // Emergency shut down
        RobotStateHandler.drive.setPowers(0, 0, 0, 0);


    }
}
