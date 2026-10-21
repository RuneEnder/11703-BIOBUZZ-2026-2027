package org.firstinspires.ftc.teamcode.utils.commands;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadController {

    public GamepadController(Gamepad input1, Gamepad input2){
        gamepad1 = input1;
        gamepad2 = input2;
    }


    private final Gamepad gamepad1;
    private final Gamepad gamepad2;

    public boolean setStartPoseToWall() { return gamepad2.dpad_down; }
    public boolean setStartPoseToCorner() { return gamepad2.dpad_up; }
    public boolean setTeamToBlue() { return gamepad2.b; }
    public boolean setTeamToRed() { return gamepad2.x; }
    public boolean toggleIntakeButton() { return gamepad2.aWasPressed(); }
    public boolean toggleOuttakeButton() { return gamepad2.bWasPressed(); }
    public boolean toggleStopperButton() { return gamepad2.xWasPressed(); }

}
