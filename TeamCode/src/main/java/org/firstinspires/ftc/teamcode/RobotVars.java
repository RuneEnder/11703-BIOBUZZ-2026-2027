package org.firstinspires.ftc.teamcode;


import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;

public class RobotVars {

    public static Telemetry telemetry;
    public static RobotConstants constants;
    public static AllianceColor allianceColor = AllianceColor.None;
    public static boolean startsInCorner = false;
    public static Pose startPose = constants.startPoseRedWall;


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

    public static void setAllianceColor(AllianceColor allianceColor) {
        RobotVars.allianceColor = allianceColor;
    }


}
