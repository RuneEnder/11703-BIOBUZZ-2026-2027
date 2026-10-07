package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;


@Autonomous(name = "Auto: 0 Artifacts", group = "Examples")
public class ZeroArtifactAuto extends OpMode {

    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;

    public int pathState = 0;

    // Various positions for each configuration
    boolean isRedAlliance = true;nb
    boolean isWallSide = true;
    private final Pose startPoseRedWall = new Pose(88, 8, Math.toRadians(90));
    private final Pose endPoseRedWall = new Pose(108, 10, Math.toRadians(90));

    private final Pose startPoseRedGoal = new Pose(118.638, 129.298, Math.toRadians(216));
    private final Pose midPoseRedGoal = new Pose(118.638, 107.089, Math.toRadians(216));
    private final Pose endPoseRedGoal = new Pose(127.967, 104.922, Math.toRadians(180));

    private final Pose startPoseBlueWall = new Pose(56, 8, Math.toRadians(90));
    private final Pose endPoseBlueWall = new Pose(36, 10, Math.toRadians(90));

    private final Pose startPoseBlueGoal = new Pose(25.671, 129.795, Math.toRadians(-36));
    private final Pose midPoseBlueGoal = new Pose(25.671, 107.089, Math.toRadians(-36));
    private final Pose endPoseBlueGoal = new Pose(15.783, 104.922, Math.toRadians(0));


    // Poses used to calculate paths
    public Pose startPose = startPoseRedWall;
    public Pose midPose = endPoseRedWall;
    public Pose endPose = endPoseRedWall;

    private PathChain startToMidPath, midToEndPath;


    public void buildPaths() {

        // Determine what startPose to use
        if (isRedAlliance) {
            if (isWallSide) {
                startPose = startPoseRedWall;
                midPose = endPoseRedWall;
                endPose = endPoseRedWall;
            } else {
                startPose = startPoseRedGoal;
                midPose = midPoseRedGoal;
                endPose = endPoseRedGoal;
            }
        } else {
            if (isWallSide) {
                startPose = startPoseBlueWall;
                midPose = endPoseBlueWall;
                endPose = endPoseBlueWall;
            } else {
                startPose = startPoseBlueGoal;
                midPose = midPoseBlueGoal;
                endPose = endPoseBlueGoal;
            }
        }


        startToMidPath = follower.pathBuilder()
                .addPath(new BezierLine(startPose, midPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), midPose.getHeading())
                .build();

        midToEndPath = follower.pathBuilder()
                .addPath(new BezierLine(midPose, endPose))
                .setLinearHeadingInterpolation(midPose.getHeading(), endPose.getHeading())
                .build();

    }











    public void autonomousPathUpdate() {
                    /* You could check for
            - Follower State: "if(!follower.isBusy()) {}"
            - Time: "if(pathTimer.getElapsedTimeSeconds() > 1) {}"
            - Robot Position: "if(follower.getPose().getX() > 36) {}"
            */

        switch (pathState) {
            case -1:
                break;
            case 0:

                // Moving to the mid position
                if (!follower.isBusy()) {

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(startToMidPath, true);
                    setPathState(1);
                }
                break;
            case 1:

                // Moving to the end position
                if (!follower.isBusy()) {

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(midToEndPath, true);
                    setPathState(-1);
                }
                break;

        }
    }













    /**
     * These change the states of the paths and actions. It will also reset the timers of the individual switches
     **/
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }






    /**
     * This is the main loop of the OpMode, it will run repeatedly after clicking "Play".
     **/
    @Override
    public void loop() {

        // These loop the movements of the robot, these must be called continuously in order to work
        follower.update();
        autonomousPathUpdate();

        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData(" ", " ");
        telemetry.update();
    }







    /**
     * This method is called once at the init of the OpMode.
     **/
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();


        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(startPose);
    }







    /**
     * This method is called continuously after Init while waiting for "play".
     **/
    @Override
    public void init_loop() {
        if (gamepad2.xWasPressed()) {
            isRedAlliance = false;
            buildPaths();
            follower.setStartingPose(startPose);
        } else if (gamepad2.bWasPressed()) {
            isRedAlliance = true;
            buildPaths();
            follower.setStartingPose(startPose);
        }

        if (gamepad2.dpadDownWasPressed()) {
            isWallSide = true;
            buildPaths();
            follower.setStartingPose(startPose);
        } else if (gamepad2.dpadUpWasPressed()) {
            isWallSide = false;
            buildPaths();
            follower.setStartingPose(startPose);
        }

        telemetry.addData("Auto: ", (isRedAlliance ? "Red " : "Blue ") + (isWallSide ? "Wall" : "Goal"));
        telemetry.update();
    }




    /**
     * This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system
     **/
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }


}