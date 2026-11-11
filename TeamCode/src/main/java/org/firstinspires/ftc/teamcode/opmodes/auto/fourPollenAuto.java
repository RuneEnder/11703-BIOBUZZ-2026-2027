package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.mechaninisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechaninisms.Intake;
import org.firstinspires.ftc.teamcode.mechaninisms.Outtake;

@Autonomous(name = "Four Pollen Auto", group = "Linear OpMode")
public class fourPollenAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        RobotVars robotVars = new RobotVars();
        Intake intake = new Intake(hardwareMap, robotVars);
        Outtake outtake = new Outtake(hardwareMap, robotVars);
        Drivetrain drive = new Drivetrain(hardwareMap, robotVars);


        while (!opModeIsActive()) { }

        //-----------------------------------------------------------------
        // Initialization ↑ and Start ↓
        //-----------------------------------------------------------------

        waitForStart();

        intake.runIntake_ToggleMode(true, false);
        outtake.runOuttake_ToggleMode(true, false);
        sleep(5000);
        drive.runRobotCentricDriveCode(
                0,
                -0.9f,
                0
        );
        sleep(5000);
        drive.runRobotCentricDriveCode(
                0,
                0,
                0
        );
        drive.setPowers(0, 0, 0, 0);
    }
}