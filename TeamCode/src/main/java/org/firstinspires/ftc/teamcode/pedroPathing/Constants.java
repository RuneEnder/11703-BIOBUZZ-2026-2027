package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(9.8);
//            .forwardZeroPowerAcceleration(-27.12606038416377)
//            .lateralZeroPowerAcceleration(-51.7361982018032)
//            .translationalPIDFCoefficients(new PIDFCoefficients(0.08, 0, 0.005, 0.02))
//            .headingPIDFCoefficients(new PIDFCoefficients(0.8, 0, 0.03, 0.15))
//            .centripetalScaling(0.0005);


    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 0.8, 1);


    public static ThreeWheelIMUConstants localizerConstants = new ThreeWheelIMUConstants()
//            .forwardTicksToInches(0.0020076437857080853)
//            .strafeTicksToInches(0.002071545144354806)
//            .turnTicksToInches(0.001995336644601396)
//            .leftPodY(6.125)
//            .rightPodY(-6.125)
//            .strafePodX(6.78125)
            .leftEncoder_HardwareMapName("lf")
            .rightEncoder_HardwareMapName("rf")
            .strafeEncoder_HardwareMapName("lr")
            .leftEncoderDirection(Encoder.FORWARD) // Needs to be the reverse of what is in mecanum drive b.c. build team
            .rightEncoderDirection(Encoder.REVERSE)
            .strafeEncoderDirection(Encoder.FORWARD) // Needs to be the reverse of what is in mecanum drive b.c. build team
            .IMU_HardwareMapName("imu")
            .IMU_Orientation(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));


    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rf")
            .rightRearMotorName("rr")
            .leftRearMotorName("lr")
            .leftFrontMotorName("lf")
            .leftFrontMotorDirection(DcMotorEx.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorEx.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorEx.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorEx.Direction.REVERSE);
//            .xVelocity(73.05625642182147)
//            .yVelocity(56.175948717819566);




    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .threeWheelIMULocalizer(localizerConstants)
                .build();
    }


}