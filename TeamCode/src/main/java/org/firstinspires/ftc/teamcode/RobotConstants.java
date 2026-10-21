package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

public class RobotConstants {

    public Pose startPoseRedWall = new Pose(0, 8, Math.toRadians(90));
    public Pose startPoseRedCorner = new Pose(0, 0, Math.toRadians(216));
    public Pose startPoseBlueWall = new Pose(0, 8, Math.toRadians(90));
    public Pose startPoseBlueCorner = new Pose(0, 0, Math.toRadians(-36));

    public double intakeMotorSpeed = 0.4;
    public double outtakeMotorSpeed = 1000;
    public double transferMotorSpeed = 0.4;
    public double stopperDownPosition = 0.4;
    public double stopperUpPosition = 0.6;

}
