package org.firstinspires.ftc.teamcode.mechaninisms;

import com.bylazar.gamepad.GamepadManager;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotVars;
import org.firstinspires.ftc.teamcode.utils.commands.AllianceColor;
import org.firstinspires.ftc.teamcode.utils.commands.GamepadController;

public class RobotStateHandler {
    private HardwareMap hardwareMap;

    public enum DriveState {
        FIELD_CENTRIC,
        HIVE_CENTRIC
    }

    public void RunMenu(RobotVars robotVars, Drivetrain drivetrain, GamepadController gamepadController) {
        if (gamepadController.setTeamToBlue()) {
            robotVars.allianceColor = AllianceColor.Blue;
            robotVars.setStartPose();

            drivetrain.follower.setStartingPose(robotVars.startPose);
        } else if (gamepadController.setTeamToRed()) {
            robotVars.allianceColor = AllianceColor.Red;
            robotVars.setStartPose();

            drivetrain.follower.setStartingPose(robotVars.startPose);
        }

        if (gamepadController.setStartPoseToWall()) {
            robotVars.startsInCorner = false;
            robotVars.setStartPose();

            drivetrain.follower.setStartingPose(robotVars.startPose);
        } else if (gamepadController.setStartPoseToCorner()) {
            robotVars.startsInCorner = true;
            robotVars.setStartPose();

            drivetrain.follower.setStartingPose(robotVars.startPose);
        }

    }

}
