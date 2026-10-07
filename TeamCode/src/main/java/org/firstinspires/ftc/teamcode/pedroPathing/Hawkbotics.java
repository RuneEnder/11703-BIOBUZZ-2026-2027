package org.firstinspires.ftc.teamcode.pedroPathing;//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierCurve;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.Path;
//import com.pedropathing.paths.PathChain;
//import com.pedropathing.util.NanoTimer;
//import com.pedropathing.util.Timer;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//
//
//
//
//
//
//
//
//@Disabled
///// 5+0 for state
//
//
//// its a 4+0
//
//
///// Ran out of time didn't work during state
//
//
//public class Hawkbotics extends OpMode {
//
//
//    private Follower follower;
//
//
//    /// All motors / servos / sensors
//    private Servo servo_outtake_flip1, servo_outtake_flip2, servo_outtake, servo_outtake_rotate;
//    private DcMotorEx up1, up2, out;
//
//    // Timers
//    private Timer opmodeTimer;
//    private NanoTimer pathTimer;
//
//    private int pathState, armState, outclawState, outgrabState, stoneState; // Different cases and states of the different parts of the robot
//
//
//
//
//    // Poses, and pickUp Poses
//    private Pose startPose = new Pose(10.000, 55.000, 0);
//
//
//
//
//    /// hang poses, Make sure they running on hard enough (x), far enough from each other (y)
//    private Pose inithangPose = new Pose(29.000, 65.000, Math.toRadians(90));
//    private Pose firsthangPose = new Pose(32, 77.000, Math.toRadians(0));
//    private Pose secondhangPose = new Pose(32, 74.000, Math.toRadians(50));
//    private Pose thirdhangPose = new Pose(32, 70.000, Math.toRadians(100)); // changed from 69
//    private Pose fourthhangPose = new Pose(32,68, Math.toRadians(0));
//
//
//    private Pose curve2pushPose = new Pose(61, 26, 0); // turn spot so make sure it would be safe
//    private Pose push1 = new  Pose(35, 26, 0);
//    private Pose push2 = new Pose(35, 16, 0);
//    private Pose push3 = new Pose(35, 7.5 , 0); // first pickup poses also doubles as end of push 3
//
//
//
//
//    private Pose beforepush2 = new Pose(59, 16,0);
//    private Pose beforepush3 = new Pose(59, 7.5, 0);
//
//
//
//
//    private Path init_hang, curve2push, first_hang, first_hang_back, second_hang, second_hang_back, third_hang, third_hang_back, fourth_hang, fourth_hang_back, back, back2, back3;
//    private Path pickup;
//    private PathChain pushall;
//
//
//    private Telemetry telemetryA;
//
//
//
//
//    public void buildPaths() {
//        init_hang = new Path(
//                /// init hang path
//                new BezierLine(
//                        new Pose(34,35),
//                        new Pose(34,35)
//                )
//        );
//        init_hang.setConstantHeadingInterpolation(Math.toRadians(0));
//        curve2push = new Path(
//                /// behind first, make sure this doesn't hit the sub
//                new BezierCurve(
//                        inithangPose,
//                        new Pose(25.9837019790454, 18.607683352735737),
//                        new Pose(55.82305005820722, 39.56228172293365),
//                        curve2pushPose
//                )
//        );
//        curve2push.setConstantHeadingInterpolation(Math.toRadians(0));
//        pushall = follower.pathBuilder()
//                .addPath(
//                        /// push first
//                        new BezierLine(
//                                curve2pushPose,
//                                push1
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        /// behind second sample
//                        new BezierCurve(
//                                push1,
//                                beforepush2
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//
//
//                .addPath(
//                        /// pushes second
//                        new BezierLine(
//                                beforepush2,
//                                push2
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        /// gets behind third
//                        new BezierCurve(
//                                push2,
//                                new Pose (60,15),
//                                beforepush3
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(  /// Pushes third
//                        // Line 7
//                        new BezierLine(
//                                beforepush3,
//                                push3
//                        )
//                )
//                .setConstantHeadingInterpolation(Math.toRadians(0))
//                .addPath(
//                        new BezierLine(
//                                push3,
//                                new Pose(35, 10.000)
//                        )
//                )
//                .setConstantHeadingInterpolation(0)
//                .addPath(
//                        new BezierLine(
//                                new Pose(35,10.000),
//                                new Pose(9,10.000)
//                        ))
//                .setConstantHeadingInterpolation(0)
//                .build();
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
//
//
//
//
//
//
//        /// First hang
//        first_hang = new Path(
//                // Line 1
//                new BezierCurve(
//                        new Pose(10,16),
//                        new Pose(7.376018626309662,75.9394644935972),
//                        firsthangPose
//                )
//        );
//        first_hang.setConstantHeadingInterpolation(Math.toRadians(0));
//
//
//        first_hang_back = new Path(
//                // Line 2
//                new BezierLine(
//                        firsthangPose,
//                        firsthangPose
//                )
//        );
//        first_hang_back.setConstantHeadingInterpolation(Math.toRadians(0));
//        pickup = new Path(
//                // Line 3
//                new BezierLine(
//                        new Pose(1, 35),
//                        new Pose(10, 35)
//                )
//        );
//        pickup.setConstantHeadingInterpolation(Math.toRadians(0));
//        second_hang = new Path(
//                // Line 4
//                new BezierCurve(
//                        new Pose(10, 35),
//                        new Pose(7.376018626309662,75.9394644935972),
//                        secondhangPose
//                )
//        );
//        second_hang.setConstantHeadingInterpolation(Math.toRadians(0));
//
//
//        second_hang_back = new Path(
//                // Line 5
//                new BezierLine(
//                        secondhangPose,
//                        new Pose(20, 35)
//                )
//        );
//        second_hang_back.setConstantHeadingInterpolation(Math.toRadians(0));
//        /// Run pickup
//        third_hang = new Path(
//                // Line 7
//                new BezierCurve(
//                        new Pose(10.000, 35.000),
//                        new Pose(10.000, 35.000),
//                        thirdhangPose
//                )
//        );
//        third_hang.setConstantHeadingInterpolation(Math.toRadians(0));
//
//
//        third_hang_back = new Path(
//                // Line 8
//                new BezierLine(
//                        new Pose(37,69),
//                        new Pose(20, 35)
//                )
//        );
//        third_hang_back.setConstantHeadingInterpolation(Math.toRadians(0));
//        /// Run pickup
//
//
//
//
//        fourth_hang = new Path(
//                // Line 10
//                new BezierLine(
//                        fourthhangPose,
//                        fourthhangPose
//                )
//        );
//        fourth_hang.setConstantHeadingInterpolation(Math.toRadians(0));
//        fourth_hang_back = new Path(
//                // Line 11
//                new BezierLine(
//                        fourthhangPose,
//                        fourthhangPose
//                )
//        );
//        fourth_hang_back.setConstantHeadingInterpolation(Math.toRadians(0));
//    }
//
//
//
//
//    public void autonomousPathUpdate() {
//        switch (pathState) {
//            case -1: // empty case for testing
//                // setArmState();
//                break;
//            case 0:
//                setArmState(1); // Ready pos, arm is fully normally aligned. make sure it is near the edge
//                setPathState(1);
//                break;
//            case 1:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // wait time for release
//                    follower.followPath(init_hang);
//                    setPathState(101);
//                }
//                break;
//            case 101: /// Hang
//                if (!follower.isBusy()) {
//                    setoutClawState(1); // release
//                    setPathState(203);
//                }
//                break;
//            case 203:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // wait time for release
//                    follower.followPath(curve2push);
//                    setPathState(204);
//                }
//                break;
//            case 204:
//                setArmState(0);
//                setPathState(2); // do i need to add a wait time here
//
//
//                break;
//            case 2:
//                if (!follower.isBusy()) {
//                    follower.followPath(pushall);
//                    setPathState(3);
//                }
//                break;
//            case 3:
//                if (!follower.isBusy()) { // grab
//                    setoutClawState(0);
//                    setPathState(401);
//                }
//                break;
//            case 401:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // Time to grab
//                    setArmState(1);
//                    setPathState(402);
//                }
//                break;
//            case 402:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // Time to grab
//                    follower.followPath(first_hang);
//                    setPathState(4);
//                }
//                break;
//
//
//            case 4:
//                if (!follower.isBusy()) { // if it is done
//                    //follower.followPath(back);
//                    setPathState(5011);
//                }
//                break;
//            case 5011:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // Time to release
//                    setoutClawState(1);
//                    //follower.followPath(first_hang_back);
//                    setPathState(501);
//
//
//                }
//                break;
//
//
//            case 501:
//                if (pathTimer.getElapsedTime() > (0.1 * (Math.pow(10, 9)))) { // Time to get out of direciton of specimen
//                    follower.followPath(first_hang_back);
//                    setArmState(0);
//                    setPathState(5);
//
//
//                }
//                break;
//
//
//            case 5:
//                if (!follower.isBusy()) { // waits for stop
//                    follower.followPath(pickup);
//                    setPathState(601);
//                }
//                break;
//            case 601:
//                if (!follower.isBusy()) { // waits for stop
//                    setoutClawState(0);
//                    setPathState(602);
//                }
//                break;
//            case 602:
//                if (pathTimer.getElapsedTime() > (0.25 * (Math.pow(10, 9)))) { // time to grab
//                    setArmState(1);
//                    setPathState(6);
//                }
//                break;
//            case 6:
//                if (pathTimer.getElapsedTime() > (0.1 * (Math.pow(10, 9)))) { // time to raise arm
//                    follower.followPath(second_hang);
//                    setPathState(7);
//                }
//                break;
//            case 7:
//                if (!follower.isBusy()) { // waits for until its at position
//                    setoutClawState(1); // lets go
//                    setPathState(801);
//                }
//                break;
//            case 801:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) {// time to relase
//                    follower.followPath(second_hang_back);
//                    setPathState(802);
//                }
//                break;
//            case 802:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // time to get out
//                    setArmState(0);
//                    setPathState(8);
//                }
//                break;
//            case 8:
//                if (!follower.isBusy()) {
//                    follower.followPath(pickup);
//                    setPathState(901);
//                }
//                break;
//            case 901:
//                if (!follower.isBusy()) {
//                    setoutClawState(0);
//                    setPathState(902);
//                }
//                break;
//            case 902:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // time to close
//                    setArmState(1);
//                    setPathState(9);
//                }
//                break;
//            case 9:
//                if (pathTimer.getElapsedTime() > (0.1 * (Math.pow(10, 9)))) { // time to raise arm
//                    follower.followPath(third_hang);
//                    setPathState(10);
//                }
//                break;
//            case 10:
//                if (!follower.isBusy()) {
//                    setoutClawState(1);
//                    setPathState(90);
//                }
//                break;
//            case 90:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // time to hang
//                    follower.followPath(third_hang_back);
//                    setPathState(111);
//                }
//                break;
//            case 111:
//                if (pathTimer.getElapsedTime() > (0.1 * (Math.pow(10, 9)))) { // time to get out of sub
//                    setArmState(0);
//                    setPathState(11);
//                }
//                break;
//            case 11:
//                if (!follower.isBusy()) { // wait for stop
//                    follower.followPath(pickup);
//                    setPathState(121);
//                }
//                break;
//            case 121:
//                if (!follower.isBusy()) { // wait for stop
//                    setoutClawState(0);
//                    setPathState(12);
//                }
//                break;
//            case 12:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // time to pick up
//                    setArmState(1);
//                    setPathState(131);
//
//
//                }
//                break;
//            case 131:
//                if (pathTimer.getElapsedTime() > (0.1 * (Math.pow(10, 9)))) { // time to get arm up
//                    follower.followPath(fourth_hang);
//                    setArmState(1);
//                    setPathState(13);
//                }
//                break;
//            case 13:
//                if (!follower.isBusy()) {
//                    setoutClawState(1);
//                    setPathState(133);
//                }
//                break;
//            case 133:
//                if (pathTimer.getElapsedTime() > (0.2 * (Math.pow(10, 9)))) { // time to get arm up
//                    follower.followPath(fourth_hang_back);
//                    setPathState(144);
//                }
//                break;
//        }
//    }
//    public void autonomousActionUpdate() {
//        switch (armState) {
//            case -1: // default stop
//                break;
//            case 0: // Zero Pos / pickup pos
//                servo_outtake_flip2.setPosition(1);
//                servo_outtake_flip1.setPosition(0);
//                servo_outtake_rotate.setPosition(0.165);
//                up1.setTargetPosition(1);
//                up2.setTargetPosition(1);
//                up1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                up2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                up1.setPower(1);
//                up2.setPower(1);
//                break;
//            case 1: // Run on position
//                servo_outtake_flip2.setPosition(0.505);
//                servo_outtake_flip1.setPosition(0.495);
//                servo_outtake_rotate.setPosition(0.805);
//
//
//
//
//                up1.setTargetPosition(580);
//                up2.setTargetPosition(580);
//
//
//
//
//                up1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                up2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//
//
//
//                up1.setPower(1);
//                up2.setPower(1);
//
//
//                break;
//            case 2:
//                servo_outtake_flip2.setPosition(0.4);
//                servo_outtake_flip1.setPosition(0.6);
//                servo_outtake_rotate.setPosition(0.825); // calibrate
//                break;
//            case 3:
//                up1.setTargetPosition(150); // calibrate
//                up2.setTargetPosition(150); // calibrate
//                up1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                up2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                up1.setPower(0.5);
//                up2.setPower(0.5);
//
//
//
//
//
//
//        }
//        switch (outclawState) {
//            case -1: // Zero power
//                break;
//            case 0: // closed
//                servo_outtake.setPosition(0.33);
//                break;
//            case 1: // open
//                servo_outtake.setPosition(1);
//                break;
//
//
//
//
//
//
//
//
//        }
//        switch (stoneState) {
//            case 1:
//                out.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                out.setPower(-0.1);
//                break;
//
//
//
//
//        }
//    }
//    public void setPathState(int pState) {
//        pathState = pState;
//        pathTimer.resetTimer();
//    }
//
//
//
//
//    public void setArmState(int aState) {
//        armState = aState;
//    }
//    public void setoutstate(int lState) {
//        armState = lState;
//    }
//    public void setstonestate(int gstate) {
//        outgrabState = gstate;
//    }
//    public void setoutClawState(int cState) {
//        outclawState = cState;
//    }
//
//
//
//
//
//
//
//
//    @Override
//    public void loop() {
//
//
//
//
//        follower.update();
//
//
//
//
//        autonomousPathUpdate();
//        autonomousActionUpdate();
//
//
//        telemetryA.addData("path state", pathState);
//        telemetryA.addData("arm state", armState);
//        telemetryA.addData("pathtimer elapsed time", pathTimer.getElapsedTimeSeconds());
//
//
//
//
//
//
//        // Poses, follower error
//        telemetryA.addData("x", follower.getPose().getX());
//        telemetryA.addData("y", follower.getPose().getY());
//        telemetryA.addData("heading", follower.getPose().getHeading());
//        telemetryA.addData("Follower busy", follower.isBusy());
//        telemetryA.update();
//        // out.setPower(-0.1);
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
//
//
//
//
//    // We init the timers, telemetry, motors , and follower
//    @Override
//    public void init() {
//
//
//
//
//        pathTimer = new NanoTimer();
//        opmodeTimer = new Timer();
//        opmodeTimer.resetTimer();
//
//
//
//
//
//
//        follower = new Follower(hardwareMap,FConstants.class, LConstants.class);
//        follower.setStartingPose(startPose);
//
//
//
//
//
//
//
//
//        out = hardwareMap.get(DcMotorEx.class, "out");
//        out.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        out.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        out.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        out.setDirection(DcMotorSimple.Direction.REVERSE);
//
//
//
//
//        //from rr version
//
//
//        telemetryA.update();
//
//
//        //setup arm to use velocity
//        //setup arm variable
//        up1 = hardwareMap.get(DcMotorEx.class, "up1");
//        up1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        up1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        up1.setDirection(DcMotorSimple.Direction.REVERSE);
//        up1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//
//
//
//
//
//        up2 = hardwareMap.get(DcMotorEx.class, "up2");
//        up2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        up2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        up2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//
//        servo_outtake_flip1 = hardwareMap.get(Servo.class, "ofip1");
//        servo_outtake_flip2 = hardwareMap.get(Servo.class, "ofip2");
//
//
//
//
//        servo_outtake = hardwareMap.get(Servo.class, "outtake");
//        servo_outtake_rotate = hardwareMap.get(Servo.class, "outtaker");
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
//        // Init Positions
//        servo_outtake_rotate.setPosition(0.805);
//        servo_outtake_flip2.setPosition(0.9);
//        servo_outtake_flip1.setPosition(0.1);
//        servo_outtake.setPosition(0.33);
//
//
//
//
//
//
//    }
//    @Override
//    public void start() {
//        buildPaths();
//        opmodeTimer.resetTimer();
//        setPathState(0);
//    }
//    @Override
//    public void stop() {
//    }
//}
//
