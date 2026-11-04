//package org.firstinspires.ftc.teamcode.pedroPathing;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.PathChain;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.teamcode.ManagerColorSensor;
//import org.firstinspires.ftc.teamcode.ManagerFlywheel;
//import org.firstinspires.ftc.teamcode.ManagerIntake;
//import org.firstinspires.ftc.teamcode.ManagerLimelight;
//import org.firstinspires.ftc.teamcode.ManagerResetRotator;
//import org.firstinspires.ftc.teamcode.ManagerRotator;
//import org.firstinspires.ftc.teamcode.ManagerStateMachine;
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//
//
//@Autonomous(name = "Auto: 3 Artifacts", group = "Examples")
//public class ThreeArtifactAuto extends OpMode {
//
//    private Follower follower;
//    private Timer pathTimer, actionTimer, opmodeTimer;
//
//    public int pathStateA, pathStateFlywheel, pathStateIntake, pathStatePivotArm = 0;
//    public int pathStateB = -1;
//
//    // Various positions for each configuration
//    boolean isRedAlliance = true;
//    boolean isWallSide = true;
//    private final Pose startPoseRedWall = new Pose(88, 8, Math.toRadians(90));
//    private final Pose startPoseRedGoal = new Pose(118.638, 129.298, Math.toRadians(216));
//    private final Pose startPoseBlueWall = new Pose(56, 8, Math.toRadians(90));
//    private final Pose startPoseBlueGoal = new Pose(25.671, 129.795, Math.toRadians(-36));
//
//    private final Pose scorePoseRed = new Pose(99.104, 92.945, Math.toRadians(45));
//    private final Pose scorePoseBlue = new Pose(44.563, 92.945, Math.toRadians(135));
//
//    private final Pose endPoseRed = new Pose(95.694, 61.789, Math.toRadians(180));
//    private final Pose endPoseBlue = new Pose(47.805, 61.789, Math.toRadians(0));
//
//    // Poses used to calculate paths
//    public Pose startPose = startPoseRedWall;
//    public Pose scorePose = scorePoseRed;
//    public Pose endPose = endPoseRed;
//
//    private PathChain startToScorePath, scoreToEndPath;
//
//    ManagerStateMachine stateMachine;
//    ManagerRotator rotatorManager;
//    ManagerFlywheel flywheelManager;
//    ManagerColorSensor colorSensorManager;
//    ManagerIntake intakeManager;
//    ManagerResetRotator resetManager;
//    MecanumDrive drive;
//    ManagerLimelight limelightManager;
//
//
//    double flywheelDelay = 1;
//    double pivotArmDelay = 1;
//    double rotatorDelay = 1;
//
//    int shotsFired = 0;
//    boolean hasStartedRotating = false;
//
//
//
//    public void buildPaths() {
//
//        // Determine what startPose to use
//        if (isRedAlliance) {
//            if (isWallSide) {
//                startPose = startPoseRedWall;
//            } else {
//                startPose = startPoseRedGoal;
//            }
//        } else {
//            if (isWallSide) {
//                startPose = startPoseBlueWall;
//            } else {
//                startPose = startPoseBlueGoal;
//            }
//        }
//
//        // Determine what scorePose & endPose to use
//        if (isRedAlliance) {
//            scorePose = scorePoseRed;
//            endPose = endPoseRed;
//        } else {
//            scorePose = scorePoseBlue;
//            endPose = endPoseBlue;
//        }
//
//        startToScorePath = follower.pathBuilder()
//                .addPath(new BezierLine(startPose, scorePose))
//                .setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading())
//                .build();
//
//        scoreToEndPath = follower.pathBuilder()
//                .addPath(new BezierLine(scorePose, endPose))
//                .setLinearHeadingInterpolation(scorePose.getHeading(), endPose.getHeading())
//                .build();
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//    public void autonomousPathUpdateA() {
//                    /* You could check for
//            - Follower State: "if(!follower.isBusy()) {}"
//            - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
//            - Robot Position: "if(follower.getPose().getX() > 36) {}"
//            */
//
//        switch (pathStateA) {
//            case -1:
//                break;
//            case 0:
//
//                // Moving to the firing position
//                if (!follower.isBusy()) {
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(startToScorePath, true);
//                    setPathStateA(1);
//                }
//                break;
//
//            case 1:
//
//                // Start flywheel & intake
//                pathStateFlywheel = 1;
//                pathStateIntake = 1;
//
//                if (pathTimer.getElapsedTimeSeconds() >= flywheelDelay) {
//                    setPathStateA(2);
//                }
//                break;
//
//            case 2:
//
//                // Recursive firing
//                if ( pathStateB == -1 && !follower.isBusy() ) {
//
//                    // Either start firing or continue to the next state, based on shotsFired
//                    if (shotsFired == 0) {
//                        setPathStateB(0);
//                    } else if (shotsFired >= 3) {
//                        setPathStateA(3);
//                    }
//
//                }
//                break;
//
//
//            case 3:
//
//                // Stop flywheel & intake
//                pathStateFlywheel = 0;
//                pathStateIntake = 0;
//
//                if (pathTimer.getElapsedTimeSeconds() >= flywheelDelay) {
//                    setPathStateA(4);
//                }
//                break;
//
//            case 4:
//
//                // Moving to the end position
//                if (!follower.isBusy()) {
//
//                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
//                    follower.followPath(scoreToEndPath, true);
//                    setPathStateA(-1);
//                }
//                break;
//
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//    public void autonomousPathUpdateB() {
//        switch (pathStateB) {
//            case -1:
//                break;
//            case 0:
//                // Snap to an initial firing position.
//
//                if (!hasStartedRotating) {
//                    hasStartedRotating = true;
//
//                    rotatorManager.currentSnappingPos = rotatorManager.getClosestFiringEncoderPos();
//                    rotatorManager.enter_RunToPosition((int) rotatorManager.currentSnappingPos, rotatorManager.inRotator_TargetPower_FIRING);
//                    rotatorManager.currentRotatorIndex = rotatorManager.getClosestFiringSlotIndex((int) rotatorManager.currentSnappingPos);
//                }
//
//
//                if (pathTimer.getElapsedTimeSeconds() >= rotatorDelay) {
//                    hasStartedRotating = false;
//                    setPathStateB(1);
//                }
//                break;
//
//            case 1:
//
//                // Lower arm
//                pathStatePivotArm = 1;
//
//                if (pathTimer.getElapsedTimeSeconds() >= pivotArmDelay) {
//                    setPathStateB(2);
//                }
//                break;
//
//            case 2:
//
//                // Raise arm
//                pathStatePivotArm = 0;
//
//                if (pathTimer.getElapsedTimeSeconds() >= pivotArmDelay) {
//                    setPathStateB(3);
//                }
//                break;
//
//            case 3:
//
//                // Cycle rotator :(
//                if (!hasStartedRotating) {
//                    hasStartedRotating = true;
//
//                    rotatorManager.currentSnappingPos -= getMultiplier() * rotatorManager.distanceBetweenSlots;
//                    rotatorManager.inRotator.setTargetPosition((int) rotatorManager.currentSnappingPos);
//                }
//
//
//                if (pathTimer.getElapsedTimeSeconds() >= rotatorDelay) {
//
//                    hasStartedRotating = false;
//                    shotsFired++; // Increment count
//
//                    if (shotsFired >= 3) {
//                        setPathStateB(-1);
//                    } else {
//                        setPathStateB(1);
//                    }
//
//                }
//                break;
//
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//    public void autonomousFlywheelUpdate() {
//        switch (pathStateFlywheel) {
//            case -1:
//                break;
//            case 0:
//                flywheelManager.runFlywheel_Manual(false);
//                break;
//            case 1:
//                flywheelManager.runFlywheel_Manual(true);
//                break;
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//    public void autonomousIntakeUpdate() {
//        switch (pathStateIntake) {
//            case -1:
//                break;
//            case 0:
//                intakeManager.runIntake(false, true, false);
//                break;
//            case 1:
//                intakeManager.runIntake(true, true, false);
//                break;
//        }
//    }
//
//
//
//
//
//
//
//    public void autonomousPivotArmUpdate() {
//        switch (pathStatePivotArm) {
//            case -1:
//                break;
//            case 0:
//                flywheelManager.runPivotArm_Manual(false);
//                break;
//            case 1:
//                flywheelManager.runPivotArm_Manual(true);
//                break;
//        }
//    }
//
//
//
//
//        /**
//         * These change the states of the paths and actions. It will also reset the timers of the individual switches
//         **/
//    public void setPathStateA(int pState) {
//        pathStateA = pState;
//        pathTimer.resetTimer();
//    }
//
//    public void setPathStateB(int pState) {
//        pathStateB = pState;
//        pathTimer.resetTimer();
//    }
//
//
//
//
//    private double getMultiplier() {
//        switch (limelightManager.greenMotifPos) {
//            case -1:
//                return 0;
//            case 0:
//                return 0;
//            case 1:
//                return 2;
//            case 2:
//                return 1;
//            default:
//                return 0;
//        }
//    }
//
//
//
//
//
//    /**
//     * This is the main loop of the OpMode, it will run repeatedly after clicking "Play".
//     **/
//    @Override
//    public void loop() {
//
//        // These loop the movements of the robot, these must be called continuously in order to work
//        follower.update();
//        autonomousPathUpdateA();
//        autonomousPathUpdateB();
//
//        autonomousFlywheelUpdate();
//        autonomousIntakeUpdate();
//        autonomousPivotArmUpdate();
//        limelightManager.updateLimelightMotif();
//
//
//        telemetry.addData("x", follower.getPose().getX());
//        telemetry.addData("y", follower.getPose().getY());
//        telemetry.addData("heading", follower.getPose().getHeading());
//        telemetry.addData(" ", " ");
//
//        telemetry.addData("pathStateA: ", pathStateA);
//        telemetry.addData("pathStateB: ", pathStateB);
//        telemetry.addData("pathStateIntake: ", pathStateIntake);
//        telemetry.addData("pathStatePivotArm", pathStatePivotArm);
//        telemetry.addData("pathStateFlywheel", pathStateFlywheel);
//        telemetry.addData(" ", " ");
//
//        telemetry.addData("Shots Fired: ", shotsFired);
//        telemetry.addData("Has Rotated?: ", hasStartedRotating);
//        telemetry.addData("Timer: ", pathTimer.getElapsedTimeSeconds());
//        telemetry.update();
//    }
//
//
//
//
//
//
//
//    /**
//     * This method is called once at the init of the OpMode.
//     **/
//    @Override
//    public void init() {
//        pathTimer = new Timer();
//        opmodeTimer = new Timer();
//        opmodeTimer.resetTimer();
//
//
//        follower = Constants.createFollower(hardwareMap);
//        buildPaths();
//        follower.setStartingPose(startPose);
//
//
//        stateMachine = new ManagerStateMachine(hardwareMap, drive);
//        rotatorManager = stateMachine.getRotatorManager();
//        flywheelManager = stateMachine.getFlywheelManager();
//        colorSensorManager = stateMachine.getColorSensorManager();
//        intakeManager = stateMachine.getIntakeManager();
//        resetManager = stateMachine.getResetManager();
//
//        limelightManager = stateMachine.getLimelightManager();
//        limelightManager.moveCameraArm(90);
//
//    }
//
//
//
//
//
//
//
//    /**
//     * This method is called continuously after Init while waiting for "play".
//     **/
//    @Override
//    public void init_loop() {
//        if (gamepad2.xWasPressed()) {
//            isRedAlliance = false;
//            buildPaths();
//            follower.setStartingPose(startPose);
//        } else if (gamepad2.bWasPressed()) {
//            isRedAlliance = true;
//            buildPaths();
//            follower.setStartingPose(startPose);
//        }
//
//        if (gamepad2.dpadDownWasPressed()) {
//            isWallSide = true;
//            buildPaths();
//            follower.setStartingPose(startPose);
//        } else if (gamepad2.dpadUpWasPressed()) {
//            isWallSide = false;
//            buildPaths();
//            follower.setStartingPose(startPose);
//        }
//
//        telemetry.addData("Auto: ", (isRedAlliance ? "Red " : "Blue ") + (isWallSide ? "Wall" : "Goal"));
//
//
//        telemetry.update();
//    }
//
//
//
//
//    /**
//     * This method is called once at the start of the OpMode.
//     * It runs all the setup actions, including building paths and starting the path system
//     **/
//    @Override
//    public void start() {
//        opmodeTimer.resetTimer();
//        setPathStateA(0);
//
//        rotatorManager.startScript();
//        flywheelManager.startScript();
//        colorSensorManager.startScript();
//        resetManager.startScript();
//        limelightManager.startScript();
//    }
//
//
//
//
//    @Override
//    public void stop() {
//        rotatorManager.inRotator.setVelocity(0);
//        flywheelManager.pivotArm.setVelocity(0);
//        flywheelManager.flywheelLeft.setVelocity(0);
//        flywheelManager.flywheelRight.setVelocity(0);
//
//        intakeManager.intakeL.setPower(0);
//        intakeManager.intakeR.setPower(0);
//    }
//
//}