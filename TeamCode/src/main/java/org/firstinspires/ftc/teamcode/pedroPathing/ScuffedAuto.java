package org.firstinspires.ftc.teamcode.pedroPathing;/* package org.firstinspires.ftc.teamcode.pedroPathing;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ManagerColorSensor;
import org.firstinspires.ftc.teamcode.ManagerFlywheel;
import org.firstinspires.ftc.teamcode.ManagerIntake;
import org.firstinspires.ftc.teamcode.ManagerLimelight;
import org.firstinspires.ftc.teamcode.ManagerResetRotator;
import org.firstinspires.ftc.teamcode.ManagerRotator;
import org.firstinspires.ftc.teamcode.ManagerStateMachine;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@Autonomous(name = "Scuffed Auto", group = "Examples")
public class ScuffedAuto {

    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;


    // References to other classes
    MecanumDrive drive;
    ManagerStateMachine stateMachine;
    ManagerRotator rotatorManager;
    ManagerFlywheel flywheelManager;
    ManagerColorSensor colorSensorManager;
    ManagerIntake intakeManager;
    ManagerResetRotator resetManager;
    ManagerLimelight limelightManager;


    // Information about the STARTING position
    boolean isRedAlliance = false;
    boolean isWallSide = true;

    // Path states
    public int pathStateA, pathStateFlywheel, pathStateIntake, pathStatePivotArm = 0;
    public int pathStateB, pathStateC = -1;

    // Various delays
    double flywheelDelay = 1; // 0.4
    double pivotArmDelay = 1; // 0.4
    double rotatorDelayB = 1; // 0.4
    double rotatorDelayC = 1; // 0.6
    double intakeTimerC = 1; // 1.5
    double midWayPauseDelay = 1; //0.1


    int nextSetOfArtifactsA = 1; // 0 = highest, 1 = middle, 2 = lowest

    int shotsFiredB = 0;

    int artifactsPickedUpC = 0;

    public void init_loop() {
        if (gamepad2.xWasPressed()) {
            isRedAlliance = false;
        } else if (gamepad2.bWasPressed()) {
            isRedAlliance = true;
        }

        if (gamepad2.dpadDownWasPressed()) {
            isWallSide = true;
        } else if (gamepad2.dpadUpWasPressed()) {
            isWallSide = false;
        }

        telemetry.addData("Auto: ", (isRedAlliance ? "Red " : "Blue ") + (isWallSide ? "wall" : "goal"));
        telemetry.update();

    }
} */