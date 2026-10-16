package org.firstinspires.ftc.teamcode;


import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

public class RobotVars {

    public static Telemetry telemetry;
    public RobotConstants constants = new RobotConstants();
    public AllianceColor allianceColor = AllianceColor.None;
    public boolean startsInCorner = false;
    public Pose startPose = constants.startPoseRedWall;


    public void setStartPose() {
        if (allianceColor == AllianceColor.Blue && startsInCorner) startPose = constants.startPoseBlueCorner;
        if (allianceColor == AllianceColor.Blue && !startsInCorner) startPose = constants.startPoseBlueWall;
        if (allianceColor == AllianceColor.Red && startsInCorner) startPose = constants.startPoseRedCorner;
        if (allianceColor == AllianceColor.Red && !startsInCorner) startPose = constants.startPoseRedWall;
    }



    public static boolean isTeleOp = true;

    public static void init_TeleOp(Telemetry telemetry) {
        isTeleOp = true;
        //imu.imuinit();
        RobotVars.telemetry = telemetry;

    }



}
