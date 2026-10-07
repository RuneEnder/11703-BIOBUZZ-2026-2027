package org.firstinspires.ftc.teamcode.pedroPathing;//package org.firstinspires.ftc.teamcode.pedroPathing; // make sure this aligns with class location
//
//import static org.firstinspires.ftc.teamcode.core.constants.*;
//
//import com.arcrobotics.ftclib.controller.PIDController;
//import com.bylazar.telemetry.PanelsTelemetry;
//import com.pedropathing.geometry.Pose;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//
//public class     Shooter {
//
//    private shooterSpeed shooterSpeed;
//    private DcMotorEx left_motor, right_motor;
//
//
//    public PIDController pid;
//
//    public static int target;
//
//    private Servo servo;
//
//    private double max_angle_serv_pos = 0;
//
//    private double min_angle_serv_pos = 0;
//
//    private double power;
//    private boolean is_updating;
//
//    private double shooter_position, shooter_velocity;
//
//    private Telemetry telemetry;
//
//
//
//    public Shooter(HardwareMap hardwareMap, Telemetry telemetry03) {
//        // hi
//        left_motor = hardwareMap.get(DcMotorEx.class, "left shoot");
//        left_motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//        left_motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
//        right_motor = hardwareMap.get(DcMotorEx.class, "right shoot");
//        right_motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
//        right_motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
//        left_motor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        shooterSpeed = new shooterSpeed();
//
//        pid = new PIDController(ep, ei, ed);
//
//        servo = hardwareMap.get(Servo.class, "shoot angle");
//        this.telemetry = telemetry03;
//
//    }
//
//    public void update() { /// PID Update loop
//        if(is_updating) {
//            pid.setPID(ep, ei, ed);
//
//            double pid_output = pid.calculate(right_motor.getVelocity(), target);
//            power = pid_output + f;
//            left_motor.setPower(power);
//            right_motor.setPower(power);
//        } else {
//            left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//            left_motor.setPower(0);
//            right_motor.setPower(0);
//        }
//
//
//    }
//    public void toggle_update_true() {
//        is_updating = true;
//    }
//    public void toggle_update_false() {
//        is_updating = false;
//    }
//
//    public void setTarget(int b) {
//        ///  sets PID target
//        target = b;
//    }
//
//    public void setTarget(int t, boolean usingRPM) {
//        // way to set target where target can be in RPM, just set usingRPM to true
//        if (!usingRPM) {
//            target = t;
//        } else {
//            target = getTPS(t);
//        }
//    }
//
//    ///  Methods for quick PID target values, methods for the four velocities
//    public void perf_velocity() {
//        toggle_update_true();
//
//        setTarget(perf_value);
//    }
//    public void auto_speed() {
//        toggle_update_true();
//
//        setTarget(auto_speed);
//    }
//    public void auto_speed_blue() {
//        toggle_update_true();
//
//        setTarget(auto_speed_blue);
//    }
//    public void closest (){
//        setTarget(closest);
//        toggle_update_true();
//
//    }
//
//    public void middle(){
//        setTarget(middle_of_field);
//        toggle_update_true();
//
//    }
//
//    public void setShooterSpeed(double distance) {
//        toggle_update_true();
//        shooter_velocity = shooterSpeed.calcVelocity(distance);
//        setTarget((int) shooter_velocity);
//    }
//
//    public void setShooterAngle(double distance) {
//        shooter_position = shooterSpeed.calcAngle(distance);
//        servo.setPosition(shooter_position);
//    }
//    public void back_perf() {
//        toggle_update_true();
//        setTarget(far_side_shoot);
//    }
//    public void turnOFF() {
//        toggle_update_false();
//        setTarget(0);
//    }
//    public void shooterON() {
//        toggle_update_true();
//    }
//    public void stress() {
//        toggle_update_true();
//        setTarget(max);
//    }
//
//
//
//
//
//    ///  Servo
//    public void positionMax() {
//        servo.setPosition(positionMax);
//        // Change this number to be the max servo position
//    }
//
//    public void positionMin() {
//        servo.setPosition(positionMin);
//        // change this number to be the minimum servo position
//    }
//
//    public void increasePositionBare() {
//        // increments servo by hundredths
//        servo.setPosition(servo.getPosition() + 0.01);
//    }
//
//    public void increasePosition() {
//        servo.setPosition(servo.getPosition() + 0.1);
//    }
//
//    public void decreasePositionBare() {
//        // increments servo
//        servo.setPosition(servo.getPosition() - 0.01);
//    }
//    public void setPos(double pos) {
//        // increments servo
//        servo.setPosition(pos);
//    }
//
//    public void decreasePosition() {
//        servo.setPosition(servo.getPosition() - 0.1);
//    }
//
//
//    public double getDistance(Pose followerPose, Boolean isRed) {
//        Pose redPose = new Pose(144, 144);
//        Pose bluePose = new Pose(0, 144);
//        double distance;
//
//        if(isRed) {
//            double x_distance = redPose.getX() - followerPose.getX();
//            double y_distance = redPose.getY() - followerPose.getY();
//
//            distance = Math.sqrt((x_distance * x_distance) + (y_distance * y_distance));
//
//        } else {
//            double x_distance = bluePose.getX() - followerPose.getX();
//            double y_distance = bluePose.getY() - followerPose.getY();
//            distance = Math.sqrt((x_distance * x_distance) + (y_distance * y_distance));
//
//        }
//        return distance * 2.54 / 100;
//
//    }
//
//
//
//    ///  old motor
//    public void increaseVelocity() {
//        // increments motor speed by 200 ticks
//        setTarget(target + 50);
//    }
//    public void decreaseVelocity() {
//        // increments motor speed by 200 ticks
//        setTarget(target - 50);
//
//    }
//
//
//
//
//    public double conversion_RPM(double velocity) {
//        ///  This function converts TPS to RPM
//        int ticks_per_rev = 28;
//        int seconds_per_min = 60;
//        return (velocity / ticks_per_rev) * seconds_per_min;
//    }
//
//    public int getTPS(double RPM) {
//        /// This function converts RPM to TPS
//        int TPR = 28;
//        return (int) (RPM * TPR) / 60; /// casts it so it only does integers
//    }
//
//    public void shooter_telemetry() {
//        telemetry.addData("Motor Speed (Ticks)", right_motor.getVelocity());
//        // telemetry.addData("motor rpm", conversion_RPM(right_motor.getVelocity()));
//        // telemetry.addData("left Motor power", left_motor.getPower());
//        // telemetry.addData("right Motor power", right_motor.getPower());
//        telemetry.addData("Shooter Angle position", servo.getPosition());
//        telemetry.addData("Target", target);
//        telemetry.addData("PID Output for power", power);
//
//        telemetry.addData("Shooter Reached", velocityReached());
//        // telemetry.addData("Servo position", servo.getPosition());
//    }
//    public int getTarget() {
//        return target;
//    }
//    public int getVelocity() {
//        return (int) left_motor.getVelocity();
//    }
//
//    public double getAngle() {
//        return servo.getPosition();
//    }
//
//    public boolean velocityReached() {
//        return Math.abs(target - right_motor.getVelocity()) <= 50;
//    }
//}
