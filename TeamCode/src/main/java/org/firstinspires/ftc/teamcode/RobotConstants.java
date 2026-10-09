package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

public class RobotConstants {

    private final Pose startPoseRedWall = new Pose(88, 8, Math.toRadians(90));
    private final Pose startPoseRedCorner = new Pose(118.638, 129.298, Math.toRadians(216));
    private final Pose startPoseBlueWall = new Pose(56, 8, Math.toRadians(90));
    private final Pose startPoseBlueCorner = new Pose(25.671, 129.795, Math.toRadians(-36));



    public Pose startPose = startPoseRedWall;

    public void build() {
        //imu orentaion
    }

    public void setStartPose(AllianceColor allianceColor, boolean isCorner) {
        if (allianceColor == AllianceColor.Blue && isCorner) startPose = startPoseBlueCorner;
        if (allianceColor == AllianceColor.Blue && !isCorner) startPose = startPoseBlueWall;
        if (allianceColor == AllianceColor.Red && isCorner) startPose = startPoseRedCorner;
        if (allianceColor == AllianceColor.Red && !isCorner) startPose = startPoseRedWall;
    }

}
